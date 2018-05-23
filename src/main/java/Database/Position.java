package Database;

import java.io.Serializable;

public class Position implements Serializable {

    private int ID;
    private String name;
    private Department department;

    public Position(String name){
        this.name = name;
    }

    public Position(int ID, String name, Department department){
        this.ID = ID;
        this.name = name;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }
}
