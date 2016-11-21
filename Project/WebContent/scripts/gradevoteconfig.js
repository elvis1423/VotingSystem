function addCandidate() {
	var newCandidateTR = "<tr><td class='two_no_border'><input type='text'><input type='image' style='width:15px;height:15px;' onclick='deleteCandidate(this)' title='删除被评选人' src='res/Button-Delete.png'>";
	$("#CandidateTable").append(newCandidateTR);
}
function deleteCandidate(deleteCandidateButton) {
	if ($("#CandidateTable tr").length > 1) {
		$(deleteCandidateButton).parent().parent().remove();
	} else {
		alert("至少一个竞选者");
	}
}
function checkNull(VoteConfig){
	if(VoteConfig.voteName=="") {
		return true;
	}
	for (var i = 0; i < VoteConfig.candidates.length; i++){
		var candidate=  VoteConfig.candidates[i];
		if(candidate=="") {
			return true;
		} 
	}
	for (var i = 0; i < VoteConfig.indexes.length; i++){
		var firstIndex =VoteConfig.indexes[i];
		if(firstIndex.name==""){ 
			return true;
		}
		for (var j = 0; j < firstIndex.secondIndexes.length; j++){
			var secondIndex =firstIndex.secondIndexes[j];
			if(secondIndex.name==""){ 
				return true;
			}
		}
	}
   	if(VoteConfig.startDate=="") {
		return true;
	}
	if(VoteConfig.endDate=="") {
		return true;
	}
}
function addFirstIndex() {
	var maxIndexRowNum = -1;
	for (var i = 0; i < $("tr[id^='IndexRow']").length; i++) {
		var indexRowIdStr = $("tr[id^='IndexRow']")[i].id;
		var indexRowIdNum = parseInt(indexRowIdStr.split('IndexRow')[1]);
		if (maxIndexRowNum < indexRowIdNum) {
			maxIndexRowNum = indexRowIdNum;
		}
	}
	
	maxIndexRowNum++;
	var newIndexTR = "<tr id='IndexRow"
			+ maxIndexRowNum
			+ "'>"
			+ "<td style='border:0;' width='25%'><input type='text'><input type='image' style='width:15px;height:15px;' onclick='deleteFirstIndex(this)' title='删除一级指标' src='res/Button-Delete.png'></td>"
			+ "<td style='border:0;' width='9%' >二级指标:</td><td style='border:0;' width='2%'><input align ='left' type='image' style='width:15px;height:15px;' onclick='addSecondIndex(this);' title='添加二级指标' src='res/Button-Add.png'></td>"
			+ "<td style='border:0;'><table border frame=void id='IndexRow" + maxIndexRowNum
			+ "_SecondIndexTable'></table></td>" + "</tr>"
	$("#IndexTable").append(newIndexTR);
}
function deleteFirstIndex(deleteFirstIndexButton) {
	if ($("tr[id^='IndexRow']").length > 1) {
		$(deleteFirstIndexButton).parent().parent().remove();
	} else {
		alert("至少一个指标");
	}
}
function addSecondIndex(addSecondeIndexButton) {
	var newSecondIndexTR = "<tr><td style='border:0;'><input type='text'><input type='image' style='width:15px;height:15px;' onclick='deleteSecondIndex(this)' title='删除二级指标' src='res/Button-Delete.png'></td></tr>";
	var SecondIndexTableId = $(addSecondeIndexButton).parent().parent().attr(
			'id')
			+ "_SecondIndexTable";
	$("#" + SecondIndexTableId).append(newSecondIndexTR);
}
function deleteSecondIndex(deleteSecondIndexButton) {
	$(deleteSecondIndexButton).parent().parent().remove();
}
function saveVoteConfig() {
//    var voteConfigJson = "{'voteName':'most welcoming teacher'," +
//    					  "'candidates':['Mr. Zhang','Ms. Wang','Mr.Na','Ms. Zhao']," +
//    					  "'indexes':[{'id':'1','secondIndexes':[" +
//    					  										 "{'id':'1','firstIndexId':'1','name':'bachelor','weight':'0.2'}," +
//    					  										 "{'id':'2','firstIndexId':'1','name':'master','weight':'0.4'}," +
//    					  										 "{'id':'3','firstIndexId':'1','name':'doctor','weight':'0.4'}" +
//    					  										"]," +
//    					  			  "'name':'Education Level'," +
//    					  			  "'weight':'1.0'}]" +
//    					  "}";
	var voteConfig = _getVoteConfig();
	supplementIndexWeightInfo(voteConfig);
    var voteConfigJson = JSON.stringify(voteConfig);
	$("[name='voteConfigJsonStr']").val(voteConfigJson);
}

