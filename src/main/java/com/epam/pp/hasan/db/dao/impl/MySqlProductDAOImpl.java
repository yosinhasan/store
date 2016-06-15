package com.epam.pp.hasan.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.config.Messages;
import com.epam.pp.hasan.config.Tables;
import com.epam.pp.hasan.db.dao.ProductDAO;
import com.epam.pp.hasan.entity.Product;
import com.epam.pp.hasan.entity.Sort;
import com.epam.pp.hasan.entity.Status;
import com.epam.pp.hasan.util.OrderUtil;
import com.epam.pp.hasan.util.ProductSearchFilterUtil;
import com.epam.pp.hasan.web.exception.DAOException;

/**
 * @author Yosin_Hasan
 */
public class MySqlProductDAOImpl extends AbstractDAOImpl implements ProductDAO {
	private static final Logger LOG = Logger.getLogger(MySqlProductDAOImpl.class);
	private ArrayList<Product> items;
	private Product item;
	private Sort sort;
	private ProductSearchFilterUtil filter;

	public MySqlProductDAOImpl() {
		initQuery(Tables.PRODUCT, Tables.PRODUCT_LABEL);
		sort = OrderUtil.getProductOrder();
		filter = new ProductSearchFilterUtil(select, sort);
	}

	@Override
	public Boolean create(Connection con, Product object) {
		ResultSet rs = null;
		long id = -1;
		insert.clear();
		insert.field(new String[] { Fields.ENTITY_NAME, Fields.PRODUCT_DESCRIPTION, Fields.PRODUCT_PRICE,
				Fields.PRODUCT_IMAGE, Fields.PRODUCT_CATEGORY_ID, Fields.PRODUCT_BRAND_ID, Fields.PRODUCT_STATUS });

		LOG.info("Query: " + insert.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(insert.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.setString(k++, object.getDescription());
			pstmt.setBigDecimal(k++, object.getPrice());
			pstmt.setString(k++, object.getImage());
			pstmt.setLong(k++, object.getCatId());
			pstmt.setLong(k++, object.getBrandId());
			pstmt.setInt(k++, object.getAvailable().ordinal());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getLong(1);
				object.setId(id);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_CREATE_ERROR, ex);
			throw new DAOException(Messages.PRODUCT_CREATE_ERROR, ex);
		} finally {
			close(rs);
		}
		if (id > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Product read(Connection con, Long id) {
		if (id < 0) {
			return null;
		}
		select.clear();
		select.where(Fields.ENTITY_ID, true);
		select.limit(1);
		LOG.info("Query: " + select.getQuery());
		ResultSet rs = null;
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			pstmt.setLong(k, id);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractProduct(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_FIND_BY_FIELD_ERROR, ex);
			throw new DAOException(Messages.PRODUCT_FIND_BY_FIELD_ERROR, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public Product read(Connection con, String field, Object value) {
		if (field == null || field.isEmpty() || value == null) {
			return null;
		}
		select.clear();
		select.where(field, true);
		select.limit(1);
		ResultSet rs = null;
		LOG.info("Query: " + select.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			pstmt.setObject(1, value);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				item = extractProduct(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_FIND_BY_FIELD_AND_VALUE, ex);
			throw new DAOException(Messages.PRODUCT_FIND_BY_FIELD_AND_VALUE, ex);
		} finally {
			close(rs);
		}
		return item;
	}

	@Override
	public Boolean update(Connection con, Product object) {
		if (object == null || object.getId() == null) {
			return false;
		}
		update.clear();
		update.field(new String[] { Fields.ENTITY_NAME, Fields.PRODUCT_DESCRIPTION, Fields.PRODUCT_PRICE,
				Fields.PRODUCT_IMAGE, Fields.PRODUCT_CATEGORY_ID, Fields.PRODUCT_BRAND_ID, Fields.PRODUCT_STATUS });
		update.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + update.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(update.getQuery())) {
			int k = 1;
			pstmt.setString(k++, object.getName());
			pstmt.setString(k++, object.getDescription());
			pstmt.setBigDecimal(k++, object.getPrice());
			pstmt.setString(k++, object.getImage());
			pstmt.setLong(k++, object.getCatId());
			pstmt.setLong(k++, object.getBrandId());
			pstmt.setInt(k++, object.getAvailable().ordinal());
			pstmt.setLong(k, object.getId());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_UPDATE_ERROR, ex);
			throw new DAOException(Messages.PRODUCT_UPDATE_ERROR, ex);
		}
		return true;
	}

	@Override
	public Boolean delete(Connection con, Long id) {
		delete.clear();
		delete.where(Fields.ENTITY_ID, true);
		LOG.info("Query: " + delete.getQuery());
		try (PreparedStatement pstmt = con.prepareStatement(delete.getQuery())) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_DELETE_ERROR, ex);
			throw new DAOException(Messages.PRODUCT_DELETE_ERROR, ex);
		}
		return true;
	}

