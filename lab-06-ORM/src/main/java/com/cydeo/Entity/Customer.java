package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
