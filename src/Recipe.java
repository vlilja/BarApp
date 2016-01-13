/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Recipe")
public class Recipe {

    int recipe_id;
    Drink drink;
    Set<RecipePart> recipeParts = new HashSet<RecipePart>();

    public Recipe() {

    }

    @Id
    @GeneratedValue
    @Column(name = "Recipe_id")
    public int getRecipe_id() {
        return this.recipe_id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Drink_Id")
    public Drink getDrink() {
        return this.drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    @OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
    public Set<RecipePart> getRecipeParts() {
        return recipeParts;
    }

    public void setRecipeParts(Set<RecipePart> recipeParts) {
        this.recipeParts = recipeParts;
    }

    
    
    
    public void addToRecipe(RecipePart rp){
        this.recipeParts.add(rp);
        rp.setRecipe(this);
    }
    

   

}
