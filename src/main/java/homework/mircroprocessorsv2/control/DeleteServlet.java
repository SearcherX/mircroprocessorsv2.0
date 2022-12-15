package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.datasource.DataSourceFactory;
import homework.mircroprocessorsv2.datasource.MicroprocessorDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int microprocessorId = Integer.parseInt(request.getParameter("id"));
            DataSourceFactory factory = new DataSourceFactory();
            MicroprocessorDataSource dataSource = factory.getDataSource();

            dataSource.deleteMicroprocessorById(microprocessorId);

            response.sendRedirect(request.getContextPath() + "/index");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
