<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Admin panel developed with the Bootstrap from Twitter.">
<meta name="author" content="travis">
<link href="../css/justified-nav.css" rel="stylesheet">
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/site.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="masthead">
			<h3 class="text-muted">Admin</h3>
			<nav>
			<ul class="nav nav-justified">
				<li class="active"><a href="adminManageDoctor">Home</a></li>
				<li><a href="adminManageDoctor">Manage Doctors</a></li>
				<li><a href="adminMakeNewDoctor">Add New Doctors</a></li>
				<li><a href="adminLogout">Logout</a></li>

			</ul>
			</nav>
		</div>

		<div class="row-fluid">
			<div class="page-header">
				<div class="col-md-3"></div>
				<h3>New Doctor</h3>
			</div>
			<form:form method="POST" action="adminChangeDocInformation" modelAttribute="changeDoc">

				<br />
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>Doctor ID :</h4>
					</div>
					<div class="col-md-3">
						<form:input type="text" path="docId" value="${doctor.docId }" readonly="true" />
					</div>

				</div>
				
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>UserName:</h4>
					</div>
					<div class="col-md-3">
						<form:input type="text" path="docUsername" value="${doctor.docUsername }"/>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>Password:</h4>
					</div>
					<div class="col-md-3">
						<form:input type="password" path="docPassword" value="${doctor.docPassword }"/>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>Code</h4>
					</div>
					<div class="col-md-3">
						<form:input type="text" path="docCode" value="${doctor.docCode }"/>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>Email</h4>
					</div>
					<div class="col-md-3">
						<form:input type="text" path="email" value="${doctor.email }"/>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>Type</h4>
					</div>
					<div class="col-md-3">
						<form:input type="text" path="type" value="${doctor.type}"/>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<h4>description</h4>
					</div>
					<div class="col-md-3">
						<textarea name="description" >${doctor.description}</textarea>
					</div>

				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-5">
						<h4>
							<button type="submit" class="btn btn-success">Change Infroam</button>
							<a class="btn" href="adminManageDoctor">Cancel</a>
						</h4>
					</div>
				</div>
			</form:form>
		</div>
	</div>


	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>

</body>
</html>