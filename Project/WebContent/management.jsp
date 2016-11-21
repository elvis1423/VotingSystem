<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
<style>
body {
	font-family: arial, 黑体;
	font-size: 15px;
	}
</style>
</head>
<frameset framespacing="0" rows="94,*">
	<frame name="banner" scrolling="no" noresize target="contents" src="banner.jsp">
	<frameset cols="160,*">
		<frame name="left" target="main" scrolling="auto" src="left.jsp">
		<frame name="main" scrolling="auto" src="main.jsp">
	</frameset>
	<noframes>
	<body></body>
	</noframes>
</frameset>
</html>