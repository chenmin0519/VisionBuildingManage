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
                                <h4 class="pull-left page-title">设计院子项目信息填写</h4>
                            </div>
<%--                            <div class="col-sm-2">--%>
<%--                                <input style="float: left;" type="button" id="addSub" class="btn btn-primary" onclick="addSubproject();" value="新增子项目">--%>
<%--                            </div>--%>
                                <ol class="breadcrumb pull-right">
                                <li><a href="#">设计院子项目信息填写</a></li>
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
                                                <input id="id" name="id" type="hidden" value="${po.id}"/>
                                                <input id="confirmStatus" name="confirmStatus" type="hidden" value="1"/>
<%--                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">--%>
<%--                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">客户来源<span--%>
<%--                                                            class="required" style="color: red"> * </span>：</label>--%>
<%--                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">--%>
<%--                                                        <input type="text" name="place" id="place" placeholder="请输入项目地点" value="${po.place}" class="form-control required" >--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">项目建档时间<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class='input-group date col-sm-6 col-md-6 col-xs-6' id='datetimepicker4' >
                                                        <input type="text" id="endTime2" readonly="readonly" value="<c:if test='${parentPo.projectCreationTime != null}'><fmt:formatDate value='${parentPo.projectCreationTime}' pattern='yyyy-MM-dd　HH:mm:ss'/></c:if>" class="form-control" /> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">客户来源<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" readonly="readonly" value="${parentPo.customerSource}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">客户编码<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" readonly="readonly" value="${parentPo.customerCode}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">项目类型<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="hidden" readonly="readonly" id="projectTypeCode" value="${po.projectTypeCode}" class="form-control" >
                                                        <input type="text" readonly="readonly" value="${po.projectTypeName}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">收单日期<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="input-group date col-sm-6 col-md-6 col-xs-6" id='datetimepicker1' >
                                                        <input type="text" id="endTimee" readonly="readonly" value="<c:if test='${po.acquisitionDate != null}'><fmt:formatDate value='${po.acquisitionDate}' pattern='yyyy-MM-dd　HH:mm:ss'/></c:if>" class="form-control" /> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12" id="constructionAreaDiv">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">建筑面积<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" name="constructionArea" id="constructionArea" placeholder="请输入建筑面积" value="${po.constructionArea}"
                                                               class="form-control required" onchange="getTotalCost()">
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12" id="renderingNumDiv">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">效果图张数<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" name="constructionArea" id="renderingNum" placeholder="效果图张数" value="${po.renderingNum}"
                                                               class="form-control required" onchange="getTotalCost()">
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">初次交付日期<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="input-group date col-sm-6 col-md-6 col-xs-6" id='datetimepicker2' >
                                                        <input type="text"  name="initialDeliveryDate" id="initialDeliveryDate"  value="<c:if test='${po.initialDeliveryDate != null}'>
                                                            <fmt:formatDate value='${po.initialDeliveryDate}' pattern='yyyy-MM-dd　HH:mm:ss'/>" class="form-control" /></c:if>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span> </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">成本单价(元/㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" name="costPrice" id="costPrice" placeholder="请输入成本单价" value="${po.costPrice}"
                                                               class="form-control required" onchange="getTotalCost()">
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">制图单位<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" name="draftingUnit" id="draftingUnit" placeholder="请输入制图单位" value="${po.draftingUnit}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">成本总价<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" readonly="readonly" name="totalCost" id="totalCost" value="${po.totalCost}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-4 col-md-4 col-xs-4 pull-left" style="line-height: 40px">设计师提成<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-6 col-md-6 col-xs-6 pull-left">
                                                        <input type="text" name="designerCommission" id="designerCommission" value="${po.designerCommission}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="col-sm-7 pull-right" style="margin-top: 10px">
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
        $('#datetimepicker2').datetimepicker({
            format:"YYYY-MM-DD HH:mm:ss"
        });
        $("#datetimepicker2").on("dp.change", function (e) {
            $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
        });

    });
    $("#save").click(function(){
        var par = $("form").serialize();
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

    $(function () {
        initTotalCost();

    });

    /**
     * 修改建筑面积(制图张数)或者成本单价时,自动计算成本总价
     */
    function getTotalCost() {
        var projectTypeCode = $("#projectTypeCode").val();
        var costPrice = $("#costPrice").val(); //成本单价
        var totalCost = '0';
        if(projectTypeCode && projectTypeCode == '005') { //按效果图张数算
            var renderingNum = $("#renderingNum").val();
            if(renderingNum && costPrice) {
                totalCost = eval(renderingNum * costPrice);
            }
        } else { //按建筑面积算
            // 成本总价=成本单价*建筑面积(制图张数)
            var constructionArea = $("#constructionArea").val();
            if(constructionArea && costPrice) {
                totalCost = eval(constructionArea * costPrice);
            }
        }
        $("#totalCost").val(totalCost);
        // var constructionArea = $("#constructionArea").val(); // 建筑面积
        // var costPrice = $("#costPrice").val(); //成本单价
        // var renderingNum = $("#renderingNum").val(); //效果图张数
        // if(constructionArea && costPrice) {
        //     var totalCost = constructionArea * 1 * costPrice * 1;
        //     $("#totalCost").val(totalCost);
        // } else {
        //     $("#totalCost").val(0);
        // }
    }

    /**
     * 初始化成本总价
     */
    function initTotalCost() {
        // 获取项目类型
        var projectTypeCode = $("#projectTypeCode").val();
        var costPrice = $("#costPrice").val(); //成本单价
        var totalCost = '0';
        if(projectTypeCode && projectTypeCode == '005') { //按效果图张数算
            // 隐藏建筑面积,显示效果图张数
            $("#constructionAreaDiv").hide();
            $("#renderingNumDiv").show();
            var renderingNum = $("#renderingNum").val();
            if(renderingNum && costPrice) {
                totalCost = eval(renderingNum * costPrice);
            }
        } else { //按建筑面积算
            // 显示建筑面积,隐藏效果图张数
            $("#constructionAreaDiv").show();
            $("#renderingNumDiv").hide();
            // 成本总价=成本单价*建筑面积(制图张数)
            var constructionArea = $("#constructionArea").val();
            if(constructionArea && costPrice) {
                totalCost = eval(constructionArea * costPrice);
            }
        }
        $("#totalCost").val(totalCost);
    }
</script>


</body>
</html>