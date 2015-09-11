package ru.dart.consumesproduces.dao;

import java.sql.Connection;
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

    public DaoImpl() throws NamingException, SQLException {
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	System.out.println("добыли контекст");
	// Look up our data source
	DataSource ds = (DataSource) envCtx.lookup("jdbc/postgres");
	System.out.println("добыли дата сурс");
	// Allocate and use a connection from the pool
	Connection conn = ds.getConnection();
	System.out.println("добыли подключение");
	conn.close();
    }

    public Collection<Producer> getProducerByRegion(String regionCode) {
	// TODO
	List<Offerts> offerts = new ArrayList<Offerts>();
	offerts.add(new Offerts(new Product("мясо"), 150));
	offerts.add(new Offerts(new Product("молоко"), 2000));
	List<Producer> tmpResult = new ArrayList<Producer>();
	tmpResult.add(new Producer(
		new Address(regionCode, "town", "street", 10), "ИП ГАЗМЯС",
		offerts));
	tmpResult.add(new Producer(
		new Address(regionCode, "town", "street", 10), "ИП ГАЗМЯС",
		offerts));
	return tmpResult;
    }

    public Collection<Consumer> getConsumerByRegion(String regionCode) {
	// TODO
	List<Needs> needs = new ArrayList<Needs>();
	needs.add(new Needs(new Product("мясо"), 150));
	needs.add(new Needs(new Product("молоко"), 2000));
	List<Consumer> tmpResult = new ArrayList<Consumer>();
	tmpResult.add(new Consumer("магаз у петровича", new Address(regionCode,
		"town", "street", 7), needs));
	tmpResult.add(new Consumer("магаз у петровича", new Address(regionCode,
		"town", "street", 7), needs));
	return tmpResult;
    }

    public Collection<Address> getAllAddress() {
	// TODO
	List<Address> tmpResult = new ArrayList<Address>();
	tmpResult.add(new Address("Moscow", "town", "street", 10));
	tmpResult.add(new Address("Moscow", "town", "street", 7));
	tmpResult.add(new Address("Voronezh", "town", "street", 10));
	tmpResult.add(new Address("Voronezh", "town", "street", 7));
	return tmpResult;
    }

}
