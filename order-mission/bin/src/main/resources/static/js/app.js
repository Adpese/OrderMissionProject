var taskManagerModule = angular
		.module('orderMisionManagerApp', [ 'ui.router', "ngStorage" ]);

taskManagerModule
		.controller(
				'orderMisionManagerController',
				function($scope, $http, $sessionStorage) {

					var urlBase = "";
					$http.defaults.headers.post["Content-Type"] = "application/json";
					$scope.status = "Abierta";
					$scope.date = new Date();
					$scope.patternNombre = /^([a-zA-ZÁÉÍÓÚñáéíóú-][\s]*)+$/;
					$scope.patternWNumbers = /^([a-zA-ZÁÉÍÓÚñáéíóú0-9&-][\s]*)+$/;

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

//					$scope.agency = {
//						model : null,
//						availableOptions : [ {
//							id : '1',
//							name : 'Valencia'
//						}, {
//							id : '2',
//							name : 'Alicante'
//						}, {
//							id : '3',
//							name : 'Madrid'
//						}, {
//							id : '4',
//							name : 'Tenerife'
//						}, {
//							id : '5',
//							name : 'Barcelona'
//						}, ]
//					};
//
//					$scope.division = {
//						model : null,
//						availableOptions : [ {
//							id : '1',
//							name : 'División A'
//						}, {
//							id : '2',
//							name : 'División B'
//						}, {
//							id : '3',
//							name : 'División C'
//						}, ]
//					};
					$http.get('/projects').success(function(data) {
						
						$scope.projects = data._embedded.projects;
						$scope.nameProj = data._embedded.projects[0].nameProj;
						console.log("NAMEPROJ: "+$scope.nameProj);
						
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
					var projectData= []
					// add a new colab
					
					$scope.setProject = function(x){
						console.log(x);	
						var proj = $scope.projects;
						console.log(proj);	
						for(i in proj){
							console.log(i);	
							if( proj[i].nameProj == x){
								var projectData = {
										nameProj : proj[i].nameProj,
										agency : proj[i].agency,
										division : proj[i].division,
										id : proj[i].id
									};
								
							}
							
						}
						$scope.projectData = projectData;
						
					}
					
					$scope.addCollab = function addCollab() {

					
						
						console.log($scope.projectData);	
						$http
								.post(urlBase + '/missionSave', {
									
									collabFirstName : $scope.collabFirstName,
									date : $scope.date,
									project : $scope.projectData,
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
						 var updateDateAcc =
						 x.toLocaleString('en-GB').slice(0,10).split("\/").reverse().join("-");
						$scope.updateDateAcc = updateDateAcc;
					}
				});


taskManagerModule.controller('collaCtrl', function($scope, $http) {

	$http.get('/missions').success(function(data) {

		$scope.colla = data._embedded.missions;

	});


	$scope.SendData = function(x) {

		if (x.status === "Abierta") {

			console.log("Entra cerrada");

			var data = ({
				collabFirstName : x.collabFirstName,
				date : x.date,
				project : x.project,
				agency : x.agency,
				division : x.division,
				status : "Cerrada",
				createdBy : x.createdBy
			});

			$http.put('/missions/' + x.id, data).success(
					function(data, status, headers, config) {
						// $scope.PostDataResponse = data;
						x.status = "Cerrada";
						$scope.buttonState = "Abrir";
					}).error(
					function(data, status, header, config) {
						$scope.ResponseDetails = "Data: " + data
								+ "<hr />status: " + status + "<hr />headers: "
								+ header + "<hr />config: " + config;
					});

		} else if (x.status === "Cerrada") {

			console.log("Entra Abierrta");

			var data = ({
				collabFirstName : x.collabFirstName,
				date : x.date,
				project : x.project,
				agency : x.agency,
				division : x.division,
				status : "Abierta",
				createdBy : x.createdBy
			});

			$http.put('/missions/' + x.id, data).success(
					function(data, status, headers, config) {
						x.status = "Abierta";
						$scope.buttonState = "Cerrar";
					}).error(
					function(data, status, header, config) {
						$scope.ResponseDetails = "Data: " + data
								+ "<hr />status: " + status + "<hr />headers: "
								+ header + "<hr />config: " + config;
					});

		}

	};
});

taskManagerModule.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise("/login");

			$stateProvider

			.state('login', {
				url : "/login",
				templateUrl : "login",
			}).state('addcolb', {
				url : "/addcolb",
				templateUrl : "addcolb",
			}).state('listar', {
				url : "/listar",
				templateUrl : "listar",
			}).state('listar_close', {
				url : "/listar_close",
				templateUrl : "listar_close",
			})

		} ]);
