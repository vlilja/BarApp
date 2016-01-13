/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;

/**
 *
 * @author User
 */
public class Main {
    
    static BarView view;
    static Controller ctrl;
    static Model model;
    
    public static void main(String[] args) throws InterruptedException{
        model = new Model();
        view = new BarView();
        ctrl = new Controller(model, view);
        ctrl.updateDrinkList();
        ctrl.updateIngredientList();
//        model.access.resetKanta();
       // Thread.sleep(5000);
       // model.access.luoUusi(model.ingredients.get(0), model.ingredients.get(1));
        
        
    }
    
    
}
