import java.math.BigDecimal;
import java.util.List;

public class DeveloperTest {
    public static void main(String[] args) {
        DeveloperDAO.save(new Developer(1L,
                "Сергей",
                "Иванов",
                "программист",
                new BigDecimal(1000)));
        DeveloperDAO.save(new Developer(2L,
                "Иван",
                "Петров",
                "программист",
                new BigDecimal(2000)));
        DeveloperDAO.save(new Developer(3L,
                "Петр",
                "Сергеев",
                "программист",
                new BigDecimal(3000)));
        for (Developer dev : DeveloperDAO.getAll()){
            System.out.println(dev);
        }

        System.out.println();

        DeveloperDAO.remove(new Developer(2L,
                "Иван",
                "Петров",
                "программист",
                new BigDecimal(2000)));

        for (Developer dev : DeveloperDAO.getAll()){
            System.out.println(dev);
        }

        System.out.println();
        System.out.println(DeveloperDAO.getById(1L));
    }
}
