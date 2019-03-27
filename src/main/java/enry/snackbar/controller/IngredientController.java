package enry.snackbar.controller;

import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.IngredientRepository;
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
    
}
