app.controller("GradeController",function($scope,$http){
	
	$scope.years=null;
	$scope.classCourse=function(){
		$http.get("/api/userbuycourse/select",{"params":{"mobile":$("#username").val(),"types":"1","years":$scope.years}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				$scope.classlist = result.data;
			}else {
				alert(result.message);
			}
		});
	}
	
	$scope.classCourse();
	$scope.yearselected=0;
	$scope.searchClass=function(years){
		$scope.yearselected=years;
		if(years==0){
			$scope.years=null;
		}else{
			$scope.years=years;
		}
		$scope.classCourse();
		
	};
})