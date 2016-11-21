<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
</head>
<frameset framespacing="0" rows="94,*">
	<frame name="banner" scrolling="no" noresize target="contents" src="banner.jsp">
	<frameset >
		<frame name="main" scrolling="auto" src="preuservote.jsp?hasElectVote=${hasElectVote }&hasGradeVote=${hasGradeVote}">
	</frameset>
	<noframes>
	<body></body>
	</noframes>
</frameset>
</html>