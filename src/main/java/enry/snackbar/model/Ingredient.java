package enry.snackbar.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredients")
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
    @Column(name="name")
    private String name;

    @Column(name="price")
    private Float price;
    
    @Column(name="isMeat")
    private Boolean isMeat;
    
    @Column(name="isCheese")
    private Boolean isCheese;

    /*@ManyToMany(mappedBy = "ingredients")
    private List<Food> foods = new ArrayList<>();

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }*/
    
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

    public Float getPrice() {
        return price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getIsMeat() {
        return isMeat;
    }

    public void setIsMeat(Boolean isCarne) {
        this.isMeat = isCarne;
    }

    public Boolean getIsCheese() {
        return isCheese;
    }

    public void setIsCheese(Boolean isCheese) {
        this.isCheese = isCheese;
    }
    
    
    
}
