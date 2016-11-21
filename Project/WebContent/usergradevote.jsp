<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/usergradevote.js" type="text/javascript"></script>
<style type="text/css">
    table{ border-collapse:collapse; width:100%; height:100%;}
	table td{ border-collapse:collapse; border:solid 1px #000;}
	table table td{ border-collapse:collapse; border:none;}
	body {
	font-family: arial, 黑体;
	font-size: 15px;
	background-color: #F0F8FD;
	width: 950px;
	margin: 0 auto;
	padding: 30px 15px;	
	}
#templatemo_content {
	width: 950px;
	margin: 0 auto;
	padding: 30px 15px;
	background: #ffffff;
}
h2 {
	font-family: arial, 宋体;
	margin: 0 0 20px 0;
	padding: 10px 0;
	font-size: 30px;
	font-weight: bold;
	color: #686b53;
}
cleaner{ clear: both; width:100%; height: 30px; }
</style>
<link rel="stylesheet" href="res/preuservote_css/zzsc.css" type="text/css" />
</head>
<body>
<div id="templatemo_content">
	<div id="NoGradeVoteDiv">没有需要评选的项目，请联系管理员</div>
	<h2><s:property value="earliestActiveGradeVote.voteName"/></h2>
	------------------------------------------------------------------------------------------- 
	<br>
	<br>
	<table id="GradeVoteTable">
		<tr>
			<s:if test="isGradeVoteHasSecondIndex">
				<td>一级指标(权重)</td>
				<td>二级指标(权重)</td>
			</s:if>
			<s:else>
				<td>一级指标(权重)</td>
			</s:else>
			<s:iterator value="earliestActiveGradeVote.candidates" status="stat">
				<td><s:property value="name" /></td>
			</s:iterator>
		</tr>
		<s:iterator value="earliestActiveGradeVote.gradeVoteIndexes" var="firstIndex" status="firstIndexStat">
		<tr>
			<s:if test="secondIndexes == null || secondIndexes.size == 0">
				<td><s:property value="name"/>(<s:property value="weightInInteger"/>%)</td>
			</s:if>
			<s:else>
				<td><s:property value="name"/>(<s:property value="weightInInteger"/>%)</td>
				<td>
					<table>
						<s:iterator value="secondIndexes" status="secondIndexStat">
						<tr>
							<td><s:property value="name"/>(<s:property value="weightInInteger"/>%)</td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</s:else>
						
			<s:iterator value="earliestActiveGradeVote.candidates" var="candidate" status="candidatesStat">
			<s:if test="secondIndexes == null || secondIndexes.size == 0">
				<td>
					<input id="AtomicGradeVoteInput" type="hidden" value="${loginStatus.userId}_${earliestActiveGradeVote.id}_${candidate.id}_${firstIndex.id}">
					<input type="text" size='5'  onblur="inputOnBlur(this)">
				</td>
			</s:if>
			<s:else>
				<td>
					<table>
						<s:iterator value="secondIndexes" status="secondIndexStat">
						<tr>
							<td>
								<input id="AtomicGradeVoteInput" type="hidden" value="${loginStatus.userId}_${earliestActiveGradeVote.id}_${candidate.id}_${firstIndex.id}_${id}">
								<input type="text" size='5' onblur="inputOnBlur(this)">
							</td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</s:else>
			</s:iterator>
		</tr>				
		</s:iterator>
	</table>
	<br>
	<s:if test="earliestActiveGradeVote.isAnonymous == 1">
		<div>本次评分为匿名评分</div>
	</s:if>
	<s:else>
		<div>实名评分，请输入姓名：<input id="Voter" type="text" size='10'></div>
	</s:else>
	-------------------------------------------------------------------------------------------
	<br>
	<br>
	<div align="center" id="GradeVoteSaveDiv" >
	<s:form name="savegradevoteform" action="savegradevote" method="post">
	    <button  style='font-size:15px' onclick="saveGradeVote()" class="button" ><span>&#10003;</span>确认评分</button>
	    <s:hidden name="atomicGradeVotesJsonStr"></s:hidden>
	</s:form>
	</div>
</div>
	<s:if test="earliestActiveGradeVote == null">
		<script type="text/javascript">
			$("#NoGradeVoteDiv").show();
			$("#GradeVoteTable").hide();
			$("#GradeVoteSaveDiv").hide();
		</script>
	</s:if>
	<s:else>
		<script type="text/javascript">
			$("#NoGradeVoteDiv").hide();
			$("#GradeVoteTable").show();
			$("#GradeVoteSaveDiv").show();
		</script>
	</s:else>
</body>
</html>