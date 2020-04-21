<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${cssjs}/blue/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
                            <h4 class="pull-left page-title">统计管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">运营中心统计管理</a></li>
                                <li class="active">统计管理</li>
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
                                                    <div class="form-group col-sm-2 col-md-2 col-xs-2 pull-left">
                                                        <input type="hidden" name="department" id="department" >
                                                        <select class="form-control departmentSelect"> <option value="">--请选择部门--</option> </select>
                                                    </div>
                                                    <div class="form-group col-sm-2 col-md-2 col-xs-2 pull-left">
                                                        <input type="hidden" name="type" id="type" >
                                                        <select class="form-control typeSelect"> <option value="">--请选择类型--</option> </select>
                                                    </div>

                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="form-group">
                                                            <label class="pull-left" style="line-height: 30px;">确认时间:</label>
                                                            <div class="col-sm-5">
                                                                <div class='input-group date pull-left' id='datetimepicker6'>
                                                                    <input type='text' id="startTime" name="startTime" class="form-control" /> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                                </div>
                                                            </div>
                                                            <label class="pull-left" style="line-height: 30px;">到</label>
                                                            <div class="col-sm-5">
                                                                <div class='input-group date' id='datetimepicker7'>
                                                                    <input type='text' id="endTime" name="endTime" class="form-control" /> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-sm-1 col-md-1 col-xs-1 pull-left">
                                                        <button id="query" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-ios7-search"></i> 查询
                                                        </button>
                                                    </div>
                                                    <div class="form-group col-sm-1 col-md-1 col-xs-1 pull-left">
                                                        <button id="export" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="glyphicon glyphicon-cloud-download"></i> 导出
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <table id="datatable"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">编码</th>
                                                    <th nowrap="nowrap">项目类型</th>
                                                    <th nowrap="nowrap">销售总价</th>
                                                    <th nowrap="nowrap">成本面积</th>
                                                    <th nowrap="nowrap">成本单价</th>
                                                    <th nowrap="nowrap">成本总价</th>
                                                    <th nowrap="nowrap">提成单价</th>
                                                    <th nowrap="nowrap">提成总价</th>
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
    var functionlist=[{targets:[3],
        mRender:function(data) {
            var result = "";
            if(data.totalSalesPrice != null){
                result = data.totalSalesPrice/100;
            }
            return result;
        }
    },{targets:[4],
        mRender:function(data) {
            var result = "";
            if(data.constructionArea != null){
                result = data.constructionArea/100;
            }
            return result;
        }
    },{targets:[5],
        mRender:function(data) {
            var result = "";
            if(data.childCostPrice != null){
                result = data.childCostPrice/100;
            }
            return result;
        }
    },{targets:[6],
        mRender:function(data) {
            var result = "";
            if(data.childTotalCost != null){
                result = data.childTotalCost/100;
            }
            return result;
        }
    },{targets:[7],
        mRender:function(data) {
            var result = "";
            if(data.designerCommission != null){
                result = data.designerCommission/100;
            }
            return result;
        }
    },{targets:[8],
        mRender:function(data) {
            var result = "";
            if(data.designerCommissionPrice != null){
                result = data.designerCommissionPrice/100;
            }
            return result;
        }
    }];
    function initTbale(){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        initBaseTable($("form").serialize(),"datatable","${base}/statics/statics/getDates",",projectCode,typeName,,,,,,",functionlist);
    }
    $(document).ready(function(){
        initTbale();
        initDepartmentOption();
        initTypeOption();
        initBaseDateTimePicker("datetimepicker6", "datetimepicker7");
    });
    function initDepartmentOption(){
        baseCallBackAJAX("post","${base}/data/department/getAll",null,"json","initDepartmentOptionCallback(data)");
    }
    function initDepartmentOptionCallback(data){
        var optionStr = "";
        var datas = data;
        var department = $("#department").val();
        for(var i = 0;i < datas.length ; i++){
            optionStr += "<option value = "+datas[i].id+">"+datas[i].department+"</option>";
        }
        $(".departmentSelect").append(optionStr);
    }
    $(".departmentSelect").change(function(){
        var department = $(".departmentSelect").val();
        $("#department").val(department);
    });

    function initTypeOption(){
        baseCallBackAJAX("post","${base}/data/projectType/getAll",null,"json","initTypeOptionCallback(data)");
    }
    $(".typeSelect").change(function(){
        var department = $(".typeSelect").val();
        $("#type").val(department);
    });
    function initTypeOptionCallback(data){
        var optionStr = "";
        var datas = data;
        var department = $("#type").val();
        for(var i = 0;i < datas.length ; i++){
            optionStr += "<option value = "+datas[i].typeCode+">"+datas[i].typeName+"</option>";
        }
        $(".typeSelect").append(optionStr);
    }


    $("#query").click(function(){
        initTbale();
    });

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

    $("#export").click(function(){
        window.location.href="${base}/statics/statics/export_statics.html?chirld=${chirld}&parent=${parent}";
    });
</script>

</body>
</html>