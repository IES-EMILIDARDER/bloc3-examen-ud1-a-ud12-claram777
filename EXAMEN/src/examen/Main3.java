package examen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main3 {

    public static void main(String[] args) {
        /*
        Implementa la funci� lambda "incrementaPreu". 
        Empra-la en la llista 'vehicles' anterior per 
        tal de tornar la mateixa llista amb el preu de cada vehicle amb un 10% m�s al preu. 

            NOTA: aquesta funci� es pot implementar de 
            2 maneres: cal triar entre les seg�ents una d'elles
            (2 punts) "Function<Vehicle, Vehicle> incrementaPreu1": 
            suposant que l'import a incrementar est� fixat a 10% dins la pr�pia funci�.
            (3 punts) "BiFunction<Vehicle, Double, Vehicle> incrementaPreu2":
            amb una BiFunci�, es pot emprar un segon par�metre que representa el factor d'increment (o decrement en cas negatiu) que cal aplicar.
        */
        
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {
            List<Vehicle> v1 = 
                    linies.filter(linia -> !linia.startsWith("#") && !linia.isBlank())
                        .map(linia -> linia.split(","))
                        .map(parts -> new Vehicle (
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                Integer.valueOf(parts[3].trim()),
                                Double.valueOf(parts[4].trim())
                        ))
                        .collect(Collectors.toList());
//            v1.stream()
                    //.incrementaPreu(n -> n.getPreu*1.1);
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}

