<#import "/layout/header.ftl" as h/>
<!DOCTYPE html>
<html>
	<@h.header title="登录"/>
<link rel="stylesheet" href="/styles/youmei.css" />
<script src="/scripts/youmei/login.js"></script>
		<style type="text/css">
body{background:url(/images/register.jpg) no-repeat 100%;background-size:cover;background-attachment: fixed;}
.register{position:relative;}
.denglu{width:300px;height:380px;background:white;padding:80px;position:fixed;right:20%;top:30%;}
.greeting{font-size:2.8rem;color:#CE0002;line-height:3.8 rem;font-weight:bold;}
.denglu dl{margin-top:32px;width:100%;}
.denglu dl dt {color:#CB0101;font-size:1.3rem;height:30px;line-height:30px;border-bottom:1px solid #E5E5E5;width:100%;margin-bottom:32px;}
.denglu dl dd {width:100%;margin-top:22px;position:relative;}
.denglu dl dd img{position:absolute;left:5px;top:5px;}
.denglu dl dd input{color:;height:30px;border:1px solid #E5E5E5;width: 88%;padding-left: 12%;}
.denglu dl dd input::-webkit-input-placeholder{color:#CACACA;}
.bt input{width:100%;background:#CC0001;margin-top:22px;color:white;line-height:40px;height:40px;text-align:center;font-size:1.4rem;border-radius:10px;}
</style>  
	<body ng-app="app" ng-controller="loginController">
	<div class="register">
<div class="nav-head">
<ul>
<a href='index.html'><li style="background:#CB0101;">首页</li></a>
<a href='Onlie-course.html'><li>网课中心</li></a><span style="float:left;">|</span>
<a href='#'><li>关于我们</li></a>
<span style="position: absolute;right: 10%; top: 15px;color:white;"><a href="registe" style="color:white;">注册</a></span>
</ul>
</div>
<div class="denglu">
<p class="greeting">你好<br/>欢迎来优渼教育</p>
<dl>
<dt>登录</dt>

<dd><input type="text" ng-model="mobile" placeholder="用户名"/><img src="/images/username.png"/></dd>
<dd><input type="text" ng-model="password" placeholder="密码"/><img src="/images/password.png"/></dd>
<dd><span style="float:right;color:#FFA47B">忘记密码？</span></dd>
</dl>

<div class="bt"><input type="button" ng-click="submitForm()" value="登陆"/></div>
</div>

</div>
	</body>

</html>
