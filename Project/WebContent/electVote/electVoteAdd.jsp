<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="../css/web_form.css">
<link type="text/css" rel="stylesheet" href="../css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="../css/jquery-ui-timepicker-addon.css">
<script src="../scripts/jquery.js" type="text/javascript"></script>
<script src="../scripts/ajaxfileupload.js" type="text/javascript"></script> 
<script src="../scripts/jquery-ui.js" type="text/javascript"></script>
<script src="../scripts/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="../scripts/jquery-ui-datepicker-zh-CN.js" type="text/javascript"></script>
<script src="../scripts/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
<script src="../scripts/jquery.json-2.4.min.js" type="text/javascript"></script>
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="../res/button_pic/electvote.png"></div>
<div  id="site_content">
<div id="vote">
	<label>名称：</label><input id="vote_name" name="voteName" type="text" style="width: 150px;"/>
	<label>内容：</label><input id="vote_content" name="voteContent" type="text" style="width: 150px;"/>
	<br>
	<br> 
	<input type="radio" name="DifferentialRadio" id="NonDifferential" value="0" checked>等额投票
	<input type="radio" name="DifferentialRadio" id="Differential" value="1">差额投票
	<label id="may_choose_count_label" style="display: none;">最多可以投几票：</label><input id="may_choose_count" name="mayChooseCount" type="text" style="width: 150px; display: none;"/><br>
	
	<input type="radio" name="AnonymousRadio" id="Anonymous" value="1" checked>匿名投票
	<input type="radio" name="AnonymousRadio" id="NonAnonymous" value="0">实名投票<br>
	<br>
	<label>类型：</label><input name="decision" class="decisions" type="checkbox" id="agree" value="agree" cname="同意" checked onclick="uncheckDimension(1)"/><label for="agree">同意</label>&nbsp;&nbsp;&nbsp;
				<input name="decision" class = "decisions" type="checkbox" id="oppse" value="oppose" cname="反对" checked onclick="uncheckDimension(1)"/><label for="oppse">反对</label>&nbsp;&nbsp;&nbsp;
				<input name="decision" class = "decisions" type="checkbox" id="abstain" value="abstain" cname="弃权" checked onclick="uncheckDimension(1)"/><label for="abstain">弃权</label><br>
	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="decision1" class="decisions" type="checkbox" id="excellent" value="excellent" cname="优" onclick="uncheckDimension(0)"/><label for="excellent">优</label>&nbsp;&nbsp;&nbsp;
				<input name="decision1" class = "decisions" type="checkbox" id="good" value="good" cname="良" onclick="uncheckDimension(0)"/><label for="good">良</label>&nbsp;&nbsp;&nbsp;
				<input name="decision1" class = "decisions" type="checkbox" id="medium" value="medium" cname="中" onclick="uncheckDimension(0)"/><label for="medium">中</label>&nbsp;&nbsp;&nbsp;
				<input name="decision1" class = "decisions" type="checkbox" id="soso" value="soso" cname="可" onclick="uncheckDimension(0)"/><label for="soso">可</label>&nbsp;&nbsp;&nbsp;
				<input name="decision1" class = "decisions" type="checkbox" id="poor" value="poor" cname="差" onclick="uncheckDimension(0)"/><label for="poor">差</label>
</div>
<div style="height: 40px;"></div>
<div id = "options">
	<input type="button" value="添加投票选项" onclick="add_option()"/> 
	<div id="option_0" order="0" class="m_option">
		<label>投票选项</label>
		<label>名称：</label><input id="option_name_0" type="text" style="width: 150px;"/>
		<label>内容：</label><input id="option_content_0" type="text" style="width: 300px;"/>&nbsp;&nbsp;&nbsp;
		<input type="file" id="option_file_0" name="file" />
		<br>
		<input type="button" value="删除" onclick="del_option(0)">
	</div>
	<div id="option_1" order="1" class="m_option">
		<label>投票选项</label>
		<label>名称：</label><input id="option_name_1" type="text" style="width: 150px;"/>
		<label>内容：</label><input id="option_content_1" type="text" style="width: 300px;"/>&nbsp;&nbsp;&nbsp;
		<input type="file" id="option_file_1" name="file" />
		<br>
		<input type="button" value="删除" onclick="del_option(1)">
	</div>
</div>
<div style="height: 40px;"></div>
<div>
		<label>开始时间：</label><input type="text" id="VoteStartDatePicker"/>
		<label>截止时间：</label><input type="text" id="VoteEndDatePicker"/>
</div>


<div style="height: 40px;"></div>
<div id = "save_div">
	<form id="saveForm" name="saveForm" action="" method="post">
		<input type="button" value="保存" onclick="save_info()"/>
	</form>
</div>
</div>
</body>


