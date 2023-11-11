package com.cydeo.service.Impl;

import com.cydeo.dto.AddressDTO;
import com.cydeo.entity.Address;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final MapperUtil mapper;
    @Override
    public List<AddressDTO> findAllAddress() {
        return repository.findAll().stream()
                .map(address -> mapper.convert(address,new AddressDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        repository.save(mapper.convert(addressDTO,new Address()));
        return addressDTO;
    }
    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {
        repository.save(mapper.convert(addressDTO,new Address()));
        return addressDTO;
    }

    @Override
    public List<AddressDTO> findAddressStartsWith(String address) {
        return repository.findAllByStreetStartingWith(address).stream()
                .map(address1 ->mapper.convert(address1,new AddressDTO()))
                .collect(Collectors.toList());

    }
}
