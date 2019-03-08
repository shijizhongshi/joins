<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
<@h.header title="网课中心"/>
<link rel="stylesheet" href="/styles/particulars.css" />

<@b.head ></@b.head> 
<style>
.courseSelected{
border:1px solid red !important;
}
.courseSelected img{
display:block !important;
}
.teachered{
color:red;
}
</style>
<script src="/scripts/particulars.js"></script>
<body ng-app="app" ng-controller="courseClassController">
<input type="hidden" value="${classId}" id="classId">
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<div class="weizhi">
<p>您的位置：<a href="index">网站首页</a>><a href="Onlie-course">网课中心</a>>{{courseClass.courseTypeSubclassName}} &nbsp;&nbsp;{{courseClass.className}}</p>
</div>
<!-- 购买课程 -->
<div class="buy">
<div class="buy-center">
<img src="/images/fengmian.jpg" style="width:55%;" >
<div class="goumai">
<b>{{courseClass.courseTypeSubclassName}} &nbsp;&nbsp;{{courseClass.className}}</b>
<p style="margin:20px 0;">价格:<span>￥{{classDiscountPrice}}</span></p>
<p>选择课程：</p>
<ul>
<!--li的ng-class[ border:1px solid red; li<img>display:block]-->
<li ng-repeat="course in courseClass.courselist" ng-click="counrsecheck(course)" ng-class="{'courseSelected':courseSelected==course}">{{course.courseName}}<img src="/images/gou.png"/></li>

<li ng-click="counrsecheck(0)" ng-class="{'courseSelected':courseSelected==0}" style="width:100%">全套课程<img src="/images/gou.png" /></li>
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
<div style="float:right" ><span ng-repeat="tearcher in courseClass.teacherlist" ng-click="teacherDetail(tearcher)" ng-class="{'teachered':teachered==tearcher}">{{tearcher.name}}</span>
</div>
</div>

<ul>
<li>
<b style="float:left;width:100px;height:100px;border-radius:50px;overflow:hidden;background:white;">
		<img src="/images/laoshi.png" style="width:100%; "></b>
<b >讲师：&nbsp;{{tearcherdetail.name}}</b><br/>
<span>课程数：&nbsp;{{tearcherdetail.courseNumber}}</span>
<p>{{tearcherdetail.details}}</p>
</li>

</ul>
</div>
<!-- 班级概述 -->
<div class="course-intro">
<div class="title">班级概述</div>
<div class="course-intro-center" >
{{tearcherdetail.detail}}
</div>
</div>


</div>
<!-- 课程章节 -->

<div class="intro-right">
<div class="title">课程章节</div>
<ul>
<li ng-repeat="course in courseClass.courselist"><img src="/images/kechengli.png"/>
{{course.courseName}}<span>查看章节</span></li>
</ul>
</div>
</div>
<!--footer-->
<#include "footer.ftl"/>
</body>
</html>