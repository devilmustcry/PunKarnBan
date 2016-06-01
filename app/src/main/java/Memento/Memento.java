package Memento;

import java.io.Serializable;

/**
 * Created by FTTX on 6/1/2016 AD.
 */
public abstract class Memento implements Serializable {

    private String name;

    public Memento(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
