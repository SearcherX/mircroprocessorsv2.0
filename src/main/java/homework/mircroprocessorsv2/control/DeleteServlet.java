package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.model.DBOrderControl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int microprocessorId = Integer.parseInt(request.getParameter("id"));
        new DBOrderControl().deleteMicroprocessorById(microprocessorId);
        response.sendRedirect(request.getContextPath() + "/index");
//        getServletContext().getRequestDispatcher("/index").forward(request, response);
    }
}
