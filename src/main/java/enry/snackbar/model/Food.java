package enry.snackbar.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "foods")
@Data
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
    @Column(unique=true)
    private String name;
    
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public Food() {}
    
    public Food(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }
    
}