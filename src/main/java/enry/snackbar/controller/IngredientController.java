package enry.snackbar.controller;

import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientController {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping("/ingredients")
    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @PutMapping("/ingredients/{id}")
    public Optional<Ingredient> updateIngredient(@PathVariable Integer id,
            @Valid @RequestBody Ingredient ingredientRequest) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    if(ingredientRequest.getName() != null) ingredient.setName(ingredientRequest.getName());
                    if(ingredientRequest.getPrice() != null) ingredient.setPrice(ingredientRequest.getPrice());
                    if(ingredientRequest.getIsMeat() != null) ingredient.setIsMeat(ingredientRequest.getIsMeat());
                    if(ingredientRequest.getIsCheese() != null) ingredient.setIsCheese(ingredientRequest.getIsCheese());
                    System.out.println("New Ingredient:");
                    System.out.println(ingredient);
                    return ingredientRepository.save(ingredient);
                });
    }

    @DeleteMapping("/ingredients/{id}")
    public Optional<ResponseEntity<Object>> deleteIngredient(@PathVariable Integer id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredientRepository.delete(ingredient);
                    return ResponseEntity.ok().build();
                });
    }
    
    public Float lightPromo(Float total, List<Ingredient> ingredients){
        boolean noBacon = true;
        boolean noLettuce = true;
        for (Ingredient ingredient : ingredients) {
            if("Alface".equals(ingredient.getName())) noLettuce = false;
            if("Bacon".equals(ingredient.getName())) noBacon = false;
        }
        if(!noLettuce && noBacon) return total*0.1f;
        else return 0.0f;
    }
    
    public Float moreMeatPromo(List<Ingredient> ingredients, List<Integer> ingredientsQty){
        Float meatPrice = -1f;
        int meatCount = 0;
        int size = ingredients.size();
        for (int i=0;i<size;i++) {
            if(ingredients.get(i).getIsMeat()){
                if(meatPrice < 0) meatPrice = ingredients.get(i).getPrice();
                meatCount += ingredientsQty.get(i);
            }
        }
        if(meatCount > 0) return meatPrice * (meatCount/3);
        else return 0.0f;
    }
    
    public Float moreCheesePromo(List<Ingredient> ingredients, List<Integer> ingredientsQty){
        Float cheesePrice = -1f;
        int cheeseCount = 0;
        int size = ingredients.size();
        for (int i=0;i<size;i++) {
            if(ingredients.get(i).getIsCheese()){
                if(cheesePrice < 0) cheesePrice = ingredients.get(i).getPrice();
                cheeseCount += ingredientsQty.get(i);
            }
        }
        if(cheeseCount > 0) return cheesePrice * (cheeseCount/3);
        else return 0.0f;
    }
    
    @PostMapping("/ingredients/build")
    @CrossOrigin(origins = "http://localhost:4200")
    public Float calculatePrice(@Valid @RequestBody IngredientWrapper wrapper){
        if(wrapper.ingredientsId.size() != wrapper.ingredientsQty.size()) return -1f;
        int size = wrapper.ingredientsId.size();
        Float total = 0.0f;
        List<Ingredient> realIngredients = new ArrayList<>(); //Granting updated values
        for(int i=0;i<size;i++){
            Optional<Ingredient> realIngredient = ingredientRepository.findById(wrapper.ingredientsId.get(i)); //test problem here
            System.out.println("RealIngredient:");
            if(realIngredient.isPresent()){
                System.out.println(realIngredient.get());
                total += realIngredient.get().getPrice() * wrapper.ingredientsQty.get(i);
                realIngredients.add(realIngredient.get());
            }
        }
        
        
        //Promos
        if(!realIngredients.isEmpty()){
            Float discountLight = lightPromo(total, realIngredients);
            Float discountMeat = moreMeatPromo(realIngredients, wrapper.ingredientsQty);
            Float discountCheese = moreCheesePromo(realIngredients, wrapper.ingredientsQty);
            Float totalDiscount = discountLight + discountMeat + discountCheese;
            return total - totalDiscount;
        }else return -1f;
        
        
    }
    
}
