<%-- 
    Document   : product
    Created on : 6/12/2019, 10:26:16 PM
    Author     : alexp
--%>

<%@page import="Models.comments"%>
<%@page import="Models.cart"%>
<%@page import="Models.User"%>
<%@page import="Utils.RequestUtils"%>
<%@page import="java.util.List"%>
<%@page import="Models.product"%>
<%
    List<product> products = (List<product>) request.getAttribute(RequestUtils.KEY_PRODUCT);
    List<User> user = (List<User>) request.getAttribute(RequestUtils.KEY_USER);
    List<User> allUsers = (List<User>) request.getAttribute(RequestUtils.KEY_ALL_USERS);
    List<cart> carts = (List<cart>) request.getAttribute(RequestUtils.KEY_CART);
    List<comments> comm = (List<comments>) request.getAttribute(RequestUtils.KEY_ALL_COMMENTS);
    String usn = request.getSession().getAttribute("usn").toString();
    int iniciado = 0;
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Brand name</title>
        <link href="css/style.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <header>
            <%
                if (usn == "si") {
                    iniciado = 0;
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
                iniciado = 1;
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

        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="Active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <%
                    for (product productos : products) {
                %>
                <div class="item newcarousel active">
                    <a href="openProduct?idProduct=<%= productos.getIdproduct()%>"><img src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=1"></a>
                    <div class="carousel-caption">
                    </div>
                </div>
                <%
                    }
                %>

                <%
                    for (product productos : products) {
                %>
                <div class="item newcarousel ">
                    <a href="openProduct?idProduct=<%= productos.getIdproduct()%>"><img src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=2"></a>
                    <div class="carousel-caption">
                    </div>
                </div>
                <%
                    }
                %>

                <%
                    for (product productos : products) {
                %>
                <div class="item newcarousel">
                    <a href="openProduct?idProduct=<%= productos.getIdproduct()%>"><img src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=3"></a>
                    <div class="carousel-caption">
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <section id="info" style="margin-top: 0%">
            <div class="container">
                <div class="row">
                    <%
                        for (product productos : products) {
                    %>
                    <div class="col-md-12 text-center">
                        <video width="420" height="360" controls>
                            <source src="getImageProduct?idProduct=<%= productos.getIdproduct()%>&opcion=4" type="video/mp4">
                        </video>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="row">
                    <%
                        for (product productos : products) {
                    %>
                    <div class="col-sm-4">
                        <h1>Name</h1>
                        <h2><%= productos.getProductName()%></h2>
                        <h1>Description</h1>
                        <h2><%= productos.getDescription()%></h2>
                    </div>
                    <div class="col-sm-4">
                        <h1>Category</h1>
                        <h2><%= productos.getCategory()%></h2>
                        <h1>Units</h1>
                        <h2><%= productos.getUnits()%></h2>
                    </div>
                    <%
                        }
                    %>
                    <%
                        for (product productos : products) {
                            float star = productos.getRate();
                            if (star < 0.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%}
                        if (star > 0.5 && star < 1.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%}
                        if (star > 1.5 && star < 2.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%}
                        if (star > 2.5 && star < 3.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%}
                        if (star > 3.5 && star < 4.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%}
                        if (star > 4.5) {
                    %>
                    <div class="col-sm-4">
                        <h1>Ranking</h1>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span><br>

                        <button class="btn" style="margin-top: 10%"><i class="fa fa-shopping-cart"></i> Add!</button>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <div class="container">
                <div class="row" style="margin-top: 10%">
                    <div class="col-md-12 text-center">
                        <h1>COMMENTS!</h1>
                    </div>
                </div>
                <%
                    for (comments comms : comm) {
                        for (product pd2 : products) {
                            if (comms.getIdProduct() == pd2.getIdproduct()) {
                                for (User allU : allUsers) {
                                    if (comms.getIdUser() == allU.getIdUser()) {
                %>
                <div class="row" style="margin: 5% 0%;">
                    <div class="col-md-3 text-center">
                        <img src="getImageUser?idUser=<%= allU.getIdUser()%>" width="100" height="100">
                    </div>
                    <div class="col-md-2 text-left">
                        <h1>By: <%= allU.getUsername()%></h1>
                    </div>
                    <div class="col-md-7 text-left">
                        <p style="color: #CD295A"><%= comms.getComment()%></p>
                    </div>
                </div>
                <%
                                    }
                                }
                            }
                        }
                    }
                %>
            </div>
            <%                for (cart carrito : carts) {
                    for (product pd : products) {
                        if (carrito.getIdProduct() == pd.getIdproduct()) {
                            if (carrito.getVerified().equals("si")) {
            %>
            <div class="container" id="comment" style="margin-bottom: 10%; margin-top: 10%;">
                <div class="row">
                    <div class="col-sm-10">
                        <h4>Let a comment</h4>
                    </div>
                    <div class="col-sm-2">
                        <h4>Raiting</h4>
                        <a href="changeRate?idProduct=<%= pd.getIdproduct()%>&opcion=1"><span class="fa fa-star"></span></a>
                        <a href="changeRate?idProduct=<%= pd.getIdproduct()%>&opcion=2"><span class="fa fa-star"></span></a>
                        <a href="changeRate?idProduct=<%= pd.getIdproduct()%>&opcion=3"><span class="fa fa-star"></span></a>
                        <a href="changeRate?idProduct=<%= pd.getIdproduct()%>&opcion=4"><span class="fa fa-star"></span></a>
                        <a href="changeRate?idProduct=<%= pd.getIdproduct()%>&opcion=5"><span class="fa fa-star"></span></a>
                    </div>
                </div>
                <form method="POST" action="addComment?idP=<%= pd.getIdproduct()%>&idU=<%= carrito.getIdUser()%>" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-sm-12" style="margin-top: 10px">
                            <textarea class="form-control" rows="6" name="taCom"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <button style="margin: 20px 0; position: relative; left: 40%" class="btn" type="submit"><i class="fa fa-comment"></i>  Send comment!</button>
                        </div>
                    </div>
                </form>
            </div>

            <%
            } else {
            %>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h1>You need buy first this product to comment & rate</h1>
                    </div>
                </div>
            </div>
            <%
                            }
                        }
                    }
                }
            %>
        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
