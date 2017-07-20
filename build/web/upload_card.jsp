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
                                    <div class="col-md-10">
                                        <% if(request.getAttribute("respMsg") != null) { %>
                                        <div class="alert alert-block alert-success fade in">
                                            <a class="close" data-dismiss="alert" href="#" aria-hidden="true">×</a>
                                            <p></p> Batch Upload Operation was successful.<p></p>
                                        </div>
                                        <% }%>
                                        <form method="post" action="upload" enctype="multipart/form-data" name="Transactions">
                                            <input type="hidden" name="SourcePage" value="upload_customers.jsp">
                                            <input type="hidden" name="Operation" value="4">
                                            <input type="hidden" name="Programme" value="1">
                                            <div id="job-create">
                                                <table class="table table-hover">
                                                    <tbody><tr><td class="text-right"><em>File:</em></td><td><input name="List" type="file"><small>Format (CSV) : CVN , Card Number, Expiry Date , Pin </small></td>
                                                    </tr>
                                                    
                                                    <tr><td></td>
                                                        <td>
                                                            <button class="col-md-3 btn btn-primary btn-sm" type="submit"><i class="fa fa-cloud-upload"></i> Upload</button>
                                                            <!--<input class="btn btn-primary" type="submit" value="    Upload    "/>-->
                                                        </td>
                                                    </tr>
                                                </tbody></table>

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
                    "ajax": "Controller?Type=39&Mode=1",
                     "columns": [
                                    {"data": 1},
                                    {"data": 2},
                                    {"data": 3},
                                    {"data": 4},
                                    {"data": 5},
                                    {"data": 6},
                                    
                    ],
                    "createdRow": function (row, data, index) {                                  
                        $('td', row).eq(5).html('<a href="handler?Type=37&id=' + data[0] + '">' +
                               '<i class="fa fa-plus-circle"></i> VIEW MORE' + '</a>');
                    }
                     
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

                $('#cust_datatable tbody').on('click', 'td.details-control', function () {
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

    
</body></html>
