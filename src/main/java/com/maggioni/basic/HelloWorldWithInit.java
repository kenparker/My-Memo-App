package com.maggioni.basic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("HelloWorldWithInit")
public class HelloWorldWithInit extends HttpServlet{

    private String message;
    
    @Override
    public void init() throws ServletException {
        System.out.println("init method");
        message = "Hello World äööö";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        
        PrintWriter out = resp.getWriter();
        out.print("<h1>" +  message + "</h1>");
    }
       
    @Override
    public void destroy() {
    
    }
    
    
    
    
}
