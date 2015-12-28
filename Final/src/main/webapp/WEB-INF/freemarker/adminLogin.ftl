<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
 <style type="text/css">   
body {
  padding-top: 40px;
  padding-bottom: 40px;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style> 
    <title>Log in</title>
</head>
<body>
  <div class="container">

      <form class="form-signin" method="POST" action="adminLogin" commandName="admin">
        <h2 class="form-signin-heading">Please Log in</h2>
        <div class="form-group">
        <label for="username" class="sr-only">User Name:</label>
        <input id="username"name="username"  class="form-control" placeholder="User Name" required autofocus/>
        </div>
        <div class="form-group">
        <label for="password" class="sr-only">Password:</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>
        </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="checkbox" value="rememberme"> Remember me 
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Admin login</button>
      </form>

    </div> <!-- /container -->
    
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
