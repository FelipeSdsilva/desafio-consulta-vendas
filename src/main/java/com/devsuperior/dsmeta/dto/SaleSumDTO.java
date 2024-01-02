package com.devsuperior.dsmeta.dto;


public class SaleSumDTO {

    private String salleName;
    private Double total;

    public SaleSumDTO(String salleName, Double total) {
        this.salleName = salleName;
        this.total = total;
    }

    public String getSalleName() {
        return salleName;
    }

    public void setSalleName(String salleName) {
        this.salleName = salleName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
