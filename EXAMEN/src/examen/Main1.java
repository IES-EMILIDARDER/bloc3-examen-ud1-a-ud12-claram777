package examen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Main1 {
    
    public static void main(String[] args) {
        /*
        De la llista de vehicles anterior, 
        tria els posteriors a l'any 2014 i 
        obté una llista ordenada (de major a menor) de les diferents 'Marca' 
        RESULTAT: 
        [Seat, Tesla, Volkswagen]
        */
        
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {
            List<Vehicle> v1 = 
                    linies.filter(linia -> !linia.startsWith("#") && !linia.isBlank())
                        .map(linia -> linia.split(","))
                        .filter(parts -> Integer.valueOf(parts[3].trim()) >= 2014)
                        .distinct()
                        .sorted((x, y) -> x[1].compareTo(y[1]))
                        .map(parts -> new Vehicle (
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                Integer.valueOf(parts[3].trim()),
                                Double.valueOf(parts[4].trim())
                        ))
                        .collect(Collectors.toList());
            
            v1.forEach(System.out::println);       
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}

