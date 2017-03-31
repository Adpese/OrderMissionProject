var taskManagerModule = angular
	.module('orderMisionManagerApp', [ 'ui.router', 'ngStorage', 'ngCookies' ]);

taskManagerModule
	.controller(
		'orderMisionManagerController',
		function($scope, $http, $sessionStorage, auth, $cookies) {
			$scope.usuario = $cookies.username
			console.log();
			var urlBase = "";
			$http.defaults.headers.post["Content-Type"] = "application/json";
			$scope.status = "Abierta";

			//$scope.prueba = 1;
			//$scope.pruebados = localStorage.getItem('incre');

			$scope.date = new Date();
			$scope.patternNombre = /^([a-zA-ZÁÉÍÓÚñáéíóú-][\s]*)+$/;
			$scope.patternWNumbers = /^([a-zA-ZÁÉÍÓÚñáéíóú0-9&-][\s]*)+$/;
			
			$scope.logout = function(){
				auth.logout();
				
			}



			var todayDate = (new Date()).toLocaleString('en-GB').slice(
				0, 10).split("\/").reverse().join("-");
			$scope.minDate = todayDate;

			$scope.trajects = [];
			$scope.addNewTraject = function() {
				$scope.trajects.push({ });
			};

			$sessionStorage.SessionMessage = "Hola Session Storage";

			$scope.removeTraject = function() {
				var lastItem = $scope.trajects.length - 1;
				$scope.trajects.splice(lastItem);
			};

			$scope.rents = [];
			$scope.addNewRent = function() {

				$scope.rents.push({});
			};

			$scope.removeRent = function() {
				var lastRent = $scope.rents.length - 1;
				$scope.rents.splice(lastRent);
			};

			$scope.accommodations = [];
			$scope.addNewAccommodation = function() {

				$scope.accommodations.push({});
			};

			$scope.removeAccommodation = function() {
				var lastAccommodation = $scope.accommodations.length - 1;
				$scope.accommodations.splice(lastAccommodation);
			};

			$http.get('/projects').success(function(data) {

				$scope.projects = data._embedded.projects;
				//	$scope.nameProj = data._embedded.projects[0].nameProj;
				//console.log("NAMEPROJ: "+$scope.nameProj);

			});


			$scope.transport = {
				model : null,
				availableOptions : [ {
					id : '1',
					name : 'Avión'
				},

					{
						id : '2',
						name : 'Tren'
					},

				]
			};


			$scope.addCollab = function addCollab() {



				console.log($scope.projectData);
				$http
					.post(urlBase + '/missionSave', {
						collabFirstName : $scope.collabFirstName,
						date : $scope.date,
						project : $scope.selectedProject,
						//									agency : $scope.agency.model,
						//									division : $scope.division.model,
						status : $scope.status,
						itineraries : $scope.trajects,
						rents : $scope.rents,
						accommodations : $scope.accommodations
					}

				)
					.success(
						function(data, status, headers) {
							swal(
								"Nueva misión creada",
								"Se ha generado una nueva misión con los datos introducidos",
								"success");

							var newColabUri = headers()["location"];

						}).error(
					function(data, status, header, config) {
						swal(
							"Error",
							"No ha sido posible realizar la orden, por favor, vuelva a intentarlo",
							"error");
					});

			};


			$scope.changeCar = function(x) {

				var updateDateCar = x.toLocaleString('en-GB').slice(0,
					10).split("\/").reverse().join("-");

				$scope.updateDateCar = updateDateCar;
			}

			$scope.changeAcc = function(x) {
				var updateDateAcc = x.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-");
				$scope.updateDateAcc = updateDateAcc;
			}

			$scope.UpdateIncr = function(x) {
				localStorage.setItem('incre', x);
			}

			$scope.UpdateIncrdos = function() {

				var getincre = localStorage.getItem('incre');
				return getincre;

			}
		//console.log($scope.prueba);
		});


