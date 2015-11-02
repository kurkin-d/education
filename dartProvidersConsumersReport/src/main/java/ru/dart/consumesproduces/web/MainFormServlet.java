package ru.dart.consumesproduces.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ru.dart.consumesproduces.dao.DaoException;
import ru.dart.consumesproduces.dao.DaoImpl;
import ru.dart.consumesproduces.dao.IDAO;

@WebServlet(urlPatterns = { "/index.jsp" }, loadOnStartup = 1)
public class MainFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IDAO dao;

    public MainFormServlet() throws NamingException, SQLException {
	dao = new DaoImpl();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
	    throws ServletException, IOException {
	try {
	    req.setAttribute("regions", dao.getRegionsNames());
	    req.getRequestDispatcher("/pages/MainForm.jsp").forward(req, res);
	} catch (DaoException e) {
	    req.setAttribute("exception", e);
	    req.getRequestDispatcher("/pages/error.jsp").forward(req, res);
	}

    }

}
