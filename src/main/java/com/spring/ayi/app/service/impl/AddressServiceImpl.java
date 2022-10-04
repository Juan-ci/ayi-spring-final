package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.mapper.IAddressMapper;
import com.spring.ayi.app.repository.IAddressRepository;
import com.spring.ayi.app.service.IAddressService;
import com.spring.ayi.app.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private IAddressRepository addressRepository;

    private IAddressMapper addressMapper;

    private IClientService clientService;

    @Override
    @Transactional
    public AddressResponse createAddress(AddressRequest request) throws NoSuchElementException {
        Address address = addressMapper.convertDtoToEntity(request);

        Client client = clientService.getClientByDocumentNumber(request.getClientDocumentNumber());

        if( client != null) {
            client.getAddresses().add(address);
            address.setClient(client);
            address = addressRepository.save(address);
        }
        return addressMapper.convertEntityToDto(address);
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<AddressResponse> getAllAddress(String uri,
                                                                        int pageReq,
                                                                        Integer size,
                                                                        UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse<AddressResponse> addressPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);

        if (addressPage != null && !addressPage.isEmpty() && !(pageReq > addressPage.getTotalPages())) {
            List<AddressResponse> addressContent = addressPage.map(
                            address -> addressMapper.convertEntityToDto(address)
                    ).stream()
                    .collect(Collectors.toList());

            addressPagesResponse.setPages(addressContent);
            addressPagesResponse.setSize(addressPage.getSize());
            addressPagesResponse.setCurrentPage(addressPage.getNumber() + 1);
            addressPagesResponse.setTotalPages(addressPage.getTotalPages());
            addressPagesResponse.setTotalElements((int) addressPage.getTotalElements());

            uriBuilder.path(uri);
            String nextPage = constructNextPageUri(uriBuilder, pageReq);
            String prevPage = constructPrevPageUri(uriBuilder, pageReq);

            /**
             * If page is equal to 0, then
             * there is no previous page
             */
            if (pageable.getPageNumber() == 0) {
                prevPage = null;
            }

            /**
             * If page is equal to the last page, then
             *  there is no next page
             */
            if (pageable.getPageNumber() == addressPage.getTotalPages() - 1) {
                nextPage = null;
            }

            addressPagesResponse.setPrevPage(prevPage);
            addressPagesResponse.setNextPage(nextPage);

            return addressPagesResponse;
        } else if (pageReq > addressPage.getTotalPages()) {
            //  Crear custom exception
            throw new RuntimeException("No existe la página " + pageReq);
        } else {
            // Crear custom exception
            throw new RuntimeException("No hay registros en address pára mostrar.");
        }
    }

    @Override
    @Transactional
    public AddressResponse getOneAddressById(Long idAddress) throws NoSuchElementException {
        Address address = this.getAddressById(idAddress);
        return addressMapper.convertEntityToDto(address);
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(Long idAddress, AddressRequest request) throws NoSuchElementException {
        Address addressToUpdate = this.getAddressById(idAddress);

        return addressMapper.convertEntityToDto(addressToUpdate);
    }

    @Override
    @Transactional
    public void deleteAddressById(Long idAddress) throws NoSuchElementException {
        Address address = this.getAddressById(idAddress);

        address.setSoftDelete(Boolean.TRUE);
        addressRepository.save(address);
    }

    private Address getAddressById(Long idAddress) throws NoSuchElementException {
        //  Create custom exception
        return addressRepository.findById(idAddress)
                .orElseThrow(() -> new NoSuchElementException("Client detail id " + idAddress + " not found."));
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
