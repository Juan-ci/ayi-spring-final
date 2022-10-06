package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.exception.AddressNotFoundException;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
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
import java.util.stream.Collectors;

import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.ADDRESS_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private IAddressRepository addressRepository;

    private IAddressMapper addressMapper;

    private IClientService clientService;

    private final String LIST_EMPTY_TYPE = "ADDRESSES";

    /**
     * It is not allowed to create an address without
     * an existing client
     *
     * @param request
     * @return Address created
     * @throws ClientNotFoundException
     */
    @Override
    @Transactional
    public AddressResponse createAddress(AddressRequest request) throws ClientNotFoundException, DocumentNumberNotFoundException {
        Address address = addressMapper.convertDtoToEntity(request);

        Client client = clientService.getClientByDocumentNumber(request.getClientDocumentNumber());

        if (client != null) {
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
                                                                        UriComponentsBuilder uriBuilder)
            throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<AddressResponse> addressPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);

        if (addressPage != null && !addressPage.isEmpty() && !(pageReq > addressPage.getTotalPages() - 1 )) {
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
        } else if (addressPage.getTotalElements() == 0) {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_EMPTY_TYPE));
        } else {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
        }
    }

    @Override
    @Transactional
    public AddressResponse getOneAddressById(Long idAddress) throws AddressNotFoundException {
        Address address = this.getAddressById(idAddress);
        return addressMapper.convertEntityToDto(address);
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(Long idAddress, AddressRequest request) throws AddressNotFoundException {
        Address addressToUpdate = this.getAddressById(idAddress);

        addressToUpdate.setCountry(request.getCountry());
        addressToUpdate.setCity(request.getCity());
        addressToUpdate.setDistrict(request.getDistrict());
        addressToUpdate.setFloor(request.getFloor());
        addressToUpdate.setStreet(request.getStreet());
        addressToUpdate.setStreetNumber(request.getStreetNumber());
        addressToUpdate.setPostalCode(request.getPostalCode());

        Address addressUpdated = addressRepository.save(addressToUpdate);

        return addressMapper.convertEntityToDto(addressUpdated);
    }

    @Override
    @Transactional
    public void deleteAddressById(Long idAddress) throws AddressNotFoundException {
        Address address = this.getAddressById(idAddress);

        address.setSoftDelete(Boolean.TRUE);
        addressRepository.save(address);
    }

    private Address getAddressById(Long idAddress) throws AddressNotFoundException {
        return addressRepository.findById(idAddress)
                .orElseThrow(
                        () -> new AddressNotFoundException(format(ADDRESS_ID_NOT_FOUND, idAddress))
                );
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