<script type="text/javascript">
	$(document).ready(function(){
		$("[name='DifferentialRadio']").change(function() {
			 showCount();
		});
		$("#VoteStartDatePicker").datetimepicker({
			timeFormat: "HH:mm:ss",
	        dateFormat: "yy-mm-dd"
		});
		$("#VoteEndDatePicker").datetimepicker({
			timeFormat: "HH:mm:ss",
	        dateFormat: "yy-mm-dd"
		});
	});

	function uncheckDimension(number) {
		if (number == 0) {
			$("input[name='decision']").each(function(i,e){
				if($(e).is(':checked')){
					$(e).attr("checked", false);
				}
			});
		} else if (number ==1) {
			$("input[name='decision1']").each(function(i,e){
				if($(e).is(':checked')){
					$(e).attr("checked", false);
				}
			});
		}
	}
	
	function showCount(){
	 switch($("[name=DifferentialRadio]:checked").attr("id")){
	  case "NonDifferential":
		   $("#may_choose_count_label").hide();
		   $("#may_choose_count").hide();
	   break;
	  case "Differential":
		  $("#may_choose_count_label").show();
		  $("#may_choose_count").show();
	   break;
	  default:
	   break;
	 }
	}


	function add_option(){
		
		var infoLen = $(".m_option").length;
		var last = 0;
		$(".m_option").each(function(i,e){
			var order = $(e).attr('order');
			if((infoLen-1) == i){
				last = parseInt(order)+1;
			}
		});
		
		var optionDiv = '<div id="option_'+last+'" order="'+last+'" class="m_option">'
						+'<label>投票选项</label>&nbsp;'
						+'<label>名称：</label><input id="option_name_'+last+'" type="text" style="width: 150px;"/>&nbsp;'
						+'<label>内容：</label><input id="option_content_'+last+'" type="text" style="width: 300px;"/>&nbsp;&nbsp;&nbsp;&nbsp;'
						+ '<input type="file" id="option_file_' + last +'" name="file" />'
						+'<input type="button" value="删除" onclick="del_option('+last+')">'
						+'</div>';
						
		$('#options').append(optionDiv);
	}
	
	function del_option(order){
		$("#option_"+order).remove();
	}
	
	function fileUpload(voteId) {
		var filelist=$(":file");
		for(var i=0;i<filelist.length;i++) {
			var currentFile = filelist[i];
			if (currentFile.value.length > 0) {
				ajaxFileUpload(voteId,currentFile);
			}
			
		}
	}
	
	function ajaxFileUpload(voteId, fileComponent) {
        $.ajaxFileUpload
        (
            {
                url: 'uploadimage.action?electVoteId=' + voteId + "&order=" + fileComponent.id.substring(12), //用于文件上传的服务器端请求地址
                secureuri: false, //一般设置为false
                fileElementId: fileComponent.id, //文件上传空间的id属性  <input type="file" id="option_file_0" name="file" />
                dataType: 'HTML', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //alert(data);
                    //$("#img1").attr("src", data);
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                            alert(data.error);
                        } else {
                            alert(data.msg);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
        return false;
    }
	
	function save_info(){
		var vote = new Object();
		$(vote).attr('id','');
		$(vote).attr('name',$('#vote_name').val());
		$(vote).attr('content',$('#vote_content').val());
		if ($("[name=DifferentialRadio]:checked").attr("id") == "Differential") {
			$(vote).attr('isDifferential', 1);
			$(vote).attr('mayChooseCount',$('#may_choose_count').val());
		} else {
			$(vote).attr('isDifferential', 0);
			var all_option_count = $(".m_option").length;
			$(vote).attr('mayChooseCount',all_option_count);
		}
		$(vote).attr('isAnonymous',$("[name=AnonymousRadio]:checked").val());
		$(vote).attr('isPublish','0');
		
		var decisions = new Array();
		$(".decisions").each(function(i,e){
			if($(e).is(':checked')){
				var decision = new Object();
				$(decision).attr('cName',$(e).attr('cname'));
				$(decision).attr('eName',$(e).val());
				decisions.push(decision);
			}
		});
		$(vote).attr('decisions',decisions);
		
		var infoLen = $(".m_option").length;
		if(infoLen < 1){
			alert("请至少添加一个投票选项");
			return;
		}
		
		var options = new Array();
		$(".m_option").each(function(i,e){
			var order = $(e).attr('order');
			var name = $('#option_name_'+order).val();
			if ($.trim(name) != "") {
				var option = new Object();
				$(option).attr('name',name);
				$(option).attr('content',$('#option_content_'+order).val());
				$(option).attr('fileName',$('#option_file_'+order).val());
				$(option).attr('order',order);
				options.push(option);
			}
		});
		
		if (options.length==0) {
			alert("请选择至少填写一个选项");
	    	return;
		}
		$(vote).attr('options',options);
		
	    var startDateStr = $("#VoteStartDatePicker").val();
	    var completeDateStr = $("#VoteEndDatePicker").val();
	    if ($.trim(startDateStr) == "") {
	    	alert("请选择投票开始时间");
	    	return;
	    } 
	    if ($.trim(completeDateStr) == "") {
	    	alert("请选择投票截至时间");
	    	return;
	    }
	    $(vote).attr('startTimeStr',startDateStr);
	    $(vote).attr('completeTimeStr',completeDateStr);
	    
	    
		var voteJsonStr = $.toJSON(vote);
		
		
		$.post('electVoteAdd', {voteJson : voteJsonStr}, function(data) {
			data = eval('('+data+')');
	        if(data.result){
	        	var voteId = data.voteId;
	        	fileUpload(voteId);    	
	        	alert('添加投票成功 ！');
	        }else{
	        	alert('添加投票失败！');
	        }
		});
	}
</script>
</html>