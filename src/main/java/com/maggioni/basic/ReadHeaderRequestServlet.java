package com.maggioni.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"ReadHeaderRequest"})
public class ReadHeaderRequestServlet extends HttpServlet {

    PrintWriter out;

    @Override
    protected void doPost(HttpServletRequest response, HttpServletResponse request) throws ServletException, IOException {
        doGet(response, request);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = parseHeader(response);

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String paramName = (String) headerNames.nextElement();
            String paramValue = request.getHeader(paramName);
            parseParameterNameAndValue(paramName, paramValue);
        }

        parseFooter();
    }

    private void parseParameterNameAndValue(String paramName, String paramValue) {
        out.print("<tr><td>" + paramName + "</td>\n");
        out.println("<td> " + paramValue + "</td></tr>\n");
    }

    private void parseFooter() {
        out.println("</tr>\n</table>\n</body></html>");
    }

    private PrintWriter parseHeader(HttpServletResponse response) throws IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Reading HEADER Request Parameters";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
                + "transitional//en\">\n";
        out.println(docType + "<html>\n"
                + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n"
                + "<h1 align=\"center\">" + title + "</h1>\n"
                + "<table width=\"100%\" border=\"1\" align=\"center\">\n"
                + "<tr bgcolor=\"#949494\">\n"
                + "<th>Param Name</th><th>Param Value(s)</th>\n"
                + "</tr>\n");
        return out;
    }

}
