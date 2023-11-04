package com.cydeo.repository;

import com.cydeo.Entity.Address;
import com.cydeo.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    //Write a derived query to get category by name
    //Write a derived query to get top 3 categories order by name desc
}
