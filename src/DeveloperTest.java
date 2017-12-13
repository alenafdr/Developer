import java.math.BigDecimal;
import java.util.List;

public class DeveloperTest {
    public static void main(String[] args) {
        DeveloperDAO devDAO = new DeveloperDAO();
        devDAO.save(new Developer(1L,
                "Сергей",
                "Иванов",
                "программист",
                new BigDecimal(1000)));
        devDAO.save(new Developer(2L,
                "Иван",
                "Петров",
                "программист",
                new BigDecimal(2000)));
        devDAO.save(new Developer(3L,
                "Петр",
                "Сергеев",
                "программист",
                new BigDecimal(3000)));
        for (Developer dev : devDAO.getAll()){
            System.out.println(dev);
        }

        System.out.println();

        devDAO.remove(new Developer(2L,
                "Иван",
                "Петров",
                "программист",
                new BigDecimal(2000)));

        for (Developer dev : devDAO.getAll()){
            System.out.println(dev);
        }

        System.out.println();
        System.out.println(devDAO.getById(1L));
    }
}
