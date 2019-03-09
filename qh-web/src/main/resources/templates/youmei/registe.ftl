<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
	<@h.header title="网课中心"/>
<link rel="stylesheet" href="/styles/youmei.css" />
<script src="/scripts/youmei/registe.js"></script>
		<style type="text/css">
body{background:url(/images/register.jpg) no-repeat 100%;background-size:cover;background-attachment: fixed;}
.register{position:relative;}
.denglu{width:300px;height:auto;background:white;padding:80px;position:fixed;right:20%;top:18%;}
.greeting{font-size:2.5rem;color:#CE0002;line-height:3.5 rem;font-weight:bold;}
.denglu dl{margin-top:32px;width:100%;}
.denglu dl dt {color:#CB0101;font-size:1.3rem;height:30px;line-height:30px;border-bottom:1px solid #E5E5E5;width:100%;margin-bottom:32px;}
.denglu dl dd {width:100%;margin-top:15px;position:relative;}
.denglu dl dd img{position:absolute;left:8px;top:10px;}
.denglu dl dd input , .denglu dl dd select {color:#a19797;height:35px;border:1px solid #E5E5E5;width: 88%;padding-left: 12%;}
.denglu dl dd select{width:49%;padding-left:3%;appearance:none;-moz-appearance:none;-webkit-appearance:none;}
.denglu dl dd select::-ms-expand { display: none; }
.denglu dl dd input::-webkit-input-placeholder{color:#CACACA;}
.bt input{width:100%;background:#CC0001;margin-top:22px;color:white;line-height:40px;height:40px;text-align:center;font-size:1.4rem;border-radius:10px;}
</style>  
<@b.head ></@b.head> 
	<body ng-app="app" ng-controller = "registeController">
	<div class="register">

<div class="denglu">
<p class="greeting">你好！<br/>欢迎来优渼教育</p>
<dl>
<dt>注册</dt>
<from>
<dd><input type="text" ng-model="user.mobile" placeholder="输入手机号"/><img src="/images/phone.png"/>
<input type="button" ng-click="sms()" value="获取验证码" style="position: absolute;border-radius:5px;right:5px;top:8px;height: 20px;background: #FAD901;color: white;border: none;padding: 0 5px;width: auto;"/></dd>
<dd><input type="text" ng-model="user.verification" placeholder="输入验证码"/><img src="/images/yanzhengma.png"/></dd>
<dd><input type="password" ng-model="user.password" placeholder="密码"/><img src="/images/mima.png"/></dd>
<dd><input type="password" ng-model="password" placeholder="确认密码"/><img src="/images/mima.png"/></dd>
</dd>
</dl>
</from>
<div class="bt"><input type="button"  ng-click="submitForm()" value="创建账号"/></div>
</div>

</div>
<!--footer-->
<#include "/layout/footer.ftl"/>
	</body>

</html>