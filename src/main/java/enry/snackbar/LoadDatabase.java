package enry.snackbar;

import enry.snackbar.model.Food;
import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.FoodRepository;
import enry.snackbar.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(IngredientRepository repository, FoodRepository repository2) {
        return args -> {
            Ingredient lettuce = new Ingredient("Alface", 0.40f, false, false);
            Ingredient bacon = new Ingredient("Bacon", 2.00f, false, false);
            Ingredient meatHamburguer = new Ingredient("Hamburguer de Carne", 3.00f, true, false);
            Ingredient egg = new Ingredient("Ovo", 0.80f, false, false);
            Ingredient cheese = new Ingredient("Queijo", 1.50f, false, true);
            log.info("Preloading " + repository.save(lettuce));
            log.info("Preloading " + repository.save(bacon));
            log.info("Preloading " + repository.save(meatHamburguer));
            log.info("Preloading " + repository.save(egg));
            log.info("Preloading " + repository.save(cheese));
            
            
            List<Ingredient> ingXBacon = new ArrayList<>();
            ingXBacon.add(bacon);
            ingXBacon.add(meatHamburguer);
            ingXBacon.add(cheese);
            log.info("Preloading " + repository2.save(new Food("X-Bacon", ingXBacon)));
            
            List<Ingredient> ingXBurguer = new ArrayList<>();
            ingXBurguer.add(meatHamburguer);
            ingXBurguer.add(cheese);
            log.info("Preloading " + repository2.save(new Food("X-Burguer", ingXBurguer)));
            
            List<Ingredient> ingXEgg = new ArrayList<>();
            ingXEgg.add(egg);
            ingXEgg.add(meatHamburguer);
            ingXEgg.add(cheese);
            log.info("Preloading " + repository2.save(new Food("X-Egg", ingXEgg)));
            
            List<Ingredient> ingXEggBacon = new ArrayList<>();
            ingXEggBacon.add(egg);
            ingXEggBacon.add(bacon);
            ingXEggBacon.add(meatHamburguer);
            ingXEggBacon.add(cheese);
            log.info("Preloading " + repository2.save(new Food("X-Egg Bacon", ingXEggBacon)));
            
        };
    }

}