	@Override
	public ArrayList<Product> readAll(Connection con) {
		return readAll(con, null);
	}

	@Override
	public ArrayList<Product> readAll(Connection con, Integer limit, Integer offset) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		return readAll(con, params);
	}

	@Override
	public ArrayList<Product> readAll(Connection con, HashMap<String, Object> params) {
		select.clear();
		select.distinct(Fields.ENTITY_ID);
		select.field(new String[] { Fields.ENTITY_NAME, Fields.PRODUCT_DESCRIPTION, Fields.PRODUCT_PRICE,
				Fields.PRODUCT_CATEGORY_ID, Fields.PRODUCT_BRAND_ID, Fields.PRODUCT_IMAGE, Fields.PRODUCT_STATUS });

		filter.init(params, false);
		ArrayList<Object> objects = filter.getObjects();
		items = new ArrayList<>();
		LOG.info("Query: " + select.getQuery());
		ResultSet rs = null;
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			Iterator<Object> iter = objects.iterator();
			while (iter.hasNext()) {
				pstmt.setObject(k++, iter.next());
			}
			LOG.info("Query: " + pstmt);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			while (rs.next()) {
				item = extractProduct(rs);
				items.add(item);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_FIND_ALL_ERROR, ex);
			throw new DAOException(Messages.PRODUCT_FIND_ALL_ERROR, ex);
		} finally {
			close(rs);
		}
		return items;
	}

	@Override
	public ArrayList<Product> readByCatId(Connection con, Long catId, Integer limit, Integer offset) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("catIds", new Long[] { catId });
		return readAll(con, params);
	}

	@Override
	public ArrayList<Product> readByBrandId(Connection con, Long brandId, Integer limit, Integer offset) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("brandIds", new Long[] { brandId });
		return readAll(con, params);
	}

	@Override
	public Integer countProducts(Connection con) {
		return countProducts(con, null);
	}

	@Override
	public Integer countProducts(Connection con, HashMap<String, Object> params) {
		select.clear();
		Integer count = 0;
		filter.init(params, true);
		ArrayList<Object> objects = filter.getObjects();
		select.aggregate("COUNT(DISTINCT " + Tables.PRODUCT_LABEL + ".`id`)");
		LOG.info("Query: " + select.getQuery());
		ResultSet rs = null;
		try (PreparedStatement pstmt = con.prepareStatement(select.getQuery())) {
			int k = 1;
			Iterator<Object> iter = objects.iterator();
			while (iter.hasNext()) {
				pstmt.setObject(k++, iter.next());
			}
			LOG.info("Query: " + pstmt);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.PRODUCT_COUNT, ex);
			throw new DAOException(Messages.PRODUCT_COUNT, ex);
		} finally {
			close(rs);
		}
		return count;
	}

	private Product extractProduct(final ResultSet rs) throws SQLException {
		Product item = new Product();
		item.setId(rs.getLong(Fields.ENTITY_ID));
		item.setName(rs.getString((Fields.ENTITY_NAME)));
		item.setDescription(rs.getString(Fields.PRODUCT_DESCRIPTION));
		item.setPrice(rs.getBigDecimal(Fields.PRODUCT_PRICE));
		item.setImage(rs.getString(Fields.PRODUCT_IMAGE));
		item.setCatId(rs.getLong(Fields.PRODUCT_CATEGORY_ID));
		item.setBrandId(rs.getLong(Fields.PRODUCT_BRAND_ID));
		item.setAvailable(Status.getStatus(rs.getInt(Fields.PRODUCT_STATUS)));
		return item;
	}

}
