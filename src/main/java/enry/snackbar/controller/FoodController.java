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

    @GetMapping("/foods")
    public Page<Food> getFoods(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @PostMapping("/foods")
    public Food createFood(@Valid @RequestBody Food food) {
        return foodRepository.save(food);
    }

    @PutMapping("/foods/{foodId}")
    public Optional<Food> updateFood(@PathVariable Integer foodId,
            @Valid @RequestBody Food foodRequest) {
        return foodRepository.findById(foodId)
                .map(food -> {
                    food.setName(foodRequest.getName());
                    food.setPrice(foodRequest.getPrice());
                    return foodRepository.save(food);
                });
    }

    @DeleteMapping("/foods/{foodId}")
    public Optional<ResponseEntity<Object>> deleteFood(@PathVariable Integer foodId) {
        return foodRepository.findById(foodId)
                .map(food -> {
                    foodRepository.delete(food);
                    return ResponseEntity.ok().build();
                });
    }

}
