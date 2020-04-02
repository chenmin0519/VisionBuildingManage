<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>在线考试运营系统</title>
    <%--<link href="${domain}/backstage/css/main.css" rel="stylesheet" type="text/css">--%>
    <link href="${cssjs}/blue/assets/css/base.css" rel="stylesheet" type="text/css">
    <%@ include file="common_css.jsp" %>
    <%@ include file="common_tag.jsp" %>
    <!-- 解决网站被其它网站嵌套 -->
    <script type="text/javascript" >
		 
	</script>
    
    <script type="text/javascript">
        function logout() {
            window.location.href = "${base}/logout";
        }
        function resetPwd(){
            window.location.href = "${base}/resetPwd.html";
        }
    </script>
</head>
<div class="topbar heard_hiden">
<div class="topbar-left">
                    <div class="text-center">
                        <a href="${base}/main.html" class="logo"><img class="system-logo-title"  style="margin: auto;" src="${cssjs}/blue/assets/images/logo.png" /></a>
                    </div>
                </div>
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="">
                            <div class="pull-left">
                                <button class="button-menu-mobile open-left">
                                    <i class="fa fa-bars"></i>
                                </button>
                                <span class="clearfix"></span>
                            </div>
                                <div class="pull-left system-title"><span>愿景建筑OA系统</span></div>
                                <button type="submit" class="btn btn-search"><i class="fa fa-search"></i></button>
                            </div>

                            <ul class="nav navbar-nav navbar-right pull-right">
                                
                                <li class="hidden-xs">
                                    <a href="#" id="btn-fullscreen" class="waves-effect"><i class="md md-crop-free"></i></a>
                                </li>
                                
                                <li class="dropdown">
                                    <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img src="${cssjs}/blue/assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:logout()"><i class="md md-face-unlock"></i> 注销</a></li>
                                        <li><a href="javascript:resetPwd()"><i class="glyphicon glyphicon-cog"></i> 修改密码</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>