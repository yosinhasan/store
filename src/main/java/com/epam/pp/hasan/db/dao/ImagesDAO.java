package com.epam.pp.hasan.db.dao;

import com.epam.pp.hasan.entity.Images;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public interface ImagesDAO extends GenericDAO<Images> {
    /**
     * Read all images by product id.
     *
     * @param con       connection
     * @param productId product id
     * @return ArrayList<Images>
     */
    ArrayList<Images> readAll(Connection con, Long productId);

}
