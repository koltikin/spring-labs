package com.cydeo;
import com.cydeo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QueryDemo implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final BalanceRepository balanceRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final DiscountRepository discountRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public QueryDemo(AddressRepository addressRepository, BalanceRepository balanceRepository,
                     CartItemRepository cartItemRepository, CartRepository cartRepository,
                     CustomerRepository customerRepository, DiscountRepository discountRepository,
                     OrderRepository orderRepository, ProductRepository productRepository,
                     CategoryRepository categoryRepository) {
        this.addressRepository = addressRepository;
        this.balanceRepository = balanceRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.discountRepository = discountRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println(addressRepository.getAllAddressesByCustomerId(3L));
//        System.out.println(balanceRepository.getAllByAmountLessThenOrEquals(BigDecimal.valueOf(30)));
//        System.out.println(customerRepository.getAllByEmail("jrosenau8@dagondesign.com"));
        System.out.println(productRepository.findByCategoryName("Wall Protection"));
    }
}
