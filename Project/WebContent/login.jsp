<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix ="s" uri='/struts-tags' %>
<html>
<head>
<title>welcome</title>
<script type="text/javascript">
	if (window != top)   
		top.location.href = location.href;	
</script>
<style type="text/css">
body{
width: 100%;
            margin: 0px;
            padding: 0px;
            background: url(res/login/bg03.jpg);
            width: 100%;
            height: 100%;
	
}
#login-box {
	width:333px;
	height: 352px;
	padding: 80px 76px 0 73px;
	color: #ebebeb;
	font: 12px Arial, Helvetica, sans-serif;
	background: url(res/login/login-box-backg.png) no-repeat left top;
}
#login-box h2 {
	padding:0 10px 30px 10px;
	margin:0;
	color: #ebebeb;
	font: bold 38px utf-8;
	line-height:90px;
}

</style>
</head>
<body>
<div></div>
<div style="padding: 100px 0 0 0;" align="center">
<div id="login-box">
${message}
<H2>科研项目评审系统</H2>
	<s:form action ="loginAction" method="post" theme="xhtml" >
	<s:textfield name="username" label="用户名" />
	<s:password name="password" size="20"  label="密 码"/>
	<s:submit value="登录"/>
	</s:form>
</div>
</div>
</body>
</html>