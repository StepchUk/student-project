package loc.stalex.city.web;

import loc.stalex.city.dao.PersonCheckDao;
import loc.stalex.city.domain.PersonRequest;
import loc.stalex.city.domain.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckPersonServlet.class);

    PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        LOGGER.info("SERVLET is created");
        dao = new PersonCheckDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String surName = req.getParameter("surName");

        PersonRequest pr = new PersonRequest();
        pr.setSurName(surName);
        pr.setGivenName("Pavel");
        pr.setPatronymic("Nikolaevich");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        try {
            PersonResponse ps = dao.checkPerson(pr);

            if (ps.isRegistered()) {
                resp.getWriter().println("Registered");
            } else {
                resp.getWriter().println("Not registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
