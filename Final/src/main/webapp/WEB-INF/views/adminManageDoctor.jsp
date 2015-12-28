
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<div class="span9">
			<div class="row-fluid">
				<div class="page-header">
					<h1>
						Doctors <small>All doctors</small>
					</h1>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>ID</th>
							<th>Code</th>
							<th>Name</th>
							<th>Type</th>
							<th>Description</th>
							<th>E-mail</th>


							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="doctor" items="${docList }">
							<tr class="list-users">
								<td># ${doctor.docId }</td>
								<td>${doctor.docCode }</td>
								<td>${doctor.docUsername }</td>
								<td>${doctor.type }</td>
								<td>${doctor.description }</td>
								<td>${doctor.email}</td>

								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown"
											href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="adminDeleteDoctors?docID=${ doctor.docId}"><i
													class="icon-trash"></i> Delete</a></li>
											<li><a href="adminViewDetails?docID=${ doctor.docId}"><i class="icon-user"></i>
													Details</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-sm-5"></div>
				<a href="adminMakeNewDoctor" class="btn btn-success">New Doctor</a>
			</div>
		</div>
	</div>

	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.dropdown-menu li a').hover(function() {
				$(this).children('i').addClass('icon-white');
			}, function() {
				$(this).children('i').removeClass('icon-white');
			});

			if ($(window).width() > 760) {
				$('tr.list-users td div ul').addClass('pull-right');
			}
		});
	</script>
</body>
</html>