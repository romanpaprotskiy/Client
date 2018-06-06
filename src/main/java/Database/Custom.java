package Database;

import java.io.Serializable;
import java.sql.Date;

public class Custom implements Serializable {

    private int id;
    private Customer customer;
    private String type;
    private int CountOfQuestions;
    private Employee employee;
    private Date date_custom, dateOfExecution, ApprovedDirection, ApprovedAccoutant;


    public Custom(int id, Customer customer, String typeCustom, int countOfQuestions, Employee employee,
                  Date date_custom, Date dateOfExecution, Date ApprovedDirection, Date ApprovedAccoutant) {
        this.id = id;
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
        this.employee = employee;
        this.date_custom = date_custom;
        this.dateOfExecution = dateOfExecution;
        this.ApprovedDirection = ApprovedDirection;
        this.ApprovedAccoutant = ApprovedAccoutant;
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions, Employee employee) {
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
        this.employee = employee;
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions) {
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
    }


    @Override
    public String toString() {
        return customer.toString() + " " + type + " " + CountOfQuestions + " "
                + " " + date_custom.toString() + " " + dateOfExecution.toString() + " "
                + ApprovedDirection.toString() + " " + ApprovedAccoutant.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getType() {
        return type;
    }

    public int getCountOfQuestions() {
        return CountOfQuestions;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getId() {
        return id;
    }
}
