package ru.dart.consumesproduces.model;

import java.util.Collection;

import ru.dart.consumesproduces.dao.DaoException;

public interface IReportService {
    public Collection<ReportRecord> createReportByRegion(String region)
	    throws DaoException;
}