function previewVoteConfig() {
	 
	var voteConfig = _getVoteConfig();
	if (checkNull(voteConfig)==true){
		alert("有选项为空，请返回检查！");
	}
	else{
		$("#VoteNamePreviewTd").text(voteConfig.voteName);//fill voteName in preview table
		$("#VoteNamePreviewDiv").html("<H2>" + voteConfig.voteName + "</H2>");
		var candidatesCount = fillCandidatesPreviewTable(voteConfig.candidates);//fill candidates in preview table
		
		fillIndexesHeadPreviewTable(voteConfig.indexes);//fill indexesHead in preview table
		
		fillIndexesPreviewTable(voteConfig.indexes, candidatesCount);
		fillTimeLinePreviewTable(voteConfig.startDate, voteConfig.endDate);
		
		$("#PreviewActionTable").show();
		$("#VoteNamePreviewDiv").show();
		$("#VoteConfigTable").hide();
		$("#ConfigPreviewTable").show();
	}
}

function fillIndexesPreviewTable(indexesFromConfig, candidatesCount) {
	$("tr[id^='indexTR']").each(function(){
		$(this).remove();
	});
	for (var i = 0; i < indexesFromConfig.length; i++) {
		//one index one tr
		var indexTR = "<tr id='indexTR" + i + "'><td><table><tr>";
		var gradeTD = "<td><table><tr>";
		var currentIndex = indexesFromConfig[i];
		if (currentIndex.secondIndexes.length == 0) {
			indexTR += "<td class='two_no_border'>" + currentIndex.name;
			indexTR += 	  "<input id='IndexInput" + i + "' value='权重' type='text' size='5' maxlength='5' onfocus='inputOnFocus(this);' onblur='inputOnBlur(this)'>";
			indexTR += "</td>"
			for (var k = 0; k < candidatesCount; k++) {
				if (k == candidatesCount - 1) {
					gradeTD += "<td class='two_no_border'><table><tr><td class='three_no_border'></td></tr></table></td>";
				} else {
					gradeTD += "<td class='two_no_border'><table><tr><td class='three_only_right'></td></tr></table></td>";
				}
			}
		} else {
			indexTR += "<td class='two_only_right'>" + currentIndex.name;
			indexTR +=    "<input id='IndexInput" + i + "' value='权重' type='text' size='5' maxlength='5' onfocus='inputOnFocus(this);' onblur='inputOnBlur(this)'>";
			indexTR += "</td>";
			
			indexTR += "<td class='two_no_border'><table>";
			var multipleGradTdByCandidatesCount = "";
			var innerGradTD = "<td class='two_no_border'><table>";
			
			for (var j = 0; j < currentIndex.secondIndexes.length; j++) {
				var currentSecondIndex = currentIndex.secondIndexes[j];
				if (j == currentIndex.secondIndexes.length -1) {
					indexTR += "<tr><td class='three_no_border'>" + currentSecondIndex.name;
					indexTR += "<input id='IndexInput" + i + "_" + j + "' type='text' style='display:none;' size='5' maxlength='5'>";
					indexTR += "</td></tr>";
					innerGradTD += "<tr><td class='three_only_right'></td></tr>";
				} else {
					indexTR += "<tr><td class='three_only_bottom'>" + currentSecondIndex.name;
					indexTR += "<input id='IndexInput" + i + "_" + j + "' type='text' style='display:none;' size='5' maxlength='5'>";
					indexTR += "</td></tr>";
					innerGradTD += "<tr><td class='three_bottom_right'></td></tr>";
				}
			}
			indexTR += "</table></td>";
			innerGradTD += "</table></td>";
			for (var k = 0; k < candidatesCount; k++) {
				if (k == candidatesCount - 1) {
					innerGradTD = innerGradTD.replace(/three_bottom_right/g, "three_only_bottom");
					innerGradTD = innerGradTD.replace(/three_only_right/g, "three_no_border");
//					innerGradTD.substring(0, innerGradTD.lastIndexOf("three_only_right")) + "three_no_border'>8</td></tr>";
				}
				multipleGradTdByCandidatesCount += innerGradTD;
			}
			gradeTD += multipleGradTdByCandidatesCount;
		}
		gradeTD += "</tr></table></td>";
		
		indexTR += "</tr></table></td>" + gradeTD + "</tr>";
		$("#ConfigPreviewTable").append(indexTR);
	}
}
function fillIndexesHeadPreviewTable(indexesFromConfig) {
	$("#voteIndexPreviewHeadTable tr").each(function(){
		$(this).remove();
	});
	var firstLevelIndexes = indexesFromConfig;
	var hasSecondLevelIndexes = false;
	for (var i = 0; i < firstLevelIndexes.length; i++) {
		var firstLevelIndex = firstLevelIndexes[i];
		if (firstLevelIndex.secondIndexes.length > 0) {
			hasSecondLevelIndexes = true;
			break;
		}
	}
	var indexHeadTr = "<tr>";
	if (hasSecondLevelIndexes) {
		indexHeadTr += "<td class='two_only_right'>一级指标</td><td class='two_no_border'>二级指标</td>";
	} else {
		indexHeadTr += "<td class='two_no_border'>一级指标</td>";
	}
	indexHeadTr += "</tr>";
	$("#voteIndexPreviewHeadTable").append(indexHeadTr);
}


