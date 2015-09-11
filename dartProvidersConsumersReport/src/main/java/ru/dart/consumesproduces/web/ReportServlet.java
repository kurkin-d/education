package ru.dart.consumesproduces.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ru.dart.consumesproduces.model.IReportService;
import ru.dart.consumesproduces.model.ReportRecord;
import ru.dart.consumesproduces.model.ReportService;

@WebServlet(urlPatterns = { "/report" }, loadOnStartup = 2)
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IReportService service;

    public ReportServlet() throws NamingException, SQLException {
	service = new ReportService();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
	    throws ServletException, IOException {
	System.out.println(Arrays.toString(req.getParameterValues("region")));
	String region = req.getParameter("region");

	Collection<ReportRecord> report = service.createReportByRegion(region);

	// List<ReportRecord> report = new ArrayList<ReportRecord>();
	// report.add(new ReportRecord(region, new Product("мясо"), new
	// Producer(
	// new Address(region, "town", "street", 10), "ИП ГАЗМЯС"),
	// new Consumer("магаз у петровича", new Address(region, "town",
	// "street", 7))));
	// report.add(new ReportRecord(region, new Product("молоко"),
	// new Producer(new Address(region, "town", "street", 10),
	// "ИП ГАЗМЯС"), new Consumer("магаз у петровича",
	// new Address(region, "town", "street", 7))));
	req.setAttribute("reportData", report);
	req.getRequestDispatcher("/pages/ReportForm.jsp").forward(req, resp);
    }
}
