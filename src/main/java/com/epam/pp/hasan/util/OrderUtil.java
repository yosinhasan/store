package com.epam.pp.hasan.util;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.entity.Sort;

import java.util.HashMap;

/**
 * @author Yosin_Hasan
 */
public final class OrderUtil {
    /**
     * Generates order list by which products can be ordered.
     *
     * @return Sort
     */
    public static Sort getProductOrder() {
        HashMap<Integer, String> orderList = new HashMap<>();
        int k = 0;
        orderList.put(k++, Fields.ENTITY_NAME);
        orderList.put(k++, Fields.PRODUCT_PRICE);
        orderList.put(k++, Fields.PRODUCT_CATEGORY_ID);
        orderList.put(k, Fields.PRODUCT_BRAND_ID);
        return new Sort(orderList);
    }
}
