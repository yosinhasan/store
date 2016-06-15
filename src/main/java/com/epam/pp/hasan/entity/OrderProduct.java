package com.epam.pp.hasan.entity;

import java.math.BigDecimal;

/**
 * @author Yosin_Hasan
 */
public class OrderProduct extends Entity {
    private final Long productId;
    private final String productName;
    private final Integer productAmount;
    private final BigDecimal productPrice;
    private final Long productOrderId;

    public OrderProduct(Long productId, String productName, Integer productAmount, BigDecimal productPrice, Long productOrderId) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.productOrderId = productOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderProduct that = (OrderProduct) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productAmount != null ? !productAmount.equals(that.productAmount) : that.productAmount != null)
            return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        return productOrderId != null ? productOrderId.equals(that.productOrderId) : that.productOrderId == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productAmount != null ? productAmount.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productOrderId != null ? productOrderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", productPrice=" + productPrice +
                ", productOrderId=" + productOrderId +
                '}';
    }
}
