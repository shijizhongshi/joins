app.controller("indexController",function($scope,$http){
	
	$scope.showNews = function() {
		$http.get("/api/news/selectNews",{'Content-Type':'application/json;charset=UTF-8'})
		.success(function(result){
			if (result.status == "0") {
				$scope.newsList = result.data;
				$scope.news = $scope.newsList.splice(2,8);
			}else {
				alert(result.message);
			}
		})
	};
	$scope.showNews();
})

