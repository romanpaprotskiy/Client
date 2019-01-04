package Data;


import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @Column(name = "ClientId",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 1;

    @Column
    private String name;

    @Column
    private double value;

    @ManyToOne
    @JoinColumn(name = "Employee",nullable = false)
    private Employee employee;

    @Override
    public String toString(){
        return id + " - " + name + " - " + value + "\n";
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Employee getEmployee() {
        return employee;
    }
}
