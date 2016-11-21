function opendivshow(width, href, ShowDetailButton) {
	var url = $(ShowDetailButton).val();
	var args = {
		"time" : new Date()
	};
	$.post(url, args, function(data) {
		htmlContent = convertDetailResultToHtml(data);
		if (typeof(htmlContent) === 'undefined') {
			return;
		}
		document.getElementById('lightshow').style.display = 'block';
		document.getElementById('lightshow').style.width = width + "px";
		document.getElementById('lightshow').style.marginLeft = -(width / 2) + "px";
		document.getElementById('messagebox').style.width = width + "px";
		document.getElementById("messagebox").innerHTML = htmlContent;
		document.getElementById('fadeshow').style.display = 'block';
		document.getElementById("goback").setAttribute("href", href);
	});
}

function convertDetailResultToHtml(gradeVoteResultsJson) {
	var gradeVoteResultsView = jQuery.parseJSON(gradeVoteResultsJson);
	if (gradeVoteResultsView.gradeVoteFinalResults.length == 0) {
		alert("暂无评分");
		return;
	}
	var html = "<div style='height:10%'><table><tr><td style='text-align:center;font-weight:bold'>" 
							+ gradeVoteResultsView.gradeVoteName +
					 "</td></tr></table></div>";
	
	html += "<div style='height:70%'><table><tr><td>评委</td>";
	for (var i = 0; i < gradeVoteResultsView.gradeVoteFinalResults.length; i++) {
		var gradeVoteFinalResultView = gradeVoteResultsView.gradeVoteFinalResults[i];
		if (i == gradeVoteResultsView.gradeVoteFinalResults.length - 1) {
			html += "<td>" + gradeVoteFinalResultView.candidateName + "</td>"
		} else {
			html += "<td>" + gradeVoteFinalResultView.candidateName + "</td>"
		}
	}
	html += "</tr>";
	
	var voterAndResultMap = gradeVoteResultsView.voterAndResultMap;
	for (var voter in voterAndResultMap) {
		html += "<tr><td>" + voter + "</td>";
		html += "";
		var candidates = voterAndResultMap[voter].candidates;
		for (var i = 0; i < candidates.length; i++) {
			if (i == candidates.length - 1) {
				html += "<td>" + candidates[i].result + "</td>";
			} else {
				html += "<td>" + candidates[i].result + "</td>";
			}
		}
		html += "</tr>";
	}
	
	html += "<tr><td>合计</td>";
	for (var i = 0; i < gradeVoteResultsView.gradeVoteFinalResults.length; i++) {
		var gradeVoteFinalResultView = gradeVoteResultsView.gradeVoteFinalResults[i];
		if (i == gradeVoteResultsView.gradeVoteFinalResults.length - 1) {
			html += "<td>" + gradeVoteFinalResultView.grade + "</td>"
		} else {
			html += "<td>" + gradeVoteFinalResultView.grade + "</td>"
		}
	}
	html += "</tr></table>";
	html += "<div style='height:20%'><table><tr>" +
				"<td align='center' style='border:0;'><a href='' id='goback' class='li_link'><li class='div_li' onClick='closedivshow()'>确 定</li></a>" +
				"</td>" +
			"</tr>";
	html += "</table></div>";
	
	return html;
}

function closedivshow() {
	document.getElementById("lightshow").style.display = 'none';
	document.getElementById("fadeshow").style.display = 'none';
}
