package Database;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id;
    private String person_customer,person_representative,address;

    public Customer(int id,String person_customer,String person_representative,String address) {
        this.id = id;
        this.person_customer = person_customer;
        this.person_representative = person_representative;
        this.address = address;
    }

    public Customer(String person_customer,String person_representative,String address){
        this.person_customer = person_customer;
        this.person_representative = person_representative;
        this.address = address;
    }


    @Override
    public String toString(){
        return person_customer + " " + person_representative;
    }
}