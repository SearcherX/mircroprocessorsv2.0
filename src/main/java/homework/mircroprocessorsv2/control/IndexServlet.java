package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.datasource.DataSourceFactory;
import homework.mircroprocessorsv2.datasource.MicroprocessorDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = {"/index",""})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DataSourceFactory factory = new DataSourceFactory();
            MicroprocessorDataSource dataSource = factory.getDataSource();

            request.setAttribute("microprocessors", dataSource.getAllMicroprocessors());
            request.setAttribute("caption", "Выбрать все микропроцессоры");
            request.setAttribute("selectMode", "all");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
