package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class Address extends BaseEntity{
    private String name;
    private String street;
    private String zipCode;


}
