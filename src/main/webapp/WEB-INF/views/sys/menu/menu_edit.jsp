<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../../common/common_css.jsp" %>

    <title>在线考试运营系统</title>

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
                            <h4 class="pull-left page-title">菜单管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">菜单管理</a></li>
                                <li class="active">系统管理</li>
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
                                                <input id="menuPid" name="menuPid" type="hidden" value="${menuPid}"/>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">菜单名：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="menuName" id="menuName" placeholder="请输入菜单名" value="${po.menuName}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">级别：</label>
                                                    <input type="hidden" name="lv" id="lv" value="${po.lv}"/>
                                                    <div class="col-sm-8">
                                                        <div class="btn-group">
                                                            <button id="xt" onclick="$('#lv').val(1)" type="button" class="btn btn-default waves-effect">系统级别</button>
                                                            <button id="zy" onclick="$('#lv').val(2)" type="button" class="btn btn-default waves-effect">资源链接级</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">Url：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="menuUrl" id="menuUrl" placeholder="请输入url" value="${po.menuUrl}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">序号：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="num" id="num" placeholder="请输入序号" value="${po.num}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">小图标：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="icon" id="icon" placeholder="请输入小图标" value="${po.icon}" class="form-control" >
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
    $(document).ready(function(){
        var lv = $("#lv").val();
        if(lv != null){
            if(lv == '1'){
                $("#lv").val('1');
                $("#xt").addClass(" btn-primary");
            }else{
                $("#lv").val('2');
                $("#zy").addClass(" btn-primary");
            }
        }
    });
    $("#xt").click(function(){
        $("#lv").val('1');
        $("#xt").addClass(" btn-primary");
        $("#zy").removeClass(" btn-primary");
    });
    $("#zy").click(function (){
        $("#lv").val('2');
        $("#zy").addClass(" btn-primary");
        $("#xt").removeClass(" btn-primary");
    });

    $("#save").click(function(){
        baseCallBackAJAX("post","${base}/sys/menu/saveMenu",$("#form").serialize(),"json","saveCallback(data)");
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
                    window.location.href="${base}/sys/menu/menu.html?chirld=${chirld}&parent=${parent}";
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