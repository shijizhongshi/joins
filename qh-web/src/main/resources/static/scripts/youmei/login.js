app.controller("loginController",function($scope,$http){
	//点击事件 成功后跳转到主页
	$scope.submitForm = function() {
		$http.get("/api/user/loginByMobileAndPassword",{"params":{"mobile":$scope.mobile,"password":$scope.password}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				//跳转到登录界面
				location.href="/web/youmei/index"
			}else {
				alert("登陆失败，账号或密码输入错误");
			}
		})
	};
})

