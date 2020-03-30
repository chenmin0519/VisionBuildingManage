<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@ include file="../common/common_css.jsp" %>

        <title>在线考试运营系统</title>

    	<%@ include file="../common/common_tag.jsp" %>

        <script src="${domain}/blue/assets/js/modernizr.min.js"></script>


        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
    <script src="${domain}/blue/assets/html5shiv.js"></script>
    <script src="${domain}/blue/assets/respond.min.js"></script>
        <![endif]-->
        
    </head>


    <body class="fixed-left">
        
        <!-- Begin page -->
        <div id="wrapper">
        
            <!-- Top Bar Start -->
          		<%@ include file="../common/header.jsp" %>
          		<%@ include file="../common/sys_left.jsp" %>
        </div>
            <!-- Top Bar End -->
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
                <div class="container">
                    <div style="text-align: center; vertical-align:middle; margin-top: 120px;height: 380px;font-size: 38px; font-family: 'Arial Negreta', 'Arial Normal', 'Arial'; font-weight: 700; font-style: normal;">欢迎使用在线考试运营系统</div>
                </div>
            </div>
        </div>
		<div>
            <%@ include file="../common/footer.jsp" %>
        </div>
        <!-- END wrapper -->
        <script>
            var resizefunc = [];
        </script>
<%@ include file="../common/common_js.jsp"%>
    
    </body>
</html>