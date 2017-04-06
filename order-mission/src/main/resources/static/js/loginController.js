app.controller('loginController', function($scope, $http, auth) {

	var urlBase = "";

	console.log($scope.userPassword);
	$scope.credentials = function() {
		$http.post(urlBase + '/loginLDAP', {
			name : $scope.userCollab,
			password : $scope.userPassword
		}).success(
			function(data, status, headers) {
				console.log(data);


				if (data === "Assistant" || data === "Director" || data === "Jefe" || data === "Colaborador") {
					auth.login($scope.userCollab, data);
				} else {
					swal(
						"Error",
						"Usuario o contraseña incorrecto, vuelva a intentarlo ",
						"error");
				}

			}).error(
			function(data, status, header, config) {
				swal(
					"Error",
					"Error al intentar conectarse al servicio, por favor, vuelva a intentarlo",
					"error");
			});
		;
	};
});