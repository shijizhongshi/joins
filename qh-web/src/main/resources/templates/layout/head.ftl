
<div class="nav-head" id="head">
<ul>
<a href='index'><li style="background:#CB0101;" onclick="bg(0)">首页</li></a>
<a  href="Onlie-course"><li onclick="bg(1)">网课中心</li> </a> 
<a  href="user"><li onclick="bg(2)" >个人中心</li></a>

</ul>
<span style="position: absolute;right: 10%; top: 15px;color:white;">
<a href="login" style="color:white;">登录</a>/<a href="registe" style="color:white;">注册</a></span>

<span style="display:none;position: absolute;right: 10%; top: 15px;">
<select >
<option disabled selected hidden>{名字}</option>
<option  >退出</option></select></span>
</div>
<style>
.nav{width:100%;position:relative;}
.nav img{width:100%;}
.nav-head{width:100%;height:50px;background:black;opacity:0.8;color:white;position:absolute;top:0;}
.nav-head ul{display:flex;justify-content: left;height: 50px;align-items: baseline;width: 60%;margin: 0 auto;padding-left: 18%;}
.nav-head ul li{height:16px;padding:0 40px;height:50px;line-height:50px;color:white;font-size:1.1rem;}
.nav-head ul  a{color:white;font-size:1.1rem;}

.nav-head select{color:white;font-size:1.2rem;background:black;appearance:none;-moz-appearance:none;-webkit-appearance:none;padding-right: 14px;border: solid 1px #000;}
.nav-head select::-ms-expand { display: none; }
</style>
<script type="text/javascript">
 function bg(s){
 			var tabs=document.getElementById("head").getElementsByTagName("li");
            for(var x=0;x<tabs.length;x++){
		
            	tabs[x].style.background='black';
                if(x==s){
                	
                	tabs[x].style.background='#CB0101';
					
                }

            }


        }
 
</script>