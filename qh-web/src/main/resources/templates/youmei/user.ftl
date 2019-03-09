<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
		<@h.header title="个人中心"/>
<link rel="stylesheet" href="/styles/user.css" />
<style>
#user{
	background:#CB0101 !important;
}
</style>
<script type="text/javascript">
//iframe 自适应高度
function changeFrameHeight(){
    var ifm= document.getElementById("test"); 
    ifm.height=document.documentElement.clientHeight-"70";

}

window.onresize=function(){  
     changeFrameHeight();  

} 
//左侧栏点击特效
 function setTab(n){
 			var tabs=document.getElementById("classify").getElementsByTagName("a");
			var shows=document.getElementById("classify").getElementsByTagName("span");
            for(var i=0;i<tabs.length;i++){
				tabs[i].style.color='black';
                shows[i].style.display='none';
            
                if(i==n){
                	tabs[i].style.color='red';
                shows[i].style.display='inline-block';
              
                }

            }


        }
 
</script>
<@b.head ></@b.head> 
<body>
<div style="100%;height:50px;background:#FEF8EC;"></div>
<div class="user">
<!--左侧选择栏-->
<div class="classify" id="classify">
<ul>
<li  ><a  onclick="setTab(0)"  href="user/user-message.ftl" target="iframes" style="color:red;" >个人信息</a><span style="display:inline-block;">></span></li>
<li><a onclick="setTab(1)"  href="user/user-grade.ftl" target="iframes" >我的班级</a><span >></span></li>
<li><a onclick="setTab(2)" href="user/user-curriculum.ftl" target="iframes"  >我的课程</a><span >></span></li>
<li><a>修改密码</a><span >></span></li>
<li><a>我的直播</a><span >></span></li>
<li><a>个人题库</a><span >></span></li>
<li><a>技能第三站</a><span >></span></li>
<li><a>我的答疑</a><span >></span></li>
<li><a>资料订单</a><span >></span></li>
<li><a>学习并充值</a><span >></span></li>
<li><a>继学申请</a><span >></span></li>
</ul>
</div>
<!--右侧信息栏-->
<div class="message">
<iframe name="iframes" src="user/user-message.html"  width="100%" height="100%" runat="server"  onload="changeFrameHeight()" id="test"></iframe>
</div>
</div>
<!--footer-->
<#include "/layout/footer.ftl"/>
</body>


</html>