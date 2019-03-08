app.controller("UserCourseController",function($scope,$http){
	
	$scope.years=null;
	$scope.courselist=function(){
		$http.get("/api/userbuycourse/select",{"params":{"mobile":$("#username").val(),"types":"2","years":$scope.years}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				$scope.list = result.data;
			}else {
				alert(result.message);
			}
		});
	}
	
	$scope.courselist();
	
	$scope.yearselected=0;
	$scope.searchClass=function(years){
		$scope.yearselected=years;
		if(years==0){
			$scope.years=null;
		}else{
			$scope.years=years;
		}
		$scope.courselist();
		
	};
})