package edu.fruitmarket.web.service;

import edu.fruitmarket.web.beans.Fruit;
import edu.fruitmarket.web.dao.FruitDao;

import java.util.ArrayList;

public class FruitService {
    private FruitDao dao=new FruitDao();

    public ArrayList<Fruit> queryAllFruit(){
        ArrayList<Fruit> fruits=dao.queryAllFruit();
        return fruits;
    }

    public ArrayList<Fruit> queryFruitByCond(Fruit fruit){
        ArrayList<Fruit> fruits=dao.queryFruitByCond(fruit);
        return fruits;
    }
    public ArrayList<Fruit> sortFruits(String sort)
    {
        ArrayList<Fruit> fruits=dao.sortFruits(sort);
        return fruits;
    }

    public boolean addFruit(Fruit fruit){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(fruit.getNumber().equals(data.getNumber()))
                return false;
        }
        dao.addFruit(fruit);
        return true;
    }

    public boolean updateFruit(Fruit fruit){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(fruit.getNumber().equals(data.getNumber())){
                dao.updateFruit(fruit);
                return true;
            }
        }
        return false;
    }

    public boolean delFruit(String delNumber){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(delNumber.equals(data.getNumber())){
                dao.delFruit(delNumber);
                return true;
            }
        }
        return false;
    }
}
