<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="css/web_form.css">
<link type="text/css" rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
<link type="text/css" rel="stylesheet" href="css/vs-gradevote-table.css">
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/jquery-ui.js" type="text/javascript"></script>
<script src="scripts/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="scripts/jquery-ui-datepicker-zh-CN.js" type="text/javascript"></script>
<script src="scripts/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
<script Charset="UTF-8" src="scripts/gradevoteconfig.js" type="text/javascript"></script>
</head>
<body >
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/voteconfig.png"></div>
<div  id="site_content">
	<div id="VoteConfigTable">
		<table border frame=void >
			<tr >
				<td style="border:0;" width="87px">评分名称:</td><td style="border:0;"><input id="VoteNameInput" type="text"></td>
			</tr>
		</table>
		<br>
		<table border frame=void>
			<tr>
				<td style="border:0;" width="8%">评分项目:</td><td style="border:0;" width="2%" ><input align ="right" type="image" style="width:15px;height:15px;" onclick="addCandidate();" title="添加被评选人" src="res/Button-Add.png"></td>
				<td style="border:0;">
					<table id="CandidateTable">
						<tr>
							<td class="two_no_border"><input type="text"><input type="image" style="width:15px;height:15px;" onclick="deleteCandidate(this)" title="删除被评选人" src="res/Button-Delete.png">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<table border frame=void>
			<tr>
				<td style="border:0;" width="8%">评分指标:</td><td style="border:0;" width="2%"><input align ="left" type="image" style="width:15px;height:15px;" onclick="addFirstIndex();" title="添加一级指标" src="res/Button-Add.png"></td>
				<td style="border:0;">
					<table border frame=void id="IndexTable">
						<tr id="IndexRow0">
							<td style="border:0;" width="25%"><input type="text"><input type="image" style="width:15px;height:15px;" onclick="deleteFirstIndex(this)" title="删除一级指标" src="res/Button-Delete.png">
							</td>
							<td style="border:0;" width="9%">二级指标:</td><td style="border:0;" width="2%"><input align ="left" type="image" style="width:15px;height:15px;" onclick="addSecondIndex(this);" title="添加二级指标" src="res/Button-Add.png"></td>
							<td style="border:0;">
								<table border frame=void id="IndexRow0_SecondIndexTable">
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<table border frame=void>
			<tr>
				<td style="border:0;" width="87px">开始时间:</td><td style="border:0;" width="203px"><input type="text" id="VoteStartDatePicker"></td>
				<td style="border:0;" width="87px">截止时间:</td><td style="border:0;"><input type="text" id="VoteEndDatePicker"></td>
			</tr>
		</table>
		<br>
	<input type="radio" name="AnonymousRadio" id="Anonymous" value="1" checked>匿名评分
	<input type="radio" name="AnonymousRadio" id="NonAnonymous" value="0">实名评分
	<br><br>
		<button onclick="previewVoteConfig()">下一步</button>
	</div>
	<div id="VoteNamePreviewDiv" align="center" style="display: none"></div>
	<table id="ConfigPreviewTable" style="display: none">
		<!-- 
		<tr>
			<td  colspan='2' style="text-align:center;font-weight:bold" id="VoteNamePreviewTd"></td>
		</tr>
		 -->
		<tr>
			<td>
				<table id="voteIndexPreviewHeadTable">
					<!-- tr>
						<td class="two_only_right">一级指标</td>
						<td class="two_no_border" >二级指标</td>
					</tr -->
				</table>
			</td>
			<td>
				<table id="VoteCandidatesPreviewTable">
					<!--tr>
						<td class="two_only_right">课题组A</td>
						<td class="two_only_right">课题组B</td>
						<td class="two_only_right">课题组C</td>
						<td class="two_no_border">课题组D</td>
					</tr -->
				</table>
			</td>
		</tr>
		<!--tr>
			<td>
				<table>
					<tr>
						<td class="two_only_right">师资力量</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom">队伍结构</td></tr>
								<tr><td class="three_only_bottom">所获荣誉</td></tr>
								<tr><td class="three_no_border">学历水平</td></tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right">9</td></tr>
								<tr><td class="three_bottom_right">6</td></tr>
								<tr><td class="three_only_right">8</td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right">9</td></tr>
								<tr><td class="three_bottom_right">6</td></tr>
								<tr><td class="three_only_right">8</td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right">9</td></tr>
								<tr><td class="three_bottom_right">6</td></tr>
								<tr><td class="three_only_right">8</td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom">9</td></tr>
								<tr><td class="three_only_bottom">6</td></tr>
								<tr><td class="three_no_border">8</td></tr>
							</table>						
						</td>
					</tr>
				</table>
			</td>
		</tr -->
	</table>
	<table id="PreviewActionTable" style="display:none">
		<tr>
			<td>
				<button onclick="returnVoteConfig()">返回</button>
			</td>
			<td>
			<s:form action="voteconfig" method="post">
				<button onclick="saveVoteConfig()">保存</button>
				<s:hidden name="voteConfigJsonStr"></s:hidden>
			</s:form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>