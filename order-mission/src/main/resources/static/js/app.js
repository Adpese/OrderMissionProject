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
			$scope.testNewAccess = localStorage.getItem('incre');  // Compruebo que no haya una sesión activa para mostrar la nueva orden, en caso de que ya la haya, se mostrará la vista que estuviese.
			if ($scope.testNewAccess == null){
			$scope.pruebados = localStorage.setItem('incre', 1);
			}

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
						accommodations : $scope.accommodations,
						createdBy: $cookies.username
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
			
			
			
			$scope.diffefechas = function(x, y) {

				var magicNumber = (1000 * 60 * 60 * 24);

				var dayDiff = Math.floor((y - x) / magicNumber);
				if (angular.isNumber(dayDiff)) {
					return dayDiff;
				}


			}
			

		});




								// CONTROLADOR DE LISTA
								// CONTROLADOR DE LISTA
								// CONTROLADOR DE LISTA                  
								// CONTROLADOR DE LISTA	
								// CONTROLADOR DE LISTA	
								// CONTROLADOR DE LISTA	
								// CONTROLADOR DE LISTA	




taskManagerModule.controller('collaCtrl', function($scope, $http, $cookies) {

	localStorage.removeItem('showTables');


	//	$http.get('/misiones').success(function(data) {
	//
	//		$scope.colla = data;
	//
	//	});


	if ($cookies.role == 'Jefe') {
		$http.get('/jefe/' + $cookies.username).success(function(data) {

			$scope.colla = data;
			console.log($scope.colla);

		});
		console.log('Ha entrado en la parte de jefe');
	} else {
		$http.get('/misiones').success(function(data) {

			$scope.colla = data;
		});
		console.log('Ha entrado en la partde de NO jefe');
	}
	
	

	$scope.SendData = function(x) {   // Función para actualizar los status de las misiones
		
		
		swal({
			  title: "Atención!",
			  text: "Está a punto de validar la orden, ¿Está seguro?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "Sí, validar la misión!",
			  closeOnConfirm: false
			},
			function(){
			  swal("Validada!", "Has validado correctamente la misión", "success");
			

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
	
	
$scope.desvalidar = function(x) {   // Función para actualizar los status de las misiones
		
		
		swal({
			  title: "Atención!",
			  text: "Está a punto de revertir la validación de esta orden, ¿Está seguro?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "Sí, revertir la validación!",
			  closeOnConfirm: false
			},
			function(){
			  swal("Éxito!", "La validación de la orden se ha realizado correctamente", "success");
			

			  x.status = "Abierta";
			
				
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
	    	var seeStatus = "!Cerrada";
	        break;
	} 
		return seeStatus;
	}
	
	$scope.showValidate = function(x){    // Funcion para asignar lo valores del filtro pr status en las listas
		
		switch(x) {
	    case "Abierta":
	    	if($cookies.role == "Jefe" || $cookies.role == "Director"){ // A 1 muestro el botón de validar/cerrar, a 0 muestro el botón de desvalidar, 2 no muestro nada y 3 muestro ambos botones.
	    		var seeStatusbutton = 'validar';
	    		}else{
	    			var seeStatusbutton = null;
	    		}
	    	break;
	    case "ValidadoDirector":
	    	if($cookies.role == "Assistant"){
	    		var seeStatusbutton = 'validar';
	    		}else if($cookies.role == "Director") {
	    			var seeStatusbutton = 'desvalidar';
	    		}else {
	    			var seeStatusbutton = null;
	    		}
	        break;
	    case "ValidadoJefe":
	    	if($cookies.role == "Director"){
	    		var seeStatusbutton = 'validarAndDesvalidar';
	    		}else if($cookies.role == "Jefe") {
	    			var seeStatusbutton = 'desvalidar';
	    		}else{
	    			var seeStatusbutton = null;
	    		}
	        break;
	} 
		return seeStatusbutton;
	}
	
	
	if ($cookies.role == 'Assistant') {
		
		$scope.cosauno = 'Cerrar';
	} else {
		$scope.cosauno = 'Validar';
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



					
					// CONTROLADOR DE VALIDACION
					// CONTROLADOR DE VALIDACION
					// CONTROLADOR DE VALIDACION                  
					// CONTROLADOR DE VALIDACION	
					// CONTROLADOR DE VALIDACION	
					// CONTROLADOR DE VALIDACION	
					// CONTROLADOR DE VALIDACION	




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
			localStorage.removeItem('incre');
			
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