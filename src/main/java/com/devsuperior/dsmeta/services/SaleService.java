package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<SaleMinDTO> reportsForAllSellers(String minDate, String maxDate, String name, Pageable pageable) {
        if (minDate == null && maxDate == null) {
            LocalDate newMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
            LocalDate newMinDate = newMaxDate.minusYears(1L);
            name = "";
            return repository.reportsForDateAndName(newMinDate, newMaxDate, name, pageable).map(SaleMinDTO::new);
        }
        return repository.reportsForDateAndName(LocalDate.parse(minDate), LocalDate.parse(maxDate), name, pageable).map(SaleMinDTO::new);
    }

    public List<SaleSumDTO> listAllSales(String minDate, String maxDate) {
        if (minDate == null && maxDate == null) {
            LocalDate newMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
            LocalDate newMinDate = newMaxDate.minusYears(1L);
            return repository.findTotalAmountAndSellerNameBetweenDates(newMinDate, newMaxDate).stream().toList();
        }
        return repository.findTotalAmountAndSellerNameBetweenDates(LocalDate.parse(minDate), LocalDate.parse(maxDate)).stream().toList();
    }
}
