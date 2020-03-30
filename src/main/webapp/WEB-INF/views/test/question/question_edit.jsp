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
                            <h4 class="pull-left page-title">部门管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">考试管理</a></li>
                                <li class="active">部门管理</li>
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
                                                <input id="answer" name="answer" type="hidden" value="${po.answer}"/>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">部门：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <select id="departmentId_select"  name="departmentId_select" class="form-control valid" >
                                                        </select>
                                                    </div>
                                                    <input type="hidden" name="departmentId" id="departmentId" value="${po.departmentId}"/>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">题干：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <textarea name="question" id="question" class="bootstrap-textarea" >${po.question}</textarea>
<%--                                                        <input type="text" name="question" id="question" placeholder="请输入编码" value="${po.question}" class="form-control">--%>
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">分数：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <input type="text" name="score" id="score" placeholder="请输入分数" value="${po.score}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                    <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">题型：</label>
                                                    <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                        <select id="type"  name="type" class="form-control valid" >
                                                            <option value="0"> 单选题 </option>
                                                            <option value="1"> 多选题 </option>
                                                            <option value="2"> 判断题 </option>
<%--                                                            <option value="3"> 填空题</option>--%>
<%--                                                            <option value="4"> 简答题 </option>--%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div id = "type0">
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项A：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checkA" id="checkA" value="${po.checkA}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项B：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checkB" id="checkB" value="${po.checkB}" class="form-control" />
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项C：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checkC" id="checkC" value="${po.checkC}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项D：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checkD" id="checkD" value="${po.checkD}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">正确答案：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <table class="table table-hover mails">
                                                                <tr class="col-sm-6 pull-left">
                                                                    <td class="mail-select">
                                                                        <div class="checkbox checkbox-primary">
                                                                            <input id="radioA" name="radio" value="A" type="radio"/>  </label>
                                                                        </div>
                                                                    </td>
                                                                    <td>A</td>
                                                                </tr>
                                                                <tr class="col-sm-6 pull-left">
                                                                    <td class="mail-select">
                                                                        <div class="checkbox checkbox-primary">
                                                                            <input id="radioB" name="radio" value="B" type="radio"/> </label>
                                                                        </div>
                                                                    </td>
                                                                    <td>B</td>
                                                                </tr>
                                                                <tr class="col-sm-6 pull-left">
                                                                    <td class="mail-select">
                                                                        <div class="checkbox checkbox-primary">
                                                                            <input id="radioC" name="radio" value="C" type="radio"/> </label>
                                                                        </div>
                                                                    </td>
                                                                    <td>C</td>
                                                                </tr>
                                                                <tr class="col-sm-6 pull-left">
                                                                    <td class="mail-select">
                                                                        <div class="checkbox checkbox-primary">
                                                                            <input id="radioD" name="radio" value="D" type="radio" /> </label>
                                                                        </div>
                                                                    </td>
                                                                    <td>D</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- 多选 -->
                                                <div id = "type1" style="display: none">
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项A：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checksA" id="checksA" value="${po.checksA}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项B：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checksB" id="checksB" value="${po.checksB}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项C：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checksC" id="checksC" value="${po.checksC}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">选项D：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <input type="text" name="checksD" id="checksD" value="${po.checksD}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div id="role_ids" class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">正确答案：</label>
                                                        <div class="form-group col-sm-8 col-md-8 col-xs-8 pull-left">
                                                            <table class="table table-hover mails">
                                                                    <tr class="col-sm-6 pull-left">
                                                                        <td class="mail-select">
                                                                            <div class="checkbox checkbox-primary">
                                                                                <input id="checkboxA" name="checks" value="A" type="checkbox"/> <label for="checkbox5"> </label>
                                                                            </div>
                                                                        </td>
                                                                        <td>A</td>
                                                                    </tr>
                                                                    <tr class="col-sm-6 pull-left">
                                                                        <td class="mail-select">
                                                                            <div class="checkbox checkbox-primary">
                                                                                <input id="checkboxB" name="checks" value="B" type="checkbox"/> <label for="checkbox5"> </label>
                                                                            </div>
                                                                        </td>
                                                                        <td>B</td>
                                                                    </tr>
                                                                    <tr class="col-sm-6 pull-left">
                                                                        <td class="mail-select">
                                                                            <div class="checkbox checkbox-primary">
                                                                                <input id="checkboxC" name="checks" value="C" type="checkbox"/> <label for="checkbox5"> </label>
                                                                            </div>
                                                                        </td>
                                                                        <td>C</td>
                                                                    </tr>
                                                                    <tr class="col-sm-6 pull-left">
                                                                        <td class="mail-select">
                                                                            <div class="checkbox checkbox-primary">
                                                                                <input id="checkboxD" name="checks" value="D" type="checkbox"/> <label for="checkbox5"> </label>
                                                                            </div>
                                                                        </td>
                                                                        <td>D</td>
                                                                    </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id = "type2" style="display: none">
                                                    <div class="form-group col-sm-12 col-md-12 col-xs-12">
                                                        <label class="form-group col-sm-2 col-md-2 col-xs-2 pull-left" style="line-height: 40px">正确答案：</label>
                                                        <input type="hidden" name="type2Answer" id="type2Answer"/>
                                                        <div class="col-sm-8">
                                                            <div class="btn-group " >
                                                                <button id="dui" onclick="$('#answer').val('T')" type="button" style="width: 80px;" class="btn btn-default waves-effect">T</button>
                                                                <button id="cuo" onclick="$('#answer').val('F')" type="button" style="width: 80px;"  class="btn btn-default waves-effect">F</button>
                                                            </div>
                                                        </div>
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
                <input id="init_type" type="hidden" value="${po.type}"/>
                <input type="hidden" id="init_department" value="${po.departmentId}"/>
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
    $("#dui").click(function(){
        $("#type2Answer").val('T');
        $("#dui").addClass(" btn-primary");
        $("#cuo").removeClass(" btn-primary");
    });
    $("#cuo").click(function (){
        $("#type2Answer").val('F');
        $("#cuo").addClass(" btn-primary");
        $("#dui").removeClass(" btn-primary");
    });

    function initDepartment(){
        baseCallBackAJAX("post","${base}/test/department/departmentAll",null,"json","departmentCallback(data)");
    }
    function departmentCallback(data){
        var options = "<option value = ''>--请选择--</option>";
        options += initOption(data.value);
        $("#departmentId_select").html(options);
    }
    function initOption(datas){
        var departmentId = $("#init_department").val();
        var optionStr = "";
        for(var i = 0;i < datas.length ; i++){
            if(departmentId == datas[i].id){
                optionStr += "<option value = "+datas[i].id+" selected='selected'>"+datas[i].departmentName+"</option>";
            }else{
                optionStr += "<option value = "+datas[i].id+">"+datas[i].departmentName+"</option>";
            }
        }
        return optionStr;
    }
    $(document).ready(function(){
        initDepartment();
        var type = $("#init_type").val();
        var answer = $("#answer").val();
        if(type == 0){
            $("#dui").addClass(" btn-primary");
            $("#cuo").removeClass(" btn-primary");
            if(answer == "A"){
                $("#radioA").attr("checked","checked");
            }else if(answer == "B"){
                $("#radioB").attr("checked","checked");
            }else if(answer == "C"){
                $("#radioC").attr("checked","checked");
            }else if(answer == "D"){
                $("#radioD").attr("checked","checked");
            }
        }
        if(type == 2){
            if(answer == 'T'){
                $("#dui").addClass(" btn-primary");
                $("#cuo").removeClass(" btn-primary");
            }else{
                $("#cuo").addClass(" btn-primary");
                $("#dui").removeClass(" btn-primary");
            }
        }else if(type == 1){
            // var answernew RegExp("W3School")
            if(answer.indexOf("A")>=0){
                $("#checkboxA").attr("checked","checked");
            }
            if(answer.indexOf("B")>=0){
                $("#checkboxB").attr("checked","checked");
            }
            if(answer.indexOf("C")>=0){
                $("#checkboxC").attr("checked","checked");
            }
            if(answer.indexOf("D")>=0){
                $("#checkboxD").attr("checked","checked");
            }
        }
        $("#type").val(${po.type});
        typeshowHide();
    });
    $("#save").click(function(){
        var type =  $("#type").val();
        var answer = "";
        if(type == 0){
           answer = $("input[name='radio']:checked").val();
        }else if(type == 1){
            var chk_value =[];//定义一个数组
            $('input[name="checks"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
                chk_value.push($(this).val());//将选中的值添加到数组chk_value中
            });
            answer = chk_value.join(",");
            // answer = $("input[name='checks']:checked").val();
        }else if(type == 2){
            answer = $("#type2Answer").val();
        }
        $("#answer").val(answer);
        baseCallBackAJAX("post","${base}/test/question/save",$("#form").serialize(),"json","saveCallback(data)");
    });
    $("#departmentId_select").change(function(){
        var departmentId = $("#departmentId_select").val();
        $("#departmentId").val(departmentId);
    });

    $("#type").change(function(e){
        typeshowHide();
    });
    function typeshowHide(){
        var type =  $("#type").val();
        if(type == 0){
            $("#type0").show();
            $("#type1").hide();
            $("#type2").hide();
        }else if(type == 1){
            $("#type0").hide();
            $("#type1").show();
            $("#type2").hide();
        }else if(type == 2){
            $("#type0").hide();
            $("#type1").hide();
            $("#type2").show();
        }
    }
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
                    window.location.href="${base}/test/question/question.html?chirld=${chirld}&parent=${parent}";
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