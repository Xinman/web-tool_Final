<!DOCTYPE html>
<html>
<head>
	<title>Healthy Home - My Info</title>
    
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href="patientHome">Healthy Home</a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="patientHomePage">Home</a></li>
                    <li><a href="showDoctors">Doctor list</a></li>
                    <li><a href="patientViewAppointmentList">Appointment</a></li>
                    <li><a href="patientViewMedicationRecords">Medication</a></li>
                    <li><a href="patientViewVisitHistory">Visit History</a></li>  
                </ul>
            </div>
            <ul class="nav pull-right">
                <li class="hidden-phone">
                   
                </li>
                <li class="settings">
                    <a href="showPatientInfor" role="button">
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
    <!-- end navbar -->

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div class="settings-wrapper" id="pad-wrapper">
                <!-- avatar column -->
                <div class="span3 avatar-box">
                    <div class="personal-image">
                       
                    </div>
                </div>

                <!-- edit form column -->
                <div class="span7 personal-info">
                    <div class="alert alert-info">
                        <i class="icon-lightbulb"></i>
                        You can change your personal Information!
                    </div>
                    <h5 class="personal-title">Personal info</h5>

                    <form action="patientChangeInfor" method="POST">
                        
                        <div class="field-box">
                            <label>Username:</label>
                            <input class="span5 inline-input" type="text"  />
                        </div>
                        <div class="field-box">
                            <label>Password:</label>
                            <input class="span5 inline-input" type="password" />
                        </div>
                        <div class="field-box">
                            <label>Confirm password:</label>
                            <input class="span5 inline-input" type="password"  />
                        </div>
                        <div class="field-box">
                            <label>Email:</label>
                            <input class="span5 inline-input" type="text"  />
                        </div>
                        <div class="field-box">
                            <label>Age:</label>
                            <input class="span5 inline-input" type="text"  />
                        </div>
                        <div class="field-box">
                            <label>SSN:</label>
                            <input class="span5 inline-input" type="text" />
                        </div>
                        <div class="field-box">
                            <label>Phone:</label>
                            <input class="span5 inline-input" type="text" />
                        </div>
                        <div class="span6 field-box actions">
                            <input type="button" type="submit" class="btn-glow primary" value="Save Changes" />
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