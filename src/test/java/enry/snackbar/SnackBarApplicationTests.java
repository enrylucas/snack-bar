package enry.snackbar;

import enry.snackbar.controller.IngredientController;
import enry.snackbar.controller.IngredientWrapper;
import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnackBarApplication.class)
public class SnackBarApplicationTests {
    
    @Autowired
    private IngredientRepository ingredientRepository;
    
    @Autowired
    private IngredientController ingredientController;

    @Test
    public void preBuildedFoodsTest() throws Exception {
        List<Integer> qts2 = new ArrayList<>();
        qts2.add(1);
        qts2.add(1);
        
        List<Integer> qts3 = new ArrayList<>();
        qts3.add(1);
        qts3.add(1);
        qts3.add(1);
        
        List<Integer> qts4 = new ArrayList<>();
        qts4.add(1);
        qts4.add(1);
        qts4.add(1);
        qts4.add(1);
        
        
        IngredientWrapper wrapperXBurger = new IngredientWrapper();
        List<Integer> ingsXBurger = new ArrayList<>();
        ingsXBurger.add(3);
        ingsXBurger.add(5);
        wrapperXBurger.setIngredientsId(ingsXBurger);
        wrapperXBurger.setIngredientsQty(qts2);
        
        
        IngredientWrapper wrapperXBacon = new IngredientWrapper();
        List<Integer> ingsXBacon = new ArrayList<>();
        ingsXBacon.add(2);
        ingsXBacon.add(3);
        ingsXBacon.add(5);
        wrapperXBacon.setIngredientsId(ingsXBacon);
        wrapperXBacon.setIngredientsQty(qts3);
        
        IngredientWrapper wrapperXEgg = new IngredientWrapper();
        List<Integer> ingsXEgg = new ArrayList<>();
        ingsXEgg.add(3);
        ingsXEgg.add(4);
        ingsXEgg.add(5);
        wrapperXEgg.setIngredientsId(ingsXEgg);
        wrapperXEgg.setIngredientsQty(qts3);
        
        IngredientWrapper wrapperXEggBacon = new IngredientWrapper();
        List<Integer> ingsXEggBacon = new ArrayList<>();
        ingsXEggBacon.add(2);
        ingsXEggBacon.add(3);
        ingsXEggBacon.add(4);
        ingsXEggBacon.add(5);
        wrapperXEggBacon.setIngredientsId(ingsXEggBacon);
        wrapperXEggBacon.setIngredientsQty(qts4);
        
        
        assertEquals(6.50f,ingredientController.calculatePrice(wrapperXBacon));
        assertEquals(4.50f,ingredientController.calculatePrice(wrapperXBurger));
        assertEquals(5.30f,ingredientController.calculatePrice(wrapperXEgg));
        assertEquals(7.30f,ingredientController.calculatePrice(wrapperXEggBacon));
        
    }
    
    @Test
    public void foodsWithPromoTest() throws Exception {
        
        List<Integer> qts = new ArrayList<>();
        qts.add(1); //Lettuce
        qts.add(0); //Bacon
        qts.add(3); //Meat
        qts.add(1); //Egg
        qts.add(7); //Cheese
        
        
        IngredientWrapper wrapperFood = new IngredientWrapper();
        List<Integer> ingsFood = new ArrayList<>();
        ingsFood.add(1);
        ingsFood.add(2);
        ingsFood.add(3);
        ingsFood.add(4);
        ingsFood.add(5);
        wrapperFood.setIngredientsId(ingsFood);
        wrapperFood.setIngredientsQty(qts);
        
        //light, 1 free meat and 2 free cheese
        assertEquals(13.23f, ingredientController.calculatePrice(wrapperFood));
        
    }
    
    @Test
    public void foodsWithPromoAndInflationTest() throws Exception {
        
        List<Integer> qts = new ArrayList<>();
        qts.add(1); //Lettuce
        qts.add(0); //Bacon
        qts.add(3); //Meat
        qts.add(1); //Egg
        qts.add(7); //Cheese
        
        
        IngredientWrapper wrapperFood = new IngredientWrapper();
        List<Integer> ingsFood = new ArrayList<>();
        ingsFood.add(1);
        ingsFood.add(2);
        ingsFood.add(3);
        ingsFood.add(4);
        ingsFood.add(5);
        wrapperFood.setIngredientsId(ingsFood);
        wrapperFood.setIngredientsQty(qts);
        
        Ingredient newMeat = new Ingredient();
        newMeat.setPrice(4.0f);
        ingredientController.updateIngredient(3, newMeat); //changing meat price to 4.00
        
        //light, 1 free meat and 2 free cheese
        assertEquals(15.03f, ingredientController.calculatePrice(wrapperFood));
        
        
        newMeat.setPrice(3.0f);
        ingredientController.updateIngredient(3, newMeat); //reverting changes     
    }

}
