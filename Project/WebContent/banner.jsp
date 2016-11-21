<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting System</title>
<%@ taglib prefix ="s" uri='/struts-tags' %>
<script type="text/javascript">
function closewindows(){
	var flag = confirm("确定要退出吗?");
	if(flag){
		location.replace("logoutAction");
	//window.open("about:blank","_top").close();
	//response.sendRedirect("login.jsp");
	}
		return false;
}

</script>

<style type="text/css">
body {
	background-color: #2292DD;
	background: url(res/banner/banner_1023.jpg);
}

h1 {
	font-family: '华文隶书';
	font-size: 45px;
	color: #FFC125;
	text-align: center;
}
h2 {
	padding: 49px 0px 0px 1100px;
	}
</style>
</head>
<body  onunload="checkLeave()">
	<h2><a>退出</a>
	<input type="image" style="width:25px;height:25px;" onclick="closewindows()" title="退出登录" src="res/exit_button/exit.png">
	</h2>
</body>
</html>