/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enry.snackbar;

import enry.snackbar.controller.*;
import enry.snackbar.model.*;

/**
 *
 * @author enry_
 */
public class InsertApp {

    public static void main(String[] args) {
        IngredientController ingredientController = null;
        FoodController foodController;

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Alface");
        ingredient.setPrice(0.40f);
        ingredient.setIsMeat(false);
        ingredient.setIsCheese(false);
        ingredientController.createIngredient(ingredient);
        
    }
}
