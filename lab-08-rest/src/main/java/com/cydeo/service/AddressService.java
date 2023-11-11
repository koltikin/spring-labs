package com.cydeo.service;

import com.cydeo.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAllAddress();

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(AddressDTO addressDTO);

    List<AddressDTO> findAddressStartsWith(String address);
    List<AddressDTO> findByCustomerId(Long customerId);
}
