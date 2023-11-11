package com.cydeo.service.Impl;

import com.cydeo.dto.DiscountDTO;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.DiscountRepository;
import com.cydeo.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository repository;
    private final MapperUtil mapper;

    @Override
    public List<DiscountDTO> findAllDiscounts() {
        return repository.findAll().stream()
                .map(discount -> mapper.convert(discount,new DiscountDTO()))
                .collect(Collectors.toList());
    }
}
