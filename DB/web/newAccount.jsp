<%-- 
    Document   : newAccount
    Created on : 25/11/2019, 11:04:41 PM
    Author     : oziel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>New account | MARKTRED</title>
        <link href="css/StyleNewAccount.css" type="text/css" rel="stylesheet">
        <link href="css/style.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
    </head>

    <body>
        <header>
            <nav class="navbar navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navegacion">
                            <span class="sr-only">Desplegar / ocultar</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>      
                        </button>
                        <a href="index.jsp" class="navbar-brand">Marktred</a>
                    </div>
                    <div class="collapse navbar-collapse" id="navegacion">
                        <ul class="nav navbar-nav navbar-left">
                            <li class="dropdown">
                                
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                           <i class="fa fa-user-circle-o" style="font-size:36px; color: white"></i>
                            <button class="Login">Username</button>
                            <button onclick="document.getElementById('Show').style.display='block'" class="Login">Login</button>
                            <button onclick="document.getElementById('ShowCart').style.display='block'" class="Cart"><i class="fa fa-shopping-cart"></i></button>
                            
                            <form class="navbar-form navbar-right">
                            <div class="input-group">
                                <input type="text" placeholder="Search" class="form-control">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </form>
                        </ul>
                        
                    </div>
                </div>
            </nav>
        </header>
        
        <form method="POST" action="AddUser" enctype="multipart/form-data" name="formUsuario" id="formUsuario" onsubmit="return validateForm()">
            
            <div class="container-fluid text-center">
                <div id="photos">
                    <img src="Images/4.jpg" class="banner" width="1000" height="400">
                </div>
                <div id="av">
                    <input type="file" name="photo" id="file" onchange="readURL(this);" class="inputfile" style="visibility: hidden; margin-top: 5%;">
                    <label id="seleccionarImagen" for="file"><i class="fa fa-upload"></i> Select your photo</label><br>
                    <img src="#" id="imgProfile" width="300px" height="300px"><br>
                </div>
            </div>
            
            <div class="container text-center">
                    <div class="form">
                        <input type="text" name="firstName" placeholder="Name" id="na">
                        <input type="text" name="lastName" placeholder="Last name" id="lna">

                        <input type="text" name="username" placeholder="Username" id="us"><i class="fa fa-info-circle" title="Min 6 characters"></i>    
                        <input type="email" name="email" placeholder="Email" id="em">

                        <input type="tel" placeholder="Tel (optional)">
                        <input type="text" placeholder="Address (optional)" id="optional">

                        <input type="password" name="pass" placeholder="Password" id="pass"><i class="fa fa-info-circle" title="Min 8 characters, 1 mayus, 1 min & 1 num"></i>
                    </div>

                    <button class="btn" type="submit">Create!</button>
            </div>
            
        </form>
                
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
               function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
    
                        reader.onload = function (e) {
                            $('#imgProfile').attr('src', e.target.result);
                        };
    
                        reader.readAsDataURL(input.files[0]);
                    }
                } 
                
                function entre(){
                    window.open("AddUser", "_self");
                }
            
                function validateForm() {
                  var x = document.forms["formUsuario"]["firstName"].value;
                  if (x == "") {
                    alert("El nombre es un campo obligatorio");
                    return false;
                  }
                    else{
                        var val = document.forms["formUsuario"]["lastName"].value;
                        if(val == ""){
                            alert("El apellido es un campo obligatorio");
                            return false;
                        }
                        else{
                            var val = document.forms["formUsuario"]["username"].value;
                            if(val == ""){
                                alert("El nombre de usuario es un campo obligatorio");
                                return false;
                            }
                            else{
                                var val = document.forms["formUsuario"]["password"].value;
                                var reg = /^(?=(?:.*\d){1})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})\S{8,}$/;
                                if(reg.test(val) == false){
                                    alert("Ingresa una contraseña valida (mínimo 8 caracteres, al menos una minúscula, una mayúscula y un número)");
                                    return false;
                                }
                                else{
                                    var val = document.forms["formUsuario"]["email"].value;
                                    reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                                    if( reg.test(val) == false ){
                                      alert("Ingresa un correo válido");
                                    return false;
                                    }
                                    
                                    else{
                                        var val = document.forms["formUsuario"]["photo"].value;
                                        if(val == ""){
                                        alert("Seleccione una imagen porfavor");
                                        return false;
                                        }
                                        else{
                                            window.open("AddUser", "_self");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        </script>
    </body>
</html>