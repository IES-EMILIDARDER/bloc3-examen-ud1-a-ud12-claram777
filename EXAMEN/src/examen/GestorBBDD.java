package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GestorBBDD {
    private final String MYSQL_CON;

    public GestorBBDD(String MYSQL_CON) {
        this.MYSQL_CON = MYSQL_CON;
    }    
    
    public Connection getConnectionFromFile() throws SQLException, IOException {
        Map<String, String> valorsConnexio = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.MYSQL_CON))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                try {
                    if (!linia.substring(0, 1).equals("#")) {
                        String[] parts = linia.split("=");
                        String clau = parts[0].trim();
                        String valor = parts[1].trim();
                    
                        switch (clau) {
                            case "SERVER", "DBASE", "USER", "PASSWD" -> valorsConnexio.put(clau, valor);
                            default -> System.err.println("Clau no valida en arxiu de connexio: " + clau);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    // Cas que l'split no funcioni
                    // No fer res
                }
            }
            if (valorsConnexio.size() != 4)
                throw new SQLException("L'arxiu no contemple totes les dades de connexi�");
        } catch (IOException e) {
            System.err.println("Error llegint l'arxiu: " + e.getMessage());
            throw e;  // Es propaga l'excepci� al m�tode anterior
        }

        // Estableix la connexi� a la BD Mysql
        return DriverManager.getConnection( valorsConnexio.get("SERVER") + valorsConnexio.get("DBASE"), 
                                            valorsConnexio.get("USER"), 
                                            valorsConnexio.get("PASSWD"));
    }
    
    public ResultSet executaQuerySQL(Connection conn, String sql, Object... arguments) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            assignaArguments(stmt, arguments);
            
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw e;  // Es propaga l'excepci� al m�tode anterior
        }
    }
    
    public void executaSQL(Connection conn, String sql, Object... arguments) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            assignaArguments(stmt, arguments);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;  // Es propaga l'excepci� al m�tode anterior
        }
    }
    
    private void assignaArguments(PreparedStatement stmt, Object... arguments) throws SQLException {
        for (int i = 0; i < arguments.length; i++) {
            Object arg = arguments[i];

            if (arg == null) {
                stmt.setObject(i + 1, null);
            } else if (arg instanceof Integer) {
                stmt.setInt(i + 1, (Integer) arg);
            } else if (arg instanceof Long) {
                stmt.setLong(i + 1, (Long) arg);
            } else if (arg instanceof Double) {
                stmt.setDouble(i + 1, (Double) arg);
            } else if (arg instanceof Float) {
                stmt.setFloat(i + 1, (Float) arg);
            } else if (arg instanceof Boolean) {
                stmt.setBoolean(i + 1, (Boolean) arg);
            } else if (arg instanceof LocalDate) {
                stmt.setDate(i + 1, Date.valueOf((LocalDate) arg));
            } else if (arg instanceof java.sql.Date) {
                stmt.setDate(i + 1, (java.sql.Date) arg);
            } else if (arg instanceof Timestamp) {
                stmt.setTimestamp(i + 1, (Timestamp) arg);
            } else {
                stmt.setObject(i + 1, arg);
            }
        }
    }
    
}
