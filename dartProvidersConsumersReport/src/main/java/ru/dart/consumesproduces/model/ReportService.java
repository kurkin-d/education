package ru.dart.consumesproduces.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import ru.dart.consumesproduces.dao.DaoImpl;
import ru.dart.consumesproduces.dao.IDAO;

public class ReportService implements IReportService {

    private IDAO dao;

    public ReportService() throws NamingException, SQLException {
	dao = new DaoImpl();
    }

    public Collection<ReportRecord> createReportByRegion(String region) {
	Collection<Consumer> consumers = dao.getConsumerByRegion(region);
	Collection<Producer> producers = dao.getProducerByRegion(region);
	List<ReportRecord> results = new ArrayList<ReportRecord>();
	for (Consumer cons : consumers) {
	    for (Needs need : cons.getNeeds()) {
		Collection<Producer> suitableProducers = findProductProducers(
			producers, need.getProduct());
		for (Producer prod : suitableProducers) {
		    results.add(new ReportRecord(region, need.getProduct(),
			    prod, cons));
		}
	    }
	}
	return results;
    }

    private Collection<Producer> findProductProducers(
	    Collection<Producer> producers, Product product) {
	Collection<Producer> result = new ArrayList<Producer>();
	for (Producer producer : producers) {
	    if (!producer.getOffertsByProduct(product).isEmpty()) {
		result.add(producer);
	    }
	}
	return result;
    }

}
