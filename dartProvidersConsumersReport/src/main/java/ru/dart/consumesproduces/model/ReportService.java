package ru.dart.consumesproduces.model;

import java.sql.SQLException;
import java.util.Collection;

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
	for (Consumer cons : consumers) {
	    for (Offerts offert : cons.getOfferts()) {
		Producer producer = findProductProducer(producers,
			offert.getProduct());
		ReportRecord rec = new ReportRecord();
	    }
	}
	return null;
    }

    private Producer findProductProducer(Collection<Producer> producers,
	    Product product) {

	return null;
    }

}
