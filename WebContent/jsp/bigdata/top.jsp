﻿<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>e巡通大数据统计分析系统</title>
<!-- Bootstrap Styles-->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
	rel="stylesheet" />
<!-- Custom Styles-->
<link href="${pageContext.request.contextPath}/css/custom-styles.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/mystyle.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/vmcss.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/jquery.json-2.2.min.js"></script>
<style>
.active {
	color: rgba(124, 189, 187, 1) !important;
	background-color: rgb(234, 234, 234) !important;
	/* color: #333 !important */
}

.active a {
	color: black !important;
}

.active a h4 {
	color: black !important;
}

.menuUl {
	width: 87% !important;
	float: left !important;
	list-style: none !important;
	text-align: center !important;
	margin-bottom: 0px !important;
	padding-left: 0px !important;
}

.menuUl li {
	width: 20% !important;
	float: left !important;
	text-align: center !important;
}

.tr-active {
	background-color: "#EAEAEA" !important;
	color: rgba(124, 189, 187, 1) !important;
}

.titleImgRight {
	width: 18%;
	margin-right: 8%;
}

.titleImgLeft {
	width: 11%;
	margin-right: 4%;
}
</style>

</head>

<body>
	<nav class="navbar navbar-default top-navbar" role="navigation"
		style="padding-left: 0px;">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">
			<img style="height:25px;display:inline-block;margin-top:-2px;margin-right: 5px;" src="${pageContext.request.contextPath}/images/gylogo.png"><strong>公元e巡通</strong></a>
		</div>
		<div class="menu" style="display:flex">
			<ul id="menuUl" class="menuUl">
				<li><a class="topspan" href="#/equipState"><h4
							class="fa fa-heart">设备健康状况分析</h4></a></li>
				<li class="active"><a class="topspan" href="#/equipFail"><h4
							class="fa fa-gear">设备故障分析</h4></a></li>
				<li><a class="topspan" href="#/equipPre"><h4
							class="fa fa-eye">设备故障预测</h4></a></li>
			</ul>
			<!-- <a class="topspan" href="#/equipBaseInfo"><h4
					class="fa fa-dashboard">能耗分析</h4></a>  -->

		</div>
	</nav>
	<section class="containner">
		<div class="overlayer"></div>

		<script>
			$(document).ready(function() {
				$("#sideNav").click(function() {
					if ($(this).hasClass('closed')) {
						$('.navbar-side').animate({
							left : '0px'
						});
						$(this).removeClass('closed');
						$('#page-wrapper').animate({
							'margin-left' : '260px'
						});

					} else {
						$(this).addClass('closed');
						$('.navbar-side').animate({
							left : '-260px'
						});
						$('#page-wrapper').animate({
							'margin-left' : '0px'
						});
					}
				});
			});

			$(window).bind("load resize", function() {
				if ($(this).width() < 768) {
					$('div.sidebar-collapse').addClass('collapse')
				} else {
					$('div.sidebar-collapse').removeClass('collapse')
				}
			});

			$("#menuUl li").click(function() {
				$(this).addClass("active").siblings().removeClass();
			})
		</script>