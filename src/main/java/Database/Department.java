package Database;

import javafx.beans.property.*;

import java.io.Serializable;

public class Department implements Serializable {

    private IntegerProperty ID;
    private StringProperty name;

    public Department(){
        this.ID = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    public Department(int id,String name){
        this.ID = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
