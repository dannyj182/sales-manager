package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.CustomerDTO;
import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.dto.SaleDTO;
import com.dannyj182.salesmanager.model.entity.Customer;
import com.dannyj182.salesmanager.model.entity.Item;
import com.dannyj182.salesmanager.model.entity.Sale;
import com.dannyj182.salesmanager.model.mapper.SaleMapper;
import com.dannyj182.salesmanager.repository.ISaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
        Customer customer = this.getCustomer(saleDTO.getCustomer());
        if(customer == null || saleDTO.getItems() == null) return null;
        List<Item> itemList = itemService.validateItems(saleDTO.getItems(), saleDTO.getSaleId());
        if(itemList.isEmpty()) return null;
        saleDTO.setTotalSale(itemList.stream().mapToDouble(Item::getTotalItem).sum());
        Sale sale = repository.save(mapper.toSale(saleDTO));
        this.setSaleToItemList(itemList, sale);
        sale.setDateSale(LocalDate.now());
        sale.setItems(itemService.saveAll(itemList));
        sale.setCustomer(customer);
        return mapper.toSaleDTO(repository.save(sale));
    }

    @Override
    public SaleDTO addItems(SaleDTO saleDTO){
        Sale sale = this.getSale(saleDTO.getSaleId());
        if (sale == null) return null;
        List<Item> itemList = itemService.validateItems(saleDTO.getItems(), sale.getSaleId());
        if(itemList.isEmpty()) return null;
        this.setSaleToItemList(itemList, sale);
        sale.getItems().addAll(itemService.saveAll(itemList));
        sale.setTotalSale(sale.getItems().stream().mapToDouble(Item::getTotalItem).sum());
        return mapper.toSaleDTO(repository.save(sale));
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
        Sale sale = this.getSale(id);
        if (sale == null) return false;
        sale.setItems(null);
        repository.save(sale);
        itemService.emptyList(sale.getSaleId());
        repository.delete(sale);
        return true;
    }

    @Override
    public SaleDTO deleteItems(SaleDTO saleDTO){
        Sale sale = this.getSale(saleDTO.getSaleId());
        if (sale == null) return null;
        List<Item> itemList = this.deleteItems(saleDTO.getItems(), sale);
        if (!itemList.isEmpty()){
            sale.setTotalSale(sale.getItems().stream().mapToDouble(Item::getTotalItem).sum());
            SaleDTO updatedSale = mapper.toSaleDTO(repository.save(sale));
            itemService.deleteAll(itemList);
            return updatedSale;
        }
        else return null;
    }

    private List<Item> deleteItems(List<ItemDTO> itemDTOList, Sale sale){
        List<Item> itemList = new ArrayList<>();
        if (itemDTOList != null){
            for(ItemDTO itemDTO : itemDTOList){
                if (itemDTO.getItemId() != null){
                    Item item = itemService.getItem(sale.getSaleId(), itemDTO.getItemId().getProductId());
                    if (item != null && sale.getItems().remove(item)) itemList.add(item);
                }
            }
        }
        return itemList;
    }

    @Override
    public SaleDTO editSale(Long id, SaleDTO saleDTO) {
        Sale sale = this.getSale(id);
        if (sale == null) return null;
        this.setDateSale(sale, saleDTO.getDateSale());
        this.setCustomer(sale, saleDTO.getCustomer());
        if (this.editItems(sale.getSaleId(), saleDTO.getItems())) {
            sale.setTotalSale(sale.getItems().stream().mapToDouble(Item::getTotalItem).sum());
        }
        return mapper.toSaleDTO(repository.save(sale));
    }

    private void setDateSale(Sale sale, String dateSale) throws DateTimeParseException{
        try {
            sale.setDateSale(LocalDate.parse(dateSale));
        } catch (DateTimeParseException e){
            System.out.println("Error: " + e);
        }
    }

    private void setCustomer(Sale sale, CustomerDTO customerDTO){
        Customer customer = this.getCustomer(customerDTO);
        if (customer != null) {
            sale.setCustomer(customer);
        }
    }

    private boolean editItems(Long saleId, List<ItemDTO> itemDTOList){
        List<Item> itemList = new ArrayList<>();
        if (itemDTOList != null){
            for(ItemDTO itemDTO : itemDTOList){
                if (itemDTO.getItemId() != null){
                    itemDTO.getItemId().setSaleId(saleId);
                    Item item = itemService.updateItem(itemDTO);
                    if (item != null) itemList.add(item);
                }
            }
        }
        return !itemList.isEmpty();
    }

    private Customer getCustomer(CustomerDTO customerDTO){
        if (customerDTO == null) return null;
        else return customerService.getCustomer(customerDTO.getCustomerId());
    }

    private void setSaleToItemList(List<Item> itemList, Sale sale) {
        for(Item item : itemList){
            item.setSale(sale);
        }
    }

    private Sale getSale(Long saleId){
        if (saleId == null) return null;
        else return repository.findById(saleId).orElse(null);
    }
}
