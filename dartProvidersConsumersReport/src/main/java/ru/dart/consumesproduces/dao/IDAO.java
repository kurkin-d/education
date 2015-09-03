package ru.dart.consumesproduces.dao;

import java.util.Collection;

import ru.dart.consumesproduces.model.Address;
import ru.dart.consumesproduces.model.Consumer;
import ru.dart.consumesproduces.model.Producer;

public interface IDAO {

    public Collection<Address> getAllAddress();

    public Collection<Producer> getProducerByRegion(String regionCode);

    public Collection<Consumer> getConsumerByRegion(String regionCode);
}
