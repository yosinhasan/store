package com.epam.pp.hasan.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.query.SelectQuery;
import com.epam.pp.hasan.entity.Sort;
/**
 * 
 * @author Yosin_Hasan
 *
 */
public class ProductSearchFilterUtil {
	public SelectQuery select;
	public Sort sort;
	private ArrayList<Object> objects;

	public ProductSearchFilterUtil(SelectQuery select, Sort sort) {
		this.select = select;
		this.sort = sort;
	}

	public void init(HashMap<String, Object> params, Boolean count) {
		objects = new ArrayList<>();
		String[] name = (String[]) params.get("name");
		Long[] catIds = (Long[]) params.get("catIds");
		Long[] brandIds = (Long[]) params.get("brandIds");
		Integer startPrice = (Integer) params.get("startPrice");
		Integer endPrice = (Integer) params.get("endPrice");
		Long[] colorIds = (Long[]) params.get("colorIds");
		Long discountId = (Long) params.get("discountId");
		Integer limit = (Integer) params.get("limit");
		Integer offset = (Integer) params.get("offset");
		Integer orderId = (Integer) params.get("orderId");
		Boolean desc = (Boolean) params.get("desc");
		addName(objects, name);
		addCatId(objects, catIds);
		addBrandId(objects, brandIds);
		addPriceRange(objects, startPrice, endPrice);
		addColorId(objects, colorIds);
		addDiscountId(objects, discountId);
		if (!count) {
			addLimitOffset(limit, offset);
			addOrder(orderId, desc);
		}
	}

	public SelectQuery getSelect() {
		return select;
	}

	public ArrayList<Object> getObjects() {
		return objects;
	}

	private void addOrder(Integer orderId, Boolean desc) {
		String orderField = sort.getOrder(orderId);
		if (orderField == null) {
			orderField = Fields.ENTITY_NAME;
		}
		if (desc == null) {
			select.order(orderField);
		} else {
			select.order(orderField, desc);
		}
	}

	private void addColorId(ArrayList<Object> objects, Long[] colorIds) {
		if (colorIds != null) {
			select.join(Tables.PRODUCT_COLOR, Tables.PRODUCT_COLOR_LABEL, Fields.ENTITY_PRODUCT_ID, Fields.ENTITY_ID);
			select.in(Tables.PRODUCT_COLOR_LABEL, Fields.PRODUCT_COLOR__COLOR_ID, colorIds.length, false, true);
			for (Long colorId : colorIds) {
				objects.add(colorId);
			}
		}
	}

	private void addDiscountId(ArrayList<Object> objects, Long discountId) {
		if (discountId != null) {
			select.join(Tables.PRODUCT_DISCOUNT, Tables.PRODUCT_DISCOUNT_LABEL, Fields.ENTITY_PRODUCT_ID,
					Fields.ENTITY_ID);
			select.where(Fields.PRODUCT_DISCOUNT_ID, Tables.PRODUCT_DISCOUNT_LABEL, true);
			objects.add(discountId);
		}
	}

	private void addName(ArrayList<Object> objects, String[] name) {
		if (name != null) {
			boolean and = true;
			for (String value : name) {
				select.like(Fields.ENTITY_NAME, and);
				objects.add("%" + value + "%");
				and = false;
			}
		}
	}

	private void addLimitOffset(Integer limit, Integer offset) {
		if (limit != null && offset != null) {
			select.limit(offset, limit);
		} else {
			if (limit != null) {
				select.limit(limit);
			}
		}
	}

	private void addBrandId(ArrayList<Object> objects, Long[] brandIds) {
		if (brandIds != null) {
			select.in(Tables.PRODUCT_LABEL, Fields.PRODUCT_BRAND_ID, brandIds.length, false, true);
			for (Long brandId : brandIds) {
				objects.add(brandId);
			}
		}
	}

	private void addCatId(ArrayList<Object> objects, Long[] catIds) {
		if (catIds != null) {
			select.in(Tables.PRODUCT_LABEL, Fields.PRODUCT_CATEGORY_ID, catIds.length, false, true);
			for (Long catId : catIds) {
				objects.add(catId);
			}
		}
	}

	private void addPriceRange(ArrayList<Object> objects, Integer startPrice, Integer endPrice) {
		if (startPrice != null && endPrice != null && endPrice > startPrice) {
			select.between(Tables.PRODUCT_LABEL, Fields.PRODUCT_PRICE, true);
			objects.add(startPrice);
			objects.add(endPrice);
		}
	}

}
