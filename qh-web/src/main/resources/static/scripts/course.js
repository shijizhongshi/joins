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
				$scope.classList();
				
			}else {
				alert(data.message);
			}
		})
	};
	//////点击大类别的事件
	$scope.clickType=function(type){
		$scope.selected=type;
		$scope.subType(type.id);
		$scope.ccd.courseTypeName=type.courseTypeName;
		$scope.ccd.courseTypeSubclassName=null;
		$scope.classList();
	};
	
	//////班级课程的集合
	$scope.classList=function(){
		$scope.ccd.page=1;
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
	
	///////子类别的点击事件
	$scope.subCourse=function(sub){
		///////点击子类别的时候加样式
		$scope.ccd.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.classList();
	}
	
	
	
})