<%-- 
    Document   : card_details
    Created on : Jun 2, 2017, 2:53:46 AM
    Author     : olumidefatoki
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <title>Smart Admin | Card Balance</title>
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
                                            <li>Card</li>
                                        </ul>
                                        <!-- /BREADCRUMBS -->
                                        <div class="clearfix">
                                            <h3 class="content-title pull-left">Card Profile</h3>

                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box border blue">
                                        <div class="box-title">
                                            <h4><i class="fa fa-book"></i>Card Balance</h4>
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
                                                        <div class="row">
                                                                    <div class="panel-body">
                                                                       <h2 > Card Number : ${requestScope.Customer['CardNumber']}</h2> 
                                                                       <h2 > Customer ID : ${requestScope.Customer['CustomerID']}</h2> 
                                                                       <h2 > Customer Name : ${requestScope.Customer['CustomerName']}</h2> 
                                                                       <h2 >  ${requestScope.Customer['HolderName']}</h2> 
                                                                       <h2 > Card Status : ${requestScope.Customer['StatusName']}</h2> 
                                                                       <h2 >  Valid Till : ${requestScope.Customer['ValidEndDate']}</h2> 
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
        <!-- JAVASCRIPTS -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- JQUERY -->
        <script src="./js/jquery-2.0.3.min.js"></script>
        <!-- JQUERY UI-->
        <script src="./js/jquery-ui-1.10.3.custom.min.js"></script>
        <!-- BOOTSTRAP -->
        <script src="./js/bootstrap.min.js"></script>


        <!-- DATE RANGE PICKER -->
        <script src="./js/moment.min.js"></script>

        <script src="./js/daterangepicker.min.js"></script>
        <!-- SLIMSCROLL -->
        <script type="text/javascript" src="./js/jquery.slimscroll.min.js"></script><script type="text/javascript" src="./js/slimScrollHorizontal.min.js"></script>
        <!-- BLOCK UI -->
        <script type="text/javascript" src="./js/jquery.blockUI.min.js"></script>
        <!-- BOOTSTRAP SWITCH -->
        <script type="text/javascript" src="./js/bootstrap-switch.min.js"></script>
        <!-- BOOTBOX -->
        <script type="text/javascript" src="./js/bootbox.min.js"></script>
        <!-- HUBSPOT MESSENGER -->
        <script type="text/javascript" src="./js/messenger.min.js"></script>
        <script type="text/javascript" src="./js/messenger-theme-flat.js"></script>
        <script type="text/javascript" src="./js/messenger-theme-future.js"></script>
        <!-- MAGIC SUGGEST -->
        <script type="text/javascript" src="./js/magicsuggest-1.3.1-min.js"></script>
        <!-- TIMEAGO -->
        <script type="text/javascript" src="./js/jquery.timeago.min.js"></script>
        <!-- DATE PICKER -->
        <script type="text/javascript" src="./js/picker.js"></script>
        <script type="text/javascript" src="./js/picker.date.js"></script>
        <script type="text/javascript" src="./js/picker.time.js"></script>
        <!-- COLOR PICKER -->
        <script type="text/javascript" src="./js/bootstrap-colorpicker.min.js"></script>
        <!-- RATY -->
        <script type="text/javascript" src="./js/jquery.raty.min.js"></script>
        <!-- COOKIE -->
        <script type="text/javascript" src="./jsjquery.cookie.min.js"></script>
        <!-- CUSTOM SCRIPT -->
        <script src="./jsscript.js"></script>
        
        <script>
            $(".sync-request").click(function (event) {
                event.preventDefault();
                var target = $(this).attr('href');
                /* Stop form from submitting normally */
                bootbox.confirm("Are you sure you want to proceed?", function (result) {
                    if (result) {
                        //Set theme
                        Messenger.options = {
                            extraClasses: 'messenger-fixed messenger-on-top',
                            theme: 'future'
                        }
                        //Call
                        var messgr = Messenger().post({
                            message: "Your request is processing...",
                            showCloseButton: false
                        });
//                        alert(target);
                        /* Send the data using post and put the results in a div */
                        $.ajax({
                            url: target,
//                            url: "/Operations",
                            type: "post",
                            dataType: "xml",
                            success: function (data) {

                                var stat = $(data).find('status').text();
                                var msg = $(data).find('message').text();
//                        alert(stat);
                                if (stat === "1") {
                                    //Call
                                    messgr.update({
                                        message: msg,
                                        type: 'success',
                                        showCloseButton: true,
                                        actions: false
                                    });
                                } else {
                                    //Call
                                    return messgr.update({
                                        message: "Request processing failed..." + msg,
                                        type: 'error',
                                        showCloseButton: true,
                                        actions: false
                                    });
                                }

                            },
                            error: function (err) {
                                //Call

                                return messgr.update({
                                    message: "Request is processing failed...",
                                    type: 'error',
                                    showCloseButton: true,
                                    actions: false
                                });
                            }

                        });
                    }
                });
            });
        </script>

    </body></html>
