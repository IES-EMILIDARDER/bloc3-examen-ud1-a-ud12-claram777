package examen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {

        /*
        Llegeix l'arxiu  'c:\temp\vehicles.csv' i calcula:
            Nombre total de vehicles amb preu superior a 15000 EUR
            Preu mitjà de tots els vehicles del fitxer
            Vehicle més car i més barat d'entre tots els vehicles del fitxer
        */
        
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {
            List<Vehicle> v1 = 
                    linies.filter(linia -> !linia.startsWith("#") && !linia.isBlank())
                        .map(linia -> linia.split(","))
                        .filter(parts -> Double.valueOf(parts[4].trim()) > 15000)
                        .map(parts -> new Vehicle (
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                Integer.valueOf(parts[3].trim()),
                                Double.valueOf(parts[4].trim())
                        ))
                        .collect(Collectors.toList());
            System.out.println("Nombre total de vehicles amb preu superior a 15000 EUR:");
            long total = v1.stream()
                    .count();
            
            System.out.println(total);
            
            System.out.println("Preu mitjà de tots els vehicles del fitxer:");
            double avg = v1.stream()
                    .mapToDouble(n -> n.getPreu())
                    .average()
                    .orElse(0);
            
            System.out.println(avg);
            
            System.out.println("Vehicle més car i més barat d'entre tots els vehicles del fitxer:");
            
            double min = v1.stream()
                    .mapToDouble(n -> n.getPreu())
                    .min()
                    .orElse(0);
                    
            System.out.println("Minimo: " + min);
                    
            double max = v1.stream()
                    .mapToDouble(n -> n.getPreu())
                    .max()
                    .orElse(0);
                    
            System.out.println("Maximo: " + max);
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}

