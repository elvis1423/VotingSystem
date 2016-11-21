<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<style type="text/css">
    table{ border-collapse:collapse; width:100%; height:100%;}
	table td{ border-collapse:collapse; border:solid 1px #000;}
	table table td{ border-collapse:collapse; border:solid 1px #000; width:15%}
	table table td.two_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table td.two_only_left{border-collapse:collapse; border-top:none;border-right:none;border-bottom:none;}
	table table td.two_no_border{border-collapse:collapse; border:none;}
	table table table td.three_bottom_right{border-collapse:collapse; border-top:none;border-left:none;}
	table table table td.three_only_bottom{border-collapse:collapse; border-top:none;border-left:none;border-right:none;}
	table table table td.three_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table table td.three_no_border{border-collapse:collapse; border:none;}
</style>
</head>
<body>
	<div id="NoGradeVoteDiv">没有需要评选的项目，请联系管理员或刷新页面</div>
	
	<s:if test="earliestActiveGradeVote == null">
		<script type="text/javascript">
			$("#NoGradeVoteDiv").show();
			$("#GradeVoteTable").hide();
		</script>
	</s:if>
	<s:else>
		<script type="text/javascript">
			$("#NoGradeVoteDiv").hide();
			$("#GradeVoteTable").show();
		</script>
	</s:else>
	<table id="GradeVoteTable">
		<tr>
			<td>2014年先进课题组评选</td>
			<td>评选单位</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="two_only_right">一级指标</td>
						<td class="two_no_border">二级指标</td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td class="two_only_right">公司A</td>
						<td class="two_only_right">公司B</td>
						<!-- td class="two_only_right">公司C</td -->
						<td class="two_no_border">公司D</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="two_only_right">
							<table>
								<tr><td Class="three_no_border">师资力量
									<label>30</label>
									</td>
								</tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom">队伍结构
									<label>10</label></td></tr>
								<tr><td class="three_only_bottom">所获荣誉
									<label>10</label></td></tr>
								<tr><td class="three_no_border">学历水平
									<label>10</label></td></tr>
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
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<!-- td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_only_right"></td></tr>
							</table>
						</td -->
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_bottom"><input type="text" size='5'></td></tr>
								<tr><td class="three_no_border"><input type="text" size='5'></td></tr>
							</table>						
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="two_only_right">
							<table>
								<tr><td Class="three_no_border">师资力量
									<label>30</label>
									</td>
								</tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom">队伍结构
									<label>10</label></td></tr>
								<tr><td class="three_only_bottom">所获荣誉
									<label>10</label></td></tr>
								<tr><td class="three_no_border">学历水平
									<label>10</label></td></tr>
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
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_bottom_right"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<!-- td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_only_right"></td></tr>
							</table>
						</td -->
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_bottom"><input type="text" size='5'></td></tr>
								<tr><td class="three_only_bottom"><input type="text" size='5'></td></tr>
								<tr><td class="three_no_border"><input type="text" size='5'></td></tr>
							</table>						
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="two_no_border">
							<table>
								<tr><td Class="three_no_border">师资力量
									<label>30</label>
									</td>
								</tr>
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
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr><td class="three_only_right"><input type="text" size='5'></td></tr>
							</table>
						</td>
						<!-- td class="two_no_border">
							<table>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_bottom_right"></td></tr>
								<tr><td class="three_only_right"></td></tr>
							</table>
						</td -->
						<td class="two_no_border">
							<table>
								<tr><td class="three_no_border"><input type="text" size='5'></td></tr>
							</table>						
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>开始时间：2014-12-20 21：00</td>
			<td>截止时间：2014-12-22 12：00</td>
		</tr>
		<tr>
			<td>评分汇总</td>
			<td>
				<table>
					<tr>
						<td class="two_only_right">20</td>
						<td class="two_only_right">30</td>
						<!-- td class="two_only_right">公司C</td -->
						<td class="two_no_border">25</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>