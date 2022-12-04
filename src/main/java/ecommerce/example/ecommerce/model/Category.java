package ecommerce.example.ecommerce.model;

//import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="category_name")
    private @NotBlank String categoryName;

    private @NotBlank String description;

//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    Set<Product> products;

    @Column(name="image_url")
    private @NotBlank String imageUrl;

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
