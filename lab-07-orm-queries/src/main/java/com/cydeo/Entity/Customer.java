package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Customer extends BaseEntity{

    private String email;
    private String firstName;
    private String lastName;
    private String userName;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;


}
