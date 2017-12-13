import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DeveloperDAO {
    private File file = new File("developers.txt");

    public DeveloperDAO() {

    }

    public void save(Developer developer){
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
    public Developer getById(long id){
        Path input = file.toPath();
        try (Stream<String> lines = Files.lines(input))
        {
            String results[] = lines.filter(line -> line.startsWith(id + ",")).toArray((String[]::new));
            if (results.length != 0){
                String params[] = results[0].split(",");
                return new Developer(Long.valueOf(params[0]),
                        params[1],
                        params[2],
                        params[3],
                        BigDecimal.valueOf(Long.valueOf(params[4])));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Developer> getAll(){
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

    public void remove(Developer dev){
        Path input = file.toPath();
        String newLines[] = null;
        try (Stream<String> lines = Files.lines(input))
        {
            newLines = lines.filter(line -> !line.equals(dev.getId() + ","
                    + dev.getFirstName() + ","
                    + dev.getLastName() + ","
                    + dev.getSpecialty()+ ","
                    + dev.getSalary()))
                    .toArray(String[]::new);


        } catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)))
        {
            for (String line : newLines){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
