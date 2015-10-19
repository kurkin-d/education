package ru.dart.consumesproduces.dao;

import java.util.Collection;

import ru.dart.consumesproduces.model.Address;
import ru.dart.consumesproduces.model.Consumer;
import ru.dart.consumesproduces.model.Producer;

public interface IDAO {

    public Collection<Address> getAllAddress() throws DaoException;

    public Collection<String> getRegionsNames() throws DaoException;

    public Collection<Producer> getProducerByRegion(String regionCode)
	    throws DaoException;

    public Collection<Consumer> getConsumerByRegion(String regionCode)
	    throws DaoException;
}
