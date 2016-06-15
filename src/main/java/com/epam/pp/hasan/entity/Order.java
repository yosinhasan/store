package com.epam.pp.hasan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class Order extends Entity {
	private OrderStatus status;
	private String detail;
	private String orderDate;
	private Long userId;
	private BigDecimal totalPrice;
	private Integer paymentId;
	private String userAddress;
	private Long creditCardNumber;
	private ArrayList<OrderProduct> orderProducts;

	public Order() {

	}

	public Order(OrderStatus status, String detail, String orderDate, Long userId, BigDecimal totalPrice,
			ArrayList<OrderProduct> orderProducts) {
		this.status = status;
		this.detail = detail;
		this.orderDate = orderDate;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.orderProducts = orderProducts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public final Integer getPaymentId() {
		return paymentId;
	}

	public final void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public final String getUserAddress() {
		return userAddress;
	}

	public final void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public final Long getCreditCardNumber() {
		return creditCardNumber;
	}

	public final void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderProducts == null) ? 0 : orderProducts.hashCode());
		result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderProducts == null) {
			if (other.orderProducts != null)
				return false;
		} else if (!orderProducts.equals(other.orderProducts))
			return false;
		if (paymentId == null) {
			if (other.paymentId != null)
				return false;
		} else if (!paymentId.equals(other.paymentId))
			return false;
		if (status != other.status)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [status=" + status + ", detail=" + detail + ", orderDate=" + orderDate + ", userId=" + userId
				+ ", totalPrice=" + totalPrice + ", paymentId=" + paymentId + ", userAddress=" + userAddress
				+ ", creditCardNumber=" + creditCardNumber + ", orderProducts=" + orderProducts + "]";
	}

	
}
