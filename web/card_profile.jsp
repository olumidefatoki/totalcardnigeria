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
                                            <h4><i class="fa fa-book"></i>Customer Profile</h4>
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
                                                <ul class="nav nav-tabs">
                                                    <li class="active"><a href="#profile-overview" data-toggle="tab"><i class="fa fa-dot-circle-o"></i> <span class="hidden-inline-mobile">Card Details</span></a></li>
                                                    <li><a href="#edit-profile" data-toggle="tab"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">Edit Card Details</span></a></li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane active fade in" id="profile-overview">
                                                        <div class="row">
                                                            <div class="col-md-9">
                                                                <div id="contact-card" class="panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <h2 class="panel-title">${requestScope.Customer['name']}</h2>
                                                                    </div>
                                                                    <div class="panel-body">
                                                                        <div id="card" class="row">
                                                                            <div class="col-md-3 col-md-offset-1 headshot">
                                                                                <div class="fa fa-user bigg-icon" height="200" width="200"></div>
                                                                            </div>
                                                                            <div class="col-md-8">
                                                                                <table class="table table-hover">
                                                                                    <tbody>

                                                                                        <tr>
                                                                                            <td><i class="fa fa-id-card"></i> Card Number</td>
                                                                                            <td id="card-name">${requestScope.Customer['card_number']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-id-card"></i> CVN</td>
                                                                                            <td>${requestScope.Customer['cvn']} </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-phone"></i> Phone</td>
                                                                                            <td>${requestScope.Customer['msisdn']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-male"></i> Gender</td>
                                                                                            <td>${requestScope.Customer['gender']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-envelope"></i> Email</td>
                                                                                            <td>${requestScope.Customer['email']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-map-marker"></i> Location</td>
                                                                                            <td>${requestScope.Customer['location']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-calendar-0"></i> Expiry Date</td>
                                                                                            <td>${requestScope.Customer['expiry_date']}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><i class="fa fa-calendar-o"></i> Date Registered</td>
                                                                                            <td>${requestScope.Customer['creation_date']} </td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div id="list-toggle" class="col-md-3">
                                                                <div class="list-group">
                                                                    <a href="#tab1" data-toggle="tab" class="list-group-item active">
                                                                        <i class="fa fa-suitcase"></i> Card  Menu
                                                                    </a>
                                                                    <a href="handler?Type=33&id=${requestScope.Customer['id']}&cardnumber=${requestScope.Customer['card_number']}"  class="list-group-item "><i class="fa fa-money"></i>Card Balance</a>
                                                                    <a href="handler?Type=32&id=${requestScope.Customer['id']}&cardnumber=${requestScope.Customer['card_number']}"  class="list-group-item "><i class="fa fa-list fa-fw"></i>Card Info </a>
                                                                     <a href="handler?Type=30&PhoneNumber=${requestScope.Customer['msisdn']}&cardnumber=${requestScope.Customer['card_number']}"  data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-unlock"></i> Send Pin</a>
                                                                    <a href="handler?Type=29&cardnumber=${requestScope.Customer['card_number']}"  data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-unlock"></i> Unblock Card</a>
                                                                    
                                                                    <a href="#" data-toggle="modal" class="list-group-item" data-target="#blacklist"><i class="fa fa-lock"></i> Blacklist Card</a>
                                                                </div>
                                                                <div class="modal fade maccops" id="blacklist" tabindex="-1" role="dialog" aria-labelledby=FundAccount aria-hidden="true">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                            <span aria-hidden="true">&times;</span>
                                                                                        </button>
                                                                                        <h4 class="modal-title"><i class="fa fa-lock"></i> Card Blacklist</h4>
                                                                                    </div>

                                                                                    <div class="modal-body">
                                                                                        <!-- The form is placed inside the body of modal -->
                                                                                        <form action="#" id="fundwallet" method="post" class="form-horizontal acc-ops">
                                                                                            <input  type="hidden" name="Type" value="1"/>
                                                                                            <input  type="hidden" name="cardNumber" value="${requestScope.Customer['card_number']}"/>
                                                                                            
                                                                                            <div class="form-group" required>
                                                                                                <label class="col-xs-3 control-label">Reason</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <select name="reason" id="bank" class="col-md-12">
                                                                                                        <option value="BL001">Payment Problem</option>
                                                                            <option value="BL002">Lost</option>
                                                                            <option value="BL003">Stolen</option>
                                                                            <option value="BL004">Wrong pin</option>
                                                                            <option value="BL005">Temporary locked</option>
                                                                            <option value="BL006">Invalid</option>
                                                                            <option value="BL007">E-purse locked</option>
                                                                            <option value="BL008">Validation exceeded</option>
                                                                            <option value="BL009">Downloaded credit issue</option>
                                                                            <option value="BL010">Invalidation</option>
                                                                            <option value="BL012">Revalidation</option>
                                                                            <option value="BL022">Incur exceeded</option>
                                                                                                    </select>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <div class="col-xs-5 col-xs-offset-3">
                                                                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                                                </div>
                                                                                            </div>
                                                                                        </form>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane" id="edit-profile">
                                                        <div class="scroller" data-height="380px">
                                                            <div class="box-body big">
                                                                <form class="form-horizontal" action="handler">
                                                                    <input  type="hidden" name="Type" value="31"/>
                                                                    <input  type="hidden" name="id" value="${requestScope.Customer['id']}"/>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">Email</label> 
                                                                        <div class="col-sm-6">
                                                                            <input name="email" type="text" class="form-control" required="true" value="${requestScope.Customer['email']}" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-sm-2 control-label">Phone </label>
                                                                        <div class="col-sm-6">
                                                                            <textarea name="msisdn" class="form-control" class="form-control" required="true" rows="3">${requestScope.Customer['msisdn']}</textarea>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">name</label> 
                                                                        <div class="col-sm-6">
                                                                            <input name="fname" type="text" class="form-control" required="true" value="${requestScope.Customer['name']}" class="form-control">
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
        <script>
            $(".acc-ops").submit(function (event) {
                event.preventDefault();
                var values = $(this).serialize();
                /* Stop form from submitting normally */
                bootbox.confirm("Are you sure you want to proceed?", function (result) {
                    $('.maccops').modal('hide');
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
                            url: "handler?Type=28",
                            data: values,
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
