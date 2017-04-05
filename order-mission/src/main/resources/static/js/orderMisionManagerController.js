
app.controller(
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