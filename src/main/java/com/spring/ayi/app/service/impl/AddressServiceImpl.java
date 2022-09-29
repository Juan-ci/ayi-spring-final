package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.mapper.IAddressMapper;
import com.spring.ayi.app.repository.IAddressRepository;
import com.spring.ayi.app.service.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private IAddressRepository addressRepository;

    private IAddressMapper addressMapper;

    @Override
    public AddressResponse createAddress(AddressRequest request) {
        Address address = addressMapper.convertDtoToEntity(request);
        Client client = address.getClient();

        address.setClient(client);
        address = addressRepository.save(address);

        return addressMapper.convertEntityToDto(address);
    }
}