function fillCandidatesPreviewTable(voteCandidates) {
	$("#VoteCandidatesPreviewTable tr").each(function(){
		$(this).remove();
	});
	var candidatesCount = voteCandidates.length;
	var candidatesTR = "<tr>";
	for (var i = 0; i < candidatesCount; i++) {
		if (i == candidatesCount -1) {
			candidatesTR += "<td class='two_no_border'>" + voteCandidates[i] + "</td>"
		} else {
			candidatesTR += "<td class='two_only_right'>" + voteCandidates[i] + "</td>"
		}
	}
	candidatesTR += "</tr>"
	$("#VoteCandidatesPreviewTable").append(candidatesTR);//fill voteCandidates in preview table
	return candidatesCount;
}

function fillTimeLinePreviewTable(startTime, endDate) {
	$("tr[id^='TimeLineRow']").each(function(){
		$(this).remove();
	});
	var timeLineTR = "<tr id='TimeLineRow'><td>开始时间：" + startTime + "</td><td>截止时间：" + endDate + "</td></tr>";
	$("#ConfigPreviewTable").append(timeLineTR);//fill voteCandidates in preview table
}

function previewValidation() {
 var voteName =	$("#VoteNameInput").val();
 if (voteName.value =="") {
	 alert("请输入候选项");
 }
}

function _getVoteConfig() {
//  var voteConfigJson = "{'voteName':'most welcoming teacher'," +
//	  "'candidates':['Mr. Zhang','Ms. Wang','Mr.Na','Ms. Zhao']," +
//	  "'indexes':[{'id':'1','secondIndexes':[" +
//	  										 "{'id':'1','firstIndexId':'1','name':'bachelor','weight':'0.2'}," +
//	  										 "{'id':'2','firstIndexId':'1','name':'master','weight':'0.4'}," +
//	  										 "{'id':'3','firstIndexId':'1','name':'doctor','weight':'0.4'}" +
//	  										"]," +
//	  			  "'name':'Education Level'," +
//	  			  "'weight':'1.0'}]," +
//	  "'startDate':'12/14/2014 15:00'," +
//	  "'endDate':'12/14/2014 18:00'"
//	  "}";
	var voteName = $("#VoteNameInput").val();
    var candidates = new Array();
    $("#CandidateTable tr").each(function(trIndex, trElement){
    	candidates.push($(trElement).find("input").val());
    });
    
    var indexes = new Array();
    $("#IndexTable tr[id^='IndexRow']").each(function(trIndex, trElement){
    	var index = {};
    	var secondIndexes = new Array();
    	$(trElement).find("input[type='text']").each(function(inputIndex, inputElement){
    		if (inputIndex == 0) {
    			index.name = $(inputElement).val();
    		} else {
    			var secondIndex = {};
    			secondIndex.name = $(inputElement).val();
    			secondIndexes.push(secondIndex);
    		}
    	})
    	index.secondIndexes = secondIndexes;
    	indexes.push(index);
    });
    var startDateStr = $("#VoteStartDatePicker").val();
    var endDateStr = $("#VoteEndDatePicker").val();
    var isAnonymous = $("[name=AnonymousRadio]:checked").val();
    var voteConfigObj = {};
    voteConfigObj.voteName = voteName;
    voteConfigObj.candidates = candidates;
    voteConfigObj.indexes = indexes;
    voteConfigObj.startDate = startDateStr;
    voteConfigObj.endDate = endDateStr;
    voteConfigObj.isAnonymous = isAnonymous;
    return voteConfigObj;
}

