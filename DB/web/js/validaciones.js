function validacion(){
    var user = document.getElementById("us").value;
    var pass = document.getElementById("pass").value;
    
    if(user == "" && pass == ""){
        alert("No ingresaste ningun campo");
        return false;
    }
    
    if(user == ""){
        alert("No ingresaste nombre de usuario");
        return false;
    }
    
    if(pass == ""){
        alert("No ingresaste tu contraseña");
        return false;
    }

    Open();
}

function validacionNew(){
    var name = document.getElementById("na").value;
    var lastName = document.getElementById("lna").value;
    var user = document.getElementById("us").value;
    var email = document.getElementById("em").value;
    var pass = document.getElementById("pass").value;
    
    if(name == "" || lastName == "" || user == "" || email == "" || pass == ""){
        alert("los campos con borde rojo son necesarios");
        return false;
    }
    
    if(user.length < 6){
        alert("El nombre de usuario debe ser minimo de 6 caracteres");
        return false;
    }
    
    if(pass.length < 8){
        alert("La contrasella debe ser minimo de 8 caracteres");
        return false;
    }
    
    var nMay = 0, nMin = 0, nNum = 0
	var t1 = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
	var t2 = "abcdefghijklmnñopqrstuvwxyz"
	var t3 = "0123456789"
	for (i=0;i<pass.length;i++) {
		if ( t1.indexOf(pass.charAt(i)) != -1 ) {nMay++}
		if ( t2.indexOf(pass.charAt(i)) != -1 ) {nMin++}
		if ( t3.indexOf(pass.charAt(i)) != -1 ) {nNum++}
	}
	
    if(nMay == 0){
        alert("La contrasella debe tener minimo una mayuscula");
        return false;
    }
    
    if(nMin == 0){
        alert("La contrasella debe tener minimo una minuscula");
        return false;
    }
    
    if(nNum == 0){
        alert("La contrasella debe tener minimo un numero");
        return false;
    }
}



function Open(){
    window.open("login.html", "_self");
}