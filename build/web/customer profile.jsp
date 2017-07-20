<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <title>Smart Admin | Customer Profile</title>
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
            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/dashboard.jsp">
                <img src="./images/Clogo.jpg" alt="Cellulant Admin Logo" class="img-responsive" height="30" width="120">
            </a>
            <!-- /COMPANY LOGO -->
            <!-- TEAM STATUS FOR MOBILE -->
            <div class="visible-xs">
                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" class="team-status-toggle switcher btn dropdown-toggle">
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
        <!-- NAVBAR LEFT -->
        <ul class="nav navbar-nav pull-left hidden-xs" id="navbar-left">
            <li class="dropdown">
                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-cog"></i>
                    <span class="name">Skins</span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu skins">
                    <li class="dropdown-title">
                        <span><i class="fa fa-leaf"></i> Theme Skins</span>
                    </li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="default">Subtle (default)</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="night">Night</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="earth">Earth</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="utopia">Utopia</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="nature">Nature</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-skin="graphite">Graphite</a></li>
                </ul>
            </li>
        </ul>
        <!-- /NAVBAR LEFT -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <span class="h3" style="color: white;padding: 20px;font-weight: bolder; margin-left: 200px;">RUFIN</span>
        <ul class="nav navbar-nav pull-right">
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <li class="dropdown user" id="header-user">
                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" class="dropdown-toggle" data-toggle="dropdown">
                    <!--<img alt="" src="img/avatars/avatar3.jpg" />-->
                    <span class="fa fa-user-md big-icon" height="70" width="70"></span>
                    <span class="username"> Olumide Fatoki (Technology)</span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#"><i class="fa fa-user"></i> My Profile</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#"><i class="fa fa-cog"></i> Account Settings</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/change_password.jsp"><i class="fa fa-lock"></i> Change Password</a></li>
                    <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemOperations?Operation=6"><i class="fa fa-power-off"></i> Log Out</a></li>
                </ul>
            </li>
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
<!--
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/WalletAdmin4/Admin/dashboard.jsp">Cellulant Wallet 4.0</a>
        </div>
        
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Olumide Fatoki</a></li>
                <li><a href="#">Technology</a></li>
                <li><a href="SystemOperations?Operation=6">Log Out</a></li>
            </ul>
        </div>
    </div>
