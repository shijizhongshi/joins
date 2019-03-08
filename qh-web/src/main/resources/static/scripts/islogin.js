app.controller("HeadController", function($scope, $http){
	
        //上传主展示图片
    	if($("#islogin").val()=="0"){
    		$scope.register=true;
    	}else{
    		$scope.register=false;
    	}
    	
    	$scope.bg=function(){
    		if(!$scope.register){
    			location.href="/web/youmei/user";
    		}else{
    			alert("请先登录~");
    		}
    	};
    	$scope.loginout=function(){
    		location.href="/web/youmei/loginout";
    	}

});