package ru.dart.consumesproduces.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = { "/index.jsp" }, loadOnStartup = 1)
public class MainFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MainFormServlet() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
	    throws ServletException, IOException {
	List<String> regions = new ArrayList<String>();
	regions.add("Moscow");
	regions.add("Voronezh");
	req.setAttribute("regions", regions);
	req.getRequestDispatcher("/pages/MainForm.jsp").forward(req, res);
    }

}
