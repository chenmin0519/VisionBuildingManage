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
                            <h4 class="pull-left page-title">子项目信息管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">子项目信息管理</a></li>
                                <li class="active">项目结算管理</li>
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
                                                    class="hidden-xs">待填写子项目</span>
                                            </a></li>
                                            <li onclick="changestatus('2')" id="type2" class=""><a href="#wks" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                    class="hidden-xs">已填写子项目</span>
                                            </a></li>
                                        </ul>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="panel panel-default">
                                                <form id="form">
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <select id="projectTypeCode" name="projectTypeCode" class="form-control">
                                                            <option value="">--请选择子项目类型--</option>
                                                        </select>
                                                        <input type="hidden" class="form-control" id="confirmStatus" name="confirmStatus" value="0">
                                                    </div>

                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button id="query" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-ios7-search"></i> 查询
                                                        </button>

                                                    </div>
                                                </form>
                                            </div>
                                            <table id="datatable"
                                                   class="table table-hover mails dataTable no-footer">
                                                <thead>
                                                <tr>
                                                    <th nowrap="nowrap">序号</th>
                                                    <th nowrap="nowrap">项目类型</th>
                                                    <th nowrap="nowrap">销售面积(㎡)</th>
                                                    <th nowrap="nowrap">销售单价(元/㎡)</th>
                                                    <th nowrap="nowrap">销售总价(元)</th>
                                                    <th nowrap="nowrap">收单日期</th>
                                                    <th nowrap="nowrap">备注</th>
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
            result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=del('"
                +data.id+"')><i class='ion-ios7-paper-outline'></i> 删除</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=edit('"
                +data.id+"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detail('"
                +data.id+"')><i class='ion-ios7-paper-outline'></i> 详情</button>";
            return result;
        }
    },{targets:[2],
        mRender:function(data) {
            var result = "";
            if(data.salesArea != null){
                result = data.salesArea/100;
            }
            return result;
        }
    },{targets:[3],
        mRender:function(data) {
            var result = "";
            if(data.unitPrice != null){
                result = data.unitPrice/100;
            }
            return result;
        }
    },{targets:[4],
        mRender:function(data) {
            var result = "";
            if(data.totalSalesPrice != null){
                result = data.totalSalesPrice/100;
            }
            return result;
        }
    },{targets:[5],
        mRender:function(data) {
            var result = "";
            if(data.acquisitionDate != null){
                result = getDateStr(data.acquisitionDate);
            }
            return result;
        }
    }];
    function initTbale(){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        var par = $("#form").serialize();
        initBaseTable(par,"datatable","${base}/project/main-project/getSubDatas",",projectTypeName,,,,,remarks,",functionlist);

    }
    $(document).ready(function(){
        // 设定默认加载的tab,然后再加载页面表单
        changestatus('1');
        initTbale();
        baseCallBackAJAX("post","${base}/data/projectType/getAll",null,"json","initProjectType(data)");
    });
    $("#query").click(function(){
        initTbale();
    });

    function del(id){
        baseCallBackAJAX("get","${base}/project/main-project/del",{'id':id},"json","callback(data)");
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
    function edit(id){
        window.location.href="${base}/project/main-project/editSub.html?id="+id+"&chirld=${chirld}&parent=${parent}";
    }
    function detail(id){
        window.location.href="${base}/project/main-project/subInfo.html?id="+id+"&chirld=${chirld}&parent=${parent}";
    }
    <%--$("#add").click(function(){--%>
    <%--    window.location.href="${base}/project/main-project/add.html?chirld=${chirld}&parent=${parent}";--%>
    <%--});--%>

    function changestatus(type) {
        //暂存现在选项卡
        // $("#type-int").val(type);
        if(type == '1') {
            // 查看待填写状态的子项目,即运营中心发布还未被设计院填写的(可以被查看和编辑)
            $("#confirmStatus").val("0");

            functionlist[0]={targets:[7],
                mRender:function(data) {
                    var result = "";
                    result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=edit('"
                        +data.id+"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detail('"
                        +data.id+"')><i class='ion-ios7-paper-outline'></i> 详情</button>";
                    return result;
                }
            };

        } else if(type == '2') {
            // 查看已填写状态的子项目(只能被查看)
            $("#confirmStatus").val("123");

            functionlist[0]={targets:[7],
                mRender:function(data) {
                    var result = "";
                    result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detail('"
                        +data.id+"')><i class='ion-ios7-paper-outline'></i> 详情</button>";
                    return result;
                }
            };
        }
        // 每次重新选择tab,加载页面的表单
        initTbale();

    }

    /**
     * 初始化项目类型下拉框
     * @param date
     */
    function initProjectType(data) {
        // console.log("aaa:"+data.aaData);
        if(data) {
            for(var i = 0; i < data.length; i++) {
                var projectType = data[i];
                var typeCode = "";
                var typeName = "";
                if (projectType.typeCode) {typeCode = projectType.typeCode}
                if (projectType.typeName) {typeName = projectType.typeName;}

                $("#projectTypeCode").append("<option value='"+typeCode+"' >"+typeName+"</option>");
            }
        }
    }
</script>

</body>
</html>