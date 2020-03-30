<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../../common/common_css.jsp" %>

    <title>在线考试运营系统</title>

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
                            <h4 class="pull-left page-title">试题导入</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">试题导入</a></li>
                                <li class="active">试题管理</li>
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
                                            <form id="form" action="${base}/import/importKskInfo" method="post" enctype="multipart/form-data">
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">excel文件：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="file" name="dbfimport" id="dbfimport"  class="form-control" >
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">导入模板下载：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <a href="${base}/import/download/excel">点击下载excel导入模板</a>
                                                    </div>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="col-sm-10 pull-right">
                                                        <button id = "add" type="button"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-checkmark"></i>&nbsp;导入
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
    <div class="modal fade" id="loadingModal">
        <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
            <div class="progress progress-striped active" style="margin-bottom: 0;">
                <div id = "jdt" class="progress-bar" style="width: 1%;"></div>
            </div>
            <h5>正在导入...数据量大等待时间较长，请稍等！</h5>
        </div>
    </div>



</div>
<!-- END wrapper -->



<script>
    var resizefunc = [];
</script>
<%@ include file="../../common/common_js.jsp"%>
<%@ include file="../../common/formajax_js.jsp"%>
<!-- jQuery  -->
<script type="text/javascript">
    $("#add").click(function(){
        var fileObj = document.getElementById("dbfimport").files[0];
        if(fileObj == undefined || fileObj == null){
            swal({
                title: "操作失败",
                text:"请选择excel文件",
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#dd6b55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            });
            return;
        }
        var formFile = new FormData();
        formFile.append("action", "UploadVMKImagePath");
        formFile.append("dbfimport", fileObj);

        //backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
        //keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
        $('#loadingModal').modal({backdrop: 'static', keyboard: false});
        $("#loadingModal").modal('show');
        window.setInterval("jdt()",300);
        baseCallBackUploadAJAX("post","${base}/import/importQuestion",formFile,"json","saveCallback(data)");
    });
    var count = 1;
    function jdt(){
        count++;
        if(count < 100){
            $("#jdt").css("width",count+"%");
        }
    }
    function saveCallback(data){
        $("#jdt").css("width","100%");
        $('#loadingModal').modal('hide');
        if(data.status=="0"){
            swal({
                title: "操作成功",
                text:data.message,
                type: "success",
                showCancelButton: false,
                confirmButtonColor: "#dd6b55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            },function(isConfirm) {
                if(isConfirm==true){
                    window.location.href="${base}/test/question/question.html?chirld=${chirld}&parent=${parent}";
                }
            });
        }else{
            swal({
                title: "操作失败",
                text:data.message,
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#dd6b55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm : true, //关闭按钮
                closeOnCancel : true
            });
        }
    }
</script>
<script type="text/javascript">

</script>


</body>
</html>