/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Logane
 */
public class Option {
    public final String NAME = "name";
    public final String ID = "id";
    
    private int id;
    private String name;

    public Option(){};
    
    public Option(String name, int id){
        this.name = name;
        this.id = id;
    }
    
    public int getId() {return id;}

    public String getName() {return name;}
    
    public Option setId(int id) {
        this.id = id;
        return this;
    }

    public Option setName(String name) {
        return this;
    }

}
