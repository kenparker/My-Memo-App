
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maggioni.mymemo.model.Memo" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My-Memo</title>
        <link rel="stylesheet" type="text/css" href="screen.css">
    </head>
    <body>
        <div id="container">
            <div id="header">
                <p>
                    <b>My-Memo</b> – A Simple Web App for Managing Memos
                </p>
            </div>
            <div id="content">
                <h1>Add Memo:</h1>

                <form method="POST" action="memos">
                    <input type="text" name="memo" size="30" placeholder="Enter your memo here"/>
                    <button type="submit" name="button" value="save">Add</button>
                    <% if (request.getAttribute("err") != null) {%>
                    <span style="color: red"><%= request.getAttribute("err")%></span>
                    <% } %>
                    <h1>My Memos:</h1>
                    <% if (session.getAttribute("memos") == null || ((List<Memo>) session.getAttribute("memos")).size() == 0) { %>
                    <p>Please add some memos.</p>
                    <% } else { %>
                    <table>
                        <tr>
                            <th>
                                Memo
                            </th>
                            <th>
                                Saved
                            </th>
                        </tr>
                        <% for (Memo memo : (List<Memo>) session.getAttribute("memos")) {%>
                        <tr>
                            <td>
                                <%= memo.getDescription()%>
                            </td>
                            <td>
                                <%= memo.getCreated()%>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                    <br/>
                    <button type="submit" name="button" value="reset">Reset list</button>
                    <% }%>
                </form>
            </div>
            <div id="footer">
                <p>(C) 2015 Schiesser/Schmollinger, MIT license</p>
            </div>
        </div>
    </body>
</html>
