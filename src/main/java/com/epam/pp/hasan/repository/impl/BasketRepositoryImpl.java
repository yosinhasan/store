package com.epam.pp.hasan.repository.impl;

import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.repository.BasketRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yosin_Hasan
 */
public class BasketRepositoryImpl implements BasketRepository {
    private HashMap<Product, Integer> data;

    public BasketRepositoryImpl() {
        data = new HashMap<>();
    }

    @Override
    public Boolean addProduct(Product item) {
        if (item != null) {
            Integer amount = 1;
            if (data.containsKey(item)) {
                amount = data.get(item) + 1;
            }
            return addProduct(item, amount);
        }
        return false;
    }

    @Override
    public Boolean addProduct(Product item, Integer amount) {
        if (item != null && amount > 0) {
            data.put(item, amount);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeProduct(Long id) {
        Iterator<Map.Entry<Product, Integer>> iter = data.entrySet().iterator();
        Map.Entry<Product, Integer> entry;
        Product item = null;
        while (iter.hasNext()) {
            entry = iter.next();
            if (entry.getKey().getId() == id) {
                item = entry.getKey();
                break;
            }
        }
        return removeProduct(item);
    }

    @Override
    public Boolean updateProduct(Long id, Integer amount) {
        Iterator<Map.Entry<Product, Integer>> iter = data.entrySet().iterator();
        Map.Entry<Product, Integer> entry;
        Product item = null;
        while (iter.hasNext()) {
            entry = iter.next();
            if (entry.getKey().getId() == id) {
                item = entry.getKey();
                break;
            }
        }
        return addProduct(item, amount);
    }

    @Override
    public Boolean removeProduct(Product item) {
        if (data.containsKey(item)) {
            data.remove(item);
            return true;
        }
        return false;
    }

    @Override
    public Map<Product, Integer> findAll() {
        return Collections.unmodifiableMap(data);
    }

    @Override
    public Integer countAmount() {
        return data.size();
    }

    @Override
    public BigDecimal countSumm() {
        BigDecimal sum = new BigDecimal(0);
        Iterator<Map.Entry<Product, Integer>> iter = data.entrySet().iterator();
        Map.Entry<Product, Integer> entry;
        BigDecimal tmp;
        while (iter.hasNext()) {
            entry = iter.next();
            tmp = entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
            sum = sum.add(tmp);
        }
        return sum;
    }

    @Override
    public void removeAll() {
        data.clear();
        return;
    }
}
