<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>愿景建筑OA系统</title>

    <script src="${domain}/blue/assets/js/modernizr.min.js"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${domain}/blue/assets/html5shiv.js"></script>
    <script src="${domain}/blue/assets/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">

    </style>

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
                            <h4 class="pull-left page-title">用户管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">用户管理</a></li>
                                <li class="active">系统管理</li>
                            </ol>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 id="panel-title" class="panel-title"></h3>
                                </div>
                                <div class="panel-body">

                                    <div class="row">

                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="panel panel-default">
                                                <form id="form">
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <input type="text" placeholder="请输入用户名" class="form-control" name="userName">
                                                    </div>

                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button id="query" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-ios7-search"></i> 查询
                                                        </button>

                                                    </div>
                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button id="add" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="glyphicon glyphicon-plus"></i> 新建

                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <table id="datatable"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">id</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">基本工资</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>


                                            </table>

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
<%@ include file="../../common/utils.jsp"%>
<!-- jQuery  -->

<script type="text/javascript">
    var functionlist=[{targets:[7],
        mRender:function(data) {
            var result = "";
            if(data.status == 1){
                result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=del('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 删除</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detil('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetPassword('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 重置密码</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetZt('"
                    + data.id+"','0')><i class='ion-ios7-paper-outline'></i> 冻结</button>";
            }else{
                result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=del('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 删除</button>&nbsp;&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detil('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetPassword('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 重置密码</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetZt('"
                    + data.id+"','1')><i class='ion-ios7-paper-outline'></i> 激活</button>";
            }
            return result;
        }
    },{targets:[6],
        mRender:function(data) {
            var result = "";
            if(data.status == '1'){
               result = "正常";
            }else{
                result = "<p style='color:red'>冻结</p>";
            }
            return result;
        }
    },{targets:[3],
        mRender:function(data) {
            var result = "******";
            return result;
        }
    }];
    function initTbale(){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        initBaseTable($("form").serialize(),"datatable","${base}/sys/user/getDates",",id,userName,,realName,wages,,",functionlist);
    }
    $(document).ready(function(){
        initTbale();
    });
    $("#query").click(function(){
        initTbale();
    });

    function resetPassword(id) {
        baseCallBackAJAX("get","${base}/sys/user/resetPassword",{'id':id},"json","resetPassCallback(data)");
    }

    function resetZt(id,status){
        baseCallBackAJAX("get","${base}/sys/user/resetZt",{'id':id,'status':status},"json","callback(data)");
    }

    function del(id){
        swal({
            title: "一经删除无法恢复,确定执行操作吗?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm : false, //关闭按钮
            closeOnCancel : true
        },function(isConfirm) {
            if(isConfirm==true){
                baseCallBackAJAX("get","${base}/sys/user/deluser",{'id':id},"json","callback(data)");
            }
        });
    }

    function resetPassCallback(data){
        if(data.status=="0"){
            swal({
                title: "操作成功",
                text:"初始密码为：123456",
                type: "success",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            },function(isConfirm) {
                if(isConfirm==true){
                    initTbale();
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
    function callback(data){
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
                    initTbale();
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
    function detil(id){
        window.location.href="${base}/sys/user/edit.html?id="+id+"&chirld=${chirld}&parent=${parent}";
    }
    $("#add").click(function(){
        window.location.href="${base}/sys/user/add.html?chirld=${chirld}&parent=${parent}";
    });
</script>

</body>
</html>