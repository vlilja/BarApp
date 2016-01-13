/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name="RecipePart")
public class RecipePart {
    
    private int recipePart_id;
    private Recipe recipe;
    private Ingredient ingredient;
    private int ingredientAmount;

    public RecipePart() {
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    
    
    
    public RecipePart(Ingredient ingredient, int ingredientAmount) {
        
        this.ingredient = ingredient;
        this.ingredientAmount = ingredientAmount;
    }

    
    
    @Id
    @GeneratedValue
    public int getRecipePart_id() {
        return recipePart_id;
    }

    public void setRecipePart_id(int recipeLine_id) {
        this.recipePart_id = recipeLine_id;
    }

    @ManyToOne(cascade={CascadeType.REMOVE})
    @JoinColumn(name="Recipe_id")
    public Recipe getRecipe() {
        return recipe;
    }
    
    

    
    
    @ManyToOne
    @JoinColumn(name="Ingredient")
    public Ingredient getIngredient() {
        return ingredient;
    }

    

    

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Column(name="Amount")
    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }
    
    
    
}
