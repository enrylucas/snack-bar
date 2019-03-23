package enry.snackbar.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "foods")
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

    @Column(name="price")
    private Float price;

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

    
}