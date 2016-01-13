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
@Table(name = "Drink")
public class Drink {
    
    int drink_id;
    String name;
    Recipe recipe;
    double price;

    public Drink() {
    }

    
    
    public Drink(String name, double price) {
        
        this.name = name;
        this.price = price;
    }

    
    
    @Id
    @GeneratedValue
    @Column(name="Drink_id")
    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    @Column(name="Drink_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToOne(mappedBy = "drink")
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.setDrink(this);
    }

    @Column(name="Drink_price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
