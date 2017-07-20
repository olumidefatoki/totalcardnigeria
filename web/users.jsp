<%-- 
    Document   : card_details
    Created on : Jun 2, 2017, 2:53:46 AM
    Author     : olumidefatoki
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <title>Smart Admin | Card Profile</title>
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
        <link rel="stylesheet" type="text/css" href="./css/daterangepicker-bs3.css">
        <!-- BOOTSTRAP SWITCH -->
        <link rel="stylesheet" type="text/css" href="./css/bootstrap-switch.min.css">
        <!-- HUBSPOT MESSENGER -->
        <link rel="stylesheet" type="text/css" href="./css/messenger.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-spinner.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-theme-air.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-theme-block.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-theme-flat.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-theme-future.min.css">
        <link rel="stylesheet" type="text/css" href="./css/messenger-theme-ice.min.css">
        <!-- MAGIC SUGGEST -->
        <link rel="stylesheet" type="text/css" href="./css/magicsuggest-1.3.1-min.css">
        <!-- DATE PICKER -->
        <link rel="stylesheet" type="text/css" href="./css/default.min.css">
        <link rel="stylesheet" type="text/css" href="./css/default.date.min.css">
        <link rel="stylesheet" type="text/css" href="./css/default.time.min.css">
        <!-- COLOR PICKER -->
        <link rel="stylesheet" type="text/css" href="./css/colorpicker.min.css">
        <!-- FONTS -->
        <link href="./css/css" rel="stylesheet" type="text/css">
        <style type="text/css">
            .bigg-icon {
                font-size: 120px;
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
                                            <li>User</li>
                                        </ul>
                                        <!-- /BREADCRUMBS -->
                                        <div class="clearfix">
                                            <h3 class="content-title pull-left">User List</h3>

                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box border blue">
                                        <div class="box-title">
                                            <h4><i class="fa fa-book"></i>User Profile</h4>
                                            <div class="tools">
                                                <a href="#box-config" data-toggle="modal" class="config">
                                                    <i class="fa fa-cog"></i>
                                                </a>
                                                <a href="javascript:;" class="reload">
                                                    <i class="fa fa-refresh"></i>
                                                </a>
                                                <a href="javascript:;" class="collapse">
                                                    <i class="fa fa-chevron-up"></i>
                                                </a>
                                                <a href="javascript:;" class="remove">
                                                    <i class="fa fa-times"></i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="tabbable">
                                                
                                                <div class="tab-content">
                                                    <div class="tab-pane active fade in" id="profile-overview">
                                                        <div class="row">
                                                            <div class="col-md-9">
                                                                <div id="contact-card" class="panel panel-default">
                                                                   
                                                                    <div class="panel-body">
                                                                        <div id="card" class="row">
                                                                            
                                                                           
                                                                                <div class="box-body">
                                           
                                            <table id="cust_datatable" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                                  
                                                                  <th>First Name</th>
                                                                   <th>Last Name</th>
                                                                  <th>Email</th>
                                                                  <th>User Group</th>
                                                                  <th>Status</th>
                                                                  <th>Creation Date</th>
                                                                  <th>Action</th>
                                                    </tr>
                                                </thead>

                                                <tfoot>
                                                    <tr>
                                                                  
                                                                  <th>First Name</th>
                                                                   <th>Last Name</th>
                                                                  <th>Email</th>
                                                                  <th>User Group</th>
                                                                  <th>Status</th>
                                                                  <th>Creation Date</th>
                                                                  <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                                                            
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div id="list-toggle" class="col-md-3">
                                                                <div class="list-group">
                                                                    <a href="#tab1" data-toggle="tab" class="list-group-item active">
                                                                        <i class="fa fa-suitcase"></i> User  Menu
                                                                    </a>
                                                                    <a href="new_user.jsp"  class="list-group-item "><i class="fa fa-money"></i>New User</a>
                                                                    </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="footer-tools">
                                <span class="go-top">
                                    <i class="fa fa-chevron-up"></i> Top
                                </span>
                            </div>
                        </div><!-- /CONTENT-->
                    </div>
                </div>
            </div>
        </section>
        <!--/PAGE -->
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
        <script type="text/javascript" src="./js/script.js"></script>>
        
        <script type="text/javascript">
            jQuery(document).ready(function () {
                App.setPage("Customer");  //Set current page
                App.init(); //Initialise plugins and elements

                $("#searchfilter").click(function () {
                    var col_text_0 = $('#card_number').val();
                    var col_text_1 = $('#cvn').val(); 
                    table
                            .column(1).search(col_text_0)
                            .column(2).search(col_text_1)                            
                            .draw();
                });

                // DataTable
                var table = $('#cust_datatable').DataTable({
                    "pageLength": 15,
                    "processing": true,
                    "serverSide": true,
//                    "searching": false,
                    "ajax": "Controller?Type=34&Mode=1",
                     "columns": [
                                    {"data": 1},
                                    {"data": 2},
                                    {"data": 3},
                                    {"data": 4},
                                    {"data": 5},
                                    {"data": 6},
                                    {"data": 7}
                                    
                    ],
                    "createdRow": function (row, data, index) {                                  
                        $('td', row).eq(6).html('<a href="#?Type=37&id=' + data[0] + '">' +
                               '<i class="fa fa-plus-circle"></i> VIEW MORE' + '</a>');
                    }
                     
                }); 
          
            });
        </script>

    </body></html>
