package com.cydeo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class OrderDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Cart ID is required.")
    private Long cartId;

    @Min(message = "Paid Price cannot be lower than 1", value = 1)
    private BigDecimal paidPrice;

    @Min(1)
    private BigDecimal totalPrice;

    @NotNull(message = "Customer ID is required.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long customer_id_for_create; // Used for creation

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CustomerDTO customer; // Used for retrieval

    @NotNull(message = "Payment ID is required.")
    private Long paymentId;
}

