package examen;

import java.util.Arrays;
import java.util.List;
import java.sql.*;

public class Main5 {
    
    final String MYSQL_CON = "c:\\temp\\mysql.con";
    GestorBBDD gestorBSD = new GestorBBDD(MYSQL_CON);
    
    public static void main(String[] args) {

        /*
 Inserta (o modifica) la següent llista en la taula de 'vehicles' en la taula 'vehicles'.
NOTA: no cal crear la taula.
        */
        
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("4321-JKL", "Ford",     "Focus",   2019, 17000),
            new Vehicle("8765-MNO", "Hyundai",  "Ioniq 5", 2023, 42000),
            new Vehicle("2109-PQR", "Peugeot",  "308",     2016, 14000)
        );
        
        
        
        
    }
    
    private void insertUpdateVehicles (List<Vehicle> vehicles) {
        
        try (   Connection conn = gestorBSD.getConnectionFromFile() ) {
                conn.setAutoCommit(true);
                
                for (Vehicle v1 : vehicles) {
                    try {
                        
                        gestorBSD.executaSQL(conn, "INSERT INTO vehicles (matricula, marca, model, any, preu) VALUES (?, ?, ?, ?, ?)" , v1.getMatricula(), v1.getMarca(), v1.getModel(), v1.getAny(), v1.getPreu());
                        
                    } catch (SQLException e) {
                        if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                            
                            gestorBSD.executaSQL(conn, "UPDATE vehicles SET marca = ?, model = ?, any = ?, preu = ? WHERE matricula = ?" , v1.getMarca(), v1.getModel(), v1.getAny(), v1.getPreu(), v1.getMatricula());
                            
                        } else {
                            throw e;
                        }
                    }
                }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    
}