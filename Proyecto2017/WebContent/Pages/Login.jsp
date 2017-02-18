 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet"></link>
</head>
<body>


    <div class="container-fluid">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
              
            </div>
            <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="white">
                                Login Form</font> </b>
                        </div>
                    
                        <div class="panel-body" >

                        <form action="LoginServlet" method="post" >
                            <div class="form-group">
                                <label for="exampleInputEmail1">DNI</label> <input
                                    type="text" class="form-control" name="txtUserName" id="txtUserName"
                                    placeholder="Ingrese su DNI" required="required">
                                    
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Contraseña</label> <input
                                    type="password" class="form-control" name="txtPass" id="txtPass"
                                    placeholder="Ingrese su contraseña" required="required">
                            </div>
                            <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>
                                                   
                        </form>

                        </div>
                    </div>
                    
                </div>
                
            </div>
            
        </div>
    </div>
    
</body>
</html>
   