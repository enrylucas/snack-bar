package enry.snackbar.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "ingredient_generator")
    @SequenceGenerator(
            name = "ingredient_generator",
            sequenceName = "ingredient_sequence",
            initialValue = 1
    )
    private Integer id;

    @NotBlank
    @Column(unique=true)
    private String name;

    private Float price;
    
    private Boolean isMeat;
    
    private Boolean isCheese;
    
    public Ingredient() {}
    
    public Ingredient(String name, Float price, Boolean isMeat, Boolean isCheese){
        this.name = name;
        this.price = price;
        this.isMeat = isMeat;
        this.isCheese = isCheese;
    }
    
}
