package br.edu.ifpb.web;


import br.edu.ifpb.domain.Campeoes;
import br.edu.ifpb.infra.CampeoesJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControladorDeCampeoes",urlPatterns = {"/"})
public class ControladorDeCampeoes extends HttpServlet {

    private final Campeoes service = new CampeoesJDBC();

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Melhores campeões do LOL</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> </h1>");
            out.println("<ol>");
            listarCampeoes(out);
            out.println("</ol>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void listarCampeoes(final PrintWriter out) {
        this.service
                .listar()
                .forEach(c->
                        out.println("<li>" + c.getNome() + "</li>")
                );
    }

}