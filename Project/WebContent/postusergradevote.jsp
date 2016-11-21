<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<style type="text/css">
    table{ border-collapse:collapse; width:100%; height:100%;}
	table td{ border-collapse:collapse; border:solid 1px #000; width:50%}
	table table td{ border-collapse:collapse; border:solid 1px #000; width:15%}
	table table td.two_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table td.two_only_left{border-collapse:collapse; border-top:none;border-right:none;border-bottom:none;}
	table table td.two_no_border{border-collapse:collapse; border:none;}
	table table table td.three_bottom_right{border-collapse:collapse; border-top:none;border-left:none;}
	table table table td.three_only_bottom{border-collapse:collapse; border-top:none;border-left:none;border-right:none;}
	table table table td.three_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table table td.three_no_border{border-collapse:collapse; border:none;}
	table table table table td{ border-collapse:collapse; border:none;}
	body {
	font-family: arial, 黑体;
	font-size: 15px;
	background-color: #F0F8FD;
	
	width: 950px;
	margin: 0 auto;
	padding: 30px 15px;	
	}
H2{
font-family: arial, 宋体;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			alert("提交成功！");
		})
	</script>
	<table>
		<tr>
			<td colspan='2' style="text-align:center;font-weight:bold"><s:property value="userGradeVote.voteName"/></td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<s:if test="isGradeVoteHasSecondIndex">
						<td class="two_only_right">
							<table>
								<tr>
									<td class="three_no_border">一级指标</td>
									<td class="three_no_border">权重</td>
								</tr>
							</table>
						</td>
						<td class="two_no_border">
							<table>
								<tr>
									<td class="three_no_border">二级指标</td>
									<td class="three_no_border">权重</td>
								</tr>
							</table>
						</td>
						</s:if>
						<s:else>
							<td class="two_no_border">
							<table>
								<tr>
									<td class="three_no_border">一级指标</td>
									<td class="three_no_border">权重</td>
								</tr>
							</table>
						</td>
						</s:else>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
					<s:iterator value="userGradeVote.candidates" status="stat">
						<s:if test="#stat.last">
							<td class="two_no_border"><s:property value="candidateName"/></td>
						</s:if>
						<s:else>
							<td class="two_only_right"><s:property value="candidateName"/></td>
						</s:else>
					</s:iterator>
					</tr>
				</table>
			</td>
		</tr>
		<s:iterator value="userGradeVote.candidates" status="candidateStat">
			<s:if test="#candidateStat.first">
				<s:iterator value="firstIndexViews" var="firstIndex" status="firstIndexStat">
					<tr>
						<td>
							<table>
								<tr>
								<s:if test="secondIndexes == null || secondIndexes.size == 0">
									<td class="two_no_border">
										<table>
											<tr>
												<td Class="three_no_border">
													<table>
														<tr>
															<td><s:property value="name"/></td>
															<td><label><s:property value="weightInInteger"/></label></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</s:if>
								<s:else>
									<td class="two_only_right">
										<table>
											<tr>
												<td Class="three_no_border">
													<table>
														<tr>
															<td><s:property value="name"/></td>
															<td><label><s:property value="weightInInteger"/></label></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td class="two_no_border">
										<table>
											<s:iterator value="secondIndexes" status="secondIndexStat">
												<s:if test="#secondIndexStat.last">
													<tr>
														<td class="three_no_border">
															<table>
																<tr>
																	<td><s:property value="name"/></td>
																	<td><label><s:property value="weightInInteger"/></label></td>
																</tr>
															</table>
														</td>
													</tr>
												</s:if>
												<s:else>
													<tr>
														<td class="three_only_bottom">
															<table>
																<tr>
																	<td><s:property value="name"/></td>
																	<td><label><s:property value="weightInInteger"/></label></td>
																</tr>
															</table>
														</td>
													</tr>
												</s:else>
											</s:iterator>
										</table>
									</td>
								</s:else>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<s:iterator value="userGradeVote.candidates" var="candidate" status="candidatesStat">
										<s:iterator value="firstIndexViews" var="firstIndexView" status="firstIndexViewStat">
										<s:if test="#firstIndexStat.index == #firstIndexViewStat.index">
											<s:if test="#candidatesStat.last">
												<td class="two_no_border">
													<table>
													<s:if test="secondIndexes == null || secondIndexes.size == 0">
														<tr>
															<td class="three_no_border">
															<s:property value="#firstIndexView.grade"></s:property></td>
														</tr>
													</s:if>
													<s:else>
														<s:iterator value="secondIndexes" status="secondIndexStat">
															<s:if test="#secondIndexStat.last">
																<tr>
																	<td class="three_no_border">
																	<s:property value="grade"></s:property></td>
																</tr>
															</s:if>
															<s:else>
																<tr>
																	<td class="three_only_bottom">
																	<s:property value="grade"></s:property></td>
																</tr>
															</s:else>
														</s:iterator>
													</s:else>
													</table>
												</td>
											</s:if>
											<s:else>
												<td class="two_no_border">
													<table>
														<s:if test="secondIndexes == null || secondIndexes.size == 0">
															<tr>
																<td class="three_only_right">
																<s:property value="#firstIndexView.grade"></s:property></td>
															</tr>
														</s:if>
														<s:else>
															<s:iterator value="secondIndexes" status="secondIndexStat">
																<s:if test="#secondIndexStat.last">
																	<tr>
																		<td class="three_only_right">
																		<s:property value="grade"></s:property></td>
																	</tr>
																</s:if>
																<s:else>
																	<tr>
																		<td class="three_bottom_right">
																		<s:property value="grade"></s:property></td>
																	</tr>
																</s:else>
															</s:iterator>
														</s:else>
													</table>
												</td>
											</s:else>
											</s:if>
										</s:iterator>
									</s:iterator>
								</tr>
							</table>
						</td>
					</tr>
				</s:iterator>
			</s:if>
		</s:iterator>
		<tr>
			<td>评分结果</td>
			<td>
				<table>
					<tr>
						<s:iterator value="userGradeVote.candidates" status="stat">
						<s:if test="#stat.last">
							<td class="two_no_border"><s:property value="result"/></td>
						</s:if>
						<s:else>
							<td class="two_only_right"><s:property value="result"/></td>
						</s:else>
						</s:iterator>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<div align="center"  >
	<input type="button" value="打印" onclick="javascript:window.print();"/>
	</div>
</body>
</html>