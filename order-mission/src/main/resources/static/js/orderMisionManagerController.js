
app.controller(
		'orderMisionManagerController',
		function($scope, $http, $sessionStorage, auth, $cookies) {
			$scope.usuario = $cookies.completeName;
			console.log();
			var urlBase = "";
			$http.defaults.headers.post["Content-Type"] = "application/json";
			$scope.status = "Abierta";
			
			//$scope.prueba = 1;
			$scope.testNewAccess = sessionStorage.getItem('incre');  // Compruebo que no haya una sesión activa para mostrar la nueva orden, en caso de que ya la haya, se mostrará la vista que estuviese.
			if ($scope.testNewAccess == null){
			$scope.pruebados = sessionStorage.setItem('incre', 1);
			}

			$scope.date = new Date();
			console.log($scope.date);
			$scope.patternNombre = /^([a-zA-ZÁÉÍÓÚñáéíóú-][\s]*)+$/;
			$scope.patternWNumbers = /^([a-zA-ZÁÉÍÓÚñáéíóú0-9&-][\s]*)+$/;
			
			$scope.logout = function(){
				auth.logout();
				
			}
			
			
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
			
			
			$scope.missionUpdate = JSON.parse(sessionStorage.getItem('updateMissionTrue'));
			console.log($scope.missionUpdate);
			
			if(($scope.missionUpdate != null) && ($scope.testNewAccess == 2)){
				
				$scope.updateId = $scope.missionUpdate.id;
				$scope.accommodations = $scope.missionUpdate.accommodations;
				$scope.rents = $scope.missionUpdate.rents;
				$scope.trajects = $scope.missionUpdate.itineraries;
				$scope.usuario = $scope.missionUpdate.collabFirstName;
				$scope.selectedProject = $scope.missionUpdate.project;
				
				
				$scope.pruebacero = $scope.missionUpdate.project.nameProj;
				
				
				// Este bucle rellena los campos de fecha y horas para la lista de itinerarios
				for(i=0; i<$scope.missionUpdate.itineraries.length; i++){
				$scope.missionUpdate.itineraries[i].date = new Date($scope.missionUpdate.itineraries[i].date.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-"));
				
				$scope.missionUpdate.itineraries[i].departureHour = new Date($scope.missionUpdate.itineraries[i].departureHour.toLocaleString());
				
				$scope.missionUpdate.itineraries[i].arrivalHour = new Date($scope.missionUpdate.itineraries[i].arrivalHour.toLocaleString());
				}

				// Este bucle rellena los campos de fecha y horas para la lista de RENTCAR
				for(i=0; i<$scope.missionUpdate.rents.length; i++){
				
					$scope.missionUpdate.rents[i].pickupDate = new Date($scope.missionUpdate.rents[i].pickupDate.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-"));
					
					$scope.missionUpdate.rents[i].deliveryDate = new Date($scope.missionUpdate.rents[i].deliveryDate.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-"));
					
					$scope.missionUpdate.rents[i].pickupHour = new Date($scope.missionUpdate.rents[i].pickupHour.toLocaleString());
					
					$scope.missionUpdate.rents[i].deliveryHour = new Date($scope.missionUpdate.rents[i].deliveryHour.toLocaleString());
					
				}
				
				// Este bucle rellena los campos de fecha y horas para la lista de HOTELES
				for(i=0; i<$scope.missionUpdate.rents.length; i++){
					
					$scope.missionUpdate.accommodations[i].entryDate = new Date($scope.missionUpdate.accommodations[i].entryDate.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-"));
					
					$scope.missionUpdate.accommodations[i].departureDate = new Date($scope.missionUpdate.accommodations[i].departureDate.toLocaleString('en-GB').slice(0, 10).split("\/").reverse().join("-"));	
					
					
				}
			}
			

			var todayDate = (new Date()).toLocaleString('en-GB').slice(
				0, 10).split("\/").reverse().join("-");
			$scope.minDate = todayDate;

			

			$http.get('/projects').success(function(data) {

				$scope.projects = data._embedded.projects;
				//	$scope.nameProj = data._embedded.projects[0].nameProj;
				//console.log("NAMEPROJ: "+$scope.nameProj);

			});
			
			//Hace que cambie el estado del boton del navegador de activo a inactivo dependiendo si está pulsado
			$scope.activebuton1=function activebuton1(){
				
				document.getElementById("botonact1").className = "active";
				document.getElementById("botonact2").className = "inactive";
				document.getElementById("botonact3").className = "inactive";
			}
			
			$scope.activebuton2=function activebuton2(){
				
				document.getElementById("botonact1").className = "inactive";
				document.getElementById("botonact2").className = "active";
				document.getElementById("botonact3").className = "inactive";
			}
			$scope.activebuton3=function activebuton3(){
				
				document.getElementById("botonact1").className = "inactive";
				document.getElementById("botonact2").className = "inactive";
				document.getElementById("botonact3").className = "active";
			}

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

			
			// Función para añadir a la BD una nueva misión tras pular el botón de "enviar"

			$scope.addCollab = function addCollab() {



				console.log($scope.selectedProject);
				$http
					.post(urlBase + '/missionSave', {
						collabFirstName : $scope.usuario,
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
			
			
			// Función para modificar una misión ya creada desde el modal de modificar
			
			$scope.modiCollab = function modiCollab() {

				
				var x = {
						collabFirstName : $scope.usuario,
						date : $scope.date,
						status : $scope.status,
						createdBy: $cookies.username,
						id : $scope.updateId,
						project : $scope.selectedProject,
						itineraries : $scope.trajects,
						rents : $scope.rents,
						accommodations : $scope.accommodations
						
						
					}
				
				
				
				
				$http.put('/missionUpdate/', x)
					.success(
						function(data, status, headers, config) {
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
				sessionStorage.setItem('incre', x);
			}

			$scope.UpdateIncrdos = function() {

				var getincre = sessionStorage.getItem('incre');
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