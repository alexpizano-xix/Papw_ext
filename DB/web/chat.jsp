<%-- 
    Document   : chat
    Created on : 8/12/2019, 05:00:01 PM
    Author     : alexp
--%>

<%@page import="Models.messages"%>
<%@page import="Models.cart"%>
<%@page import="Models.User"%>
<%@page import="java.util.List"%>
<%@page import="Utils.RequestUtils"%>
<%@page import="Models.product"%>
<%@page import="javax.naming.Context"%>

<%
    List<product> products = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS);
    List<User> user2 = (List<User>) request.getAttribute(RequestUtils.KEY_ALL_USERS);
    List<User> user = (List<User>) request.getAttribute(RequestUtils.KEY_USER);
    List<cart> carts = (List<cart>) request.getAttribute(RequestUtils.KEY_ALL_CART);
    List<cart> carts2 = (List<cart>) request.getAttribute(RequestUtils.KEY_CART);
    List<messages> msgs = (List<messages>) request.getAttribute(RequestUtils.KEY_ALL_MESSAGE);
    String usn = request.getSession().getAttribute("usn").toString();
    String id = request.getSession().getAttribute("id").toString();
    String usn2 = request.getSession().getAttribute("username").toString();
    int newId = Integer.parseInt(id);
    String waiting = "SI";
    int totalPrice = 0;
    String readyToBuy = "SI";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Marktred</title>
        <link href="css/style.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <header>
            <%
                if (usn == "si") {
            %>
            <nav class="navbar navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navegacion">
                            <span class="sr-only">Desplegar / ocultar</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>      
                        </button>
                        <a href="#" class="navbar-brand">Marktred</a>
                    </div>
                    <div class="collapse navbar-collapse" id="navegacion">
                        <ul class="nav navbar-nav navbar-left">

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <button onclick="document.getElementById('Show').style.display = 'block'" class="Login">Login</button>

                            <form class="navbar-form navbar-right" action="search" method="POST">
                                <div class="input-group">
                                    <input type="text" placeholder="Search" class="form-control" name="seeker">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>>
                                    </div>
                                </div>
                            </form>
                        </ul>
                    </div>
                </div>
            </nav>
            <%
            } else {
            %>
            <nav class="navbar navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navegacion">
                            <span class="sr-only">Desplegar / ocultar</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>      
                        </button>
                        <a href="showProducts" class="navbar-brand">Marktred</a>
                    </div>
                    <div class="collapse navbar-collapse" id="navegacion">
                        <ul class="nav navbar-nav navbar-left">

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <%
                                for (User usuarios : user) {
                            %>
                            <img alt="brand" src="getImageUser?idUser=<%= usuarios.getIdUser()%>" width="50" height="50">

                            <a href="showProductsProfile"><button class="Login"><%= usn%></button></a>
                            <a href="index.jsp"><button class="Login">LogOut</button></a>
                            <button onclick="document.getElementById('ShowCart').style.display = 'block'" class="Cart"><i class="fa fa-shopping-cart"></i></button>
                                <%
                                    }
                                %>
                            <form class="navbar-form navbar-right" action="search" method="POST">
                                <div class="input-group">
                                    <input type="text" placeholder="Search" class="form-control" name="seeker">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>>
                                    </div>
                                </div>
                            </form>
                        </ul>

                    </div>
                </div>
            </nav>
            <%
                }
            %>
        </header>

        <div id="Show" class="modal">
            <form class="modal-content animate" method="POST" action="loggin" enctype="multipart/form-data">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('Show').style.display = 'none'" class="close" title="Close">&times;</span>
                </div>
                <div class="container">

                    <h1>Marktred</h1> 
                    <input type="text" placeholder="Enter username" id="us" name="username">

                    <input type="password" placeholder="Enter password" id="pass" name="pass">

                    <button class="btn" type="submit">Login</button><br>                   

                    <label class="RM">
                        <input type="checkbox" checked="checked" name="remember">Remember me
                    </label>
                </div>
                <div class="container New">
                    <span class="psw"><a href="newAccount.jsp">Create an account</a></span>
                </div>
            </form>
        </div>

        <%
            if (usn.equals("alexpizano")) {
        %>
        <div class="container-fluid" style="margin-top: 5%;">
            <div class="row">
                <div class="col-md-12 text-center">
                    <%
                        if (newId > 0) {
                    %>
                    <h1><img src="getImageUser?idUser=<%= newId%>" width="50" height="50"> <%= usn2%></h1></a>
                        <%
                        } else {
                        %>

                    <%
                        }
                    %>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <%
                        for (User usuarios : user2) {
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <a href="chatSession?id=<%= usuarios.getIdUser()%>&username=<%= usuarios.getUsername()%>" class="btn" style="width: 100%;"><h1><img src="getImageUser?idUser=<%= usuarios.getIdUser()%>" width="50" height="50"> <%= usuarios.getUsername()%></h1></a>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="col-md-6 text-center">
                    <div class="row">
                        <div class="col-md-12">

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 messages">
                            <%
                                for (messages allMsg : msgs) {
                                    if (allMsg.getIdUser() == newId) {
                                        if (allMsg.getIdSender() == 26) {
                            %>
                            <div class="row">
                                <div class="col-md-6 text-right">
                                </div>
                                <div class="col-md-6 text-right">
                                    <h3 style="background-color: white; color: #38ADAE; padding: 3% 3%;"><%= allMsg.getMessages()%></h3>
                                </div>
                            </div>
                            <%
                            } else {
                            %>
                            <div class="row">
                                <div class="col-md-6 text-left">
                                    <h3 style="background-color: #CD295A; color: white; padding: 3% 3%;"><%= allMsg.getMessages()%></h3>
                                </div>
                                <div class="col-md-6 text-right">
                                </div>
                            </div>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                    </div>
                    <div class="row">
                        <form method="POST" action="setMessage?idAdmi=26&idUn=<%= newId%>&idSender=26" enctype="multipart/form-data">                           
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-8 text-right">
                                <input type="text" class="form-control" name="msg">
                            </div>
                            <div class="col-md-2 text-left">
                                <button type="submit" class="btn">Send Message!</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-3 text-center">
                    <div class="row">
                        <div class="col-md-12">
                            <h1>PRODUCTS</h1>
                        </div>
                    </div>
                    <%
                        for (cart carro : carts) {
                            if (carro.getStatus().equals("stored") || carro.getStatus().equals("waiting")) {
                                for (product Producto : products) {
                                    if (carro.getIdProduct() == Producto.getIdproduct()) {
                                        if (carro.getIdUser() == newId) {
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <form method="POST" action="setPrice?idCart=<%= carro.getIdCart()%>" enctype="multipart/form-data">
                                <h1><%= Producto.getProductName()%></h1>
                                <input type="text" value="<%= carro.getPrice()%>" name="SP">
                                <button class="btn" type="submit">Set Price!</button>
                            </form>
                        </div>
                    </div>
                    <%
                                        }
                                    }
                                }
                            }
                        }
                    %>
                    <%
                        if (newId > 0) {
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn" style="margin-top: 15%" href="changeStatusCart?idUn=<%= newId%>&opcion=1">Send budget!</a>
                        </div>
                    </div>
                    <%
                    } else {
                    %>

                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <%        } else {
        %>
        <div class="container-fluid" style="margin-top: 5%;">
            <div class="row">
                <div class="col-md-10 text-center">
                    <h1><img src="getImageUser?idUser=26" width="100" height="100"> Alex Pizano</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9 text-center">
                    <div class="row">
                        <div class="col-md-12">

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 messages">
                            <%
                                for (cart carro : carts2) {
                                    newId = carro.getIdUser();
                                }
                                for (messages allMsg : msgs) {
                                    if (allMsg.getIdUser() == newId) {
                                        if (allMsg.getIdSender() == 26) {
                            %>
                            <div class="row">
                                <div class="col-md-6 text-left">
                                    <h3 style="background-color: #CD295A; color: white; padding: 3% 3%;"><%= allMsg.getMessages()%></h3>
                                </div>
                                <div class="col-md-6 text-right">
                                </div>
                            </div>
                            <%
                            } else {
                            %>
                            <div class="row">
                                <div class="col-md-6 text-right">
                                </div>
                                <div class="col-md-6 text-right">
                                    <h3 style="background-color: white; color: #38ADAE; padding: 3% 3%;"><%= allMsg.getMessages()%></h3>
                                </div>
                            </div>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                    </div>
                    <div class="row">
                        <%
                            for (cart carro : carts2) {
                                newId = carro.getIdUser();
                            }
                        %>
                        <form method="POST" action="setMessage?idAdmi=26&idUn=<%= newId%>&idSender=<%= newId%>" enctype="multipart/form-data">                           
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-8 text-right">
                                <input type="text" class="form-control" name="msg">
                            </div>
                            <div class="col-md-2 text-left">
                                <button type="submit" class="btn">Send Message!</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-3 text-center">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <h1>PRODUCTS</h1>
                        </div>
                    </div>
                    <%
                        for (cart carro : carts) {
                            if (carro.getStatus().equals("stored") || carro.getStatus().equals("waiting")) {
                                for (product Producto : products) {
                                    if (carro.getIdProduct() == Producto.getIdproduct()) {
                                        for (User usuarios : user) {
                                            if (carro.getIdUser() == usuarios.getIdUser()) {
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <h1><%= Producto.getProductName()%></h1>
                            <input type="text" value="<%= carro.getPrice()%>" name="SP" class="text-center" disabled="">
                        </div>
                    </div>
                    <%
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    %>
                    <div class="row">
                        <div class="col-md-12" style="margin-top: 10%">
                            <%
                                for (cart carro : carts2) {
                                    newId = carro.getIdUser();
                                    if (carro.getStatus().equals("stored") || carro.getStatus().equals("Accept")) {
                                        waiting = "NO";
                                    }
                                }
                                if (waiting.equals("NO")) {
                            %>
                            <%
                            } else {
                            %>
                            <h6>Would you like to accept the offer?</h6>
                            <a class="btn" href="changeStatusCart?idUn=<%= newId%>&opcion=2">ACCEPT</a>
                            <a class="btn" href="changeStatusCart?idUn=<%= newId%>&opcion=3">REFUSE</a>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>

    </body>
</html>
