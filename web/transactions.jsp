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
        <script type="text/javascript"  src="js/datetimepicker_css.js"></script>
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
            td.details-control {
                background: url('./images/details_open.png') no-repeat center center;
                cursor: pointer;
            }
            tr.shown td.details-control {

                background: url('./images/details_close.png') no-repeat center center;
            }
            .margin-top-bottom-8{
                margin-top: 8px;
                margin-bottom: 8px;
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
                                    <div class="box border blue">
                                        <div class="box-title">
                                            <h4><i class="fa fa-table"></i>Total Card Customers</h4>
                                            <div class="tools hidden-xs">
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
                                            <div class="row">
                                                <div class="col-md-9">
                                                    <div class="row">
                                                        <div class="col-md-3"><input class="form-control input-sm" id="card_number" type="text" placeholder="Card Number"/></div>
                                                        <div class="col-md-3"><input class="form-control input-sm" id="msisdn" type="text" placeholder="Phone Number"/></div>
                                                        <div class="col-md-3"><select id="status" class="form-control input-sm"><option value="">Status</option><option value="Successful">Successful</option><option value="Failed">Failed</option><option value="Pending">Pending</option></select></div>
                                                        <div class="col-md-3 "><select id="action" class="form-control input-sm"><option value="">Action</option><option value="ACTIVATE">Active</option><option value="Balance">Balance</option><option value="BLOCK">Block</option><option value="TOPUP">Topup</option></select></div>                                                         
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-3 margin-top-bottom-8 mt20"><input class="form-control input-sm datepicker" id="date-from-sch" type="text" placeholder="Date from (yyyy-mm-dd)"  onclick="javascript:NewCssCal('date-from-sch','yyyyMMdd','dropdown',true,'24',true)" /></div>
                                                        <div class="col-md-3 margin-top-bottom-8 mt20"><input class="form-control input-sm datepicker" id="date-to-sch" type="text" placeholder="Date to (yyyy-mm-dd)" onclick="javascript:NewCssCal('date-to-sch','yyyyMMdd','dropdown',true,'24',true)" /></div>     
                                                        <div class="col-md-3 margin-top-bottom-8"><select id="channel" class="form-control input-sm"><option value="">Channel</option><option value="SMS">SMS</option><option value="WEB">WEB</option><option value="USSD">USSD</option></select></div>                                                        
                                                        <div class="col-md-3 margin-top-bottom-8"><button class="btn btn-primary btn-sm form-control input-sm" id="searchfilter"><i class="fa fa-filter"></i> Filter Search</button></div>
                                                    </div>

                                                </div>
                                                <div id='summary-info' class="col-md-3">
                                                    <div class="sparkline-row text-right">
                                                        <span class="title"> <br> </span>
                                                        <!--<span class="value"><span id="rec-tot"></span></span> -->
                                                        <span class="sparkline big" data-color="blue"><canvas height="40" width="82" style="display: inline-block; width: 82px; height: 40px; vertical-align: top;"></canvas></span>
                                                        <!--<div><button class="margin-top-50 btn btn-default btn-sm form-control input-sm" id="download"><i class="fa fa-download"></i> Download full report</button></div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <table id="trx_datatable" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                                  <th></th>
                                                                 <th>Phone Number</th>
                                                                  <th>Card Number</th>
                                                                  <th>Action</th>
                                                                  <th>Remark</th>
                                                                  <th>Channel</th>
                                                                  <th>Network</th>
                                                                  <th>Date</th>
                                                                  
                                                        
                                                    </tr>
                                                </thead>

                                                <tfoot>
                                                    <tr>
                                                                  <th></th>
                                                                  <th>Phone Number</th>
                                                                  <th>Card Number</th>
                                                                  <th>Action</th>
                                                                  <th>Remark</th>
                                                                  <th>Channel</th>
                                                                  <th>Network</th>
                                                                  <th>Date</th>
                                                                  
                                                                  
                                                    </tr>
                                                </tfoot>
                                            </table>
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
                App.setPage("Customer");  //Set current page
                App.init(); //Initialise plugins and elements

                $("#searchfilter").click(function () {
                    var col_text_0 = $('#card_number').val();
                    var col_text_1 = $('#msisdn').val(); 
                    var col_text_2 = $('#status').val();
                    var col_text_3 = $('#action').val(); 
                    var col_text_4 = $('#date-from-sch').val();
                    var col_text_5 = $('#date-to-sch').val();
                    var col_text_6 = $('#channel').val();
                    table
                            .column(1).search(col_text_0)
                            .column(2).search(col_text_1)
                            .column(3).search(col_text_2)
                            .column(4).search(col_text_3)
                            .column(5).search(col_text_4)
                            .column(6).search(col_text_5)
                            .column(7).search(col_text_6)
                            .draw();
                });

                // DataTable
                var table = $('#trx_datatable').DataTable({
                    "pageLength": 15,
                    "processing": true,
                    "serverSide": true,
//                    "searching": false,
                    "ajax": "Controller?Type=40&Mode=1",
                    "searchCols": [
                        null,
                        null,
                        null,
                        null,
                        null,
                        {"search": "Successful"},
                        null
                    ],
                     "columns": [
                         {
                            "className": 'details-control',
                            "orderable": false,
                            "data": null,
                            "defaultContent": ''
                        },
                                    {"data": 0},
                                    {"data": 1},
                                    {"data": 3},
                                    {"data": 4},
                                    {"data": 5},
                                    {"data": 6},
                                    {"data": 7}
                                    
                    ],
                    dom: 'Bfrtip',
                    buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                    ]
    
                     
                }); 
          //    alert(JSON.stringify(table));
                $("#download").click(function () {
                    var data = table.ajax.params();
                    window.location= "handler?Type=37&columns[2][search][value]=&download=1&"+$.param(data);
                });
                  
              //  table.on('xhr', function () {
              //      var json = table.ajax.json();
                //     alert(JSON.stringify(json));
                //    $("#rec-tot").html(Number(json.recordsTotal).toLocaleString('en'));
                // });

                $('#trx_datatable tbody').on('click', 'td.details-control', function () {
                    var tr = $(this).closest('tr');
                    var row = table.row(tr);

                    if (row.child.isShown()) {
                        // This row is already open - close it
                        row.child.hide();
                        tr.removeClass('shown');
                    }
                    else {
                        // Open this row
                        row.child(format(row.data())).show();
                        tr.addClass('shown');
                    }
                });
            });
        </script>
<script type="text/javascript">
            /* Formatting function for row details - modify as you need */
            function format(d) {
                // `d` is the original data object for the row
                return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
                        '<tr>' +
                        '<td>Payload</td>' +
                        '<td>' + d[2] + '</td>' +
                        '</tr>' +                         
                        '</table>';
            }
        </script>
    
</body></html>
