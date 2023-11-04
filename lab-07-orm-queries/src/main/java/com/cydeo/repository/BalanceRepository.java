package com.cydeo.repository;


import com.cydeo.Entity.Address;
import com.cydeo.Entity.Balance;
import com.cydeo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance,Long> {
    //Write a derived query to check balance exists for specific customer
    Boolean existsByCustomer(Customer customer);
    //Write a derived query to get balance for specific customer
    Balance findAllByCustomer(Customer customer);
    //Write a native query to get top 5 max balance
    @Query(nativeQuery = true,value = "SELECT * FROM balance ORDER BY amount DESC limit 5")
    List<Balance> getTop5Balance();
    //Write a derived query to get all balances greater than or equal specific balance amount
    List<Balance> findAllByAmountIsGreaterThanEqual(BigDecimal amount);
    //Write a native query to get all balances less than specific balance amount
    @Query(nativeQuery = true,value = "SELECT * FROM balance WHERE amount<=?1")
    List<Balance> getAllByAmountLessThenOrEquals(BigDecimal amount);
}
