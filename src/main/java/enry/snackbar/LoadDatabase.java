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
            Ingredient alface = new Ingredient("Alface", 0.40f, false, false);
            Ingredient bacon = new Ingredient("Bacon", 2.00f, true, false);
            Ingredient hamburguerCarne = new Ingredient("Hamburguer de Carne", 3.00f, true, false);
            Ingredient ovo = new Ingredient("Ovo", 0.80f, false, false);
            Ingredient queijo = new Ingredient("Queijo", 1.50f, false, true);
            log.info("Preloading " + repository.save(alface));
            log.info("Preloading " + repository.save(bacon));
            log.info("Preloading " + repository.save(hamburguerCarne));
            log.info("Preloading " + repository.save(ovo));
            log.info("Preloading " + repository.save(queijo));
            
            
            List<Ingredient> listXBacon = new ArrayList<>();
            listXBacon.add(bacon);
            listXBacon.add(hamburguerCarne);
            listXBacon.add(queijo);
            log.info("Preloading " + repository2.save(new Food("X-Bacon", listXBacon)));
            
            List<Ingredient> listXBurguer = new ArrayList<>();
            listXBurguer.add(hamburguerCarne);
            listXBurguer.add(queijo);
            log.info("Preloading " + repository2.save(new Food("X-Burguer", listXBurguer)));
            
            List<Ingredient> listXEgg = new ArrayList<>();
            listXEgg.add(ovo);
            listXEgg.add(hamburguerCarne);
            listXEgg.add(queijo);
            log.info("Preloading " + repository2.save(new Food("X-Egg", listXEgg)));
            
            List<Ingredient> listXEggBacon = new ArrayList<>();
            listXEggBacon.add(ovo);
            listXEggBacon.add(bacon);
            listXEggBacon.add(hamburguerCarne);
            listXEggBacon.add(queijo);
            log.info("Preloading " + repository2.save(new Food("X-Egg Bacon", listXEggBacon)));
            
        };
    }

}
