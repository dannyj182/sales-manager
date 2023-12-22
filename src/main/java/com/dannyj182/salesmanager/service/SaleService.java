package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.SaleDTO;
import com.dannyj182.salesmanager.model.entity.Customer;
import com.dannyj182.salesmanager.model.entity.Item;
import com.dannyj182.salesmanager.model.entity.Sale;
import com.dannyj182.salesmanager.model.mapper.SaleMapper;
import com.dannyj182.salesmanager.repository.ISaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleRepository repository;
    private final SaleMapper mapper;
    private final ICustomerService customerService;
    private final IItemService itemService;

    @Override
    public SaleDTO saveSale(SaleDTO saleDTO) {
        Optional<Customer> optionalCustomer = customerService.getCustomer(saleDTO.getCustomer().getCustomerId());
        if(optionalCustomer.isEmpty() || saleDTO.getItems() == null) return null;
        List<Item> itemList = itemService.validateItems(saleDTO.getItems());
        if(itemList.isEmpty()) return null;
        saleDTO.setDateSale(LocalDate.now());
        saleDTO.setTotalSale(itemList.stream().mapToDouble(Item::getTotalItem).sum());
        Sale sale = repository.save(mapper.toSale(saleDTO));
        this.setSaleToItemList(itemList, sale);
        sale.setCustomer(optionalCustomer.get());
        sale.setItems(itemService.saveAll(itemList));
        return mapper.toSaleDTO(repository.save(sale));
    }

    private void setSaleToItemList(List<Item> itemList, Sale sale) {
        for(Item item : itemList){
            item.setSale(sale);
        }
    }

    @Override
    public Optional<SaleDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toSaleDTO);
    }

    @Override
    public List<SaleDTO> findAll() {
        return mapper.toSalesDTO((List<Sale>) repository.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Sale> saleOptional = repository.findById(id);
        if(saleOptional.isEmpty()) return false;
        Sale sale = saleOptional.get();
        sale.setItems(null);
        repository.save(sale);
        itemService.emptyList(sale.getSaleId());
        repository.delete(sale);
        return true;
    }

    @Override
    public SaleDTO editSale(Long id, SaleDTO saleDTO) {
        Optional<Sale> optionalSale = repository.findById(id);
        if (optionalSale.isEmpty()) return null;
        Sale sale = optionalSale.get();
        if (saleDTO.getDateSale() != null) sale.setDateSale(saleDTO.getDateSale());
        Optional<Customer> optionalCustomer = customerService.getCustomer(saleDTO.getCustomer().getCustomerId());
        optionalCustomer.ifPresent(sale::setCustomer);
        return mapper.toSaleDTO(repository.save(sale));
    }
}
