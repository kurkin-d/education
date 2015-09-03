package ru.dart.consumesproduces.model;

import java.util.Collection;

public interface IReportService {
    public Collection<ReportRecord> createReportByRegion(String region);
}
