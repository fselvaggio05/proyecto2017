<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/bootstrap.css" rel="stylesheet"/>
	<link href="css/sidebar.css" rel="stylesheet"/>
 	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/boot.css">
    

<title>Personas</title>
</head>

<body>

<!-- Barra de navegacion -->

<nav class="row navbar navbar-light bg-faded">
   
  <img src="kr2png12.png" width="100" height="100" class="navbar-brand" href="#"></a>
 
      <nav class="nav navbar-nav pull-xs-left">
      <a class="nav-item nav-link" href="#">Quienes somos </a>
      <a class="nav-item nav-link" href="#">Area pacientes </a>
      <a class="nav-item nav-link" href="#">Area profesionales</a>
      
    </nav>
    <form action="LoginServlet" class="form-inline pull-xs-right" method="post" id="FormLogin">
        <input id="email" type="email" class="form-control" name="email" value="" placeholder="DNI"> 
        <input id="password" type="password" class="form-control" name="password" value="" placeholder="Clave">
        <a class="btn btn-primary" href="#"><i class="fa fa-sign-in fa-lg fa-fw"></i>Login</a>
    </form>
</nav>

<!-- Sidebar ok -->
<div class="container">
	<div class="row">
		<div class="wrapper">
    	    <div class="side-bar">
                <ul>
                    <li class="menu-head">
                        <a href="#" class="push_menu"><span class="glyphicon glyphicon-align-justify pull-right"></span></a>
                    </li>
                    <div class="menu">
                        <li>
                            <a href="#" class="active">Inicio <span class="glyphicon pull-right"></span></a>
                        </li>
                        <li>
                            <a href="#" >Administracion<span class="glyphicon pull-right"></span></a>
                            
                        </li>
                        <li>
                            <a href="#">Contacto <span class="glyphicon pull-right"></span></a>
                        </li>
                        
                    </div>
                    
                </ul>
    	    </div>   
           
		</div>
	</div>
</div>

<!-- Formulario -->
<form class="form-horizontal" action="ABMPersonaServlet" method="post" id="FormABMPersona">
<fieldset>


<legend align="center">Alta paciente</legend>


<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
  <div class="col-md-4">
  <input id="nombre" name="txtNombre" type="text" placeholder="" class="form-control input-md">
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido</label>  
  <div class="col-md-4">
  <input id="apellido" name="txtApellido" type="text" placeholder="" class="form-control input-md">
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email</label>  
  <div class="col-md-4">
  <input id="tel" name="txtEmail" type="text" placeholder="" class="form-control input-md">
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="tipoDoc">Tipo de documento</label>
  <div class="col-md-4">
    <select id="invites_side" name="cmbTipoDoc" class="form-control">
      <option value="1">DNI</option>
      <option value="2">LC</option>
      <option value="3">LE</option>
    </select>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="dni">Numero de documento</label>  
  <div class="col-md-4">
  <input id="dni" name="txtDNI" type="text" placeholder="" class="form-control input-md">
    
  </div>
   </div>
  
  <div class="form-group">
  <label class="col-md-4 control-label" for="nroAfiliado">Nro. de afiliado</label>  
  <div class="col-md-4">
  <input id="nroAfiliado" name="txtNroAfiliado" type="text" placeholder="" class="form-control input-md">
    
  </div>
 </div>

<div class="form-group">
  <label class="col-md-4 control-label" for="fecha_nac">Fecha de nacimiento</label>  
  <div class="col-md-4">
  <input id="fechaNac" name="txtFechaNac" type="text" placeholder="" class="form-control input-md">
    
  </div>
</div>



<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="count">Sexo</label>
  <div class="col-md-4">
    <input type="radio" name="radMasc" >Masculino <br>
     <input type="radio" name="radFem" >Femenino <br>
    
  
  </div>
 <div>
 	<input type="submit" name="accion">Actualizar
 </div>
  
</div>
</fieldset>
</form>

<script src="js/bootstrap.js"></script>
<script src="js/jquery.js"></script>
<script src="js/sidebar.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.3/js/tether.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js" integrity="sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7" crossorigin="anonymous"></script>

</body>
</html>