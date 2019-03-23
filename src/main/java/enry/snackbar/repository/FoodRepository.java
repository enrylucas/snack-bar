package enry.snackbar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import enry.snackbar.model.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    
}