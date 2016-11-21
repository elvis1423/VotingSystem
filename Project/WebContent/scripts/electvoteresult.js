function opendivshow(width, href, ShowDetailButton) {
	var url = $(ShowDetailButton).val();
	var args = {
		"time" : new Date()
	};
	$.post(url, args, function(data) {
		htmlContent = convertDetailResultToHtml(data);
		if (typeof(htmlContent) === 'undefined') {
			alert("暂无投票");
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

function convertDetailResultToHtml(electVoteResultsJson) {
	var electVoteResultsView = jQuery.parseJSON(electVoteResultsJson);
	var isElectVoteEmpty = true;
	for (key in electVoteResultsView.voterAndResultsMap) {
		isElectVoteEmpty = false;
		break;
	}
	if (isElectVoteEmpty) {
		return;
	}
	
	var html = "<div style='height:10%'><table><tr><td style='text-align:center;font-weight:bold'>" 
							+ electVoteResultsView.electVoteName +
					 "</td></tr></table></div>";
	html += "<div style='height:70%'><table><tr><td></td>";
	for (var i = 0; i < electVoteResultsView.allOptions.length; i++) {
		var option = electVoteResultsView.allOptions[i];
		if (i == electVoteResultsView.allOptions.length - 1) {
			html += "<td>" + option + "</td>"
		} else {
			html += "<td>" + option + "</td>"
		}
	}
	html += "</tr>";
	
	var decisionAndOptionCountMap = electVoteResultsView.decisionAndOptionCountMap;
	for (var i = 0;  i < electVoteResultsView.allDecisions.length; i++) {
		var decision = electVoteResultsView.allDecisions[i];
		html += "<tr><td>" + decision + "</td>";
		html += "";
		for (var j = 0;  j < electVoteResultsView.allOptions.length; j++) {
			var option = electVoteResultsView.allOptions[j];
			if (j == electVoteResultsView.allOptions.length - 1) {
				if (typeof(decisionAndOptionCountMap[decision]) === 'undefined') {
					html += "<td>0</td>";
				} else {
					if (typeof(decisionAndOptionCountMap[decision][option]) === 'undefined') {
						html += "<td>0</td>";
					} else {
						html += "<td>" + decisionAndOptionCountMap[decision][option] + "</td>";
					}
				}
			} else {
				if (typeof(decisionAndOptionCountMap[decision]) === 'undefined') {
					html += "<td>0</td>";
				} else {
					if (typeof(decisionAndOptionCountMap[decision][option]) === 'undefined') {
						html += "<td>0</td>";
					} else {
						html += "<td>" + decisionAndOptionCountMap[decision][option] + "</td>";
					}
				}
			}
		}
		html += "</tr>";
	}
//	html += "<tr><td>结果</td><td><table><tr>";
//	for (var i = 0;  i < electVoteResultsView.allOptions.length; i++) {
//		var option = electVoteResultsView.allOptions[i];
//		if (i == electVoteResultsView.allOptions.length - 1) {
//			html += "<td class='two_no_border_elect'>" + electVoteResultsView.optionAndFinalResultMap[option] + "</td>";
//		} else {
//			html += "<td class='two_only_right_elect'>" + electVoteResultsView.optionAndFinalResultMap[option] + "</td>";
//		}
//	}
//	
//	html += "</tr></table></td></tr>";
	html += "</table></div>";
	html += "<div style='height:20%'><table><tr>" +
	"<td style='border:0;'><a href='' id='goback' class='li_link'><li class='div_li' onClick='closedivshow()'>关闭</li></a>" +
	"</td>" +
	"<td style='border:0;'><a href='downloadElectVoteDetailFile.action?electVoteId=" + electVoteResultsView.electVoteId + "'" + "id='goback' class='li_link'><li class='div_li'>下载</li></a>" +
	"</td>"
	"</tr></table></div>";
	return html;
}

function closedivshow() {
	document.getElementById("lightshow").style.display = 'none';
	document.getElementById("fadeshow").style.display = 'none';
}
