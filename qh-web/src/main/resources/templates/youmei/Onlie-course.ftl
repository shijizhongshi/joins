<#import "/layout/header.ftl" as h/>
<!DOCTYPE html>
<html>
	<@h.header title="网课中心"/>
<link rel="stylesheet" href="/styles/youmei.css" />
<link rel="stylesheet" href="/styles/online.css" />

<body>
<div class="nav">
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<img src="/images/wkbanner.png"/>
<#include "/layout/head.ftl" />

</div>
<div class="onlie">
<p class="choose"><span></span><b>选择类别</b></p>
<ul class="choose-lei">
<!-- li的ng-class写   【background:url(/images/choose2.png)  no-repeat;background-size:100% 100%;"】这一串 -->
<li style="background:url(/images/choose2.png)  no-repeat;background-size:100% 100%;">医师资格</li>
<li>药师资格</li>
<li>中医基础理论</li>
<li>卫生资格</li>
<li>健康管理师</li>
</ul>
<p class="choose"><span></span><b>选择专业</b></p>
<ul class="choose-zy">
<li>临床(执业助理)医师</li>
<li>中医(执业助理)医师</li>
</ul>
<div >
	<ul class="choose-kc">
					<li>
					<div>
					<img src="/images/fengmian.jpg" style="width:100%;height:130px;"/>
   <p style="line-height: 40px;font-size: 1.2rem;">中医理论基础</p>
	<p class="jiage">￥666 <span >111人已购买</span></p>
					</div>
		<p class="laoshi">
		<b style="display:inline-block;width:40px;height:40px;border-radius:50px;overflow:hidden;background:white;">
		<img src="/images/laoshi.png" style="width:110%; alt="老师名字"/></b>
					<span>徐志摩</span></p>
			
					</li> 
	</ul>
	</div>
</div>

</body>
</html>