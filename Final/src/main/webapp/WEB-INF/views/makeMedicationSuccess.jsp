<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Healthy Home - Make Medication</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="../css/layout.css" />
<link rel="stylesheet" type="text/css" href="../css/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/icons.css" />

<!-- libraries -->
<link href="../css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="../css/lib/select2.css" type="text/css" rel="stylesheet" />

<!-- this page specific styles -->
<link rel="stylesheet" href="../css/compiled/tables.css" type="text/css"
	media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script>
<script>
	/*$(document).ready(function(){
		
	var data =[{"name":"abc","gender":"aa"},{"name":"abcd","gender":"lll"}]
	
	/* $("#addDrug").click(function(){
	 var drugID = $("input[name='drugID']:checked").serialize();
	var amounts = $(".select2").serialize();
	var selects ="";
	selects += "{selectedDrug:[";
	$.each(function(){
		var drugs ="";
		drugs+="'drugID':";
		drugs+=drugID;
		drugs+="'quantity':";
		drugs+=amounts;
		selects.append(drugs);
	
	})
	selects += "]}";
	
	// $('select:selected').each(function(){
	 //var amounts = ;
	 //$("".amount ').each(function(){
	 //amounts.push($(this).value());
	// alert($(this).value());
	// });
	
	alert(drugID);
	alert(amounts);*/
	/* $.ajax({
	 url:"http://localhost:8080/project/docMakeMedication",
	 type:"POST",
	 contentType:"application/json",
	 //data:"drugID="+drugID+"&amount="+amounts,
	 data:JSON.stringify(data),
	 success: function(result){
		 $.each(result, function() {
			 console.log(this);
		 });
	 },
	 error: function(){
		 $("#addDrug").append("can't do this");
		 }
	 });
	 });*/
	 //});
	
</script>
</head>
<body>

	<!-- navbar -->
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<button type="button" class="btn btn-navbar visible-phone"
				id="menu-toggler">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="brand" href="doctorHome">Healthy Home</a>

			<ul class="nav pull-right">



				<li class="notification-dropdown hidden-phone">

					<div class="pop-dialog">
						<div class="pointer right">
							<div class="arrow"></div>
							<div class="arrow_border"></div>
						</div>
						<div class="body"></div>
					</div>
				</li>
				<li class="dropdown"><a href="#"
					class="dropdown-toggle hidden-phone" data-toggle="dropdown">
						Your account <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="showDoctorInfor">Personal info</a></li>
						<li><a href="doctorChangePass">Change Password</a></li>
					</ul></li>
				<li class="settings hidden-phone"><a href="showDoctorInfor"
					role="button"> <i class="icon-cog"></i>
				</a></li>
				<li class="settings hidden-phone"><a href="logout"
					role="button"> <i class="icon-share-alt"></i>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- end navbar -->

	<!-- sidebar -->
	   <div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div> <a href="doctorHome"> <i class="icon-home"></i> <span>Home</span>
			</a>
			</li>
			<li><a class="dropdown-toggle"> <i class="icon-group"></i>
					<span>Manage Patient</span> <i class="icon-chevron-down"></i>
			</a>
				<ul class="submenu">
					<li><a href="showPatient">Patient List</a></li>

				</ul></li>
			<li><a href="patientAppointmentList"> <i
					class="icon-tasks"></i> <span>Manage Appointment</span> <i
					class="icon-chevron-down"></i>
			</a></li>

			<li><a href="showDoctorInfor"> <i class="icon-cog"></i> <span>My
						Info</span>
			</a></li>
		</ul>
	</div>
	<!-- end sidebar -->


	<!-- main container -->
	<div class="content">


		<div class="container-fluid">
			<div id="pad-wrapper">
					<!-- products table-->
					<!-- the script for the toggle all checkboxes from header is located in js/theme.js -->
					


		<!-- orders table -->
	
		<div class="table-wrapper orders-table section">
			<div class="row-fluid head">
				<div class="span12">
					<h4>Medication</h4>
					<br/><br/>
				</div>
			</div>

			

			<div class="row-fluid">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="span2">Drug ID</th>
							<th class="span3">Drug Name</th>
							<th class="span3"><span class="line"></span> Price</th>
							<th class="span3"><span class="line"></span> Items</th>
							<th class="span3"><span class="line"></span> Total</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="med" items="${medication }">
					
						<!-- row -->
						<tr class="first">
					
							<td>${med.drugID }</td>
							<td>${med.drugname }</td>
							<td>${med.price }</td>
							<td>${med.quantity }</td>
							<td>${med.total }</td>
							
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
			</div>
			
			</div>
		
		</div>
	</div>

	<!-- end orders table -->
	</div>
	<!-- end main container -->

	<!-- scripts -->
	<script src="../js/jquery-latest.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/theme.js"></script>
	<script src="../js/select2.min.js"></script>
	<script src="../js/bootstrap.datepicker.js"></script>
	<script src="../js/jquery.uniform.min.js"></script>

	<script type="text/javascript">
		$(function() {

			// add uniform plugin styles to html elements
			$("input:checkbox, input:radio").uniform();

			// select2 plugin for select elements
			$(".select2").select2({
				placeholder : "Select quantity"
			});

			// datepicker plugin

			// wysihtml5 plugin on textarea

		});
	</script>

</body>
</html>