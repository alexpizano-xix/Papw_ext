<%-- 
    Document   : profile
    Created on : 24/11/2019, 07:30:44 PM
    Author     : alexp
--%>

<%@page import="Models.cart"%>
<%@page import="java.util.List"%>
<%@page import="Utils.RequestUtils"%>
<%@page import="Models.product"%>
<%@page import="Models.User"%>
<%@page import="javax.naming.Context"%>

<%
    List<product> products = (List<product>) request.getAttribute(RequestUtils.KEY_ALL_PRODUCTS);
    List<User> user = (List<User>) request.getAttribute(RequestUtils.KEY_USER);
    List<User> allusers = (List<User>) request.getAttribute(RequestUtils.KEY_ALL_USERS);
    List<cart> carts = (List<cart>) request.getAttribute(RequestUtils.KEY_CART);
    List<cart> allCarts = (List<cart>) request.getAttribute(RequestUtils.KEY_ALL_CART);
    String usn = request.getSession().getAttribute("usn").toString();
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
        <title>Welcome to Marktred</title>
        <link href="css/style.css" type="text/css" rel="stylesheet">
        <link href="css/StyleNewAccount.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>

    <body>

        <header>
            <% if (usn == null) {
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
                            <i class="fa fa-user-circle-o" style="font-size:36px; color: white"></i>
                            <button class="Login">Username</button>
                            <a href="index.jsp"><button class="Login">LogOut</button></a>
                            <button onclick="document.getElementById('ShowCart').style.display = 'block'" class="Cart"><i class="fa fa-shopping-cart"></i></button>

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
                            <%
                                }
                            %>
                            <button class="Login"><%= usn%></button>
                            <a href="index.jsp"><button class="Login">LogOut</button></a>
                            <button onclick="document.getElementById('ShowCart').style.display = 'block'" class="Cart"><i class="fa fa-shopping-cart"></i></button>

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
                        <a class="btn" href="changeUnits?idProd=<%= carro.getIdCart()%>&opcion=3"><i class="fa fa-angle-double-up"></i></a>
                        <input type="text" value="<%= carro.getUnits()%>" readonly style="width: 50px">
                        <a class="btn" href="changeUnits?idProd=<%= carro.getIdCart()%>&opcion=4"><i class="fa fa-angle-double-down"></i></a>
                    </div>
                    <div class="col-sm-3">
                        <h6 style="color: white">Price per Unit: $<%= carro.getPrice()%></h6>
                        <a class="btn" href="deleteCart?id=<%= carro.getIdCart()%>&opcion=3"><i class="fa fa-trash-o" style="font-size:20px"></i></a>
                    </div>
                </div>
                <%
                                    }
                                }
                            }
                        }
                    }
                    if (readyToBuy.equals("NO")) {

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
                        <a class="btn" href="deleteCart?id=<%= newId%>&opcion=4"  style="margin-bottom: 40px"><i class="fa fa-trash-o" style="font-size:20px"></i>  Delete all!</a>
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

        <div class="container-fluid text-center">
            <div id="photos">
                <img src="Images/4.jpg" width="1000" height="400">
            </div>
        </div>

        <div class="container text-center" id="profile">
            <div class="row">
                <%
                    for (User usuarios : user) {
                %>
                <div class="col-sm-3">
                    <h4><%= usuarios.getFirstName()%></h4>
                    <h4><%= usuarios.getLastName()%></h4>
                    <h4><%= usuarios.getEmail()%> </h4>
                </div>
                <div class="col-sm-6">
                    <img src="getImageUser?idUser=<%= usuarios.getIdUser()%>" width="250" height="250" class="avatar">
                </div>
                <div class="col-sm-3">
                    <h1><%= usuarios.getUsername()%></h1>
                    <%
                        if (usuarios.getUsername().equals("AlexPizano")) {
                    %>
                    <button onclick="document.getElementById('NewProduct').style.display = 'block'" class="btn"><i class="fa fa-cloud-upload"></i>  New product!</button>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <div id="NewProduct" class="modal container-fluid text-center" style="overflow-y: auto">
            <form class="modal-content animate" method="POST" action="addProduct" enctype="multipart/form-data">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('NewProduct').style.display = 'none'" class="close" title="Close">&times;</span>
                </div>

                <h1 style="position: relative; left: 0%; color: white">New product!</h1>

                <div class="row">
                    <div class="col-sm-12">
                        <label style="color: white">Name</label><br>
                        <input type="text" name="productName" placeholder="Name product" style="width: 80%; color: white; background: #CD295A;  border-bottom: 1px solid #fff; outline: none; border: none; text-align: center; font-size: 18px; margin-bottom: 15px">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label style="color: white">Description</label>
                        <textarea class="form-control" name="description" rows="4" style="margin-bottom: 15px"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label style="color: white">Category</label>
                        <select class="form-control" name="category" style="margin-bottom: 15px">
                            <option>3D Model</option>
                            <option>Photography</option>
                            <option>Character design</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label style="color: white">Units</label><br>
                        <input type="text" name="units" placeholder="units" style="width: 80%; color: white; background: #CD295A;  border-bottom: 2px solid #fff; outline: none; border: none; text-align: center; font-size: 18px; margin-bottom: 15px">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label style="color: white">Select image #1</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="file" name="image1" style="color: white">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label style="color: white">Select image #2</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="file" name="image2" style="color: white">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label style="color: white">Select image #3</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="file" name="image3" style="color: white">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label style="color: white">Select video</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="file" name="video" style="color: white; margin-bottom: 15px">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button class="btn" type="submit" style="margin-bottom: 15px; width: 50%">Save!</button>
                    </div>
                </div>
            </form>
        </div>
        <%
            for (User usuarios : user) {
                if (usn.equals("alexpizano")) {
        %>
        <div class="container text-center">
            <div class="row">
                <div class="col-sm-12">
                    <h2>In store!</h2>
                </div>
            </div>
            <div class="row">
                <%
                    for (product productos : products) {
                        if (productos.getStatus().equals("stored")) {
                %>
                <div class="col-sm-4">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <img src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=1" width="280" height="200"></a>
                        </div>
                        <div class="panel-body">
                            <p><%= productos.getProductName()%></p>

                            <p><%= productos.getDescription()%></p>
                            <p><%= productos.getCategory()%></p>
                        </div>
                        <div class="panel-footer">
                            <a href="productChangeSave?pN=<%= productos.getIdproduct()%>"><button class="btn btn-lg">Save</button></a>
                            <a href="deleteProduct?pN=<%= productos.getIdproduct()%>"><button class="btn btn-lg">Delete</button></a>
                            <button class="btn btn-lg">Edit</button>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%}

            }
        %>
        <%
            for (User usuarios : user) {
                if (usn.equals("alexpizano")) {
        %>
        <div class="container text-center">
            <div class="row">
                <div class="col-sm-12">
                    <h2>Saved!</h2>
                </div>
            </div>
            <div class="row">
                <%
                    for (product productos : products) {
                        if (productos.getStatus().equals("save")) {
                %>
                <div class="col-sm-4">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <a href="product.html"><img src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=1" width="280" height="200"></a>
                        </div>
                        <div class="panel-body">
                            <input type="text" name="pN" value="<%= productos.getProductName()%>" disabled="true" class="text-center" style="background-color: transparent; border: none; font-weight: bold; font-size: 20px">                           
                            <p><%= productos.getDescription()%></p>
                            <p><%= productos.getCategory()%></p>
                        </div>
                        <div class="panel-footer">
                            <a href="productCRUD?pN=<%= productos.getIdproduct()%>"><button class="btn btn-lg" name="publish" type="submit">Publish!</button></a>
                            <a href="deleteProduct?pN=<%= productos.getIdproduct()%>"><button class="btn btn-lg">Delete</button></a>
                            <button class="btn btn-lg" name="edit" type="submit">Edit</button>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%}
            }
        %>

        <div class="container text-center">

            <%
                if (usn.equals("alexpizano")) {
            %>
            <div class="row">
                <div class="col-sm-12">
                    <h2>Purchase History!</h2>
                </div>
            </div>
            <%
                for (cart allC : allCarts) {
                    if (allC.getVerified().equals("si")) {
                        for (product p : products) {
                            if (allC.getIdProduct() == p.getIdproduct()) {
                                for (User us : allusers) {
                                    if (allC.getIdUser() == us.getIdUser()) {
            %>
            <div class="row">
                <div class="col-sm-4">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <a href="product.html"><img src="getImageProduct?idProduct=<%= p.getIdproduct()%>&opcion=1" width="280" height="200"></a></a>
                        </div>
                        <div class="panel-body">
                            <p><%= p.getProductName()%></p>
                            <p><%= p.getCategory()%></p>
                            <p>Bought by: <%= us.getUsername()%></p>
                            <p>Price: <%= allC.getPrice()%></p>
                            <p>Units <%= allC.getUnits()%></p>
                            <p>Total: <%= (allC.getPrice() * allC.getUnits())%></p>
                        </div>
                        <div class="panel-footer">
                            <h1 style="color: #CD295A">SOLD!</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%                }
                            }
                        }
                    }
                }
            }
        } else {
        %>
        <div class="container text-center">
            <div class="row">
                <div class="col-sm-12">
                    <h2>Purchase History!</h2>
                </div>
            </div>
            <div class="row">
            <%
                for (cart allC : allCarts) {
                    for (User usY : user) {
                        if (usY.getIdUser() == allC.getIdUser()) {
                            newId = allC.getIdUser();
                        }
                        if (allC.getIdUser() == newId) {
                            if (allC.getVerified().equals("si")) {
                                for (product p : products) {
                                    if (allC.getIdProduct() == p.getIdproduct()) {
                                        for (User us : allusers) {
                                            if (allC.getIdUser() == us.getIdUser()) {
            %>
            
                <div class="col-sm-4">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <a href="product.html"><img src="getImageProduct?idProduct=<%= p.getIdproduct()%>&opcion=1" width="280" height="200"></a></a>
                        </div>
                        <div class="panel-body">
                            <p><%= p.getProductName()%></p>
                            <p><%= p.getCategory()%></p>
                            <p>Price: <%= allC.getPrice()%></p>
                            <p>Units <%= allC.getUnits()%></p>
                            <p>Total: <%= (allC.getPrice() * allC.getUnits())%></p>
                        </div>
                        <div class="panel-footer">
                            <h1 style="color: #CD295A">BOUGHT!</h1>
                        </div>
                    </div>
                </div>
                        <%
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            %>
            </div>
            
        </div>
        <%
            }
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
                        function test() {
                            alert($('.productName').text());
                        }
                        ;
        </script>
    </body>
</html>
