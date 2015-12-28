<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Healthy Home</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.poptrox.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/init.js"></script>
<noscript>
	<link rel="stylesheet" href="css/skel-noscript.css" />
	<link rel="stylesheet" href="css/style.css" />
</noscript>
<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script>
<script type="text/javascript" src="../validate/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../validate/jquery.validate.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script>
<script type="text/javascript">
	/*function pw() {
	 var pw1 = document.getElementById("pw1").value;
	 var pw2 = document.getElementById("pw2").value;
	 if(pw1 == pw2) {
	 document.getElementById("s_msg").innerHTML="";
	 document.getElementById("submit").disabled = false;
	 }
	 else {
	 document.getElementById("s_msg").innerHTML="Doesn't Match!";
	 document.getElementById("submit").disabled = true;
	 }
	 }*/

	$(document).ready(function() {

		$("#pw2").keyup(function() {
			var pw1 = $("#pw1").val();
			var pw2 = $("#pw2").val();
			if (pw1 != pw2) {
				$("#s_msg").text("Doesn't Match!");
				$("#submit").prop("disabled", true);
			} else {
				$("#s_msg").text("Password can be used");
				$("#submit").prop("disabled", false);
			}
		});
		$("#age").keyup(function() {
			var a = $("#age").val();
			if (isNaN(a)) {
				$("#age_msg").text("Age should be Integer");
				$("#submit").prop("disabled", true);
			} else {
				$("#age_msg").text("");
				$("#submit").prop("disabled", true);
			}
		});
		
	});

</script>
<script type="text/javascript">
function checkIsExist() {
    var name= $("#username").val();
	var username = $.trim(name);
	//var email = $.trim($("#email").val());
    // alert(name);
	$.ajax({
		type : "POST",
		url : "http://localhost:8080/project/isExist",
		async: false,
		data :({username:username}),
		dataType : "json",
		complete : function(msg) {
			var result = eval("(" + msg.responseText + ")");
			if (result.success) {
				$("#showResult").html(result.message);
			} else {
				$("#showResult").html(result.message);
			}
		}
	});
}
	
	function checkEmail() {
		var email = $.trim($("#email").val());
		
	     //alert(email);
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/project/checkEmail",
			async: false,
			data :({email:email}),
			dataType : "json",
			complete : function(emsg) {
				var results = eval("(" + emsg.responseText + ")");
				if (results.success) {
					$("#resultEmail").html(results.message);
				} else {
					$("#resultEmail").html(results.message);
				}
			}
		});
	}

	function checkSSN() {
		var ssn = $.trim($("#ssn").val());
		
	     //alert(email);
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/project/checkSSN",
			async: false,
			data :({ssn:ssn}),
			dataType : "json",
			complete : function(emsg) {
				var results = eval("(" + emsg.responseText + ")");
				if (results.success) {
					$("#resultSsn").html(results.message);
				} else {
					$("#resultSsn").html(results.message);
				}
			}
		});
	}
	
	function clearEmailCss() {
		$("#resultEmail").html("");
		
	}
	function clearNameCss() {
	
		$("#showResult").html("");
		
	}
	function clearSsnCss() {
		
		$("#resultSsn").html("");
	}
	
</script>

<script type="text/javascript">




</script>


