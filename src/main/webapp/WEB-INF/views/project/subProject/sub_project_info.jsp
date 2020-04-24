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
                                <h4 class="pull-left page-title">子项目详情</h4>
                            </div>
<%--                            <div class="col-sm-2">--%>
<%--                                <input style="float: left;" type="button" id="addSub" class="btn btn-primary" onclick="addSubproject();" value="新增子项目">--%>
<%--                            </div>--%>
                                <ol class="breadcrumb pull-right">
                                <li><a href="#">子项目详情</a></li>
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
                                                <table class="table table-striped table-bordered">
                                                    <thead>
                                                    <input type="hidden" id="resourcePid" value="${po.id}">
                                                    <tr>
                                                        <th width="15%" style="text-align: center">项目建档时间</th>
                                                        <td width="35%"><div>${parentPo.projectCreationTime}</div></td>
                                                        <th width="15%" style="text-align: center">客户来源</th>
                                                        <td width="35%"><div >${parentPo.customerSource}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">客户编码</th>
                                                        <td><div >${parentPo.customerCode}</div></td>
                                                        <th style="text-align: center">项目类型</th>
                                                        <td><div >${po.projectTypeName}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">收单日期</th>
                                                        <td><div ><c:if test='${po.acquisitionDate != null}'><fmt:formatDate value='${po.acquisitionDate}' pattern='yyyy-MM-dd　HH:mm:ss'/></c:if></div></td>
                                                       <c:if test="${po.projectTypeCode != '005'}">
                                                           <th style="text-align: center">建筑面积</th>
                                                           <td><div >${po.constructionArea/100}</div></td>
                                                       </c:if>
                                                        <c:if test="${po.projectTypeCode == '005'}">
                                                            <th style="text-align: center">效果图张数</th>
                                                            <td><div >${po.renderingNum/100}</div></td>
                                                        </c:if>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">初次交付日期</th>
                                                        <td><div ><c:if test='${po.initialDeliveryDate != null}'>
                                                            <fmt:formatDate value='${po.initialDeliveryDate}' pattern='yyyy-MM-dd　HH:mm:ss'/></c:if></div></td>
                                                        <th style="text-align: center">成本单价(元/㎡)</th>
                                                        <td><div >${po.costPrice/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">制图单位</th>
                                                        <td><div >${po.draftingUnit}</div></td>
                                                        <th style="text-align: center">成本总价</th>
                                                        <td><div >${po.totalCost/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">设计师提成</th>
                                                        <td><div >${po.designerCommission/100}</div></td>
                                                        <th style="text-align: center"></th>
                                                        <td><div ></div></td>
                                                    </tr>
                                                    </thead>
                                                </table>

                                                <div class="panel-body">
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

    /*$(function () {
        baseCallBackAJAX("post","${base}/data/projectType/getAll",null,"json","initProjectType(data)");

        // 成本总价=成本单价*建筑面积(制图张数)
       var constructionArea = $("#constructionArea").val();
       var costPrice = $("#costPrice").val();
       if(constructionArea && costPrice) {
           var totalCost = constructionArea * 1 * costPrice * 1;
           $("#totalCost").val(totalCost);
       } else {
           $("#totalCost").val(0);
       }
    });
*/
</script>


</body>
</html>