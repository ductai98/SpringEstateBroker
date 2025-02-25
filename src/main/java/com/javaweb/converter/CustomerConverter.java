package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO toCustomerDTO(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerDTO.class);
    }

    public CustomerEntity toCustomerEntity(CustomerDTO dto) {
        return modelMapper.map(dto, CustomerEntity.class);
    }

}
