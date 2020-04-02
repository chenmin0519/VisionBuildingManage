<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../../common/common_css.jsp" %>

    <title>愿景建筑OA系统</title>

    <%@ include file="../../common/common_tag.jsp" %>

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
    <%@ include file="../../common/header.jsp" %>
    <%@ include file="../../common/sys_left.jsp" %>
</div>
<!-- Top Bar End -->
<div>
    <div>
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
                <div class="container">

                    <!-- Page-Title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <h4 class="pull-left page-title">设计类型管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">设计类型管理</a></li>
                                <li class="active">基础数据管理</li>
                            </ol>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-10 col-md-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 id="panel-title" class="panel-title"></h3>
                                </div>
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="panel panel-default">
                                            <form id="form">
                                                <input id="id" name="id" type="hidden" value="${po.id}"/>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">编码：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="typeCode" id="typeCode" placeholder="请输入编码" value="${po.typeCode}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">名称：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="typeName" id="typeName" placeholder="请输入名称" value="${po.typeName}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="col-sm-10 pull-right">
                                                        <button id="save" type="button"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-checkmark"></i>&nbsp;保存
                                                        </button>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button onclick="history.back();" type="button"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-reply"></i>&nbsp;返回
                                                        </button>
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
                <!-- End Row -->

            </div>
            <!-- container -->

        </div>


        <!-- ========== Left Sidebar Start ========== -->

        <%@ include file="../../common/footer.jsp" %>

    </div>
    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->



</div>
<!-- END wrapper -->



<script>
    var resizefunc = [];
</script>
<%@ include file="../../common/common_js.jsp"%>
<%@ include file="../../common/formajax_js.jsp"%>
<!-- jQuery  -->
<script type="text/javascript">

    $("#save").click(function(){
        baseCallBackAJAX("post","${base}/data/projectType/save",$("#form").serialize(),"json","saveCallback(data)");
    });
    function saveCallback(data){
        if(data.status=="0"){
            swal({
                title: "操作成功",
                text:data.message,
                type: "success",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            },function(isConfirm) {
                if(isConfirm==true){
                    window.location.href="${base}/data/projectType/projectType.html?chirld=${chirld}&parent=${parent}";
                }
            });
        }else{
            swal({
                title: "操作失败",
                text:data.message,
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            });
        }
    }
</script>


</body>
</html>