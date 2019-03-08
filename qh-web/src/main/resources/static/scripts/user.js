app.controller("userController",function($scope,$http){
	
	$http.get("/api/user/single",{"params":{"mobile":$("#username").val()}},{'Content-Type':'application/json;charset=UTF-8'})
	.success(function(result){
		if (result.status == "0") {
			$scope.user = result.data;
		}else {
			alert(data.message);
		}
	});
	
})