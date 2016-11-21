function saveGradeVote() {
	var atomicGradeVotes = new Array();
	$("input[id^='AtomicGradeVoteInput']").each(function(){
		var atomicGradeVote = {};
		var idArray = $(this).val().split("_");
		atomicGradeVote.userId = idArray[0];
		atomicGradeVote.voteConfigId = idArray[1];
		atomicGradeVote.candidateId = idArray[2];
		atomicGradeVote.firstIndexId = idArray[3];
		if (typeof(idArray[4]) != "undefined") {
			atomicGradeVote.secondIndexId = idArray[4];
		}
		atomicGradeVote.voter=$("#Voter").val();
		var grade = this.nextSibling.nextSibling.value;
		atomicGradeVote.grade = grade;
		atomicGradeVotes.push(atomicGradeVote);
	});
	for (var i = 0; i < atomicGradeVotes.length; i++) {
		var currentGradeVote =  atomicGradeVotes[i];
		if (currentGradeVote.grade == "undefined" || currentGradeVote.grade == "") {
			alert("评分不能为空");
			savegradevoteform.action="usergradevote.action";
			break;
		}
	}
	
	var atomicGradeVotesJson = JSON.stringify(atomicGradeVotes);
	$("[name='atomicGradeVotesJsonStr']").val(atomicGradeVotesJson);
}
//function saveGradeVote() {
//	var atomicGradeVotes = new Array();
//	$("input[id^='AtomicGradeVoteInput']").each(function(){
//		var atomicGradeVote = {};
//		var idArray = $(this).val().split("_");
//		atomicGradeVote.userId = idArray[0];
//		atomicGradeVote.voteConfigId = idArray[1];
//		atomicGradeVote.candidateId = idArray[2];
//		atomicGradeVote.firstIndexId = idArray[3];
//		if (typeof(idArray[4]) != "undefined") {
//			atomicGradeVote.secondIndexId = idArray[4];
//		}
//		var grade = this.nextSibling.nextSibling.value;
//		atomicGradeVote.grade = grade;
//		atomicGradeVotes.push(atomicGradeVote);
//	});
//	var atomicGradeVotesJson = JSON.stringify(atomicGradeVotes);
//	for(var i=0;i<atomicGradeVotes.length;i++){
//		var grade =atomicGradeVotes[i].grade;
//		if(grade==""){
//			alert("有值为空，请返回检查。");
//			
//		}else $("[name='atomicGradeVotesJsonStr']").val(atomicGradeVotesJson);
//	}
//}

function inputOnBlur(GradeInput) {
	if(isNaN($(GradeInput).val()) || isOutOfRange($(GradeInput).val())) {
		alert("请输入0到100之间的数");
		$(GradeInput).val("");
	}
}

function isOutOfRange(inputValue) {
	if (isNaN(inputValue)) {
		return true;
	} else {
		inputValue.toString();
		if (inputValue > 100 || inputValue < 0) {
			return true; 
		} else {
			return false;
		}
	}
}