function returnVoteConfig() {
	$("#VoteNamePreviewDiv").hide();
	$("#PreviewActionTable").hide();
	$("#ConfigPreviewTable").hide();	
	$("#VoteConfigTable").show();
}

function inputOnFocus(FirstWeightInput) {
	if ($(FirstWeightInput).val() == "权重") {
		$(FirstWeightInput).val("");
	}
}

function inputOnBlur(FirstWeightInput) {
	if(isNaN($(FirstWeightInput).val()) || !isInteger($(FirstWeightInput).val())) {
		$(FirstWeightInput).val("权重");
		$("input[id^='" + FirstWeightInput.id + "_']").each(function(){
			$(this).hide();
		});
	} else {
		var firstLevelIndexWeightInputId = FirstWeightInput.id;
		var sequenceOfIndex = parseInt(firstLevelIndexWeightInputId.substring(("IndexInput").length));
		
		var secondLevelIndexInputPrefix = FirstWeightInput.id + "_";
		$("input[id^='" + secondLevelIndexInputPrefix + "']").each(function(){
			$(this).show();
		});
		
		var secondIndexCount = $("input[id^='" + FirstWeightInput.id + "_']").length;
		if (secondIndexCount > 0) {
			var firstIndexWeight = $(FirstWeightInput).val();
			var avg = firstIndexWeight / secondIndexCount;
			for (var i = 0; i < secondIndexCount; i++) {
				var secondIndexId = $("input[id^='" + FirstWeightInput.id + "_']").get(i).id;
				$("#" + secondIndexId).val(formatSecondIndexWeight(avg));
				$("#" + secondIndexId).show();
			}
		}
	}
}

function isInteger(inputValue) {
	if (isNaN(inputValue)) {
		return false;
	} else {
		inputValue.toString();
		return inputValue.indexOf('.') < 0 ? true : false;
	}
}

function formatSecondIndexWeight(x) {    
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.floor(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {
    	return s;
//        rs = s.length;    
//        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}

function supplementIndexWeightInfo(voteConfig) {
	var allIndexInputCount = $("input[id^='IndexInput']").length;
	var j = 0;
	for (var i = 0; i < allIndexInputCount; i++) {
		var currentIndexInputId = $("input[id^='IndexInput']")[i].id;
		if (currentIndexInputId.indexOf("_") >= 0) {
			//this index is a second level index, should skip this
			continue;
		} else {
			voteConfig.indexes[j].weight = $("#" + currentIndexInputId).val();
			var secondIndexCount = $("input[id^='" + currentIndexInputId + "_']").length;
			if (secondIndexCount > 0) {
				for (var k = 0; k < voteConfig.indexes[j].secondIndexes.length; k++) {
					var secondIndexId = $("input[id^='" + currentIndexInputId + "_']").get(k).id;
					voteConfig.indexes[j].secondIndexes[k].weight = $("#" + secondIndexId).val();
				}
			}
			j++;
		}
	}
}

$(document).ready(function() {
	$("#VoteStartDatePicker").datetimepicker({
		timeFormat: "HH:mm:ss",
        dateFormat: "yy-mm-dd"
	});
	$("#VoteEndDatePicker").datetimepicker({
		timeFormat: "HH:mm:ss",
        dateFormat: "yy-mm-dd"
	});
}); 