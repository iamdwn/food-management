<%-- 
    Document   : admin
    Created on : Jun 25, 2023, 11:36:48 PM
    Author     : truon
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.food"%>
<%@page import="DAO.dbDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <style>
        .thongbao{
            color: red;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
        }

        .list_form{
            align-items: center;
            justify-content: space-around;
        }

    </style>
    <body>
        <%
            String display_hide = (String) session.getAttribute("display_hide");
            String user = (String) session.getAttribute("login_user");
            if (user != null) {
                out.print("<h3>Welcome,</h3>" + user);
            } else {
                out.print("<h3>Welcome,</h3>" + "guest");
            }
        %>
        <form>
            <input type="submit" name="myBtn" value="Logout">
        </form></br></br>
        <form action="MainController" >
            <input type="submit" id="myBtn" name="myBtn" value=<%=(display_hide)%>></br></br></br>
        </form>
        <div class="list_form">
            <div>
                <form class="form" action="MainController">
                    <h4>FORM INSERT</h4>
                    ID:<input  type="text" name="id" required=""></br>
                    Name:<input type="text" name="name" required=""></br>
                    Description:<input type="text" name="desp" required=""></br>
                    Price:<input type="number" name="price" min="0" required=""></br>
                    Cooking Time:<input type="number" name="time" min="0" required="">
                    <input type="hidden" name="status" value="1"></br></br>
                    <input type="submit" id="myBtn" name="myBtn" value="Insert">
                    </br></br>
                </form>
                <div class="thongbao">
                    <%
                        Object alert = request.getAttribute("food_alert");
                        if (alert == null) {
                            out.print("");
                        } else {
                            out.print(alert);
                        }
                    %>
                </div>    
            </div>

            <div>
                <%
                    String show = (String) session.getAttribute("show_list");
                    if (show == "1") {
                %>
                <h3>Foods List</h3>
                <table>
                    <thead>
                    <th>id</th>
                    <th>name</th> 
                    <th>description</th>
                    <th>price</th>
                    <th>cookingTime</th>
                    <th>status</th>
                        <%
                            dbDAO db = new dbDAO();
                            List<food> list = new ArrayList<>();
                            list = db.getListFoods();
                            for (food f_list : list) {
                        %>
                    <tbody>
                    <td id="id" name="id"><%=f_list.getId()%></td>
                    <td id="name" name="name"><%=f_list.getName()%></td>
                    <td id="desp" name="desp"><%=f_list.getDescription()%></td>
                    <td id="price" name="price"><%=f_list.getPrice()%></td>
                    <td id="time" name="time"><%=f_list.getCookingTime()%></td>
                    <td id="status" name="status"><%=f_list.getStatus()%></td>
                    </tbody>
                    <%
                        }
                    %> 
                </table>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
