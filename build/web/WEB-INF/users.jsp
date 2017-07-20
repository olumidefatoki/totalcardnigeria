<%-- 
    Document   : card_details
    Created on : Jun 2, 2017, 2:53:46 AM
    Author     : olumidefatoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        
        
        <title>Total Card | Card List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" type="text/css" href="./css/cloud-admin.css">
        <link rel="stylesheet" type="text/css" href="./css/default.css" id="skin-switcher">
        <link rel="stylesheet" type="text/css" href="./css/responsive.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" type="text/css">
        <link href="./css/font-awesome.min.css" rel="stylesheet">
        
        <!-- JQUERY UI-->
        <link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.10.3.custom.min.css">
        <!-- DATE RANGE PICKER -->
        <link rel="stylesheet" type="text/css" href="./css/daterangepicker-bs3.css">
        <!-- DATA TABLES -->
        <link href="./css/jquery.dataTables.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="./css/TableTools.min.css">

        <!-- FONTS -->
        <link href="./css/css" rel="stylesheet" type="text/css">
        <style type="text/css">
            tfoot input {
                width: 100%;
                padding: 3px;
                box-sizing: border-box;
            }
            td.highlight {
                font-weight: bold;
                color: blue;
            }
        </style>
    </head>
    <body cz-shortcut-listen="true">
        <!-- HEADER -->
        <style type="text/css">
.dataTables_filter {
     display: none;
}
</style>

<header class="navbar clearfix" id="header">
    <div class="container">
        <div class="navbar-brand">
            <!-- COMPANY LOGO -->
            <a href="#">
                <img src="./images/TotalCard_logo.png" alt="Total Logo" class="img-responsive" height="30" width="120">
            </a>
            <!-- /COMPANY LOGO -->
            <!-- TEAM STATUS FOR MOBILE -->
            <div class="visible-xs">
                <a href="#" class="team-status-toggle switcher btn dropdown-toggle">
                    <i class="fa fa-users"></i>
                </a>
            </div>
            <!-- /TEAM STATUS FOR MOBILE -->
            <!-- SIDEBAR COLLAPSE -->
            <div id="sidebar-collapse" class="sidebar-collapse btn">
                <i class="fa fa-bars" data-icon1="fa fa-bars" data-icon2="fa fa-bars"></i>
            </div>
            <!-- /SIDEBAR COLLAPSE -->
        </div>
        <%@include  file="/WEB-INF/dashboard_header.jspf" %>
        <!-- NAVBAR LEFT -->
        
        <!-- /NAVBAR LEFT -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <span class="h3" style="color: white;padding: 20px;font-weight: bolder; margin-left: 200px;">&nbsp;</span>
        <ul class="nav navbar-nav pull-right">
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <%@include  file="/WEB-INF/user_info.jspf" %>
            <!-- END USER LOGIN DROPDOWN -->
        </ul>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- TEAM STATUS -->
    <div class="container team-status" id="team-status" style="position: relative; margin-top: 0px; display: none;">
        <div id="scrollbar">
            <div class="handle">
            </div>
        </div>
        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: 100%; height: auto;"><div id="teamslider" style="overflow: hidden; width: 100%; height: auto;">
        </div><div class="slimScrollBar ui-draggable" style="background: rgb(255, 255, 255); height: 5px; position: absolute; bottom: 1px; opacity: 0.5; display: block; border-radius: 5px; z-index: 99;"></div><div class="slimScrollRail" style="width: 100%; height: 5px; position: absolute; bottom: 1px; display: none; border-radius: 5px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90;"></div></div>
    </div>
    <!-- /TEAM STATUS -->
