<#macro head >
<body>
<script src="/scripts/islogin.js"></script>
<div class="nav-head" id="head" ng-app="app" ng-controller="HeadController">
<ul>

<img src="/images/youmeilogo.png" style="
    height: 70%;
    margin-top: 8px;
    margin-right: 3%;
"  />
<a href='/web/youmei/index'><li id="index">首页</li></a>
<a  href="/web/youmei/onliecourse"><li id="course" >网课中心</li> </a> 
<li id="user" ng-click="bg()">个人中心</li>
<input type="hidden" value="${islogin}" id="islogin"/>

</ul>
<span style="position:absolute;right:10%; top:15px;color:white;" ng-show="register">
<a href="login" style="color:white;">登录</a>/<a href="registe" style="color:white;">注册</a></span>

<span style="position: absolute;right: 10%; top: 15px;" ng-show="!register">
<select >
<option>${username}</option>
<option ng-click="loginout()">退出</option></select></span>
</div>
<style>
*{ margin: 0px; padding: 0px;border:none; list-style: none;font-family:"/";outline:none;text-decoration: none;font-size:14px}
a{outline: none;color:black;outline: none;}
.nav-head{width:100%;height:50px;background:black;opacity:0.8;color:white;position:absolute;top:0;}
.nav-head ul{display:flex;justify-content: left;height: 50px;width: 60%;margin: 0 auto;min-width:600px;}
.nav-head ul li{height:16px;padding:0 40px;height:50px;line-height:50px;color:white;font-size:1.1rem;}
.nav-head ul  a{color:white;font-size:1.1rem;}

.nav-head select{color:white;font-size:1.2rem;background:black;appearance:none;-moz-appearance:none;-webkit-appearance:none;padding-right: 14px;border: solid 1px #000;}
.nav-head select::-ms-expand { display: none; }
</style>
<!--<script type="text/javascript">
 function bg(s){
 			var tabs=document.getElementById("head").getElementsByTagName("li");
            for(var x=0;x<tabs.length;x++){
		
            	tabs[x].style.background='black';
                if(x==s){
                	
                	tabs[x].style.background='#CB0101';
					
                }

            }


        }
 
</script>-->
</body>      
</#macro>