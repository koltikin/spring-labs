package com.cydeo.repository;

import com.cydeo.Entity.Address;
import com.cydeo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    //Write a derived query to get address with a specific street
    List<Address> findAllByCustomer(Customer customer);
    //Write a derived query to get top 3 address with a specific customer email
    List<Address> findTop3ByCustomer_Email(String email);
    //Write a derived query to get all address with a specific customer and name
    List<Address> findAllByCustomerAndName(Customer customer,String name);
    //Write a derived query to list all address where the beginning of the street contains the keyword
    List<Address> findAllByStreetStartsWith(String keyWord);
    //Write a JPQL query to get all address with a specific customerId
    @Query("select ad FROM Address ad WHERE ad.customer.id = :id")
    List<Address> getAllAddressesByCustomerId(Long id);
}
