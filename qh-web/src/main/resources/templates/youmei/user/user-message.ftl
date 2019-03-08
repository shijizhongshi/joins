<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
	</head>
<link rel="stylesheet" href="/styles/user.css" />
<script type="text/javascript" src="/scripts/youmei/jquery-1.8.3.min.js"></script>
<script src="/assets/js/angular/angular.js"></script>
<script src="/assets/js/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="/assets/js/angular-bootstrap/angular-locale_zh.js"></script>
<script src="/scripts/layout/app.js"></script>
<script type="text/javascript" src="/scripts/user.js"></script>
<body ng-app="app" ng-controller="userController">


<!--右侧信息栏-->
<div class="message-top" >
<input type="hidden" value="${username}" id="username"/>
<b><img src="/images/touxiang.png"/></b>
<p>{{user.nickname}}&nbsp;您好，欢迎来到中师传承<br/>
<span>您的累计学习时长:0</span></p>
</div>
<div class="message-bottom">
<ul><li>学员昵称：</li><li>{{user.nickname}}</li></ul>
<ul><li>学员姓名：</li><li>{{user.realname}}</li></ul>
<ul><li>手机号码：</li><li>{{user.mobile}}</li></ul>
<ul><li>QQ号码&nbsp;：</li><li></li></ul>
<ul><li>所在地区：</li>
<li><select id="province" name="province" class="std_se_01" onchange="cityName(this.selectedIndex);">"
</select>
	<select id="city" name="city" class="std_se_01" onchange="citychange(this.value);"></select></li></ul>
<ul><li>详细地址：</li><li>{{user.address}}</li></ul>
<ul><li>电子邮箱：</li><li></li></ul>
<ul><li>注册时间：</li><li>{{user.addtime | date:'yyyy-MM-dd HH:mm:ss'}}</li></ul>
</div>



</body>
<script type="text/javascript" src="/scripts/youmei/layout.js"></script>
<script type="text/javascript" src="/scripts/youmei/diqu.js"></script>
<script type="text/javascript" src="/scripts/youmei/LocalStore.js"></script>
</html>