taskManagerModule.controller('collaCtrl', function($scope, $http, $cookies) {

	localStorage.removeItem('showTables');
	
	
//	if($cookies.role == "Assistant"){
//		$scope.statusdos = "ValidadoDirector";
//		console.log("iieeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee "+$scope.statusdos);
//	}
	
	

	$http.get('/misiones').success(function(data) {

		$scope.colla = data;

	});


	$scope.SendData = function(x) {

		
		switch(x.status) {
	    case "Abierta":
	        if($cookies.role == "Jefe")
	        	{x.status = "ValidadoJefe";} 
	        else{ x.status = "ValidadoDirector"}
	        break;
	    case "ValidadoJefe":
	        x.status = "ValidadoDirector";
	        break;
	    case "ValidadoDirector":
	        x.status = "Cerrada";
	        break;
	}
		
			
			$http.put('/missions/' + x.id, x).success(
				function(data, status, headers, config) {
//					x.status = "Cerrada";
//					$scope.buttonState = "Abrir";
				}).error(
				function(data, status, header, config) {
					$scope.ResponseDetails = "Data: " + data
						+ "<hr />status: " + status + "<hr />headers: "
						+ header + "<hr />config: " + config;
				});
			
		
			
//			if (x.status === "Cerrada") {
//
//			console.log("Entra Abierrta");
//			x.status="Abierta";
//		
//			$http.put('/missions/' + x.id, x).success(
//				function(data, status, headers, config) {
//					
//					//$scope.buttonState = "Cerrar";
//				}).error(
//				function(data, status, header, config) {
//					$scope.ResponseDetails = "Data: " + data
//						+ "<hr />status: " + status + "<hr />headers: "
//						+ header + "<hr />config: " + config;
//				});
//
//		}

	};
	
	$scope.callCOllaBD = function(x){  // Funcion para cargar los datos para la mision x (es el id de la mision en concreto) que pasamos por parametro
		
		var showTable = localStorage.getItem('showTables');
		
		
		if (showTable != x){
		localStorage.setItem('showTables', x);
		

		$http.get('/busquedaMission/'+ x).success(function(data) {

			$scope.collaitin = data.itineraries;
			$scope.collarents = data.rents;
			$scope.collaccom= data.accommodations;

		});
		}else {localStorage.setItem('showTables', -1);}
		
	}
	
	$scope.callShowTables = function(){  // Funcion para cargar la id, que en la lista me permite comprobar qué mision se desplega
		
		var showTable = localStorage.getItem('showTables');
		return showTable;
		
	}
	
	$scope.filtStatus = function(){    // Funcion para asignar lo valores del filtro pr status en las listas
	
		switch($cookies.role) {
	    case "Assistant":
	    	var seeStatus = "ValidadoDirector";
	    	break;
	    case "Director":
	    	var seeStatus = "!Cerrada";
	        break;
	    case "Jefe":
	    	var seeStatus = "!Cerrada";
	        break;
	    default:
	    	var seeStatus = "Abierta";
	        break;
	} 
		return seeStatus;
	}
	
	$scope.showValidate = function(x){    // Funcion para asignar lo valores del filtro pr status en las listas
		
		switch(x) {
	    case "Abierta":
	    	if($cookies.role == "Jefe" || $cookies.role == "Director"){
	    		var seeStatusbutton = 1;
	    		}else{
	    			var seeStatusbutton = 0;
	    		}
	    	break;
	    case "ValidadoDirector":
	    	if($cookies.role == "Assistant"){
	    		var seeStatusbutton = 1;
	    		}else{
	    			var seeStatusbutton = 0;
	    		}
	        break;
	    case "ValidadoJefe":
	    	if($cookies.role == "Director"){
	    		var seeStatusbutton = 1;
	    		}else{
	    			var seeStatusbutton = 0;
	    		}
	        break;
	} 
		return seeStatusbutton;
	}
	
	
});

taskManagerModule.config([ '$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {

		//$urlRouterProvider.otherwise("/login");

		//			$stateProvider
		//
		//			.state('login', {
		//				url : "/login",
		//				//templateUrl : "login",
		//			}).state('addcolb', {
		//				url : "/addcolb",
		//				//templateUrl : "addcolb",
		//			}).state('listar', {
		//				url : "/listar",
		//				//templateUrl : "listar",
		//			}).state('listar_close', {
		//				url : "/listar_close",
		//				//templateUrl : "listar_close",
		//			})

	} ]);

taskManagerModule.factory("auth", function($cookies, $cookieStore, $window, $location) {
	return {
		login : function(username, role) {
			
			$cookies.username = username;
			$cookies.role = role;
			$window.location.href = "http://localhost:8080/home";
		},
		logout : function() {
			$cookieStore.remove("username");
			$cookieStore.remove("role");
			$window.location.href = "http://localhost:8080/login";
		},
		checkStatus : function() {
			var privateRoutes = [ "http://localhost:8080/home"];
			var publicRoutes = ["http://localhost:8080/login"];			
			if (this.in_array($location.absUrl(), privateRoutes) && typeof ($cookies.username) == "undefined") {
				$window.location.href = "http://localhost:8080/login";
			}

			if (this.in_array($location.absUrl(), publicRoutes) && typeof ($cookies.username) != "undefined") {
				$window.location.href = "http://localhost:8080/home";
			}
		},
		in_array : function(needle, haystack) {
			var key = '';
			for (key in haystack) {
				if (haystack[key] == needle) {
					return true;
				}
			}
			return false;
		},
		getRole : function() {
			return $cookies.role;
		}
	}


});

taskManagerModule.controller('loginController', function($scope, $http, auth) {

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
					"Error al intentar conectarse al servicio, porfavor, vuelva a intentarlo",
					"error");
			});
		;
	};
});


taskManagerModule.run(function($rootScope, auth)
{
		console.log(auth.getRole());
		auth.checkStatus();
})