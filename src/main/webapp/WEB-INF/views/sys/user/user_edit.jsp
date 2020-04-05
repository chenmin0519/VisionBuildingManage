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
                            <h4 class="pull-left page-title">用户管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">用户管理</a></li>
                                <li class="active">系统管理</li>
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
                                                <input id="id" name="id" type="hidden" value="${userpo.id}"/>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">用户名：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="userName" id="userName" placeholder="请输入用户名" value="${userpo.userName}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">密码：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="password" name="password" id="password" placeholder="请输入密码" value="${userpo.password}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">姓名：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="realName" id="realName" placeholder="请输入姓名" value="${userpo.realName}" class="form-control" >
                                                    </div>
                                                </div>

                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">部门：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="hidden" name="department" id="department" value="${userpo.department}">
                                                        <select class="form-control departmentSelect"> <option value="">--请选择--</option> </select>
                                                    </div>
                                                </div>

                                                <div id="role_ids" class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">角色：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                    <table class="table table-hover mails">
                                                    <c:if test="${not empty po.roles}">
                                                    <c:forEach items="${po.roles}" var="allRole">
                                                        <tr class="col-sm-6 pull-left">
                                                            <td class="mail-select">
                                                                <div class="checkbox checkbox-primary">
                                                                    <c:if test="${allRole.status == '1'}">
                                                                    <input id="${allRole.id}" name="role" type="checkbox" value="${allRole.id}" checked="checked" /> <label for="checkbox5"> </label>
                                                                    </c:if>
                                                                    <c:if test="${allRole.status == '0'}">
                                                                        <input id="${allRole.id}" name="role" value="${allRole.id}" type="checkbox"/> <label for="checkbox5"> </label>
                                                                    </c:if>
                                                                    <c:if test="${empty allRole.status}">
                                                                        <input id="${allRole.id}" name="role" value="${allRole.id}" type="checkbox"/> <label for="checkbox5"> </label>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                            <td>${allRole.roleName}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </c:if>
                                                        <c:if test="${empty po.roles}" >当前没有角色信息</c:if>
                                                    </table>
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

    $(document).ready(function() {
        initDepartmentOption();
    });
    function initDepartmentOption(){
        baseCallBackAJAX("post","${base}/data/department/getAll",null,"json","initDepartmentOptionCallback(data)");
    }
    function initDepartmentOptionCallback(data){
        var optionStr = "";
        var datas = data;
        var department = $("#department").val();
        for(var i = 0;i < datas.length ; i++){
            if(department == datas[i].id){
                optionStr += "<option value = "+datas[i].id+" selected = 'selected'>"+datas[i].department+"</option>";
            } else {
                optionStr += "<option value = "+datas[i].id+">"+datas[i].department+"</option>";
            }
        }
        $(".departmentSelect").append(optionStr);
    }
    $(".departmentSelect").change(function(){
        var department = $(".departmentSelect").val();
        $("#department").val(department);
    });
    $("#save").click(function(){
        var roleIds = "";
        $("input:checkbox[name='role']:checked").each(function() {
            roleIds += $(this).val() + ",";
        });
        var userName = $("#userName").val();
        var password = $("#password").val();
        var tel = $("#tel").val();
        var realName = $("#realName").val();
        var department = $("#department").val();
        var id = $("#id").val();
        baseCallBackAJAX("post","${base}/sys/user/saveuser",{'department':department,'userName':userName,'password':password,'tel':tel,'realName':realName,'id':id,'roles':roleIds},"json","saveCallback(data)");
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
                    window.location.href="${base}/sys/user/user.html?chirld=${chirld}&parent=${parent}";
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