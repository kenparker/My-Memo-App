package com.maggioni.mymemo.controller;

import com.maggioni.mymemo.model.Memo;
import com.maggioni.mymemo.view.MemoViewRenderer;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String err = (String)request.getAttribute("err");
        List<Memo> memos = getMemos(request);
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

    private synchronized void actionReset(HttpServletRequest request) {
        System.out.println("actionReset called");
        List<Memo> memos = getMemos(request);
        memos.clear();
    }

    private List<Memo> getMemos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Memo> memos = (List<Memo>)session.getAttribute("memos");
        if (memos == null) {
            memos = new LinkedList<>();
            session.setAttribute("memos", memos);
        }
        return memos;
    }
    
    private synchronized void actionAddMemo(HttpServletRequest request) {
        System.out.println("actionAddMemo called");
        String memoDescr = request.getParameter("memo");
        if (memoDescrIsFilled(memoDescr)) {
            Memo memo = createMemo(memoDescr);
            addMemoToMemos(request, memo);
        } else {
            request.setAttribute("err", "Please enter a Memo");
        }
    }

    private void addMemoToMemos(HttpServletRequest request, Memo memo) {
        List<Memo> memos = getMemos(request);
        memos.add(memo);
    }

    private boolean memoDescrIsFilled(String memoDescr) {
        return (memoDescr != null && !memoDescr.isEmpty());
    }

    private Memo createMemo(String memoDescr) {
        Memo memo = new Memo();
        memo.setDescription(memoDescr);
        memo.setCreated(new Date());
        return memo;
    }


}
