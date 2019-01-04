package Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "EmployeeId",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",length = 45)
    private String name;

    @Column(name = "salary")
    private double salary;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private List<Client> clients;

    @Override
    public String toString(){
        return id + " - " + name + " - " + salary + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
