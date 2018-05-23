package Database;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id;
    private String person_customer,person_representative;

    public Customer(int id,String person_customer,String person_representative) {
        this.id = id;
        this.person_customer = person_customer;
        this.person_representative = person_representative;
    }

    public Customer(String person_customer,String person_representative){
        this.person_customer = person_customer;
        this.person_representative = person_representative;
    }


    @Override
    public String toString(){
        return person_customer + " " + person_representative;
    }
}