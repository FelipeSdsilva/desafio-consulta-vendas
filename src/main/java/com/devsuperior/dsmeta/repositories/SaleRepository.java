package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT s FROM Sale s 
            JOIN s.seller seller 
            WHERE s.date BETWEEN :minDate AND :maxDate AND UPPER(seller.name) LIKE UPPER(CONCAT('%', :name, '%'))""")
    Page<Sale> reportsForDateAndName(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("""
            SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(s.seller.name, SUM(s.amount)) 
            FROM Sale s 
            WHERE s.date BETWEEN :minDate AND :maxDate 
            GROUP BY s.seller.name
            """)
    List<SaleSumDTO> findTotalAmountAndSellerNameBetweenDates(LocalDate minDate, LocalDate maxDate);

}
