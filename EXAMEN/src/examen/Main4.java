package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Main4 {

    final String MYSQL_CON = "c:\\temp\\mysql.con";
    GestorBBDD gestorBSD = new GestorBBDD(MYSQL_CON);
    Set<Vehicle> v1 = new HashSet<>();
    String ruta = "c:\\temp\\vehicles.csv";
    
    public static void main(String[] args) {
        /*
        Selecciona de la taula 'vehicles' aquells 
        que siguin de l'any 2020 o superior en la 
        següent estructura de memòria "List<Vehicle> vehicles" i mostra-la per pantalla.
            NOTA: cal fer una SELECT amb paràmetres, emprant "
                    Prepared Statements" o "Instruccions concatenades" en previsió de l'existència d'un paràmetre "any >= ?"
        */
        
        
    }
    
    public void leerVehiculos (String ruta) {
        LeerVehiculos(v1);
        LeerVehiculos(v1, ruta);
        
        System.out.println("\n" + v1.toString());
    }
    
    private void LeerVehiculos (Set<Vehicle> v1) {
        String sql = "SELECT matricula, marca, model, any, preu FROM Vehicle where any >= ?";
        
        try (   Connection conn = gestorBSD.getConnectionFromFile();
                ResultSet resultSet = gestorBSD.executaQuerySQL(conn, sql, 2020) ) {
            
                    while (resultSet.next()) {
                        agregarVehiculos(v1, new Vehicle (
                                
                                resultSet.getString("matricula"),
                                resultSet.getString("marca"),
                                resultSet.getString("model"),
                                resultSet.getInt("any"),
                                resultSet.getDouble("preu")
                                
                        ));
                    }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    private void LeerVehiculos (Set<Vehicle> v1, String ruta) {
        
        try (BufferedReader leer = new BufferedReader(new FileReader(ruta))) {
            
            String linea;
            
            while ((linea = leer.readLine()) != null) {
                if (!(linea.isEmpty() || linea.startsWith("#"))) {
                    String parts[] = linea.split(",");
                    if (parts.length == 5) {
                        agregarVehiculos (v1, new Vehicle(
                                
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                Integer.valueOf(parts[3].trim()),
                                Double.valueOf(parts[3].trim())
                                
                        ));
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    private void agregarVehiculos (Set<Vehicle> v1, Vehicle vehicle) {
        v1.add(vehicle);
    }
    
}