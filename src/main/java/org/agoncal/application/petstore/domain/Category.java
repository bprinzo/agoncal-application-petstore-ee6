package org.agoncal.application.petstore.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.agoncal.application.petstore.constraint.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Entity
@NamedQueries({
        // TODO fetch doesn't work with GlassFIsh
//        @NamedQuery(name = Category.FIND_BY_NAME, query = "SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.name = :pname"),
        @NamedQuery(name = Category.FIND_BY_NAME, query = "SELECT c FROM Category c WHERE c.name = :pname"),
        @NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c")
})
@XmlRootElement
@ToString
public class Category {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter Long id;
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 1, max = 30)
    @Getter @Setter private String name;
    @Column(nullable = false)
    @NotEmpty
    @Getter @Setter private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    @XmlTransient
    @ToString.Exclude @Getter @Setter private List<Product> products;

    // ======================================
    // =             Constants              =
    // ======================================

    public static final String FIND_BY_NAME = "Category.findByName";
    public static final String FIND_ALL = "Category.findAll";

    // ======================================
    // =            Constructors            =
    // ======================================

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================

    public Long getId() {
        return id;
    }

    public void addProduct(Product product) {
        if (products == null)
            products = new ArrayList<Product>();
        products.add(product);
    }


    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!name.equals(category.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
