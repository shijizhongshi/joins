app.controller("loginController",function($scope,$http){
	alert("1111");
	//点击事件
	$scope.submitForm = function() {
		alert("1111");
		$http.get("/api/login/login",{"params":{"mobile":$scope.mobile,"password":$scope.password}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(count){
			if (count == 1) {
				//跳转到登录界面
				location.href="/web/youmei/index"
			}else {
				alert("登陆失败");
			}
		})
	};
})