</div>    
--> 
        <!--/HEADER -->

        <!-- PAGE -->
        <section id="page">
            <!-- SIDEBAR -->
            <div id="sidebar" class="sidebar">
    <div class="sidebar-menu nav-collapse">
        <div class="divide-20"></div>
        <!-- SEARCH BAR -->
        <div id="search-bar">
            <input class="search" type="text" placeholder="Search"><i class="fa fa-search search-icon"></i>
        </div>
        <ul>
            
            <li class="active">
                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/dashboard.jsp">
                    <i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">Dashboard</span>
                    <span class="selected"></span>
                </a>					
            </li>
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/customers.jsp"><i class="fa fa-users fa-fw"></i> 
                    <span class="menu-text">Customers 
                    </span>
                </a>
            </li>
            
            
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="fa fa-road fa-fw"></i> <span class="menu-text">Aggregators &amp; Agents</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/aggregators.jsp"><span class="sub-menu-text">Aggregators</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/agents.jsp"><span class="sub-menu-text">Agents</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/create_aggregator.jsp"><span class="sub-menu-text">Create an aggregator</span></a></li>

                </ul>
            </li>
            
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/transactions.jsp"><i class="fa fa-list fa-fw"></i> 
                    <span class="menu-text">Transactions 
                    </span>
                </a>
            </li>
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/customer_accounts.jsp"><i class="fa fa-book fa-fw"></i> 
                    <span class="menu-text">Customer Accounts 
                    </span>
                </a>
            </li>
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/checker_inbox.jsp"><i class="fa fa-folder-open fa-fw"></i> 
                    <span class="menu-text">Requests Inbox 
                    </span>
                </a>
            </li>
            
            
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="fa fa-bar-chart-o fa-fw"></i> <span class="menu-text">Reports</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/custom_reports.jsp"><span class="sub-menu-text">Custom Reports</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/statement.jsp"><span class="sub-menu-text">Statement</span></a></li>

                </ul>
            </li>
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/batch_inbox.jsp"><i class="fa fa-folder-open fa-fw"></i> 
                    <span class="menu-text">Batch Inbox 
                    </span>
                </a>
            </li>
            
            
            <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/revenue.jsp"><i class="fa fa-credit-card fa-fw"></i> 
                    <span class="menu-text">Revenue 
                    </span>
                </a>
            </li>
            
            
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="fa fa-road fa-fw"></i> <span class="menu-text">Audit Trail &amp; Logs</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/audit_trail.jsp"><span class="sub-menu-text">Audit Trail</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#audit_logs.jsp"><span class="sub-menu-text">Audit Logs</span></a></li>

                </ul>
            </li>
            
            
            
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="fa fa-th-large fa-fw"></i> <span class="menu-text">Fees &amp; Commission</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/fees.jsp"><span class="sub-menu-text">Transaction Fees</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/fees_sharing_table.jsp"><span class="sub-menu-text">Fee Sharing Structure</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/fees_range.jsp"><span class="sub-menu-text">Transaction Fees(Range)</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/fees_sharing_aggregator.jsp"><span class="sub-menu-text">Fee Sharing (Aggregator Model)</span></a></li>
                </ul>
            </li>
            
            
            
            <li class="has-sub">
                <a href="javascript:;" class="">
                    <i class="fa fa-tags fa-fw"></i> <span class="menu-text">Admin Operations</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/upload_customers.jsp"><span class="sub-menu-text">Upload Customers</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/upload_mfb_customers.jsp"><span class="sub-menu-text">Upload Customers (MFB)</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/upload_agents.jsp"><span class="sub-menu-text">Upload Agents</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/disburse_funds.jsp"><span class="sub-menu-text">Disburse Funds (Direct Credit)</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/disburse_from_wallet.jsp"><span class="sub-menu-text">Disburse Funds from Wallet</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/disburse_programs.jsp"><span class="sub-menu-text">Disbursement programs</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/batch_report.jsp"><span class="sub-menu-text">Batch Report</span></a></li>
                    <li><a class="" href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/airtime_retry.jsp"><span class="sub-menu-text">Failed Airtime Retry</span></a></li>
                    
                </ul>
            </li>
            
            
        </ul>
        <!-- /SIDEBAR MENU -->
    </div>
