package enry.snackbar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "foods")
/*@JsonIgnoreProperties(
        value = {"price"},
        allowGetters = true
)*/
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(generator = "food_generator")
    @SequenceGenerator(
            name = "food_generator",
            sequenceName = "food_sequence",
            initialValue = 1
    )
    private Integer id;

    @NotBlank
    @Column(name="name")
    private String name;
    
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public Food() {}
    
    public Food(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Float getPrice() {
        return price;
    }
    
    public void setPrice() { // regras de negócio aqui
        this.price = 0.0f;
        boolean light = true;
        
        for (Ingredient ingredient : ingredients) {
            this.price += ingredient.getPrice();
            if(ingredient.getName().equals("Bacon")) light = false;
        }
        
        if(light) this.price *= 0.9f;
    }*/

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    
}