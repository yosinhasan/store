package com.epam.pp.hasan.db.transaction;

/**
 * 
 * @author Yosin_Hasan
 *
 * @param <T>
 */
public interface ExecuteOperation<T> {
    /**
     * Execute operation.
     *
     * @return T
     */
    <T> T execute();
}
