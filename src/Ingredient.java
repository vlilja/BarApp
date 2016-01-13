/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name="Ingredient")
public class Ingredient {
    
    int ingredient_id;
    String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
       
        this.name = name;
    }

    
    
    @Id
    @GeneratedValue
    @Column(name="Ingredient_id")
    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    @Column(name="Ingredient_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
