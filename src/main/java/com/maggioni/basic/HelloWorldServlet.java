package com.maggioni.basic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"HelloWorld", "Hello"})
public class HelloWorldServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
	
	writer.println("<html>");
	writer.println("<head><title>Hello World Servlet</title></head>");
	writer.println("<body>");
	writer.println("	<h1>Hello World from a Sevlet!</h1>");
	writer.println("<body>");
	writer.println("</html>");
		
	writer.close();
    }
    
}
