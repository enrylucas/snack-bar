package enry.snackbar.controller;

import org.springframework.beans.factory.annotation.Autowired;

import enry.snackbar.model.Food;
import enry.snackbar.repository.FoodRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/foods")
    public Page<Food> getFoods(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @PostMapping("/foods")
    public Food createFood(@Valid @RequestBody Food food) {
        return foodRepository.save(food);
    }

    @PutMapping("/foods/{id}")
    public Optional<Food> updateFood(@PathVariable Integer id,
            @Valid @RequestBody Food foodRequest) {
        return foodRepository.findById(id)
                .map(food -> {
                    if(foodRequest.getName() != null) food.setName(foodRequest.getName());
                    if(foodRequest.getIngredients() != null) food.setIngredients(foodRequest.getIngredients());
                    return foodRepository.save(food);
                });
    }

    @DeleteMapping("/foods/{id}")
    public Optional<ResponseEntity<Object>> deleteFood(@PathVariable Integer id) {
        return foodRepository.findById(id)
                .map(food -> {
                    foodRepository.delete(food);
                    return ResponseEntity.ok().build();
                });
    }

}
