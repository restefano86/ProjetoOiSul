angular.module('projetoOiA').controller('cadastroCtrl', function ($scope, $http, UserResource ) {
	$scope.save = function(user){
		alert("a");
		salvarUser(user);
		delete $scope.user;
		$scope.cadastroForm.$setPristine();
	};
	
	var salvarUser = function(user){
		alert("b");
		$http.post("http://localhost:8080ProjetoOiSulrest/usersSite", user).success(function(data){
			console.log("sucesso");
		}).error(function(data){
			console.log("erro");
		});
	}
	
	/*
    $scope.save = function() {
    	alert("A");
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The user was created successfully.'});
            $location.path('/Users');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        alert(UserResource);
        UserResource.save($scope.user, successCallback, errorCallback);
    };

	*/
});