<!DOCTYPE html>
<html>
<head>
	<title>Health Home - ManagePatient</title>
    
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
    <link href="../css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    <link href='../css/lib/fullcalendar.css' rel='stylesheet' />
    <link href='../css/lib/fullcalendar.print.css' rel='stylesheet' media='print' />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="../css/compiled/calendar.css" type="text/css" media="screen" />

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
            <div id="pad-wrapper">
                <div class="row-fluid calendar-wrapper">
                    <div class="span12">

                        <!-- div that fullcalendar plugin uses  -->
                        <div id='calendar'></div>

                        <!-- edit image pop up -->
                        <div class="new-event popup">
                            <div class="pointer">
                                <div class="arrow"></div>
                                <div class="arrow_border"></div>
                            </div>
                            <i class="close-pop table-delete"></i>
                            <h5>New event popup example</h5>
                            <div class="field">
                                Date: 
                                <span class="date">Thu, 18 April</span>
                            </div>
                            <div class="field">
                                Event:
                                <input type="text" class="event-input" />
                            </div>
                            <input type="submit" value="Create" class="btn-glow primary" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


	<!-- scripts for this page -->
    <script src="../js/jquery-latest.js"></script>
    <script src="../js/jquery-ui-1.10.2.custom.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src='../js/fullcalendar.min.js'></script>
    <script src="../js/theme.js"></script>

    <!-- builds fullcalendar example -->
    <script>
        $(document).ready(function() {
        
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            
            $('#calendar').fullCalendar({
                header: {
                    left: 'month,agendaWeek,agendaDay',
                    center: 'title',
                    right: 'today prev,next'
                },
                selectable: true,
                selectHelper: true,
                editable: true,
                events: [
                    {
                        title: 'All Day Event',
                        start: new Date(y, m, 1)
                    },
                    {
                        title: 'Long Event',
                        start: new Date(y, m, d-5),
                        end: new Date(y, m, d-2)
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: new Date(y, m, d-3, 16, 0),
                        allDay: false
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: new Date(y, m, d+4, 16, 0),
                        allDay: false
                    },
                    {
                        title: 'Meeting',
                        start: new Date(y, m, d, 10, 30),
                        allDay: false
                    },
                    {
                        title: 'Lunch',
                        start: new Date(y, m, d, 12, 0),
                        end: new Date(y, m, d, 14, 0),
                        allDay: false
                    },
                    {
                        title: 'Birthday Party',
                        start: new Date(y, m, d+1, 19, 0),
                        end: new Date(y, m, d+1, 22, 30),
                        allDay: false
                    },
                    {
                        title: 'Click for Google',
                        start: new Date(y, m, 28),
                        end: new Date(y, m, 29),
                        url: 'http://google.com/'
                    }
                ],
                eventBackgroundColor: '#278ccf'
            });
            
            // handler to close the new event popup just for displaying purposes
            // more documentation for fullcalendar on http://arshaw.com/fullcalendar/
            $(".popup .close-pop").click(function () {
                $(".new-event").fadeOut("fast");
            });
        });
    </script>

</body>
</html>