</header>

        <!--/HEADER -->

        <!-- PAGE -->
        <section id="page">
            <!-- SIDEBAR -->
         <%@include  file="/WEB-INF/sidebar.jspf" %>    
            <!-- /SIDEBAR -->
            <div id="main-content">

                <div class="container">
                    <div class="row">
                        <div id="content" class="col-lg-12">
                            <!-- PAGE HEADER-->
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="page-header">
                                        <!-- STYLER -->

                                        <!-- /STYLER -->
                                        <!-- BREADCRUMBS -->
                                        <ul class="breadcrumb">
                                            <li>
                                                <i class="fa fa-home"></i>
                                                <a href="#">Home</a>
                                            </li>
                                            <li>Customers</li>
                                        </ul>
                                        <!-- /BREADCRUMBS -->
                                        <div class="clearfix">
                                            <h3 class="content-title pull-left">Total Card Report</h3>

                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- BOX -->
                                    <div class="box-body big">
                                                                <form class="form-horizontal acc-ops" action="#">
                                                                    <input  type="hidden" name="Type" value="36"/>
                                                                    <input  type="hidden" name="id" value="${requestScope.Customer['id']}"/>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">Email</label> 
                                                                        <div class="col-sm-6">
                                                                            <input name="email" type="text" value="${requestScope.Customer['email']}" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-sm-2 control-label">Phone </label>
                                                                        <div class="col-sm-6">
                                                                            <textarea name="msisdn" class="form-control" rows="3">${requestScope.Customer['msisdn']}</textarea>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">name</label> 
                                                                        <div class="col-sm-6">
                                                                            <input name="fname" type="text" value="${requestScope.Customer['name']}" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">Gender:</label> 
                                                                        <div class="col-sm-6">

                                                                            <select id="gender" name ="gender" class="form-control"><option value="Male">Male</option><option value="Female">Female</option></select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">location</label> 
                                                                        <div class="col-sm-6">
                                                                            <select id="location" name ="location" class="form-control"> 
                                                                                <option value="">Select One</option>
                                                                                <option value="ABIA">ABIA</option>
                                                                                <option value="ADAMAWA">ADAMAWA</option>
                                                                                <option value="AKWA_IBOM">AKWA IBOM</option>
                                                                                <option value="ANAMBRA">ANAMBRA</option>
                                                                                <option value="BAUCHI">BAUCHI</option>
                                                                                <option value="BAYELSA">BAYELSA</option>
                                                                                <option value="BENUE">BENUE</option>
                                                                                <option value="BORNO">BORNO</option>
                                                                                <option value="CROSS_RIVER">CROSS RIVER</option>
                                                                                <option value="DELTA">DELTA</option>
                                                                                <option value="EBONYI">EBONYI</option>
                                                                                <option value="EDO">EDO</option>
                                                                                <option value="EKITI">EKITI</option>
                                                                                <option value="ENUGU">ENUGU</option>
                                                                                <option value="FCT">FCT</option>
                                                                                <option value="GOMBE">GOMBE</option>
                                                                                <option value="IMO">IMO</option>
                                                                                <option value="JIGAWA">JIGAWA</option>
                                                                                <option value="KADUNA">KADUNA</option>
                                                                                <option value="KANO">KANO</option>
                                                                                <option value="KATSINA">KATSINA</option>
                                                                                <option value="KEBBI">KEBBI</option>
                                                                                <option value="KOGI">KOGI</option>
                                                                                <option value="KWARA">KWARA</option>
                                                                                <option value="LAGOS">LAGOS</option>
                                                                                <option value="NASSARAWA">NASSARAWA</option>
                                                                                <option value="NIGER">NIGER</option>
                                                                                <option value="OGUN">OGUN</option>
                                                                                <option value="ONDO">ONDO</option>
                                                                                <option value="OSUN">OSUN</option>
                                                                                <option value="OYO">OYO</option>
                                                                                <option value="PLATEAU">PLATEAU</option>
                                                                                <option value="RIVERS">RIVERS</option>
                                                                                <option value="SOKOTO">SOKOTO</option>
                                                                                <option value="TARABA">TARABA</option>
                                                                                <option value="YOBE">YOBE</option>
                                                                                <option value="ZAMFARA">ZAMFARA</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-12">
                                                                        <div class="col-md-offset-3 col-md-9">
                                                                            <button type="submit" class="btn btn-primary">Submit</button>                         
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </div>
                                    <!-- /BOX -->
                                </div>
                            </div>
                            
                        </div><!-- /CONTENT-->
                    </div>
                </div>
            </div>
        </section>
        <!--/PAGE -->
        <!-- JAVASCRIPTS -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- JQUERY -->
        <script type="text/javascript" src="./js/jquery-2.0.3.min.js"></script>
        <!--<script src="media/js/jquery.js" type="text/javascript"></script>-->
        <!-- JQUERY UI-->
        <script type="text/javascript" src="./js/jquery-ui-1.10.3.custom.min.js"></script>
        <!-- BOOTSTRAP -->
        <script type="text/javascript" src="./js/bootstrap.min.js"></script>


        <!-- DATE RANGE PICKER -->
        <script type="text/javascript" src="./js/moment.min.js"></script>

        <script type="text/javascript" src="./js/daterangepicker.min.js"></script>
        <!-- SLIMSCROLL -->
        <script type="text/javascript" src="./js/jquery.slimscroll.min.js"></script>
        <script type="text/javascript" src="./js/slimScrollHorizontal.min.js"></script>
        <!-- BLOCK UI -->
        <script type="text/javascript" src="./js/jquery.blockUI.min.js"></script>
        <!-- DATA TABLES -->
        <script src="./js/jquery.dataTables.js" type="text/javascript"></script>
        <!--<script type="text/javascript" src="js/datatables/media/js/jquery.dataTables.min.js"></script>-->
        <script type="text/javascript" src="./js/datatables.min.js"></script>

        <script type="text/javascript" src="./js/TableTools.min.js"></script>
        <script type="text/javascript" src="./js/ZeroClipboard.min.js"></script>
        <!-- COOKIE -->
        <script type="text/javascript" src="./js/jquery.cookie.min.js"></script>
        <!-- CUSTOM SCRIPT -->
        <script type="text/javascript" src="./js/script.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function () {
                App.setPage("Users");  //Set current page
                App.init(); //Initialise plugins and elements

               

                 
          

               
            });
        </script>

    
</body></html>
