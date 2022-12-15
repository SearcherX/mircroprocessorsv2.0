package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.datasource.DBMicroprocessorDataSource;
import homework.mircroprocessorsv2.datasource.DataSourceFactory;
import homework.mircroprocessorsv2.datasource.MicroprocessorDataSource;
import homework.mircroprocessorsv2.datasource.model.Microprocessor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "CreateServlet", value = "/CreateServlet")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "create");
        getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String model = request.getParameter("model");
            int dataBitDepth = Integer.parseInt(request.getParameter("dataBitDepth"));
            int addressBitDepth = Integer.parseInt(request.getParameter("addressBitDepth"));
            String clockSpeeds = request.getParameter("clockSpeeds");
            long addressSpaces = Long.parseLong(request.getParameter("addressSpaces"));
            String numberOfCommandsStr = request.getParameter("numberOfCommands");
            Integer numberOfCommands = numberOfCommandsStr.equals("") ? null : Integer.parseInt(numberOfCommandsStr);
            int numberOfElements = Integer.parseInt(request.getParameter("numberOfElements"));
            int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));

            Microprocessor microprocessor = new Microprocessor();
            microprocessor.setModel(model);
            microprocessor.setDataBitDepth(dataBitDepth);
            microprocessor.setAddressBitDepth(addressBitDepth);
            microprocessor.setAddressSpaces(addressSpaces);
            microprocessor.setNumberOfCommands(numberOfCommands);
            microprocessor.setNumberOfElements(numberOfElements);
            microprocessor.setReleaseYear(releaseYear);
            microprocessor.setClockSpeeds(clockSpeeds);

            DataSourceFactory factory = new DataSourceFactory();
            MicroprocessorDataSource dataSource = factory.getDataSource();

            dataSource.saveMicroprocessor(microprocessor);

            response.sendRedirect(request.getContextPath() + "/index");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
