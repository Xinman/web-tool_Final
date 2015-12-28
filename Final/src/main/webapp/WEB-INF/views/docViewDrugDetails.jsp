<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Healthy Home - Drug Details</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="../css/lib/bootstrap-wysihtml5.css" type="text/css" rel="stylesheet" />
    <link href="../css/lib/uniform.default.css" type="text/css" rel="stylesheet" />
    <link href="../css/lib/select2.css" type="text/css" rel="stylesheet" />
    <link href="../css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet" />
    <link href="../css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="../css/layout.css" />
    <link rel="stylesheet" type="text/css" href="../css/elements.css" />
    <link rel="stylesheet" type="text/css" href="../css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="../css/compiled/form-showcase.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
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
            <div id="pad-wrapper" class="form-page">
                <div class="row-fluid form-wrapper">
                <!-- left column -->
                    <div class="span2 column">
                    </div>
                    <!-- middle column -->
               
                    <div class="span8 column">
                     
                            <div class="field-box">
                                <label>Drug ID:</label>
                                <input class="span8 inline-input"  type="text" readonly="readonly" value="${drug.drugID }" />
                            </div>
                            <div class="field-box">
                                <label>Drug Name:</label>
                                <input class="span8 inline-input"  type="text" readonly="readonly" value="${drug.drugname }" />
                            </div>
                            <div class="field-box">
                                <label>Drug Code:</label>
                                <input class="span8 inline-input"  type="text" readonly="readonly" value="${drug.code }" />
                            </div>
                            <div class="field-box">
                                <label>Drug brand:</label>
                                <input class="span8 inline-input"  type="text" readonly="readonly" value="${drug.brand }" />
                            </div>
                            <div class="field-box">
                                <label>Drug price:</label>
                                <input class="span8 inline-input" type="text"  readonly="readonly" value="${drug.price }"/>
                            </div>
                             <div class="field-box">
                                <label>Drug availablity:</label>
                                <input class="span8 inline-input" type="text"  readonly="readonly" value="${drug.availablity }"/>
                            </div>    
                            <div class="field-box">
                                <label>Drug period:</label>
                                <input class="span8 inline-input" type="text"  readonly="readonly" value="${drug.period }"/>
                            </div>                      
                            <div class="field-box">
                                <label>Description of Drug :</label>
                                <textarea class="span8" rows="4" readonly="readonly">${drug.drugDescription}</textarea>
                            </div>
                            
                           <div class="field-box">
                             <div class="span9"></div>
                                    <a href="showPatient" class="btn-glow primary" >Back</a>
                           </div>
                                    
                             
                             
                    </div> 
            
                    </div>
                </div>
            </div>
        </div>
    <!-- end main container -->

	<!-- scripts for this page -->
    <script src="../js/wysihtml5-0.3.0.js"></script>
    <script src="../js/jquery-latest.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-wysihtml5-0.0.2.js"></script>
    <script src="../js/bootstrap.datepicker.js"></script>
    <script src="../js/jquery.uniform.min.js"></script>
    <script src="../js/select2.min.js"></script>
    <script src="../js/theme.js"></script>

    <!-- call this page plugins -->
    <script type="text/javascript">
        $(function () {

            // add uniform plugin styles to html elements
            $("input:checkbox, input:radio").uniform();

            // select2 plugin for select elements
            $(".select2").select2({
                placeholder: "Select a State"
            });

            // datepicker plugin
            $('.datepicker').datepicker().on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });

            // wysihtml5 plugin on textarea
            $(".wysihtml5").wysihtml5({
                "font-styles": false
            });
        });
    </script>

</body>
</html>