</div> 
            <!-- /SIDEBAR -->
            <div id="main-content">
                <!-- SAMPLE BOX CONFIGURATION MODAL FORM-->
                <div class="modal fade" id="box-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title">Box Settings</h4>
                            </div>
                            <div class="modal-body">
                                Here goes box setting content.
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /SAMPLE BOX CONFIGURATION MODAL FORM-->
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
                                                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/index.html">Home</a>
                                            </li>
                                            <li>
                                                <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#">Customers</a>
                                            </li>
                                            <li>Customer Profile</li>
                                        </ul>
                                        <!-- /BREADCRUMBS -->
                                        <div class="clearfix">
                                            <h3 class="content-title pull-left">Customer</h3>
                                        </div>
                                        <div class="description">Name,Contact Details, etc...</div>
                                    </div>
                                </div>
                            </div>
                            <!-- /PAGE HEADER -->
                            <!-- SAMPLE -->
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- BOX -->
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box border blue">
                                                <div class="box-title">
                                                    <h4><i class="fa fa-book"></i>Customer Profile</h4>
                                                    <div class="tools">
                                                        <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#box-config" data-toggle="modal" class="config">
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
                                                            <li class="active"><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#profile-overview" data-toggle="tab"><i class="fa fa-dot-circle-o"></i> <span class="hidden-inline-mobile">Profile Overview</span></a></li>
                                                            <li><a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#edit-profile" data-toggle="tab"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">Edit Profile</span></a></li>
                                                        </ul>
                                                        <div class="tab-content">
                                                            <div class="tab-pane active fade in" id="profile-overview">
                                                                <div class="row">
                                                                    <div class="col-md-9">
                                                                        <div id="contact-card" class="panel panel-default">
                                                                            <div class="panel-heading">
                                                                                <h2 class="panel-title">INNOCENT EREMIOKHALE</h2>
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
                                                                                                    <td><i class="fa fa-user-md"></i> Name</td>
                                                                                                    <td id="card-name">INNOCENT EREMIOKHALE</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-sun-o"></i> KYC</td>
                                                                                                    <td id="card-name">0</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-home"></i> Address</td>
                                                                                                    <td> </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-phone"></i> Phone</td>
                                                                                                    <td>2347052613123</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-cogs"></i> Account State</td>
                                                                                                    <td>1</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-envelope"></i> Email</td>
                                                                                                    <td>Not supplied</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-briefcase"></i> Occupation</td>
                                                                                                    <td>Not supplied</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-money"></i> Balance</td>
                                                                                                    <td></td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td><i class="fa fa-calendar-o"></i> Date Registered</td>
                                                                                                    <td>Feb 24, 2017</td>
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
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#tab1" data-toggle="tab" class="list-group-item active">
                                                                                <i class="fa fa-suitcase"></i> Customer Admin Menu
                                                                            </a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/Operations?Operation=0&amp;Type=1&amp;EntityType=C&amp;PhoneNumber=2347052613123" data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-unlock-alt"></i> Reset PIN</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/Operations?Operation=0&amp;Type=2&amp;EntityType=C&amp;PhoneNumber=2347052613123" data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-sun-o"></i> Resend Secret Answer</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/Operations?Operation=0&amp;Type=3&amp;EntityType=C&amp;PhoneNumber=2347052613123" data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-question-circle"></i> Resend Secret Question</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/Operations?Operation=0&amp;Type=4&amp;EntityType=C&amp;PhoneNumber=2347052613123" data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-check"></i> Activate Customer</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/Operations?Operation=0&amp;Type=5&amp;EntityType=C&amp;PhoneNumber=2347052613123" data-toggle="tab" class="list-group-item sync-request"><i class="fa fa-gavel"></i> Block Account</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-toggle="modal" class="list-group-item" data-target="#fundacc"><i class="fa fa-money"></i> Credit Wallet</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-toggle="modal" class="list-group-item" data-target="#upgradekyc"><i class="fa fa-user"></i> Update KYC</a>
                                                                            <a href="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" data-toggle="tab" class="list-group-item confirm-dialog"><i class="fa fa-arrows"></i> Others...</a>
                                                                        </div>
                                                                        <!-- Modal Fund account -->
                                                                        <div class="modal fade maccops" id="fundacc" tabindex="-1" role="dialog" aria-labelledby="FundAccount" aria-hidden="true">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                            <span aria-hidden="true">×</span>
                                                                                        </button>
                                                                                        <h4 class="modal-title"><i class="fa fa-money"></i> Fund Wallet</h4>
                                                                                    </div>

                                                                                    <div class="modal-body">
                                                                                        <!-- The form is placed inside the body of modal -->
                                                                                        <form action="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" id="fundwallet" method="post" class="form-horizontal acc-ops">
                                                                                            <input type="hidden" name="Type" value="1">
                                                                                            <input type="hidden" name="PhoneNumber" value="2347052613123">
                                                                                            <div class="form-group">
                                                                                                <label class="col-xs-3 control-label">Amount</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <input type="text" class="form-control" required="true" name="Amount" placeholder="e.g 1500">
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group" required="">
                                                                                                <label class="col-xs-3 control-label">Bank</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <select name="Bank" id="bank" class="col-md-12">
                                                                                                        <option value="">select bank</option>
                                                                                                        <option value="GTBank">GTBank</option>
                                                                                                        <option value="DIAMOND BANK">DIAMOND BANK</option>
                                                                                                        <option value="FCMB">FCMB</option>
                                                                                                        <option value="ECOBANK">ECOBANK</option>
                                                                                                        <option value="FIRST BANK">FIRST BANK</option>
                                                                                                        <option value="STERLING BANK">STERLING BANK</option>
                                                                                                        <option value="SKYE BANK">SKYE BANK</option>
                                                                                                        <option value="ZENITH BANK">ZENITH BANK</option>
                                                                                                        <option value="MAIN STREET">MAIN STREET</option>
                                                                                                        <option value="ACCESS BANK">ACCESS BANK</option>
                                                                                                        <option value="UBA">UBA</option>
                                                                                                        <option value="UNION BANK">UNION BANK</option>
                                                                                                        <option value="WEMA BANK">WEMA BANK</option>
                                                                                                        <option value="STANBIC IBTC">STANBIC IBTC</option>
                                                                                                        <option value="CITI BANK">CITI BANK</option>
                                                                                                        <option value="STANDARD CHARTERED">STANDARD CHARTERED</option>
                                                                                                        <option value="UNITY BANK">UNITY BANK</option>
                                                                                                        <option value="KEYSTONE BANK">KEYSTONE BANK</option>
                                                                                                        <option value="FIDELTY BANK">FIDELTY BANK</option>
                                                                                                        <option value="HERITAGE BANK">HERITAGE BANK</option>
                                                                                                        <option value="ENTERPRISE BANK">ENTERPRISE BANK</option>
                                                                                                        <option value="JAIZ BANK">JAIZ BANK</option>
                                                                                                        <option value="CPOS">CPOS</option>
                                                                                                    </select>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label class="col-xs-3 control-label">Remark</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <textarea rows="2" cols="5" name="Remark" class="form-control"></textarea>
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
                                                                        <div class="modal fade maccops" id="upgradekyc" tabindex="-1" role="dialog" aria-labelledby="UpgradeKYC" account="" aria-hidden="true">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                            <span aria-hidden="true">×</span>
                                                                                        </button>
                                                                                        <h4 class="modal-title"><i class="fa fa-user"></i> Update KYC</h4>
                                                                                    </div>

                                                                                    <div class="modal-body">
                                                                                        <!-- The form is placed inside the body of modal -->
                                                                                        <form action="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#" id="kycform" method="post" class="form-horizontal acc-ops">
                                                                                            <input type="hidden" name="Type" value="2">
                                                                                            <input type="hidden" name="PhoneNumber" value="2347052613123">
                                                                                            <div class="form-group">
                                                                                                <label class="col-xs-3 control-label">Class</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <select name="KYC" id="kyc" class="col-md-12">
                                                                                                        <option></option>
                                                                                                        <option value="0">Bronze</option>
                                                                                                        <option value="1">Gold</option>
                                                                                                        <option value="2">Platinum</option>
                                                                                                    </select>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label class="col-xs-3 control-label">Remark</label>
                                                                                                <div class="col-xs-7">
                                                                                                    <textarea rows="2" cols="5" name="Remark" class="form-control"></textarea>
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
                                                                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 380px;"><div class="scroller" data-height="380px" style="overflow: hidden; width: auto; height: 380px;">
                                                                    <div class="box-body big">
                                                                        <form class="form-horizontal acc-ops" action="https://www.lordaragorn.cellulant.com.ng/SmartAdmin/SystemReports?Type=5&amp;Phone=2347052613123&amp;PlatformId=2348033470022#">
                                                                            <input type="hidden" name="Type" value="3">
                                                                            <input type="hidden" name="PhoneNumber" value="2347052613123">
                                                                            <div class="form-group">
                                                                                <label class="col-md-2 control-label">Name:</label> 
                                                                                <div class="col-sm-6">
                                                                                    <input name="Name" type="text" value="INNOCENT EREMIOKHALE" class="form-control">
                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-sm-2 control-label">Address</label>
                                                                                <div class="col-sm-6">
                                                                                    <textarea name="Address" class="form-control" rows="3"></textarea>
                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-md-2 control-label">Occupation:</label> 
                                                                                <div class="col-sm-6">
                                                                                    <input name="Occupation" type="text" value="Not supplied" class="form-control">
                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-md-2 control-label">Gender:</label> 
                                                                                <div class="col-sm-6">
                                                                                    <input name="Gender" type="text" class="form-control" value="">
                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-md-2 control-label">Email:</label> 
                                                                                <div class="col-sm-6">
                                                                                    <input type="Email" name="Email" class="form-control" value="Not supplied">
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-12">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button type="submit" class="btn btn-primary">Submit</button>                         
                                                                                </div>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                </div><div class="slimScrollBar" style="background: rgb(161, 178, 189); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.1; z-index: 90; right: 1px;"></div></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /BOX -->
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
            jQuery(document).ready(function () {
                App.setPage("customer_profile");  //Set current page
                App.init(); //Initialise plugins and elements
            });
        </script>
        <!-- /JAVASCRIPTS -->
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
                                        message: "Your request was successfully submited",
                                        type: 'success',
                                        showCloseButton: true,
                                        actions: false
                                    });
                                } else {
                                    //Call
                                    return messgr.update({
                                        message: "Request is processing failed..." + msg,
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
                            url: "Operations?Operation=1&EntityType=C",
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
                                        message: "Your request was successfully submited",
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