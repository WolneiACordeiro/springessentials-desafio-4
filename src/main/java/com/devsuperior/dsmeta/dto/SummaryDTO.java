package com.devsuperior.dsmeta.dto;

public class SummaryDTO {
    private String sellerName;
    private Double totalSales;

    public SummaryDTO(String sellerName, Double totalSales) {
        this.sellerName = sellerName;
        this.totalSales = totalSales;
    }

    public SummaryDTO() {
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}
