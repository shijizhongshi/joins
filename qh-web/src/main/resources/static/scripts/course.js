app.controller("CourseController",function($scope,$http){
	//点击事件 成功后跳转到主页

	$http.get("/api/course/courseTypeList")
	.success(function(result){
		if (result.status == "0") {
			$scope.selected=result.data[0];
			$scope.subType(1);
			$scope.typelist=result.data;
		}else {
			alert(data.message);
		}
	});
	$scope.ccd=null;
	$scope.subType=function(courseTypeId){
		$http.get("/api/course/courseTypeSubclassList",{"params":{"courseTypeId":courseTypeId}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				//跳转到登录界面
				$scope.subtype=result.data[0];
				$scope.ccd=$scope.subtype;
				$scope.ccd.courseTypeName=$scope.subtype.courseTypeName;
				$scope.ccd.courseTypeSubclassName=$scope.subtype.courseTypeSubclassName;
				$scope.ccd.page=1;
				$scope.classList();
			}else {
				alert(data.message);
			}
		})
	};
	$scope.clickType=function(type){
		$scope.selected=type;
		$scope.subType(type.id);
	};
	
	
	$scope.classList=function(){
		
		$http.post("/api/courseclass/list",$scope.ccd,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				//跳转到登录界面
				$scope.classlist=result.data;
			}else {
				alert(data.message);
			}
		})
	};
	
	
	
})