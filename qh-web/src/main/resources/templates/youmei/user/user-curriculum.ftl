<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆</title>
	</head>
<link rel="stylesheet" href="/styles/user.css" />
<script type="text/javascript" src="/scripts/youmei/jquery-1.8.3.min.js"></script>
<script src="/assets/js/angular/angular.js"></script>
<script src="/assets/js/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="/assets/js/angular-bootstrap/angular-locale_zh.js"></script>
<script src="/scripts/layout/app.js"></script>
<script type="text/javascript" src="/scripts/usercourse.js"></script>
<body ng-app="app" ng-controller="UserCourseController">
<input type="hidden" value="${username}" id="username" />
<div class="grade">
<p>我的课程<br/><span>(提示：当大纲未出来之前可以观看去年网课，当大纲出来之后，可以观看当年网课，去年网课将关闭</span></p>
<ul class="fenlei">
<li ng-click="searchClass(0)" ng-class="{'yearselected':yearselected==0}">全部</li>
<li ng-click="searchClass(2019)" ng-class="{'yearselected':yearselected==2019}">2019</li>
<li ng-click="searchClass(2018)" ng-class="{'yearselected':yearselected==2018}">2018</li>
</ul>
<dl ng-repeat="c in list">
<img ng-src="{{c.courseImgUrl}}"/>
<dt>{{c.courseName}}</dt>
<dd>开始学习</dd>
</dl>

</div>
</body>

</html>