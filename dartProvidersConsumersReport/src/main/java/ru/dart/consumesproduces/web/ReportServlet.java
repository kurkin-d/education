package ru.dart.consumesproduces.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ru.dart.consumesproduces.model.IReportService;
import ru.dart.consumesproduces.model.ReportService;

@WebServlet(urlPatterns = { "/report" }, loadOnStartup = 1)
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IReportService service;

    public ReportServlet() throws NamingException, SQLException {
	service = new ReportService();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
	    throws ServletException, IOException {
	resp.getWriter().print("<html>");
	resp.getWriter().print("<body>hello</body>");
	resp.getWriter().print("</html>");
    }

}
