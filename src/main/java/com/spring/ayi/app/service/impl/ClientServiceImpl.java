package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.repository.IClientRepository;
import com.spring.ayi.app.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private IClientRepository clientRepository;

    private IClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        Client client = clientMapper.convertDtoToEntity(request);
        ClientDetail clientDetail = client.getClientDetail();
        List<Address> address = client.getAddresses();

        if ( address != null && address.size() > 0) {
            Client finalClient = client;
            address.forEach(addr -> {
                addr.setClient(finalClient);
            });
        }

        clientDetail.setClient(client);
        client.setAddresses(address);
        client = clientRepository.save(client);

        return clientMapper.convertEntityToDto(client);
    }
}
