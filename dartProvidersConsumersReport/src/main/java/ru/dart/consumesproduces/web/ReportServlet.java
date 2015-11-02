package ru.dart.consumesproduces.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ru.dart.consumesproduces.dao.DaoException;
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
	try {
	    Collection<ReportRecord> report = new ArrayList<ReportRecord>();
	    for (String region : req.getParameterValues("region")) {
		report.addAll(service.createReportByRegion(region));
	    }
	    req.setAttribute("reportData", report);
	    req.getRequestDispatcher("/pages/ReportForm.jsp")
		    .forward(req, resp);
	} catch (DaoException e) {
	    req.setAttribute("exception", e);
	    req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
	}
    }
}
