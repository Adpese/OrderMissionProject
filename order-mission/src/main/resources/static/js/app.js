var taskManagerModule = angular.module('orderMisionManagerApp', ['ui.router']);

taskManagerModule.controller('orderMisionManagerController', function($scope, $http) {

	var urlBase = "";
	$http.defaults.headers.post["Content-Type"] = "application/json";
	$scope.date = new Date();

	//add a new colab
	$scope.addCollab = function addCollab() {



		if ($scope.collabName == null || $scope.collab_id == null || $scope.collabFirstName == null
			|| $scope.project == null || $scope.agency.model == null || $scope.division.model == null || $scope.date == null) {
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		} else {
			$http.post(urlBase + '/collaboraters', {
				collab_id : $scope.collab_id,
				collabName : $scope.collabName,
				collabFirstName : $scope.collabFirstName,
				date : $scope.date,
				project : $scope.project,
				agency : $scope.agency.model,
				division : $scope.division.model
			}

			).success(function(data, status, headers) {
				alert("Colab added");
				var newColabUri = headers()["location"];
				console.log("Might be good to GET " + newColabUri + " and append the task.");
			});
		}
	};

	$scope.agency = {
		model : null,
		availableOptions : [
			{
				id : '1',
				name : 'Valencia'
			},
			{
				id : '2',
				name : 'Alicante'
			},
			{
				id : '3',
				name : 'Madrid'
			},
			{
				id : '3',
				name : 'Tenerife'
			},
			{
				id : '3',
				name : 'Barcelona'
			}
		]
	};

	$scope.division = {
		model : null,
		availableOptions : [
			{
				id : '1',
				name : 'División A'
			},
			{
				id : '2',
				name : 'División B'
			},
			{
				id : '3',
				name : 'División C'
			}
		]
	};

});




taskManagerModule.config(function($stateProvider, $urlRouterProvider) {



    $stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "indexgg.html"
        })
        .state('form', {
            url: "/form",
            templateUrl: "indexgg.html",
        }) 
        .state('ex', {
            url: "/ex",
            templateUrl: "menu.html",
        }) 

});
