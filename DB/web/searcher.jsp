<%-- 
    Document   : searcher
    Created on : 10/12/2019, 03:15:38 AM
    Author     : alexp
--%>

<%@page import="Models.cart"%>
<%@page import="Models.User"%>
<%@page import="java.util.List"%>
<%@page import="Utils.RequestUtils"%>
<%@page import="Models.product"%>
<%@page import="javax.naming.Context"%>

<%
    List<product> products = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS);
    List<product> products2 = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS_BY_TEXT);
    List<product> productsRate = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS_RATE);
    List<product> productsViews = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS_VIEWS);
    List<User> user = (List<User>) request.getAttribute(RequestUtils.KEY_USER);
    List<cart> carts = (List<cart>) request.getAttribute(RequestUtils.KEY_CART);
    String usn = request.getSession().getAttribute("usn").toString();
    String sWord = request.getSession().getAttribute("sWord").toString();
    String readyToBuy = "SI";
    int newId = 0;
    int totalPrice = 0;
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

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
                        <a href="showProducts" class="navbar-brand">Marktred</a>
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
            if (usn == "si") {
        %>
        <%
        } else {
        %>
        <div id="ShowCart" class="modal container-fluid">
            <form class="modal-content animate">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('ShowCart').style.display = 'none'" class="close" title="Close">&times;</span>
                </div>

                <h1 style="color: white">your shopping cart!</h1>
                <%
                    for (cart carro : carts) {
                        if (carro.getVerified().equals("no")) {
                            newId = carro.getIdUser();
                            if (carro.getStatus().equals("waiting") || carro.getStatus().equals("stored") || carro.getStatus().equals("buy")) {
                                readyToBuy = "NO";
                            }
                            if (carro.getVerified().equals("no")) {
                                for (product Producto : products) {
                                    if (carro.getIdProduct() == Producto.getIdproduct()) {
                                        totalPrice = totalPrice + (carro.getUnits() * carro.getPrice());

                %>
                <div class="row" style="margin-bottom: 20px">
                    <div class="col-sm-3 text-center">
                        <a href="openProduct?idProduct=<%= carro.getIdProduct()%>"><img src="getImageProduct?idProduct=<%= carro.getIdProduct()%>&opcion=1" width="140" height="100"></a>
                    </div>
                    <div class="col-sm-3">
                        <h6 style="color: white">Title: <%= Producto.getProductName()%></h6>
                        <h6 style="color: white">By: Alex Pizano</h6>
                    </div>
                    <div class="col-sm-3">
                        <a class="btn" href="changeUnits?idProd=<%= carro.getIdCart()%>&opcion=1"><i class="fa fa-angle-double-up"></i></a>
                        <input type="text" value="<%= carro.getUnits()%>" readonly style="width: 50px">
                        <a class="btn" href="changeUnits?idProd=<%= carro.getIdCart()%>&opcion=2"><i class="fa fa-angle-double-down"></i></a>
                    </div>
                    <div class="col-sm-3">
                        <h6 style="color: white">Price per Unit: $<%= carro.getPrice()%></h6>
                        <a class="btn" href="deleteCart?id=<%= carro.getIdCart()%>&opcion=1"><i class="fa fa-trash-o" style="font-size:20px"></i></a>
                    </div>
                </div>
                <%
                                    }
                                }
                            }
                        }
                    }
                    if (readyToBuy.equals("SI")) {

                    } else {
                        readyToBuy = "NO";
                    }
                %>
                <div class="row">
                    <div class="col-md-12 text-right">
                        <h3 style="color: white; margin-right: 5%">Total: $<%= totalPrice%></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 text-right">
                        <a class="btn" href="deleteCart?id=<%= newId%>&opcion=2"  style="margin-bottom: 40px"><i class="fa fa-trash-o" style="font-size:20px"></i>  Delete all!</a>
                    </div>
                    <%
                        if (readyToBuy.equals("SI")) {
                    %>
                    <div class="col-sm-6">
                        <a href="changeStatusCart?idUn=<%= newId%>&opcion=4" class="btn"><i class="fa fa-shopping-cart" style="font-size:20px"></i>  BUY!</a>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="col-sm-6">
                        <a href="openChat" class="btn"><i class="fa fa-shopping-cart" style="font-size:20px"></i>  Ask for budget!</a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </form>
        </div>
        <%            }
        %>

        <div class="container">
            <div class="row" style="margin-top: 10%;">
                <div class="col-md-12 text-center">
                    <form  action="search" method="POST">
                        <div class="input-group">
                            <input type="text" placeholder="Search" class="form-control text-center" name="seeker" value="<%= sWord%>">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row" style="margin-top: 3%;">
                <div class="col-sm-3 text-center">
                    <a class="btn" href="search?txt=<%= sWord %>&opcion=1">By Name</a>
                </div>
                <div class="col-sm-3 text-center">
                    <a class="btn" href="search?txt=<%= sWord %>&opcion=2">By Description</a>
                </div>
                <div class="col-sm-2 text-center">
                    <a class="btn" href="search?txt=<%= sWord %>&opcion=3">By 3D Models</a>
                </div>
                <div class="col-sm-2 text-center">
                    <a class="btn" href="search?txt=<%= sWord %>&opcion=4">By Character Design</a>
                </div>
                <div class="col-sm-2 text-center">
                    <a class="btn" href="search?txt=<%= sWord %>&opcion=5">By Photography</a>
                </div>
            </div>
            <div class="row" style="margin-top: 5%">
                <%
                    for (product pd : products2) {
                %>
                <div class="col-sm-4">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <a href="openProduct?idProduct=<%= pd.getIdproduct()%>"><img src="getImageProduct?idProduct=<%= pd.getIdproduct()%>&opcion=1" width="280" height="200"></a>
                        </div>
                        <div class="panel-body">
                            <p><%= pd.getProductName()%></p>

                            <p><%= pd.getDescription()%></p>
                            <p><%= pd.getCategory()%></p>
                        </div>
                    </div>
                </div>
                <%}
                %>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>