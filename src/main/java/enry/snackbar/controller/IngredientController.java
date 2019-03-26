package enry.snackbar.controller;

import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.IngredientRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientController {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/ingredients")
    public Page<Ingredient> getIngredients(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

    @PostMapping("/ingredients")
    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @PutMapping("/ingredients/{ingredientId}")
    public Optional<Ingredient> updateIngredient(@PathVariable Integer ingredientId,
            @Valid @RequestBody Ingredient ingredientRequest) {
        return ingredientRepository.findById(ingredientId)
                .map(ingredient -> {
                    if(ingredientRequest.getName() != null) ingredient.setName(ingredientRequest.getName());
                    if(ingredientRequest.getPrice() != null) ingredient.setPrice(ingredientRequest.getPrice());
                    if(ingredientRequest.getIsMeat() != null) ingredient.setIsMeat(ingredientRequest.getIsMeat());
                    if(ingredientRequest.getIsCheese() != null) ingredient.setIsCheese(ingredientRequest.getIsCheese());
                    return ingredientRepository.save(ingredient);
                });
    }

    @DeleteMapping("/ingredients/{ingredientId}")
    public Optional<ResponseEntity<Object>> deleteIngredient(@PathVariable Integer ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .map(ingredient -> {
                    ingredientRepository.delete(ingredient);
                    return ResponseEntity.ok().build();
                });
    }
    
}
