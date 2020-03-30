<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">
<title>登录</title>
<%@ include file="common/common_css.jsp"%>
<%@ include file="common/common_tag.jsp"%>
</head>
<body>
	<div class="wrapper-page">
		<div class="panel panel-color panel-primary panel-pages">
			<div class="panel-heading bg-img"> 
                    <div class="bg-overlay"></div>
                    <h3 class="text-center m-t-10 text-white"> <strong>在线考试运营系统</strong> </h3>
                </div>
			<div class="panel-body" >
				<form class="form-horizontal m-t-20" method="" id="form" action="">
					<div class="form-group">
						<div class="col-xs-12">
							<input class="form-control input-lg" type="text" name="user_name" id="user_name" required
								placeholder="用户名" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12">
							<input class="form-control input-lg" type="password" name="password" id="password" required
								placeholder="密码" /><font id="errorMsg" style="color:red"></font>
						</div>
						
					</div>
			
					<div class="form-group text-center m-t-40">
						<div class="col-xs-12">
							<div id="login1div"><button id="login1"   class="btn btn-primary btn-lg w-lg waves-effect waves-light" type="button">登&nbsp;&nbsp;录</button></div>
						</div>
					</div>

					<div class="form-group m-t-30">
						<div class="col-sm-7">
							<%--<a href="#"><i class=""></i>@fly.project</a>--%>
						</div>
						<div class="col-sm-5 text-right">
							<%--<a href="${base}/adduser.html">去开卡</a>--%>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>


	<script>
        var resizefunc = [];
	</script>

	<%@ include file="common/common_js.jsp"%>
	<script type="text/javascript">
		$("#login1").click(function(){
			var user_name = $("#user_name").val();
			var password = $("#password").val();
            $.ajax({
                type: 'post',
                url: "login",
                data: {"userName":user_name,"password":password},
                dataType: "json",
                success: function (data) {
					if(data.status == 0){
						window.location.href = "${base}/main.html";
					}else{
					    $("#errorMsg").text(data.message);
					}
                }
            });
		});
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#login1").click();
            }
        });
	</script>
</body>
</html>