package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

import java.time.LocalDate;

public class SaleMinDTO {

    private Long id;
    private Double amount;
    private LocalDate date;
    private String sellerName;

    public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.sellerName = sellerName;
    }

    public SaleMinDTO(Sale entity) {
        id = entity.getId();
        amount = entity.getAmount();
        date = entity.getDate();
        sellerName = entity.getSeller().getName();
    }

    public SaleMinDTO(SaleProjection projection) {
        id = projection.getId();
        amount = projection.getAmount();
        date = projection.getDate();
        sellerName = projection.getName();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSellerName() {
        return sellerName;
    }
}
