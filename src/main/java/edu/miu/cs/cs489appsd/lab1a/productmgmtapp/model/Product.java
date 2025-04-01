package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

    private Long productId;
    private String name;
    private LocalDate dateSupplied;
    private int quantityInStock;
    private BigDecimal unitPrice;

    public Product() {
    }

    public Product(Long productId, String name, LocalDate dateSupplied, int quantityInStock, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{id=" + productId + ", name='" + name + "', date=" + dateSupplied + ", quantity=" + quantityInStock + ", price=" + unitPrice + '}';
    }
}
