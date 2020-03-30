<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>在线考试运营系统</title>

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
                            <h4 class="pull-left page-title">菜单管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">菜单管理</a></li>
                                <li class="active">系统管理</li>
                            </ol>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-2 col-md-4">
                            <button id="addMenu" type="button" class="btn btn-primary waves-effect waves-light m-b-5">
                                <i class="ion-plus"></i> 添加菜单
                            </button>
                            <div class="zTreeDemoBackground left">
                                <ul id="ztree" class="ztree"
                                style="max-height: 725px; overflow: auto;"></ul>
                            </div>
                        </div>
                        <div class="col-lg-10 col-md-8">
							<div id="resMess" style="display: none;"
								class="panel panel-default">
								<div class="panel-heading">
									<h3 id="panel-title1" class="panel-title"></h3>
								</div>
								<div class="panel-body">
									<!--目录导航  -->

									<div class="row">
										<div>
											<div style="text-align: right">
												<input type=hidden class=btn-success id="dataid"
													name="dataid"> <input type="hidden"
													id="provienceId" name="provienceId" />&nbsp;
												<!--  <input id="updateBtn" type=button class=btn-success value="修改">&nbsp;
													<input type=button class=btn-success onclick="deleteOrg(0)" value="删除">
														<input id="configOrgByParenAdm" value="机构模板 "  type=button class=btn-success onclick="configOrgByParenAdm()" /> -->
												<button id="editBtn" type="button" onclick="detil($('#resourceId').text(),$('#resourcePid').val())"
													class="btn btn-primary waves-effect waves-light m-b-5">
													<i class="ion-edit"></i> 修改
												</button>

												<button id="updateBtn" type="button"
													onclick="del($('#resourceId').text())"
													class="btn btn-primary waves-effect waves-light m-b-5">
													<i class="ion-trash-a"></i> 删除

												</button>

											</div>

											<table class="table table-striped table-bordered">
												<thead>
                                                    <<input type="hidden"  id="resourcePid" value="">
													<tr>
														<th width="15%" style="text-align: center">id</th>
														<td width="35%"><div id="resourceId"></div></td>
														<th width="15%" style="text-align: center">名称</th>
														<td width="35%"><div id="resName"></div></td>

													</tr>
											
													<tr>
														<th style="text-align: center">入口地址</th>
														<td><div id="resEntryaddress"></div></td>
														<th style="text-align: center">资源类型</th>
														<td><div id="resJumpType"></div></td>
													</tr>
													
													<tr>


													</tr>

												</thead>
											</table>
										</div>
									</div>

								</div>

							</div>
							
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 id="panel-title" class="panel-title"></h3>
                                </div>
                                <div class="panel-body">

                                    <div class="row">

                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="panel panel-default">
                                                <form id="form">
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <input type="text" placeholder="请输入菜单名" class="form-control" name="menuName">
                                                    </div>
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <input type="text" placeholder="请输入url" class="form-control" name="menuUrl">
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
                                                    <th nowrap="nowrap">资源名称</th>
                                                    <th nowrap="nowrap">父级id</th>
                                                    <th nowrap="nowrap">链接</th>
                                                    <th nowrap="nowrap">级别</th>
                                                    <th nowrap="nowrap">小图标</th>
                                                    <th nowrap="nowrap">状态</th>
                                                    <th nowrap="nowrap">排序</th>
                                                    <th>操作</th>
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

        <input type="hidden" id="parentId" value="0"/>
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
        //设置结构
        var setting = {
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

        function initParentInfo(parentId){
            $.ajax({
                type : 'POST',
                url : '${base}/sys/menu/queryMenuById',
                data : {
                    'id' : parentId
                },
                dataType : 'json',
                async : false,
                success : function(data) {
                    if (data.status == 0) {
                        $("#resourceId").text(data.value.id);
                        $("#resName").text(data.value.menuName);
                        $("#resEntryaddress").text(data.value.menuUrl);
                        $("#resourcePid").val(data.value.menuPid);
                        var lvStr = '';
                        if(data.value.lv == '1'){
                            lvStr = '系统级';
                        }else{
                            lvStr = '链接资源级';
                        }
                        $("#resJumpType").text(lvStr);

                    }
                }
            });
        }
        //点击节点事件
        function zTreeOnClick(event, treeId, treeNode) {
            var parentId = treeNode.id;
            initParentInfo(parentId);
            $("#parentId").val(parentId);
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
                            var dats = eval(dat);
                            if (dats.length != 0) {
                                //var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                                //treeObj.addNodes(treeNode, dats);
                                treeObj.addNodes(treeNode, dats);

                            }
                        }
                    });
                } else {
                    treeObj.expandNode(treeNode);
                }
            }
            $("#resMess").show();
            initTbale(parentId);
        }


        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("ztree");
            type = {"Y": "", "N": ""};
            zTree.setting.check.chkboxType = type;
        }
    </script>

<script type="text/javascript">
    var functionlist=[{targets:[8],
        mRender:function(data) {
            var result = "";
            if(data.status == 1){
                result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=del('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 删除</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detil('"
                    +data.id+"','"+ data.menuPid +"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetZt('"
                    + data.id+"','0')><i class='ion-ios7-paper-outline'></i> 冻结</button>";
            }else{
                result = "<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=del('"
                    +data.id+"')><i class='ion-ios7-paper-outline'></i> 删除</button>&nbsp;&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=detil('"
                    +data.id+"','"+ data.menuPid +"')><i class='ion-ios7-paper-outline'></i> 编辑</button>&nbsp;&nbsp;<button type=button style='padding: 1px 8px !important;' class='btn btn-primary waves-effect waves-light m-b-5' onclick=resetZt('"
                    + data.id+"','1')><i class='ion-ios7-paper-outline'></i> 激活</button>";
            }
            return result;
        }
    },{targets:[6],
        mRender:function(data) {
            var result = "";
            if(data.status == '1'){
               result = "正常";
            }else{
                result = "<p style='color:#ff0311'>冻结</p>";
            }
            return result;
        }
    }];
    function initTbale(parentId){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        var par = $("form").serialize();
        if(parentId != undefined && parentId != null){
            par += "&menuPid="+parentId;
        }
        initBaseTable(par,"datatable","${base}/sys/menu/getDatas",",menuName,menuPid,menuUrl,lv,icon,,num,",functionlist);
    }
    $(document).ready(function(){
        initTbale();
    });
    $("#query").click(function(){
        initTbale();
    });


    function resetZt(id,status){
        baseCallBackAJAX("get","${base}/sys/menu/resetMenuZt",{'id':id,'status':status},"json","callback(data)");
    }

    function del(id){
        baseCallBackAJAX("get","${base}/sys/menu/deluMenu",{'id':id},"json","callback(data)");
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
                    location.reload();
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
    function detil(id,parentId){
        window.location.href="${base}/sys/menu/edit.html?parentId="+parentId+"&id="+id+"&chirld=${chirld}&parent=${parent}";
    }
    $("#addMenu").click(function(){
        var parent = $("#parentId").val();
        window.location.href="${base}/sys/menu/addMenu.html?parentId="+parent+"&chirld=${chirld}&parent=${parent}";
    });
</script>

</body>
</html>