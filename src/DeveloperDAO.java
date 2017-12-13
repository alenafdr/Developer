import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DeveloperDAO {
    private static File file = new File("developers.txt");

    public DeveloperDAO() {

    }

    public static void save(Developer developer){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(developer.getId() + ","
                    + developer.getFirstName() + ","
                    + developer.getLastName() + ","
                    + developer.getSpecialty() + ","
                    + developer.getSalary() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Developer getById(long id){
        List<Developer> list = getAll();
        for(Developer dev : list){
            if(dev.getId() == id){
                return dev;
            }
        }
        return null;
    }

    public static List<Developer> getAll(){
        List<Developer> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr))
        {
            String line;
            while((line = br.readLine()) != null){
                String params[] = line.split(",");
                Developer developer = new Developer(Long.valueOf(params[0]),
                        params[1],
                        params[2],
                        params[3],
                        BigDecimal.valueOf(Long.valueOf(params[4])));
                result.add(developer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void remove(Developer dev){

        Path input = file.toPath();
        Path temp = null;
        try {
            temp = Files.createTempFile("temp", ".txt");
        } catch (IOException e) {
            System.out.println("createTempFile " + e.toString());
        }

        try (Stream<String> lines = Files.lines(input);
              BufferedWriter writer = Files.newBufferedWriter(temp))
        {
            lines.filter(line -> !line.equals(dev.getId() + ","
                    + dev.getFirstName() + ","
                    + dev.getLastName() + ","
                    + dev.getSpecialty()+ ","
                    + dev.getSalary()))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println(e.toString());
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            Files.move(temp, input, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
