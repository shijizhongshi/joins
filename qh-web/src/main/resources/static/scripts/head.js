app.controller("HeadController",function($scope,$http){
	//点击事件 成功后跳转到主页
	$scope.selected=0;
	$scope.bg=function(type){
		if(type==0){
			location.href="/web/youmei/index";
		}
		if(type==1){
			location.href="/web/youmei/Onliecourse";
		}
		
	}
})