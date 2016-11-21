<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>

<style type="text/css">

body {
	font-family: arial, 黑体;
	font-size: 20px;
	color: #FFE4E1
}

body {
background-color: #F0F8FD;
}

* {
	margin: 0px;
	padding: 0px;
}

#nav {
	width: 140px;
	line-height: 60px;
	list-style-type: none;
	text-align: left; /*定义整个ul菜单的行高和背景色*/
}
/*==================一级目录===================*/
#nav a {
	width: 140px;
	display: block;
	padding-left: 20px; /*Width(一定要)，否则下面的Li会变形*/
}

#nav li {
	border-bottom: #FFF 1px solid; /*下面的一条白边*/
	float: left;
	background-color: #c5dbf2;
	color: #DD1336;
	font-weight: bold;
}

#nav li a:hover {
	background: #2687eb; /*一级目录onMouseOver显示的背景色*/
}

#nav a:link {
	color: #2687eb;
	text-decoration: none;
}

#nav a:visited {
	color: #2687eb;
	text-decoration: none;
}

#nav a:hover {
	color: #FFF;
	text-decoration: none;
	font-weight: bold;
}
/*==================二级目录===================*/
#nav li ul {
	list-style: none;
	text-align: left;
}

#nav li ul li {
	background: #FFF; /*二级目录的背景色*/
	font-weight: normal;
}

#nav li ul a {
	padding-left: 50px;
	line-height: 30px;
	width: 140px;
	/* padding-left二级目录中文字向右移动，但Width必须重新设置=(总宽度-padding-left)*/
}
/*下面是二级目录的链接样式*/
#nav li ul a:link {
	color: #666;
	text-decoration: none;
}

#nav li ul a:visited {
	color: #666;
	text-decoration: none;
}

#nav li ul a:hover {
	color: #FFF;
	text-decoration: none;
	font-weight: normal;
	background: #FFAA1C; /* 二级onmouseover的字体颜色、背景色*/
}
/*==============================*/
#nav li:hover ul {
	left: auto;
}

#nav li.sfhover ul {
	left: auto;
}

#content {
	clear: left;
}

#nav ul.collapsed {
	display: none;
}

#PARENT {
	width: 174px;
}
</style>
</head>
<body>
	<div id="PARENT">
		<ul id="nav">
			<li><a href="#Menu=ChildMenu1" onClick="DoMenu('ChildMenu1')">  用 户 管 理</a>
				<ul id="ChildMenu1" class="collapsed">
					<li><a href="CreateUser.jsp" target="main">用户创建</a></li>
					<!--<li><a href=##Menu=ChildMenu1>密码修改</a></li>
					<li><a href=##Menu=ChildMenu1>用户删除</a></li> -->
				</ul></li>
			<li><a href="#Menu=ChildMenu2" onClick="DoMenu('ChildMenu2')">  投 票 管 理</a>
				<ul id="ChildMenu2" class="collapsed">
					<li><a href="electVote/electVoteAdd.jsp" target="main">投票创建</a></li>
					<li><a href="electVoteList.action" target="main">投票列表</a></li>
					<li><a href="viewelectvoteresults.action" target="main">结果查询</a></li>
				</ul></li>
			<li><a href="#Menu=ChildMenu3" onClick="DoMenu('ChildMenu3')">  评 分 管 理</a>
				<ul id="ChildMenu3" class="collapsed">
					<li><a href="gradevoteconfig.jsp" target="main">评分创建</a></li>
					<li><a href="viewgradevoteconfig.action" target="main">评分列表</a></li>
					<li><a href="viewgradevoteresult.action" target="main">结果查询</a></li>
				</ul></li>
			<!-- <li><a href="#Menu=ChildMenu4" onClick="DoMenu('ChildMenu4')">  流 程 控 制</a>
				<ul id="ChildMenu4" class="collapsed">
					<li><a href=##Menu=ChildMenu4>流程发起</a></li>
					<li><a href=##Menu=ChildMenu4>过程监控</a></li>
					<li><a href=##Menu=ChildMenu4>结         束</a></li>
				</ul>
			</li> -->
		</ul>
	</div>
	<script type=text/javascript>
	<!--
		var LastLeftID = "";
		function menuFix() {
			var obj = document.getElementById("nav").getElementsByTagName("li");
			for (var i = 0; i < obj.length; i++) {
				obj[i].onmouseover = function() {
					this.className += (this.className.length > 0 ? " " : "")
							+ "sfhover";
				}
				obj[i].onMouseDown = function() {
					this.className += (this.className.length > 0 ? " " : "")
							+ "sfhover";
				}
				obj[i].onMouseUp = function() {
					this.className += (this.className.length > 0 ? " " : "")
							+ "sfhover";
				}
				obj[i].onmouseout = function() {
					this.className = this.className.replace(new RegExp(
							"( ?|^)sfhover\\b"), "");
				}
			}
		}
		function DoMenu(emid) {
			var obj = document.getElementById(emid);
			obj.className = (obj.className.toLowerCase() == "expanded" ? "collapsed"
					: "expanded");
			if ((LastLeftID != "") && (emid != LastLeftID)) //关闭上一个Menu
			{
				document.getElementById(LastLeftID).className = "collapsed";
			}
			LastLeftID = emid;
		}
		function GetMenuID() {
			var MenuID = "";
			var _paramStr = new String(window.location.href);
			var _sharpPos = _paramStr.indexOf("#");
			if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1) {
				_paramStr = _paramStr
						.substring(_sharpPos + 1, _paramStr.length);
			} else {
				_paramStr = "";
			}
			if (_paramStr.length > 0) {
				var _paramArr = _paramStr.split("&");
				if (_paramArr.length > 0) {
					var _paramKeyVal = _paramArr[0].split("=");
					if (_paramKeyVal.length > 0) {
						MenuID = _paramKeyVal[1];
					}
				}
				/*
				 if (_paramArr.length>0)
				 {
				 var _arr = new Array(_paramArr.length);
				 }
				 //取所有#后面的，菜单只需用到Menu
				 //for (var i = 0; i < _paramArr.length; i++)
				 {
				 var _paramKeyVal = _paramArr[i].split('=');
				 if (_paramKeyVal.length>0)
				 {
				 _arr[_paramKeyVal[0]] = _paramKeyVal[1];
				 }
				 }
				 */
			}
			if (MenuID != "") {
				DoMenu(MenuID)
			}
		}
		GetMenuID(); //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
		menuFix();
		-->
	</script>
</body>
</html>