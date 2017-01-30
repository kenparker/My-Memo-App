package com.maggioni.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"ReadAllParams"})
public class ReadAllParamsServlet extends HttpServlet {

    PrintWriter out;

    @Override
    protected void doPost(HttpServletRequest response, HttpServletResponse request) throws ServletException, IOException {
        doGet(response, request);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = parseHeader(response);

        Enumeration paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            parseRowName(paramName);

            String[] paramValues = request.getParameterValues(paramName);
            parseParameterValues(paramValues);
        }

        parseFooter(out);
    }

    private void parseRowName(String paramName) {
        out.print("<tr><td>" + paramName + "</td>\n<td>");
    }

    private void parseFooter(PrintWriter out) {
        out.println("</tr>\n</table>\n</body></html>");
    }

    private void parseParameterValues(String[] paramValues) {
        if (paramValues.length == 1) {
            parameterHasOnlyOneValue(paramValues);
        } else {
            parameterHasMoreThenOneValue(paramValues);
        }
    }

    private void parameterHasMoreThenOneValue(String[] paramValues) {
        // Read multiple valued data
        out.println("<ul>");

        for (int i = 0; i < paramValues.length; i++) {
            out.println("<li>" + paramValues[i]);
        }

        out.println("</ul>");
    }

    private void parameterHasOnlyOneValue(String[] paramValues) {
        String paramValue = paramValues[0];
        if (paramValue.length() == 0) {
            out.println("<i>No Value</i>");
        } else {
            out.println(paramValue);
        }
    }

    private PrintWriter parseHeader(HttpServletResponse response) throws IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Reading All Form Parameters";
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