</head>
<body>

	<!-- Header -->
	<header id="header">

		<!-- Logo -->
		<h1 id="logo">
			<a href="adminLogin">Admin Login</a>
		</h1>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a href="#intro">Intro</a></li>
				<li><a href="#one">What We Do</a></li>
				<li><a href="#two">Who We are</a></li>
				<li><a href="#contact">Join/Log in</a></li>
			</ul>
		</nav>

	</header>

	<!-- Intro -->
	<section id="intro" class="main style1 dark fullscreen">
		<div class="content container small">
			<header>
				<h2>Hey.</h2>
			</header>
			<p>
				Welcome to <strong>Healthy Home</strong>. Follow the steps to have
				better healthcare!
			</p>
			<footer>
				<a href="#one" class="button style2 down">More</a>
			</footer>
		</div>
	</section>


	<!-- One -->
	<section id="one" class="main style2 right dark fullscreen">
		<div class="content box style2">
			<header>
				<h2>What We Do</h2>
			</header>
			<p>You are my patient! Your health is my only concern. And You
				will be alright by our helping. No matter where, we will always be
				with you!</p>
		</div>
		<a href="#two" class="button style2 down anchored">Next</a>
	</section>

	<!-- Two -->
	<section id="two" class="main style2 left dark fullscreen">
		<div class="content box style2">
			<header>
				<h2>Who We Are</h2>
			</header>
			<p>We are Your Baymaxes! Your personal health care companion. We
				will not deactivate until you say: You are satisfied with your care.</p>
		</div>
		<a href="#contact" class="button style2 down anchored">Next</a>
	</section>



	<!-- Contact -->
	<section id="contact" class="main style3 secondary">
		<div class="content container">
			<header>
				<h2>Join us.</h2>
				<p>
					Let us help you to have good health. <strong>You worth it</strong>
				</p>
			</header>
			<div class="box container small">

				<!--
							 Contact Form
							 
							 To get this working, place a script on your server to receive the form data, then
							 point the "action" attribute to it (eg. action="http://mydomain.tld/mail.php").
							 More on how it all works here: http://www.1stwebdesigner.com/tutorials/custom-php-contact-forms/
						-->
				<form:form method="post" modelAttribute="patient">
					<div class="row half">
						<div class="2u">
							<span>UserName:</span>
						</div>
						<div class="3u">
							<form:input type="text" path="paUsername" id="username" onblur="checkIsExist()"
							onfocus="clearNameCss();" name="username" placeholder="UserName" required="true" />
						</div>
						<div class="3u">
							<span>Contact Email:</span>
						</div>
						<div class="4u">
							<form:input type="text" path="contactemail" id="email" onblur="checkEmail()"
							onfocus="clearEmailCss();" name="email" placeholder="Email" required="true" />
						</div>
					</div>
					<div class="row half">
						<div class="6u">
							<span id="showResult" ></span>
						</div>
						<div class="6u">
							<span id="resultEmail" ></span>
						</div>
					</div>
					
					<div class="row half">	
						<div class="6u">
							<form:errors path="paUsername"></form:errors>
						</div>
						<div class="6u">
							<form:errors path="contactemail"></form:errors>
						</div>
					</div>
					
					<div class="row half">

						<div class="2u">
							<span>Age:</span>
						</div>
						<div class="3u">
							<form:input type="text" id="age" path="age" name="age"
								placeholder="Age" required="true" />
						</div>
						<div class="3u">
							<span>SSN:</span>
						</div>
						<div class="4u">
							<form:input type="text" id="ssn" path="ssn" name="ssn" onblur="checkSSN()"
							onfocus="clearSsnCss();" placeholder="SSN" required="true" />
						</div>

					</div>
					<div class="row half">
						<div class="6u">
							<span id="age_msg"></span>
						</div>
						
						<div class="6u">
							<span id="resultSsn"></span>
						</div>

					</div>
					<div class="row half">
						
						<div class="6u">
							<form:errors path="age"></form:errors>
						</div>
						<div class="6u">
							<form:errors path="ssn"></form:errors>
						</div>

					</div>
					<div class="row half">
						<div class="2u">
							<span>Phone:</span>
						</div>
						<div class="7u">
							<form:input type="text" id="phone" path="phone" name="phone"
								placeholder="Phone" required="true" />
						</div>
						<form:errors path="phone"></form:errors>

					</div>
					<div class="row half">
						<div class="2u">
							<span>Password:</span>
						</div>
						<div class="7u">
							<form:input type="password" path="papassword" id="pw1"
								name="password1" placeholder="Password" required="true" />
						</div>

					</div>
					<div class="row half">

						<div class="2u">
							<span>Enter Again:</span>
						</div>
						<div class="7u">
							<input type="password" id="pw2" name="password2"
								placeholder="Enter Password Again" onkeyup="pw()" required />
						</div>
						<span id="s_msg"></span>
					</div>
					<div class="row">
						<div class="12u">
							<ul class="actions">
								<li><input type="submit" id="submit" name="submit"
									class="button" value="Join us" /></li>
							</ul>
						</div>
					</div>

					<div class="12u">
						<ul class="actions">
							<li><a class="button" href="accountLogin">Aleardy have
									Account. Go >></a></li>
						</ul>
					</div>
				</form:form>

			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer id="footer">

		<!--
				     Social Icons
				     
				     Use anything from here: http://fortawesome.github.io/Font-Awesome/cheatsheet/)
				-->


		<!-- Menu -->
		<ul class="menu">
			<li>&copy; Healtchy Home</li>
			<li>Collect from M</li>
		</ul>

	</footer>

</body>
</html>
