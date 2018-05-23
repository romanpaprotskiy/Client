package Database;

import java.io.Serializable;
import java.sql.Date;

public class Custom implements Serializable{

    private int id;
    private Customer customer;
    private String type;
    private int CountOfQuestions;
    private Questions questions;
    private Executor executor;
    private Date date_custom,dateOfExecution,ApprovedDirection,ApprovedAccoutant;


    public Custom(int id, Customer customer, String typeCustom, int countOfQuestions, Questions questions, Executor executor,
                  Date date_custom, Date dateOfExecution, Date ApprovedDirection, Date ApprovedAccoutant)
    {
        this.id = id;
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
        this.questions = questions;
        this.executor = executor;
        this.date_custom = date_custom;
        this.dateOfExecution = dateOfExecution;
        this.ApprovedDirection = ApprovedDirection;
        this.ApprovedAccoutant = ApprovedAccoutant;
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions, Questions questions, Executor executor){
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
        this.questions = questions;
        this.executor = executor;
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions, Questions questions){
        this.customer = customer;
        this.type = typeCustom;
        this.CountOfQuestions = countOfQuestions;
        this.questions = questions;
    }


    @Override
    public String toString(){
        return customer.toString() + " " + type + " " + CountOfQuestions + " " + questions.toString() + " "
                + " " + date_custom.toString() + " " + dateOfExecution.toString() + " "
                + ApprovedDirection.toString() + " " + ApprovedAccoutant.toString();
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getType(){
        return type;
    }

    public int getCountOfQuestions(){
        return CountOfQuestions;
    }

    public Questions getQuestions(){
        return questions;
    }

    public Executor getExecutor(){
        return executor;
    }

    public int getId() {
        return id;
    }
}
