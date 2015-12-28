<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Healthy Home - Doctor Info</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="../css/layout.css" />
    <link rel="stylesheet" type="text/css" href="../css/elements.css" />
    <link rel="stylesheet" type="text/css" href="../css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="../css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="../css/compiled/personal-info.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script>
<script type="text/javascript">
/*$(document).ready(function(){

	$("#pws2").keyup(function(){
	var pw1 = $( "#pws1" ).val();
	var pw2 = $( "#pws2" ).val();
	alert(pw2);
	if(pw1!=pw2){
		$("#p_msg").text("Doesn't Match!");
		$( "#submit" ).prop( "disabled", true );
	}else{
		$("#p_msg").text("Password can be used");
		$( "#submit" ).prop( "disabled", false );
	}
});
	
});*/

function pw() {
	 var pw1 = document.getElementById("pws1").value;
	 var pw2 = document.getElementById("pws2").value;
	 if(pw1 == pw2) {
	 document.getElementById("p_msg").innerHTML="";
	 document.getElementById("submit").disabled = false;
	 }
	 else {
	 document.getElementById("p_msg").innerHTML="Doesn't Match!";
	 document.getElementById("submit").disabled = true;
	 }
	 }
	 
</script>
</head>
<body>

    <!-- navbar -->
   <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href="doctorHome">Healthy Home</a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="doctorHome">Home</a></li>
                    <li><a href="showPatient">Manage Patient</a></li>
                    <li><a href="patientAppointmentList">Manage Appointment</a></li>
                    <li><a href="showDoctorInfor">My Infor</a></li>
                
                </ul>
            </div>
            <ul class="nav pull-right">
                <li class="hidden-phone">
                    <input class="search" type="text" />
                </li>
                <li class="settings">
                    <a href="personal-info.html" role="button">
                        <span class="navbar_icon"></span>
                    </a>
                </li>
                <li id="fat-menu" class="dropdown">
                    <a href="logout" role="button" class="logout">
                        <span class="navbar_icon"></span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div class="settings-wrapper" id="pad-wrapper">
                <!-- avatar column -->
                <div class="span 7"></div>
                <div class="span 7"></div>
                <div class="span 7"></div>
                <div class="span 7"></div>
                <div class="span 7"></div>
                <!-- edit form column -->
                <div class="span7 personal-info">
                    <h5 class="personal-title">Change Password</h5>

                    <form action="changeDoctorPass" method="post">
                       
                        <div class="field-box">
                            <label>Old Password:</label>
                            <input class="span5 inline-input"  type="text" name="oldPass" />
                            
                        </div>
                        <div class="field-box">
                            <label>New Password:</label>
                            <input class="span5 inline-input" type="password" name="newPass1" id="pws1" required/>
                        </div>
                        <div class="field-box">
                            <label>Enter Again:</label>
                            <input class="span5 inline-input"  type="password" name="newPass2" id="pws2" onkeyup="pw()" required/><span id="p_msg" ></span>
                        </div>
                       
                      
                        <div class="span6 field-box actions">
                            <input type="submit" class="btn-glow primary" value="Save Changes" />
                            <span>OR</span>
                            <input type="reset" value="Cancel" class="reset" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


	<!-- scripts -->
    <script src="../js/jquery-latest.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/theme.js"></script>

</body>
</html>