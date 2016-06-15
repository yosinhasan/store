package com.epam.pp.hasan.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.repository.BasketRepository;
import com.epam.pp.hasan.service.BasketService;

/**
 * @author Yosin_Hasan
 */
public class BasketServiceImpl implements BasketService {
    private BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Map<Product, Integer> findAll() {
        return basketRepository.findAll();
    }

    @Override
    public Boolean removeProduct(Product item) {
        return basketRepository.removeProduct(item);
    }

    @Override
    public Boolean addProduct(Product item, Integer amount) {
        return basketRepository.addProduct(item, amount);
    }

    @Override
    public void clear() {
        basketRepository.removeAll();
    }

    @Override
    public BigDecimal countSumm() {
        return basketRepository.countSumm();
    }

    @Override
    public Integer countAmount() {
        return basketRepository.countAmount();
    }

	public Boolean addProduct(Product item) {
		return basketRepository.addProduct(item);
	}

	public Boolean updateProduct(Long id, Integer amount) {
		return basketRepository.updateProduct(id, amount);
	}

	@Override
	public Boolean removeProduct(Long id) {
		return basketRepository.removeProduct(id);
	}


}
