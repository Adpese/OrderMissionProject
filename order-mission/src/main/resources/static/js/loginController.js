app.controller('loginController', function($scope, $http, auth, $cookies) {

	var urlBase = "";

	console.log($scope.userPassword);
	$scope.credentials = function() {
		$http.post(urlBase + '/loginLDAP', {
			name : $scope.userCollab,
			password : $scope.userPassword
		}).success(
			function(data, status, headers) {
				
				console.log(data.userRole);
				
//				var dat = data.split("/");
//				$cookies.role=dat[0];
//				console.log(dat[2]);
//				$cookies.agency= dat[1];
//				
//				$cookies.completeName= dat[2];
//				
				
				$cookies.role = data.userRole;
				$cookies.agency = data.userAgency;
				$cookies.completeName = data.userDisplayName;
				userList = data.displayNameList;
			

				if ($cookies.role === "Assistant" || $cookies.role === "Director" ||$cookies.role  === "Jefe" || $cookies.role === "Colaborador") {
					auth.login($scope.userCollab, data.userRole);
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