package Database;

import javafx.beans.property.*;

import java.io.Serializable;

public class Customer implements Serializable {

    private IntegerProperty id;
    private StringProperty person_customer,person_representative,address;

    public Customer(){
        this.id = new SimpleIntegerProperty();
        this.person_customer = new SimpleStringProperty();
        this.person_representative = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
    }

    public Customer(int id,String person_customer,String person_representative,String address) {
        this.id = new SimpleIntegerProperty(id);
        this.person_customer = new SimpleStringProperty(person_customer);
        this.person_representative = new SimpleStringProperty(person_representative);
        this.address = new SimpleStringProperty(address);
    }

    public Customer(String person_customer,String person_representative,String address){
        this.person_customer = new SimpleStringProperty(person_customer);
        this.person_representative = new SimpleStringProperty(person_representative);
        this.address = new SimpleStringProperty(address);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPerson_customer() {
        return person_customer.get();
    }

    public StringProperty person_customerProperty() {
        return person_customer;
    }

    public void setPerson_customer(String person_customer) {
        this.person_customer.set(person_customer);
    }

    public String getPerson_representative() {
        return person_representative.get();
    }

    public StringProperty person_representativeProperty() {
        return person_representative;
    }

    public void setPerson_representative(String person_representative) {
        this.person_representative.set(person_representative);
    }
}