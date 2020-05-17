<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../../common/common_css.jsp" %>

    <title>愿景建筑OA系统</title>

    <%@ include file="../../common/common_tag.jsp" %>

    <script src="${domain}/blue/assets/js/modernizr.min.js"></script>
    <link href="${cssjs}/blue/select2/css/select2.css" rel="stylesheet" />


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
<%--                                <input style="float: left;" type="button" id="addSub" class="btn btn-primary" value="新增子项目">--%>
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
                                                <input id="menuPid" name="menuPid" type="hidden" value="${menuPid}"/>

                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">客户来源<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left" style="text-align: center!important;">

                                                        <select id="customerCode" name="customerCode"   style="display: inline;"
                                                                class="form-control valid required js-example-tags">
                                                            <option value="">--请选择--</option>
                                                        </select>

                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">项目地点：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="place" id="place" placeholder="请输入项目地点" value="${po.place}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">客户电话：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="customerPhone" id="customerPhone" placeholder="请输入客户电话" value="${po.customerPhone}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售面积(㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" id="salesArea" placeholder="请输入销售面积" value="${po.salesArea/100}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">销售单价(元/㎡)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" id="unitPrice" placeholder="请输入销售单价" value="${po.unitPrice/100}" class="form-control required" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">设计院总成本(元)<span
                                                            class="required" style="color: red"> * </span>：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" id="allCost" placeholder="设计院总成本" value="${po.allCost/100}" class="form-control required" >
                                                    </div>
                                                </div>
<%--                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">--%>
<%--                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">成本单价(元/㎡)<span--%>
<%--                                                            class="required" style="color: red"> * </span>：</label>--%>
<%--                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">--%>
<%--                                                        <input type="text" id="costPrice" placeholder="请输入成本单价" value="${po.costPrice/100}" class="form-control required" >--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">已回款金额：</label>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <input type="text" id="amountReturned" placeholder="请输入已回款金额" value="${po.amountReturned/100}" class="form-control" >
                                                    </div>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <label class="form-group col-sm-8 col-md-8 col-xs-8 pull-left" style="line-height: 40px">上月已回款金额：</label>
                                                        <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                            <input type="text"   readonly="readonly"  value="${po.amountReturnedOld/100}" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">应收金额：</label>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <input type="text" id="amountReceivable" placeholder="请输入应收金额" value="${po.amountReceivable/100}" class="form-control" >
                                                    </div>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <label class="form-group col-sm-8 col-md-8 col-xs-8 pull-left" style="line-height: 40px">上月应收金额：</label>
                                                        <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                            <input type="text"   readonly="readonly"  value="${po.amountReceivableOld/100}" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">已支出金额：</label>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <input type="text" id="amountSpent" placeholder="请输入已支出金额" value="${po.amountSpent/100}" class="form-control" >
                                                    </div>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <label class="form-group col-sm-8 col-md-8 col-xs-8 pull-left" style="line-height: 40px">上月已支出金额：</label>
                                                        <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                            <input type="text"   readonly="readonly"  value="${po.amountSpentOld/100}" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">应付金额：</label>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <input type="text" id="amountsPayable" placeholder="请输入应付金额" value="${po.amountsPayable/100}" class="form-control" >
                                                    </div>
                                                    <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                        <label class="form-group col-sm-8 col-md-8 col-xs-8 pull-left" style="line-height: 40px">上月应付金额：</label>
                                                        <div class="form-group col-sm-4 col-md-4 col-xs-4 pull-left">
                                                            <input type="text"   readonly="readonly"  value="${po.amountsPayableOld/100}" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">合同号：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="contractNo" id="contractNo" placeholder="请输入合同号" value="${po.contractNo}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group  col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">是否生产：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
<%--                                                        <input type="text" name="production" id="production" placeholder="请输入合同号" value="${po.production}" class="form-control" >--%>
                                                        <select class="form-control" id="production" name="production">
                                                            <option value="" selected>请选择</option>
                                                            <option value="0" <c:if test="${po.production == '0'}">selected</c:if>>是</option>
                                                            <option value="1" <c:if test="${po.production == '1'}">selected</c:if>>否</option>
                                                        </select>
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
<script src="${cssjs}/blue/select2/js/select2.min.js"></script>
<script src="${cssjs}/blue/select2/js/i18n/zh-CN.js"></script>
<script src="${cssjs}/blue/layui/layui.all.js"></script>
<!-- jQuery  -->
<script type="text/javascript">
    $(function () {
        $(".js-example-tags").select2({
            tags: true,
            language: 'zh-CN',
            placeholder: '--请选择--',
        });
        baseCallBackAJAX("post","${base}/data/business/getAll",null,"json","initBusiness(data)");
    })



    $("#save").click(function(){
        // 非空校验
        if($("#form").valid() == false){
            alert("操作失败,请检查各个输入项是否正确!");
            return false;
        }
        var par = $("#form").serialize();

        // 把单价和面积都*100,总价除以100
        var salesArea = $("#salesArea").val();


        var unitPrice = $("#unitPrice").val();
        // 销售费用
        var designSalesCost = salesArea*unitPrice
        if(designSalesCost){
            designSalesCost = parseInt(eval(designSalesCost).toFixed(2) * 100);
            par += "&designSalesCost="+designSalesCost;
        }
        if(salesArea){
            salesArea = parseInt(eval(salesArea).toFixed(2) * 100);
            par += "&salesArea="+salesArea;
        }
        if(unitPrice) {
            unitPrice = parseInt(eval(unitPrice).toFixed(2) * 100);
            par += "&unitPrice="+unitPrice;
        }

        // var costPrice = $("#costPrice").val();
        // if(costPrice) {
        //     costPrice = eval(costPrice) * 100;
        //     par += "&costPrice="+costPrice;
        // }

        var amountReturned = $("#amountReturned").val();
        if(amountReturned) {
            amountReturned = parseInt(eval(amountReturned).toFixed(2) * 100);
            par += "&amountReturned="+amountReturned;
        }

        var amountReceivable = $("#amountReceivable").val();
        if(amountReceivable) {
            amountReceivable = parseInt(eval(amountReceivable).toFixed(2) * 100);
            par += "&amountReceivable="+amountReceivable;
        }

        var amountSpent = $("#amountSpent").val();
        if(amountSpent) {
            amountSpent = parseInt(eval(amountSpent).toFixed(2) * 100);
            par += "&amountSpent="+amountSpent;
        }

        var amountsPayable = $("#amountsPayable").val();
        if(amountsPayable) {
            amountsPayable = parseInt(eval(amountsPayable).toFixed(2) * 100);
            par += "&amountsPayable="+amountsPayable;
        }

        var allCost = $("#allCost").val();
        if(allCost) {
            allCost = parseInt(eval(allCost).toFixed(2) * 100);
            par += "&allCost="+allCost;
        }

        var customerSource = $('#customerCode option:selected').text();
        if(customerSource) {
            par += "&customerSource="+customerSource;
        }
        baseCallBackAJAX("post","${base}/project/main-project/save",par,"json","saveCallback(data)");
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
                    window.location.href="${base}/project/main-project//project.html?chirld=${chirld}&parent=${parent}";
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
     * 初始化客户来源下拉框
     * @param date
     */
    function initBusiness(data) {
        if(data) {
            for(var i = 0; i < data.length; i++) {
                var business = data[i];
                var pinyin = "";
                var businessName = "";

                if (business.pinyin) {pinyin = business.pinyin}
                if (business.business) {businessName = business.business;}
                if("${po.customerSource}" == businessName) {
                    $("#customerCode").append("<option value='"+pinyin+"' selected>"+businessName+"</option>");
                } else {
                    $("#customerCode").append("<option value='"+pinyin+"' >"+businessName+"</option>");
                }
            }
        }
    }
</script>


</body>
</html>