/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baari;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.Stoppable;

/**
 *
 * @author User
 */
public class DbAccess {
    
    private static SessionFactory istuntotehdas;
    private Session session;
    private Transaction transaction;
    private boolean alustettu = false;
    
    public ArrayList<Drink> getDrinks(){
        ArrayList<Drink> drinks;
        String hql = "FROM Drink";
        drinks = (ArrayList<Drink>) haeKannasta(hql);
        for(Drink d : drinks){
            System.out.println(d.getName());
        }
        
        return drinks;
        
        
    }
    
    public ArrayList<Recipe> getRecipes() {
        ArrayList<Recipe> recipes;
        String hql = "FROM Recipe";
        recipes = (ArrayList<Recipe>) haeKannasta(hql);
        for(Recipe r : recipes){
            System.out.println("Recipe:"+r.getRecipe_id());
        }
        
        return recipes;
    }
    
    public ArrayList<RecipePart> getRecipeParts() {
        ArrayList<RecipePart> recipeParts;
        String hql = "FROM RecipePart";
        recipeParts = (ArrayList<RecipePart>) haeKannasta(hql);
        
        return recipeParts;
    }
    
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients;
        String hql = "FROM Ingredient";
        ingredients = (ArrayList<Ingredient>) haeKannasta(hql);
        
        return ingredients;
    }
    
    public void saveNewIngredient(Ingredient i){
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        session.saveOrUpdate(i);
        transaction.commit();
        //closeConnection();
    }

    public void deleteIngredient(Ingredient i){
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        session.delete(i);
        transaction.commit();
        
        
       // closeConnection();
    }
    
    
    
    public void saveNewRecipePart(RecipePart r){
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        
        
        session.saveOrUpdate(r);
        transaction.commit();
       // closeConnection();
                
    } 
    
    public void saveNewDrink(Drink d){
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        
        session.saveOrUpdate(d);
        transaction.commit();
        
       
        
    }
    
    public void saveNewRecipe(Recipe r){
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        
        session.saveOrUpdate(r);
        transaction.commit();
       // closeConnection();
    }
    
    public void deleteRecipe(Recipe r){
//        if(session == null){
//            session = alustaSessio();
//        }
//        verifyTransaction();
//        boolean testing = false;
//        System.out.println("Tähän mä vittu tökkäään");
//        while(!testing){
//        try{
//        session.delete(r);
//        testing = true;
//        }
//        catch(org.hibernate.HibernateException e){
//            System.out.println(" Catch a fish");
//            session = istuntotehdas.
//        }
//        }
//        transaction.commit();
        
        session = alustaSessio();
        verifyTransaction();
        Query query = session.createQuery("delete Recipe where recipe_id= :id");
        query.setParameter("id", r.getRecipe_id());
        query.executeUpdate();
        transaction.commit();
    }
    
    
    
    public void deleteDrink(Drink d){
       
        session = alustaSessio();
        verifyTransaction();
        Query query = session.createQuery("delete Drink where drink_id= :id");
        query.setParameter("id", d.getDrink_id());
        query.executeUpdate();
        transaction.commit();
        
        
        
        
    }
    
    
    public void deleteRecipePart(RecipePart rp){
        session = alustaSessio();
        verifyTransaction();
        Query query = session.createQuery("delete RecipePart where recipePart_id= :id");
        query.setParameter("id", rp.getRecipePart_id());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Poistin osan");
        
    }
    
    public DbAccess() {
    }
    
    public void verifyTransaction(){
        if(transaction == null || transaction.wasCommitted()){
            transaction = session.beginTransaction();
        }
    }
    
    public List haeKannasta(String hql){
        if(!alustettu){
            alustaHibernate();
        }
       Session session = alustaSessio();
       Query query = session.createQuery(hql);
       List list = query.list();
    //   closeConnection();
       return list;
       
    }
    
    public void alustaHibernate() {

        Configuration config = new Configuration().configure("Baari/hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

        //StandardServiceRegistryBuilder builder
        //       = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        istuntotehdas = config.buildSessionFactory(serviceRegistry);

        config.setSessionFactoryObserver(
                new SessionFactoryObserver() {
                    @Override
                    public void sessionFactoryCreated(SessionFactory factory) {
                    }

                    @Override
                    public void sessionFactoryClosed(SessionFactory factory) {
                        ((StandardServiceRegistryImpl) serviceRegistry).destroy();
                    }
                });
        
       alustettu = true;
        
    }    
    
    public Session alustaSessio(){
        return istuntotehdas.openSession();
    }
    
        public void resetKanta(){
         alustaHibernate();
         session = alustaSessio();   
            
         Transaction transaktio = session.beginTransaction();

        Ingredient vodka = new Ingredient("Vodka");
        Ingredient rhum = new Ingredient("Rhum");
        Ingredient cola = new Ingredient("Cola");
        Ingredient sprite = new Ingredient("Sprite");
        Drink vodkaSprite = new Drink("VodkaSprite", 5.5);
        Drink rhumCoke = new Drink("RhumCoke", 6.0);
        
        
        RecipePart sixPartsOfCoke = new RecipePart(cola, 6);
        RecipePart fourPartsOfVodka = new RecipePart(vodka, 4);
        RecipePart fourPartsOfRhum = new RecipePart(rhum, 4);
        RecipePart sixPartsOfSprite = new RecipePart(sprite, 6);
        
        Recipe vodkaSpriteRecipe = new Recipe();
        Recipe rhumCokeRecipe = new Recipe();
        
        vodkaSpriteRecipe.addToRecipe(sixPartsOfSprite);
        vodkaSpriteRecipe.addToRecipe(fourPartsOfVodka);
        rhumCokeRecipe.addToRecipe(fourPartsOfRhum);
        rhumCokeRecipe.addToRecipe(sixPartsOfCoke);
         
        vodkaSprite.setRecipe(vodkaSpriteRecipe);
        rhumCoke.setRecipe(rhumCokeRecipe);
        
        session.saveOrUpdate(cola);
        session.saveOrUpdate(rhum);
        session.saveOrUpdate(vodka);
        session.saveOrUpdate(sprite);
        session.saveOrUpdate(sixPartsOfSprite);
        session.saveOrUpdate(sixPartsOfCoke);
        session.saveOrUpdate(fourPartsOfVodka);
        session.saveOrUpdate(fourPartsOfRhum);
        
        session.saveOrUpdate(vodkaSprite);
        session.saveOrUpdate(rhumCoke);
        session.saveOrUpdate(vodkaSpriteRecipe);
        session.saveOrUpdate(rhumCokeRecipe);
        
        transaktio.commit();
        
        closeConnection();
        
        
                
        
    
    
    }
    
    
    
        
    public void closeConnection(){
       
        SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) istuntotehdas;
        ConnectionProvider connectionProvider = sessionFactoryImplementor.getConnectionProvider();
        if (Stoppable.class.isInstance(connectionProvider)) {
            ((Stoppable) connectionProvider).stop();
        }
    }

    void updateIngredient(int id, String newName) {
        if(session == null){
            session = alustaSessio();
        }
        verifyTransaction();
        Query query = session.createQuery("update Ingredient set name = :newName "
                + "where ingredient_id = :id");
        query.setParameter("newName", newName);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }
    
}
