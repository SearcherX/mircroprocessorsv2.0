package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.model.DBOrderControl;
import homework.mircroprocessorsv2.model.Microprocessor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int microprocessorId = Integer.parseInt(request.getParameter("id"));
        Microprocessor microprocessor = new DBOrderControl().getMicroprocessorById(microprocessorId);
        request.setAttribute("microprocessor", microprocessor);
        request.setAttribute("action", "update");
        getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("данные получены");
        System.out.println(request.getParameter("clockSpeeds"));
        Microprocessor microprocessor = new Microprocessor();
        microprocessor.setId(Integer.parseInt(request.getParameter("id")));
        microprocessor.setModel(request.getParameter("model"));
        microprocessor.setDataBitDepth(Integer.parseInt(request.getParameter("dataBitDepth")));
        microprocessor.setAddressBitDepth(Integer.parseInt(request.getParameter("addressBitDepth")));
        microprocessor.setClockSpeeds(request.getParameter("clockSpeeds"));
        microprocessor.setAddressSpaces(Long.parseLong(request.getParameter("addressSpaces")));

        String numberOfCommandsStr = request.getParameter("numberOfCommands");
        microprocessor.setNumberOfCommands(numberOfCommandsStr.equals("") ? null :
                Integer.parseInt(numberOfCommandsStr));

        microprocessor.setNumberOfElements(Integer.parseInt(request.getParameter("numberOfElements")));
        microprocessor.setReleaseYear(Integer.parseInt(request.getParameter("releaseYear")));

        new DBOrderControl().updateMicroprocessor(microprocessor);
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
