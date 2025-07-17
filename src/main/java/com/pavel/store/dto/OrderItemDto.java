package com.pavel.store.dto;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long id;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
    private String sku;

    public OrderItemDto() {
    }

    private OrderItemDto(Builder builder) {
        this.id = builder.id;
        this.itemName = builder.itemName;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.subtotal = builder.subtotal;
        this.sku = builder.sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String itemName;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal subtotal;
        private String sku;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder subtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public OrderItemDto build() {
            return new OrderItemDto(this);
        }
    }
}