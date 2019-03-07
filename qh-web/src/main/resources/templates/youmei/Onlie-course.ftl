<#import "/layout/header.ftl" as h/>
<!DOCTYPE html>
<html>
	<@h.header title="网课中心"/>
<link rel="stylesheet" href="/styles/youmei.css" />
<link rel="stylesheet" href="/styles/online.css" />
<style>
.selected{
background:url(/images/choose2.png)  no-repeat !important;
background-size:100% 100%  !important;
}
</style>
<script src="/scripts/course.js"></script>
<body ng-app="app" ng-controller="CourseController">
<div class="nav">
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<img src="/images/wkbanner.png"/>
<#include "/layout/head.ftl" />

</div>
<div class="onlie">
<p class="choose"><span></span><b>选择类别</b></p>
<ul class="choose-lei">
<!-- li的ng-class写   【background:url(/images/choose2.png)  no-repeat;background-size:100% 100%;"】这一串 -->
<li ng-repeat="type in typelist" ng-click="clickType(type)" ng-class="{'selected':selected==type}">{{type.courseTypeName}}</li>
</ul>
<p class="choose"><span></span><b>选择专业</b></p>
<ul class="choose-zy">
<li ng-repeat="sub in subtype.sublist">{{sub.courseTypeSubclassName}}</li>
</ul>
<div >
	<ul class="choose-kc">
					<li ng-repeat="class in classlist">
					<div>
					<img ng-src="{{class.imgUrl}}" style="width:100%;height:130px;"/>
   <p style="line-height: 40px;font-size: 1.2rem;">{{class.className}}</p>
	<p class="jiage">￥{{class.classDiscountPrice}}<!-- <span >111人已购买</span>--></p>
					</div>
		<p class="laoshi">
		<b style="display:inline-block;width:40px;height:40px;border-radius:50px;overflow:hidden;background:white;">
		<img src="/images/laoshi.png" style="width:110%; alt="老师名字"/></b>
					<span>{{class.courseLecturer}}</span></p>
			
					</li> 
	</ul>
	</div>
</div>

</body>
</html>