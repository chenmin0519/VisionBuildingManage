<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../../common/common_css.jsp" %>

    <link href="${cssjs}/blue/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
                                <h4 class="pull-left page-title">运营中心子项目信息填写</h4>
                            </div>
<%--                            <div class="col-sm-2">--%>
<%--                                <input style="float: left;" type="button" id="addSub" class="btn btn-primary" onclick="addSubproject();" value="新增子项目">--%>
<%--                            </div>--%>
                                <ol class="breadcrumb pull-right">
                                <li><a href="#">运营中心子项目信息填写</a></li>
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
                                                <input id="parentId" name="parentId" type="hidden" value="${po.parentId}"/>

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

                                                        <select id="projectTypeCode" name="projectTypeCode"
                                                                class="form-control">
                                                            <option value="">--请选择--</option>
                                                        </select>

                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售面积(㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" <%--name="constructionArea"--%> id="constructionArea" placeholder="请输入销售面积" value="${po.salesArea/100}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售单价(元/㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" <%--name="unitPrice"--%> id="unitPrice" value="${po.unitPrice/100}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售总价(元)：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" readonly="readonly" <%--name="totalSalesPrice"--%> id="totalSalesPrice"  value="${po.totalSalesPrice/100}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">收单日期：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left" >
                                                        <div class="col-sm-6 col-lg-6 col-md-6 pull-left">
                                                            <div class='input-group date' id='datetimepicker4' >
                                                                <input placeholder="收单日期" type='text' id="endTime2" value="${po.acquisitionDate}"  name="acquisitionDate" class="form-control" /> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                            </div>
                                                        </div>
<%--                                                        <input type="text" name="initialDeliveryDate" id="initialDeliveryDate"  value="${po.initialDeliveryDate}" class="form-control" >--%>
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

    $(document).ready(function(){
        // initBaseDateTimePicker("datetimepicker3", "datetimepicker4");
        $('#datetimepicker4').datetimepicker({
            format:"YYYY-MM-DD HH:mm:ss"
        });
        $("#datetimepicker4").on("dp.change", function (e) {
            $('#datetimepicker4').data("DateTimePicker").minDate(e.date);
        });
    });
    $("#save").click(function(){
        // 非空校验
        if($("#form").valid() == false){
            alert("操作失败,请检查各个输入项是否正确!");
            return false;
        }
        var par = $("#form").serialize();
        var totalSalesPrice = $("#totalSalesPrice").val();
        var projectTypeName = $('#projectTypeCode option:selected').text();
        console.log("projectTypeName:"+projectTypeName);
        if(projectTypeName != undefined && projectTypeName != null){
            par += "&projectTypeName="+projectTypeName;
        }
        if(totalSalesPrice) {
            totalSalesPrice = eval(totalSalesPrice) * 100;
            par += "&totalSalesPrice="+totalSalesPrice;
        }
        baseCallBackAJAX("post","${base}/project/main-project/save-sub-project",par,"json","saveCallback(data)");
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
                    history.back();
                    <%--window.location.href="${base}/project/main-project/project.html?chirld=${chirld}&parent=${parent}";--%>
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
        // console.log("aaa:"+data.aaData);
        if(data) {
            for(var i = 0; i < data.length; i++) {
                var projectType = data[i];
                var typeCode = "";
                var typeName = "";
                if (projectType.typeCode) {typeCode = projectType.typeCode}
                if (projectType.typeName) {typeName = projectType.typeName;}
                if("${po.projectTypeCode}" == typeCode) {
                    $("#projectTypeCode").append("<option value='"+typeCode+"' selected >"+typeName+"</option>");
                } else {
                    $("#projectTypeCode").append("<option value='"+typeCode+"' >"+typeName+"</option>");
                }
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