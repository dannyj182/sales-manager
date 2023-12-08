package com.dannyj182.salesmanager.repository;

import com.dannyj182.salesmanager.model.entity.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends CrudRepository<Sale, Long> {
}
