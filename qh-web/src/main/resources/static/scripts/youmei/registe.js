app.controller("registeController",function($scope,$http){
	
	$scope.user=null;
	//点击事件 成功后跳转到登录页面
	$scope.submitForm = function() {
		
		$http.post("/api/user/web/registe",$scope.user,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				//注册成功 跳转到登录界面
				alert("注册成功");
				location.href="/web/youmei/login"
			}else {
				alert(result.message);
			}
		})
	};
	//点击事件 点击获取验证码
	$scope.sms = function() {
		$http.get("/api/sms/sendmobile",{"params":{"mobile":$scope.user.mobile,"types":$scope.types=1}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				//成功
				alert("ture")
			}else {
				alert(result.message);
			} 
		})
	};
})

