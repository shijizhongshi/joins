<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
<@h.header title="网课中心"/>
<link rel="stylesheet" href="/styles/particulars.css" />
<@b.head ></@b.head> 
<body>
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<div class="weizhi">
<p>您的位置：<a href="index">网站首页</a>><a href="Onlie-course">网课中心</a>>{中医(执业)助理医师精品无忧班}</p>
</div>
<!-- 购买课程 -->
<div class="buy">
<div class="buy-center">
<img src="/images/fengmian.jpg" style="width:55%;" >
<div class="goumai">
<b>{中医(执业)助理医师精品无忧班}</b>
<p style="margin:20px 0;">价格:<span>￥12344</span></p>
<p>选择课程：</p>
<ul>
<!--li的ng-class[ border:1px solid red; li<img>display:block]-->
<li>2018把拉啊啦啦啦啦<img src="/images/gou.png"/></li>
<li>2018把拉啊啦啦啦啦<img src="/images/gou.png"/></li>
<li>2018把拉啊啦啦啦啦<img src="/images/gou.png"/></li>
<li style="border:1px solid red;width:100%">全套课程<img src="/images/gou.png" style="display:block;"/></li>
</ul>
<div class="btn">
<form >
<input type="button" value="购买课程" style="background:#CC0001;"/>
<input type="button" value="试听课程" style="background:#CFCFCF;float:right"/></form>
</div>
</div>
</div>
</div>

<div class="intro">
<div class="intro-left">
<!--老师简介 -->
<div class="teacher-intro">
<div class="title">老师简介
<!-- 这个老师跪求最多循环三个吧 -->
<div style="float:right"><span>刘家辉</span>
<span style="color:red;">郭子寒</span></div>
</div>

<ul>
<li>
<b style="float:left;width:100px;height:100px;border-radius:50px;overflow:hidden;background:white;">
		<img src="/images/laoshi.png" style="width:100%; "></b>
<b >讲师：&nbsp;郭子寒</b><br/>
<span>课程数：&nbsp;111</span>
<p>这个老师很牛啊，他巴拉巴拉巴拉吧巴拉巴拉巴拉巴拉</p>
</li>

</ul>
</div>
<!-- 班级概述 -->
<div class="course-intro">
<div class="title">班级概述</div>
<div class="course-intro-center" >
今夜不让你入睡嘻嘻嘻嘻
</div>
</div>


</div>
<!-- 课程章节 -->

<div class="intro-right">
<div class="title">课程章节</div>
<ul>
<li><img src="/images/kechengli.png"/>
2018临床基础课程<span>查看章节</span></li>
</ul>
</div>
</div>
<!--footer-->
<#include "footer.ftl"/>
</body>
</html>