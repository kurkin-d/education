package ru.dart.consumesproduces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ru.dart.consumesproduces.model.Address;
import ru.dart.consumesproduces.model.Consumer;
import ru.dart.consumesproduces.model.Needs;
import ru.dart.consumesproduces.model.Offerts;
import ru.dart.consumesproduces.model.Producer;
import ru.dart.consumesproduces.model.Product;

public class DaoImpl implements IDAO {

    private DataSource ds;

    public DaoImpl() throws NamingException, SQLException {
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	System.out.println("добыли контекст");
	ds = (DataSource) envCtx.lookup("jdbc/providers_and_consumers");
	System.out.println("добыли дата сурс");
	Connection conn = ds.getConnection();
	System.out.println("добыли подключение");
	conn.close();
    }

    @Override
    public Collection<Producer> getProducerByRegion(String regionCode)
	    throws DaoException {
	List<Producer> result = new ArrayList<Producer>();
	try (Connection conn = ds.getConnection();) {
	    PreparedStatement st = conn
		    .prepareStatement("select producer.id, region, town, street, \"houseNumber\", title  from producer join address "
			    + "on producer.addressid = address.id where region like ?");
	    st.setString(1, regionCode);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
		Producer pr = new Producer(new Address(rs.getString("region"),
			rs.getString("town"), rs.getString("street"),
			rs.getInt("houseNumber")), rs.getString("title"));
		pr.setOfferts(getOfferts(conn, rs.getLong("id")));
		result.add(pr);
	    }
	} catch (SQLException e) {
	    throw new DaoException("НЕ удалось выбрать производителей", e);
	}
	return result;
    }

    private Collection<Offerts> getOfferts(Connection conn, long producerId)
	    throws SQLException {
	List<Offerts> result = new ArrayList<Offerts>();
	PreparedStatement ps = conn
		.prepareStatement("select * from (offerts join product on "
			+ "offerts.productId = product.id) where offerts.producerid = ?");
	ps.setLong(1, producerId);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    result.add(new Offerts(new Product(rs.getString("title")), rs
		    .getInt("count")));
	}
	return result;
    }

    private Collection<Needs> getNeeds(Connection conn, long consumerId)
	    throws SQLException {
	List<Needs> result = new ArrayList<Needs>();
	PreparedStatement ps = conn
		.prepareStatement("select * from (needs join product on "
			+ "needs.productId = product.id) where needs.consumerid = ?");
	ps.setLong(1, consumerId);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    result.add(new Needs(new Product(rs.getString("title")), rs
		    .getInt("count")));
	}
	return result;
    }

    @Override
    public Collection<Consumer> getConsumerByRegion(String regionCode)
	    throws DaoException {
	List<Consumer> result = new ArrayList<Consumer>();
	try (Connection conn = ds.getConnection();) {
	    PreparedStatement st = conn
		    .prepareStatement("select consumer.id, region, town, street, \"houseNumber\", title  from consumer join address "
			    + "on consumer.addressid = address.id where region like ?");
	    st.setString(1, regionCode);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {

		Consumer cons = new Consumer(rs.getString("title"),
			new Address(rs.getString("region"),
				rs.getString("town"), rs.getString("street"),
				rs.getInt("houseNumber")));
		cons.setNeeds(getNeeds(conn, rs.getLong("id")));
		result.add(cons);
	    }
	} catch (SQLException e) {
	    throw new DaoException("НЕ удалось выбрать потребителей", e);
	}
	return result;
    }

    @Override
    public Collection<Address> getAllAddress() throws DaoException {
	List<Address> result = new ArrayList<Address>();
	try (Connection conn = ds.getConnection();) {
	    ResultSet resultSet = conn.createStatement().executeQuery(
		    "select * from address");
	    while (resultSet.next()) {
		String region = resultSet.getString("region");
		String town = resultSet.getString("town");
		String street = resultSet.getString("street");
		Integer houseNumber = resultSet.getInt("houseNumber");
		result.add(new Address(region, town, street, houseNumber));
	    }

	} catch (SQLException e) {

	    throw new DaoException("НЕ удалось выбрать список регионов", e);
	}
	return result;
    }

    @Override
    public Collection<String> getRegionsNames() throws DaoException {
	List<String> result = new ArrayList<>();
	try (Connection conn = ds.getConnection();) {
	    ResultSet resultSet = conn.createStatement().executeQuery(
		    "select distinct region from address");
	    while (resultSet.next()) {
		String region = resultSet.getString("region");
		result.add(region);
	    }
	} catch (SQLException e) {

	    throw new DaoException("НЕ удалось выбрать список регионов", e);
	}
	return result;
    }

}
