package Database;

import java.io.*;

public class Department implements Serializable {

    private int ID;
    private String name;

    public Department(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public int getID() {
        return ID;
    }
}
