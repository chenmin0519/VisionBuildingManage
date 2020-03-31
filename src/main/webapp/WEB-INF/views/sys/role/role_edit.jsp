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
                            <h4 class="pull-left page-title">角色管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">角色管理</a></li>
                                <li class="active">系统管理</li>
                            </ol>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-8 col-md-8">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 id="panel-title" class="panel-title"></h3>
                                </div>
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="panel panel-default">
                                            <form id="form">
                                                <input id="id" name="id" type="hidden" value="${po.id}"/>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">角色名：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="roleName" id="roleName" placeholder="请输入角色名" value="${po.roleName}" class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">状态：</label>
                                                    <input type="hidden" name="status" id="status" value="${po.status}"/>
                                                    <div class="col-sm-8">
                                                        <div class="btn-group">
                                                            <button id="jh" onclick="$('#jh').val(1)" type="button" class="btn btn-default waves-effect">正常</button>
                                                            <button id="dj" onclick="$('#dj').val(0)" type="button" class="btn btn-default waves-effect">冻结</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">角色说明：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <textarea id="roleComment" style="height: 100px" class="form-control">${po.roleComment}</textarea>
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
                        <div class="col-lg-4 col-md-4">
                            <div class="zTreeDemoBackground left">
                                <ul id="ztree" class="ztree"
                                    style="max-height: 725px; overflow: auto;"></ul>
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-10 pull-right">
                                    <button id="saveMenu" type="button"
                                            class="btn btn-primary waves-effect waves-light m-b-5">
                                        <i class="ion-checkmark"></i>&nbsp;保存
                                    </button>
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


<script type="text/javascript">
    $(document).ready(function(){
        var status = $("#status").val();
        if(status != null){
            if(status == '1'){
                $("#status").val('1');
                $("#jh").addClass(" btn-primary");
            }else{
                $("#status").val('0');
                $("#dj").addClass(" btn-primary");
            }
        }
    });
    $("#jh").click(function(){
        $("#status").val('1');
        $("#jh").addClass(" btn-primary");
        $("#dj").removeClass(" btn-primary");
    });
    $("#dj").click(function (){
        $("#status").val('0');
        $("#dj").addClass(" btn-primary");
        $("#jh").removeClass(" btn-primary");
    });
    //设置结构
    var setting = {
        check: {
            enable: true
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                idKey: "id",
                pIdKey: "pId",
                enable: true
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    //树的值
    var zNodes = [${menuTree}];

    //初始化树
    $(document).ready(function () {
        var treeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
    });
    //点击节点事件
    function zTreeOnClick(event, treeId, treeNode) {
        var parentId = treeNode.id;
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var sNodes = treeObj.getSelectedNodes();
        if (sNodes.length > 0) {
            if (!treeNode.isParent) {
                $.ajax({
                    type : 'POST',
                    url : '${base}/sys/menu/queryChildByParentId',
                    data : {
                        'parentId' : parentId
                    },
                    dataType : 'json',
                    async : false,
                    success : function(dat) {
                        if(treeNode.checked){
                            for(var i = 0; i <dat.length;i++){
                                dat[i].checked = true;
                            }
                        }
                        var dats = eval(dat);
                        if (dats.length != 0) {
                            treeObj.addNodes(treeNode, dats);
                        }
                    }
                });
            } else {
                treeObj.expandNode(treeNode);
            }
        }
    }


    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("ztree");
        type = {"Y": "", "N": ""};
        zTree.setting.check.chkboxType = type;
    }
</script>

<script type="text/javascript">
    $("#saveMenu").click(function () {
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var nodes = treeObj.getCheckedNodes(true);
        var roleId =  $("#id").val();
        if(roleId == null || roleId == ""){
            swal({
                title: "警告",
                text: "请先保存角色信息",
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            });
            return;
        }
        if(0 === nodes.length) {
            swal({
                title: "请选择",
                text: "请选择菜单",
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            });
            return;
        }
        var dataNodes = "";
        for(var i = 0; i < nodes.length; i++) {
            dataNodes += nodes[i].id + ",";
        }
        baseCallBackAJAX("post","${base}/sys/role/saveRoleAndMenus",{"roleId":roleId,"menuIds":dataNodes},"json","saveMenuCallback(data)");
    });
    function saveMenuCallback(data){
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
                    window.location.href="${base}/sys/role/role.html?chirld=${chirld}&parent=${parent}";
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
<!-- jQuery  -->
<script type="text/javascript">

    $("#save").click(function(){
        var roleName = $("#roleName").val();
        var roleComment = $("#roleComment").val();
        var status = $("#status").val();
        var id = $("#id").val();
        baseCallBackAJAX("post","${base}/sys/role/saveRole",{"id":id,"roleName":roleName,"roleComment":roleComment,"status":status},"json","saveCallback(data)");
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
            });
            $("#id").val(data.value.id);
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