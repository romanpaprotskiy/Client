package Database;

import java.io.Serializable;

import Exceptions.*;

public enum TypeCustom implements Serializable {
    UNWRITTEN("усне"), WRITTEN("письмове"), ECONOMICALLY("економічно-правове"), PROVIDE("забезпечення"), SEMINAR("семінар"), AUDYT("аудит");

    private String type;

    TypeCustom(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String push(String type) {
        this.type = type;
        return this.type;
    }


    @Override
    public String toString() {
        return type;
    }
}