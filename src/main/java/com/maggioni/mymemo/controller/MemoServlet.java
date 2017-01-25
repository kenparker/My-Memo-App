package com.maggioni.mymemo.controller;

import com.maggioni.mymemo.model.Memo;
import com.maggioni.mymemo.view.MemoViewRenderer;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MemoServlet", urlPatterns = {"/memos"})
public class MemoServlet extends HttpServlet {

    private static final long serialVersionUID = -7843898075264520941L;
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendResponse(request, response);
    }

    private void sendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getOutputStream().print(MemoViewRenderer.renderResponse(Collections.<Memo>emptyList(), null));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String button = request.getParameter("button");
        switch (button) {
            case "reset":
                actionReset(request);
                break;
            case "save":
                actionAddMemo(request);
                break;
            default:
        }
        sendResponse(request, response);

    }

    private void actionReset(HttpServletRequest request) {
        System.out.println("");
    }

    private void actionAddMemo(HttpServletRequest request) {
        System.out.println("");
    }

}
