package ru.dart.consumesproduces.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ru.dart.consumesproduces.model.Address;
import ru.dart.consumesproduces.model.Consumer;
import ru.dart.consumesproduces.model.Producer;

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
	// TODO Auto-generated method stub
	return null;
    }

    public Collection<Consumer> getConsumerByRegion(String regionCode) {
	// TODO Auto-generated method stub
	return null;
    }

    public Collection<Address> getAllAddress() {
	// TODO Auto-generated method stub
	return null;
    }

}
