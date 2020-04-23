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

                                <ol class="breadcrumb pull-right">
                                <li><a href="#">项目管理</a></li>
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
                                        <div class="panel panel-default">
                                            <form id="form">
                                                <input id="id" name="id" type="hidden" value="${po.id}"/>
                                                <input id="menuPid" name="menuPid" type="hidden" value="${menuPid}"/>

<%--                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">--%>
<%--                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">客户来源<span--%>
<%--                                                            class="required" style="color: red"> * </span>：</label>--%>
<%--                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">--%>
<%--                                                        <input type="text" name="place" id="place" placeholder="请输入项目地点" value="${po.place}" class="form-control required" >--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>

                                                <table class="table table-striped table-bordered">
                                                    <thead>
                                                    <input type="hidden" id="resourcePid" value="${po.id}">
                                                    <tr>
                                                        <th width="15%" style="text-align: center">项目建档时间</th>
                                                        <td width="35%"><div >${po.projectCreationTime}</div></td>
                                                        <th width="15%" style="text-align: center">客户来源</th>
                                                        <td width="35%"><div >${po.customerSource}</div></td>
<%--                                                        <td><div ><c:if test="${po.production == 0}">是</c:if><c:if test="${po.production == 1}">否</c:if></div></td>--%>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">客户编码</th>
                                                        <td><div >${po.customerCode}</div></td>
                                                        <th style="text-align: center">项目地点</th>
                                                        <td><div >${po.place}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">客户电话</th>
                                                        <td ><div >${po.customerPhone}</div></td>
                                                        <th style="text-align: center">销售面积(㎡)</th>
                                                        <td><div >${po.salesArea/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">销售单价(元/㎡)</th>
                                                        <td><div >${po.unitPrice/100}</div></td>
                                                        <th style="text-align: center">成本单价(元/㎡)</th>
                                                        <td><div >${po.costPrice/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">已回款金额</th>
                                                        <td><div >${po.amountReturned/100}</div></td>
                                                        <th style="text-align: center">应收金额</th>
                                                        <td><div >${po.amountReceivable/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">已支出金额</th>
                                                        <td><div >${po.amountSpent/100}</div></td>
                                                        <th style="text-align: center">应付金额</th>
                                                        <td><div >${po.amountReceivable/100}</div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">合同号</th>
                                                        <td><div >${po.contractNo}</div></td>
                                                        <th style="text-align: center">是否生产</th>
                                                        <td><div ><c:if test="${po.production == 0}">是</c:if><c:if test="${po.production == 1}">否</c:if></div></td>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center">备注</th>
                                                        <td><div >${po.remarks}</div></td>
                                                        <th style="text-align: center"></th>
                                                        <td><div ></div></td>
                                                    </tr>
                                                    </thead>
                                                </table>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="row">


                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="panel panel-default">
                                                <form id="form_sub">
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <input type="text" placeholder="请输入子项目类型" class="form-control" name="projectTypeName">
                                                    </div>
                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button id="query" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-ios7-search"></i> 查询
                                                        </button>
                                                    </div>
                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button id="addSub" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="glyphicon glyphicon-plus"></i>新增
                                                        </button>
                                                    </div>
                                                    <div class="form-group col-sm-1 col-md-1 col-xs-3 pull-left">
                                                        <button onclick="history.back();" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="glyphicon glyphicon-share-alt"></i>返回
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
            if(data != null){
                result = getDateStr(data);
            }
            return result;
        }
    }];


    /**
     * 添加子产品
     * @param parentId
     */
    $("#addSub").click(function(){

        window.location.href="${base}/project/main-project/addSub.html?id=${po.id}&chirld=${chirld}&parent=${parent}";
        <%--window.location.href="${base}/project/main-project/info.html?id="+id+"&chirld=${chirld}&parent=${parent}";--%>

    });

    $("#query").click(function(){
        initTbale();
    });
    $(document).ready(function(){
        initTbale();
    })
    function initTbale(){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        var par = $("#form_sub").serialize();
        var id = $("#id").val();
        par += "&parentId="+id;
        initBaseTable(par,"datatable","${base}/project/main-project/getSubDatas",",projectTypeName,,,,acquisitionDate,remark,",functionlist);
    }

    function edit(id){
        window.location.href="${base}/project/main-project/addSub.html?id="+id+"&chirld=${chirld}&parent=${parent}";
    }
    function detail(id){
        window.location.href="${base}/project/main-project/subInfo.html?id="+id+"&chirld=${chirld}&parent=${parent}";
    }

    function del(id){
        baseCallBackAJAX("get","${base}/project/main-project/delSub",{'id':id},"json","callback(data)");
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