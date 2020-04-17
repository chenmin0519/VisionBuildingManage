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
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/sys_left.jsp" %>
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
                            <h4 class="pull-left page-title">日志管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">操作日志管理</a></li>
                                <li class="active">日志管理</li>
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

                                        <ul id="checkul" class="nav nav-tabs navtab-bg" style="border-bottom: 2px solid #EED8AE;margin-left: 10px;margin-bottom:20px">
                                            <li onclick="changestatus('1')" id="type1" class="active "><a href="#jxz" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-home"></i></span> <span
                                                    class="hidden-xs">大项目添加</span>
                                            </a></li>
                                            <li onclick="changestatus('2')" id="type2" class=""><a href="#wks" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">大项目修改</span>
                                            </a></li>
                                            <li onclick="changestatus('3')" id="type3" class=""><a href="#yjs" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">小项目添加</span>
                                            </a></li>
                                            <li onclick="changestatus('4')" id="type4" class=""><a href="#yjs" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">小项目修改</span>
                                            </a></li>
                                            <li onclick="changestatus('5')" id="type5" class=""><a href="#yjs" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">数据上报</span>
                                            </a></li>
                                            <li onclick="changestatus('6')" id="type6" class=""><a href="#yjs" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">数据审核</span>
                                            </a></li>
                                        </ul>
                                        <div class="panel panel-default">
                                            <form id="form">
                                                <input id ="type-int" name="type" value="1" type="hidden">
                                                <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                    <input type="text" placeholder="请输入用户id" class="form-control" name="userId">
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
                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_1">
                                            <table id="datatable_1"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>

                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_2" style="display: none">
                                            <table id="datatable_2"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>

                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_3" style="display: none">
                                            <table id="datatable_3"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>

                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_4" style="display: none">
                                            <table id="datatable_4"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>

                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_5" style="display: none">
                                            <table id="datatable_5"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div_6" style="display: none">
                                            <table id="datatable_6"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">用户名</th>
                                                    <th nowrap="nowrap">密码</th>
                                                    <th nowrap="nowrap">姓名</th>
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
        <%@ include file="../common/footer.jsp" %>

    </div>
    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->



</div>
<!-- END wrapper -->



<script>
    var resizefunc = [];
</script>
<%@ include file="../common/common_js.jsp"%>
<%@ include file="../common/formajax_js.jsp"%>
<%@ include file="../common/utils.jsp"%>
<!-- jQuery  -->

<script type="text/javascript">
    var functionlist=[{targets:[5],
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
    },{targets:[2],
        mRender:function(data) {
            var result = "******";
            return result;
        }
    }];
    function initTbale(type){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        var par = $("form").serialize();
        par += "&type="+type
        if(type == 1){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }else if(type == 2){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }else if(type == 3){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }else if(type == 4){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }else if(type == 5){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }else if(type == 6){
            initBaseTable(par,"datatable_"+type,"${base}/log/log/getDates",",id,,type,,",functionlist);
        }

    }

    $(document).ready(function(){
        //初始化第一个表单
        initTbale(1);
    });

    $("#query").click(function(){
        var type = $("#type-int").val();
    });
    function changestatus(type){
        //暂存现在选项卡
        $("#type-int").val(type);
        //隐藏显示表格
        for(var i = 1 ; i <= 6 ; i++){
            if(type == i){
                $("#datatable_div_"+i).show();
                initTbale(type);
            }else{
                $("#datatable_div_"+i).hide();
            }
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
</script>

</body>
</html>