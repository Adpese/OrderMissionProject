var taskManagerModule = angular.module('orderMisionManagerApp', []);

taskManagerModule.controller('orderMisionManagerController', function($scope, $http) {

	var urlBase = "";
	$http.defaults.headers.post["Content-Type"] = "application/json";
	$scope.date = new Date();

	//add a new colab
	$scope.addCollab = function addCollab() {
		
		
		
		if ($scope.collabName == null || $scope.collab_id == null || $scope.collabFirstName == null
			|| $scope.project == null || $scope.agency == null || $scope.division == null  || $scope.date == null) {
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		} else {
			$http.post(urlBase + '/collaboraters', {
				collab_id : $scope.collab_id,
				collabName : $scope.collabName,
				collabFirstName : $scope.collabFirstName,
				date : $scope.date,
				project : $scope.project,
				agency : $scope.agency.model,
				division : $scope.division
			}

			).success(function(data, status, headers) {
				alert("Colab added");
				var newColabUri = headers()["location"];
				console.log("Might be good to GET " + newColabUri + " and append the task.");
			});
		}
	};
	
	 $scope.agency = {
	  model: null,
    availableOptions: [
      {id: '1', name: 'Valencia'},
      {id: '2', name: 'Alicante'},
      {id: '3', name: 'MADRID'}
    ]
   };





});
