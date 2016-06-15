package com.epam.pp.hasan.entity;

import java.util.HashMap;

/**
 * @author Yosin_Hasan
 */
public class Sort {
	private HashMap<Integer, String> orderList;

	/**
	 * Constructor for order list.
	 *
	 * @param orderList
	 *            sets param to order list
	 */
	public Sort(HashMap<Integer, String> orderList) {
		this.orderList = orderList;
	}

	/**
	 * Get order from order list.
	 *
	 * @param orderId
	 *            order id
	 * @return String
	 */
	public String getOrder(final Integer orderId) {
		String order = null;
		if (orderList.containsKey(orderId)) {
			order = orderList.get(orderId);
		}
		return order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderList == null) ? 0 : orderList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sort other = (Sort) obj;
		if (orderList == null) {
			if (other.orderList != null)
				return false;
		} else if (!orderList.equals(other.orderList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sort [orderList=" + orderList + "]";
	}

}
