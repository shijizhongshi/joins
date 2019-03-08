app.controller("courseClassController", function($scope, $http){
	$scope.courseSelected=0;
	$http.get("/api/courseclass/single",{"params":{"classId":$("#classId").val()}},{'Content-Type':'application/json;charset=UTF-8'})
	.success(function(result){
		if (result.status == "0") {
			$scope.courseClass=result.data;
			$scope.classDiscountPrice=$scope.courseClass.classDiscountPrice;
			$scope.tearcherdetail=$scope.courseClass.teacherlist[0];
		}else {
			alert(data.message);
		}
	});
	$scope.counrsecheck=function(course){
		if(course==0){
			$scope.courseSelected=0;
			$scope.classDiscountPrice=$scope.courseClass.classDiscountPrice;
		}else{
			$scope.classDiscountPrice=course.courseDiscountPrice;
			$scope.courseSelected=course;
		}
		
	}
	
	$scope.teacherDetail=function(tearcher){
		$scope.tearcherdetail=tearcher;
	}

});