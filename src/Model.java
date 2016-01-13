/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author User
 */
public class Model {
    
    DbAccess access;
    ArrayList<Drink> drinks = new ArrayList<Drink>();
    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    ArrayList<RecipePart> recipeParts = new ArrayList<RecipePart>();
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    ArrayList<RecipePart> pickedRecipeParts = new ArrayList<RecipePart>();

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public DbAccess getAccess() {
        return access;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<RecipePart> getRecipeParts() {
        return recipeParts;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public Set<RecipePart> getPartsOfRecipe(String drinkName){
        Recipe wantedRecipe= null;
        for(Recipe r : recipes){
            if(drinkName.contains(r.getDrink().getName())){
                wantedRecipe = r;
                System.out.println("Found " + wantedRecipe.toString());
            }
        }
        return wantedRecipe.getRecipeParts();
        
    }

    public void createNewIngredient(String s){
        Ingredient i = new Ingredient(s);
        ingredients.add(i);
        access.saveNewIngredient(i);
    }
    
    public void deleteIngredient(String s){
        Ingredient ing = null;
        for(Ingredient i : ingredients){
            if(i.getName().equalsIgnoreCase(s)){
                ing = i;
            }
        }
        ingredients.remove(ing);
        access.deleteIngredient(ing);
    }
    
    public void newRecipePart(int amnt, String ingredient){
        RecipePart newPart = new RecipePart();
        newPart.setIngredientAmount(amnt);
        Ingredient ing = null;
        for(Ingredient i : ingredients){
            if(i.getName().equalsIgnoreCase(ingredient)){
                ing = i;
            }
        }
        
        newPart.setIngredient(ing);
        recipeParts.add(newPart);
        pickedRecipeParts.add(newPart);
        access.saveNewRecipePart(newPart);
        
        
        
    }
    
    public Recipe createNewRecipe(String[] recipePartNames, int[] parts){
        Recipe newRecipe = new Recipe();
        for(RecipePart p : pickedRecipeParts){
            System.out.println(p.getIngredient().getName());
            newRecipe.addToRecipe(p);
        }
        recipes.add(newRecipe);
        access.saveNewRecipe(newRecipe);
        
        pickedRecipeParts.clear();
        return newRecipe;
    }
    
    
    public void createNewDrink(String drinkName, double price, String[] recipeParts, int[] parts){
        Drink newDrink = new Drink();
        newDrink.setName(drinkName);
        newDrink.setPrice(price);
        Recipe recipe = createNewRecipe(recipeParts, parts);
        Set<RecipePart> set = recipe.getRecipeParts();
        Iterator<RecipePart> i = set.iterator();
        while(i.hasNext()){
            System.out.println(i.next().getIngredient().getName());
        }
        newDrink.setRecipe(recipe);
        
        
        drinks.add(newDrink);
        
        access.saveNewDrink(newDrink);
        
        
        
        
    }
    
   
    private void deleteRecipePart(Set<RecipePart> list){
        
        Iterator<RecipePart> i = list.iterator();
        while(i.hasNext()){
            RecipePart rp = i.next();
            recipeParts.remove(rp);
            access.deleteRecipePart(rp);
        }
        
        System.out.println("RecipeParts removed");
    }
    
    
    public void deleteDrink(String drinkName){
        Drink drink = null;
        
        for(Drink d : drinks){
            if(drinkName.contains(d.getName())){
                drink = d;
            }
        }
        Recipe drinkRecipe = drink.getRecipe();
        deleteRecipePart(drinkRecipe.getRecipeParts());
        
        access.deleteRecipe(drinkRecipe);
        access.deleteDrink(drink);
        drinks.remove(drink);
        recipes.remove(drinkRecipe);
        
        
    }
    
    
    public Model(){
        access = new DbAccess();
        drinks = access.getDrinks();
        recipes = access.getRecipes();
        recipeParts = access.getRecipeParts();
        ingredients = access.getIngredients();
        
       
            
            }

    void editIngredient(String selectedIngredient, String newIngredientName) {
        
        for(Ingredient i : ingredients){
            if(i.getName().equalsIgnoreCase(selectedIngredient)){
                i.setName(newIngredientName);
                System.out.println(i.getName());
                access.updateIngredient(i.getIngredient_id(), newIngredientName);
            }
        }
        
        
        
    }

   
    
    
    
   
    
    
}
