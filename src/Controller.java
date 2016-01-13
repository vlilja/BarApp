/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

import java.util.ArrayList;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Controller {
    
    Model model;
    BarView view;
    
    public Controller(Model model, BarView view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
        
    }
    
   
    public void updateDrinkList(){
        view.setDrinks(model.getDrinks());
    }
    
    public void updateIngredientList(){
        view.setIngredients(model.getIngredients());
    }
    
    public void updateDrinkRecipeList(String drinkListValue){
        Set<RecipePart> set = model.getPartsOfRecipe(drinkListValue);
        view.setDrinkRecipe(set);
        
    }
    
    public void createNewIngredient(){
        String s = view.getNewIngredientText();
        model.createNewIngredient(s);
        view.updateIngredients();
        
    }
    
    public void deleteIngredient(String s){
        model.deleteIngredient(s);
        view.updateIngredients();
    }
    
    public void pickNewIngredient(int i, String s){
        model.newRecipePart(i, s);
        String pickedIngredient = i + " Parts of " + s;
        view.setPickedIngredient(pickedIngredient);
        
        
    }

    private int[] getParts(String[] array){
        ArrayList list = new ArrayList();
        for(String s : array){
           String[] splitted = s.split(" ");
           list.add(splitted[0]);
        }
        int[] parts = new int[list.size()];
        for(int i = 0; i < parts.length; i++){
            parts[i] = Integer.parseInt((String)list.get(i));
        }
        return parts;
    }
    
    private String[] getIngredientsOfParts(String[] array){
        ArrayList list = new ArrayList();
        for(String s : array){
           String[] splitted = s.split(" ");
           list.add(splitted[3]);
        }
        String[] parts = new String[list.size()];
        for(int i = 0; i < parts.length; i++){
            parts[i] = (String)list.get(i);
        }
        return parts;
    }
    
    public void createNewDrink() {
        if(view.getNewDrinkPrice() != 0 && view.getNewDrinkPrice()>0){
            String name = view.getNewDrinkName();
            double price = view.getNewDrinkPrice();
            String[] pickedParts = view.getPickedIngredients();
            
            
         model.createNewDrink(name, price, getIngredientsOfParts(pickedParts), getParts(pickedParts));
         view.clearCreationScreen();
            
        }
        else {
            view.clearNewDrinkPrice();
            JOptionPane.showMessageDialog(view, new String("Input price in num.num format.\n Example: 3.0"));
        }
    }
    
    
    
   public void deleteDrink(String drinkName){
       model.deleteDrink(drinkName);
      view.setDrinkRecipe(null);
       this.updateDrinkList();
   }

    public void editIngredient(String selectedIngredient, String newIngredientName) {
        model.editIngredient(selectedIngredient, newIngredientName);
        this.updateIngredientList();
        
    }
  
}
