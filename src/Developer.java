import java.math.BigDecimal;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private BigDecimal salary;

    public Developer(Long id, String firstName, String lastName, String specialty, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
       String str = this.id + "," + this.getFirstName() + "," + this.getLastName() + "," + this.getSpecialty() + "," + this.getSalary();
        return str;
    }
}
