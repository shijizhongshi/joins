<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆</title>
	</head>
<link rel="stylesheet" href="/styles/user.css" />
<script type="text/javascript" src="/scripts/youmei/jquery-1.8.3.min.js"></script>

<body>


<!--右侧信息栏-->
<div class="message-top">
<b><img src="/images/touxiang.png"/></b>
<p>{某某}&nbsp;您好，欢迎来到中师传承<br/>
<span>您的累计学习时长:{}</span></p>
</div>
<div class="message-bottom">
<ul><li>学员昵称：</li><li>{昵称}</li></ul>
<ul><li>学员姓名：</li><li>{姓名}</li></ul>
<ul><li>手机号码：</li><li>{15258544555}</li></ul>
<ul><li>QQ号码&nbsp;：</li><li><input type="text"></li></ul>
<ul><li>所在地区：</li>
<li><select id="province" name="province" class="std_se_01" onchange="cityName(this.selectedIndex);"></select>
	<select id="city" name="city" class="std_se_01" onchange="citychange(this.value);"></select></li></ul>
<ul><li>详细地址：</li><li><input type="text"></li></ul>
<ul><li>电子邮箱：</li><li><input type="text"></li></ul>
<ul><li>注册时间：</li><li>{2019-10-16}</li></ul>
</div>



</body>
<script type="text/javascript" src="/scripts/youmei/layout.js"></script>
<script type="text/javascript" src="/scripts/youmei/diqu.js"></script>
<script type="text/javascript" src="/scripts/youmei/LocalStore.js"></script>
</html>