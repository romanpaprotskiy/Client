package Database;

import javafx.beans.property.*;

import java.io.Serializable;

public class Customer implements Serializable {

    private IntegerProperty id;
    private StringProperty person_customer,person_representative,Address;

    public Customer(){
        this.id = new SimpleIntegerProperty();
        this.person_customer = new SimpleStringProperty();
        this.person_representative = new SimpleStringProperty();
        this.Address = new SimpleStringProperty();
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

    public String getAddress() {
        return Address.get();
    }

    public StringProperty addressProperty() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }
}