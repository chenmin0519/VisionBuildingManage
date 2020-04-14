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
                            <div class="col-sm-2">
                                <h4 class="pull-left page-title">项目结算管理</h4>
                            </div>
<%--                            <div class="col-sm-2">--%>
<%--                                <input style="float: left;" type="button" id="addSub" class="btn btn-primary" onclick="addSubproject();" value="新增子项目">--%>
<%--                            </div>--%>
                                <ol class="breadcrumb pull-right">
                                <li><a href="#">项目管理</a></li>
                                <li class="active">项目结算管理</li>
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

<%--                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">--%>
<%--                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">客户来源<span--%>
<%--                                                            class="required" style="color: red"> * </span>：</label>--%>
<%--                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">--%>
<%--                                                        <input type="text" name="place" id="place" placeholder="请输入项目地点" value="${po.place}" class="form-control required" >--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">项目类型<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">

                                                        <select id="projectType" name="projectType"
                                                                class="form-control">
                                                            <option value="">--请选择--</option>
                                                        </select>

                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售面积<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" <%--name="constructionArea"--%> id="constructionArea" placeholder="请输入销售面积" value="${po.salesArea}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售单价(元/㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" <%--name="unitPrice"--%> id="unitPrice" value="${po.unitPrice}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售总价：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" name="totalSalesPrice" id="totalSalesPrice"  value="${po.totalSalesPrice}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">平面收单日期：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="initialDeliveryDate" id="initialDeliveryDate"  value="${po.initialDeliveryDate}" class="form-control" >
<%--                                                        <input type="text" onfocus="WdatePicker({onpicked:true,minDate:'',maxDate:''})" id="initialDeliveryDate" name="initialDeliveryDate" value="${po.initialDeliveryDate}" class="form-control" readonly="readonly">--%>

                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">备注：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="remarks" id="remarks" placeholder="请输入备注" value="${po.remarks}" class="form-control" >
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
        baseCallBackAJAX("post","${base}/project/main-project/save-sub-project",$("#form").serialize(),"json","saveCallback(data)");
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
                    window.location.href="${base}/project/main-project/project.html?chirld=${chirld}&parent=${parent}";
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

    /**
     * 添加子产品
     * @param parentId
     */
    <%--$("#addSub").click(function(){--%>
    <%--    window.location.href="${base}/project/main-project/addSub.html?chirld=${chirld}&parent=${parent}";--%>
    <%--});--%>

    /**
     * 初始化项目类型下拉框
     * @param date
     */
    function initProjectType(data) {
        console.log(data);
        // console.log("aaa:"+data.aaData);
        if(data) {
            for(var i = 0; i < data.length; i++) {
                var projectType = data[i];
                var typeCode = "";
                var typeName = "";
                if (projectType.typeCode) {typeCode = projectType.typeCode}
                if (projectType.typeName) {typeName = projectType.typeName;}
                $("#projectType").append("<option value='"+typeCode+"' >"+typeName+"</option>");
            }
        }
    }

    $(function () {
        baseCallBackAJAX("post","${base}/data/projectType/getAll",null,"json","initProjectType(data)");

        // 销售总价=销售单价*销售面积
       var unitPrice = $("#unitPrice").val();
       var constructionArea = $("#constructionArea").val();
       var totalPrice = unitPrice * 1 * constructionArea * 1;
       $("#totalSalesPrice").val(totalPrice);
    });
</script>


</body>
</html>