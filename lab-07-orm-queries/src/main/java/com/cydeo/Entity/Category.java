package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category extends BaseEntity{

    private String name;

    @ManyToMany
    @JoinTable(name = "product_category_rel",
    joinColumns = @JoinColumn(name = "c_id"),
    inverseJoinColumns = @JoinColumn(name = "p_id"))
    private List<Product> products;

}
