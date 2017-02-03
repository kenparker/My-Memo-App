package com.maggioni.basic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"CheckBoxServlet"})
public class CheckBoxServlet extends HttpServlet {
    private String parameterChemistry;
    private String parameterPhysics;
    private String parameterMaths;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        parameterMaths = request.getParameter("maths");
        parameterPhysics = request.getParameter("physics");
        parameterChemistry = request.getParameter("chemistry");
        
        formatHTMLSite(response);
    }

    private void formatHTMLSite(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Reading Checkbox Data";

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
                + "transitional//en\">\n";

        out.println(docType + "<html>\n"
                + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n"
                + "<h1 align=\"center\">" + title + "</h1>\n"
                + "<ul>\n"
                + "  <li><b>Maths Flag : </b>: "
                + parameterMaths + "\n"
                + "  <li><b>Physics Flag: </b>: "
                + parameterPhysics + "\n"
                + "  <li><b>Chemistry Flag: </b>: "
                + parameterChemistry + "\n"
                + "</ul>\n"
                + "</body></html>");
    }

}
