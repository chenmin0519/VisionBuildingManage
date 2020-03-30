<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ include file="common_tag.jsp" %>
<head>
<script type="text/javascript">

</script>
</head>
<div class="left side-menu">
	<div class="sidebar-inner slimscrollleft">
		<div class="user-details">
			<div class="pull-left">
				<img
					src="${cssjs}/blue/assets/images/users/avatar-1.jpg"
					alt="" class="thumb-md img-circle">
			</div>
			<div class="user-info">
				<div class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						aria-expanded="false">欢迎使用<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:logout()"><i
								class="md md-face-unlock"></i> 注销</a></li>
					</ul>
				</div>
					<p class="text-muted m-0">管理员</p>
			</div>
		</div>
		<!--- Divider -->
		<div id="sidebar-menu">
			<ul>
				<li class="has_sub">
					<a href="${base}/main.html" id = "main-click" class="waves-effect waves-light">
						<i class="glyphicon glyphicon-home"></i>
						<span>主页</span>
					</a>
				</li>
				<c:forEach items="${menusInfo}" var="parentMenu">
					<c:choose>
						<c:when test="${parentMenu.id == parent}">
							<li class="has_sub" id="${parentMenu.id}">
								<a href="#" class="waves-effect waves-light"><i class="${parentMenu.icon}"></i>
								<span>${parentMenu.menuName}</span><span class="pull-right"><i class="md md-remove"></i></span></a>
								<ul class="list-unstyled"  style="display: block">
									<c:forEach items="${parentMenu.chrildMenus}" var="chrildMenu">
										<c:choose>
											<c:when test="${chirld == chrildMenu.id}">
												<li class = " active "><a href="${base}${chrildMenu.menuUrl}?chirld=${chrildMenu.id}&parent=${parentMenu.id}">${chrildMenu.menuName}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${base}${chrildMenu.menuUrl}?chirld=${chrildMenu.id}&parent=${parentMenu.id}">${chrildMenu.menuName}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</li>
							</c:when>
							<c:otherwise>
								<li class="has_sub" id="${parentMenu.id}">
									<a href="#" class="waves-effect waves-light"><i class="${parentMenu.icon}"></i>
										<span>${parentMenu.menuName}</span><span class="pull-right"><i class="md md-add"></i></span>
									</a>
									<ul class="list-unstyled"  style="display: none">
										<c:forEach items="${parentMenu.chrildMenus}" var="chrildMenu">
											<li><a href="${base}${chrildMenu.menuUrl}?chirld=${chrildMenu.id}&parent=${parentMenu.id}">${chrildMenu.menuName}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:otherwise>
						</c:choose>

				</c:forEach>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<script src="${base}/js/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">

    $("#main-click").click(function(){
        window.location.href="${base}/main.html";
    });

</script>