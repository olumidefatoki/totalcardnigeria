<%-- 
    Document   : index
    Created on : Mar 1, 2017, 6:56:21 AM
    Author     : olumidefatoki
--%>

<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <title>Total | Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- STYLESHEETS --><!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
        <link rel="stylesheet" type="text/css" href="./css/cloud-admin.css">
        <link rel="stylesheet" type="text/css" href="./css/default.css" id="skin-switcher">
        <link rel="stylesheet" type="text/css" href="./css/responsive.css">

        <link href="./css/font-awesome.min.css" rel="stylesheet">
        <!-- JQUERY UI-->
        <link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.10.3.custom.min.css">
        <!-- DATE RANGE PICKER -->
        <!--<link rel="stylesheet" type="text/css" href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />-->
        <!-- DATE PICKER -->
        <link rel="stylesheet" type="text/css" href="./css/default.min.css">
        <link rel="stylesheet" type="text/css" href="./css/themen.css">
        <!-- DATA TABLES -->
        <link href="./css/jquery.dataTables.css" rel="stylesheet" type="text/css">
        <!--<link href="media/css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>-->
        <!--        <link rel="stylesheet" type="text/css" href="js/datatables/media/css/jquery.dataTables.min.css" />
                <link rel="stylesheet" type="text/css" href="js/datatables/media/assets/css/datatables.min.css" />-->
        <link rel="stylesheet" type="text/css" href="./css/TableTools.min.css">

        <!-- FONTS -->
        
    </head>
    <body class="login" cz-shortcut-listen="true">	
        <!-- PAGE -->
        <section id="page">
            <!-- HEADER -->
            <header>
                <!-- NAV-BAR -->
                <div class="container">
                    <div class="row">
                        <!--                        <div class="col-md-4 col-md-offset-4">
                                                    <div id="logo">
                                                        <a href="login.jsp"><img src="/resources/img/logo/TING - Cellulant Logo.png"  height="40" alt=""/></a>
                                                    </div>
                                                </div>-->
                    </div>
                </div>
                <!--/NAV-BAR -->
            </header>
            <!--/HEADER -->
            <!-- LOGIN -->
            <section id="login" class="visible">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">

                            <div class="login-box-plain">
                                <div class="col-md-4">
                                    <a href="#"><img src="./images/TotalCard_logoII.png" height="80" width="80" alt=""></a>
                                </div>
                                
                                <h3 class="col-md-8">Sign In</h3>
                                <div class="divide-100"></div>
                               
                               
                                <% if(request.getAttribute("ErrorMsg") != null) { %>
                                        <div class="alert alert-block alert-border-left alert-danger fade in">
                                            <a class="close" data-dismiss="alert" href="#" aria-hidden="true">×</a>
                                            <p></p> ${ErrorMsg}.<p></p>
                                        </div>
                                        <% }%>
                               
                                <form role="form" action="login" method="POST">
                                    <div class="form-group">
                                        <label for="login-email">User Name</label>
                                        <i class="fa fa-envelope"></i>
                                        <input type="text" name="Username" class="form-control" id="login-email">
                                    </div>
                                    
                                    <div class="form-group"> 
                                        <label for="login-password">Password</label>
                                        <i class="fa fa-lock"></i>
                                        <input type="password" name="Password" class="form-control" id="login-password">
                                    </div>
                                    <div class="form-actions">
                                        
                                        <button type="submit" class="btn btn-info">Login</button>                                       
                                    </div>
                                </form>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--/LOGIN -->
            <!-- FORGOT PASSWORD -->
            
            <!-- FORGOT PASSWORD -->
        </section>
        <!--/PAGE -->
        <!-- JAVASCRIPTS -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- JQUERY -->
        <script src="./js/jquery-2.0.3.min.js"></script>
        <!-- JQUERY UI-->
        <script src="./js/jquery-ui-1.10.3.custom.min.js"></script>
        <!-- BOOTSTRAP -->
        <script src="./js/bootstrap.min.js"></script>


        <!-- UNIFORM -->
        <script type="text/javascript" src="./js/jquery.uniform.min.js"></script>
        <!-- CUSTOM SCRIPT -->
        <script src="./js/script.js"></script>
        
    
</body></html>
