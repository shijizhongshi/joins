<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
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
#course{
	background:#CB0101 !important;
}
</style>
<script src="/scripts/course.js"></script>
<@b.head ></@b.head> 
<body ng-app="app" ng-controller="CourseController">
<div class="nav">
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<img src="/images/wkbanner.png"/>
</div>
<div class="onlie">
<p class="choose"><span></span><b>选择类别</b></p>
<ul class="choose-lei">
<!-- li的ng-class写   【background:url(/images/choose2.png)  no-repeat;background-size:100% 100%;"】这一串 -->
<li ng-repeat="type in typelist" ng-click="clickType(type)" ng-class="{'selected':selected==type}">{{type.courseTypeName}}</li>
</ul>
<p class="choose"><span></span><b>选择专业</b></p>
<ul class="choose-zy">
<li ng-repeat="sub in subtype.sublist" ng-click="subCourse(sub)">{{sub.courseTypeSubclassName}}</li>
</ul>
<div >
	<ul class="choose-kc">
					<li ng-repeat="class in classlist" ng-click="singleClass(class.id)">
					<div>
					<img ng-src="{{class.imgUrl}}" style="width:100%;height:130px;"/>
   <p style="line-height: 40px;font-size: 1.2rem;">{{class.className}}</p>
	<p class="jiage">￥{{class.classDiscountPrice}} <span >{{class.buyCount}}人已购买</span></p>
					</div>
		<p class="laoshi">
		<b style="display:inline-block;width:40px;height:40px;border-radius:50px;overflow:hidden;background:white;">
		<img ng-src="{{class.tearcherImg}}" style="width:110%; alt="老师名字"/></b>
					<span>{{class.courseLecturer}}</span></p>
			
					</li> 
	</ul>
	</div>
</div>

</body>
</html>