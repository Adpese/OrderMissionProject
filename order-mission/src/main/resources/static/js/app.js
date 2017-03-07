var taskManagerModule = angular.module('orderMisionManagerApp', []);




























































































































taskManagerModule.controller('orderMisionManagerController', function($scope, $http) {

	var urlBase = "";
	$http.defaults.headers.post["Content-Type"] = "application/json";
	$scope.date = new Date();
	$scope.status = "Abierta";
	//add a new colab
	$scope.addCollab = function addCollab() {



		if ($scope.collabFirstName == null || $scope.status == null
			|| $scope.project == null || $scope.agency.model == null || $scope.division.model == null || $scope.date == null) {
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		} else {
			$http.post(urlBase + '/collaboraters', {
				collabFirstName : $scope.collabFirstName,
				date : $scope.date,
				project : $scope.project,
				agency : $scope.agency.model,
				division : $scope.division.model,
				status : $scope.status
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
			},
			{
				id : '3',
				name : 'Seleccione una agencia por favor'
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
			},
			{
				id : '3',
				name : 'Seleccione una división por favor'
			}
		]
	};
	
	$scope.emisiones1 = [
			
			 
				{
					date : '10/10/10',
					name : 'División A',
					status : 'Open',
					agency : 'Valencia', 
					proyect: 'Proyecto A'
				},
				{
					date : '10/10/10',
					name : 'División A',
					status : 'Close',
					agency : 'Valencia', 
					proyect: 'Proyecto B'
				},
				{
					date : '10/10/10',
					name : 'División A',
					status : 'Open',
					agency : 'Tenerife', 
					proyect: 'Proyecto C'
				},
				{
					date : '10/10/10',
					name : 'División A',
					status : 'Close',
					agency : 'Barcelona', 
					proyect: 'Proyecto C'
				}
			
		];
	

});


var countryApp = angular.module('collayApp', []);

      countryApp.controller('collaCtrl', function ($scope, $http){

        $http.get('/collaboraters').success(function(data) {

          $scope.colla = data._embedded.collaboraters;

          
          
        });

        
        $scope.SendData = function (x) {
            // use $.param jQuery function to serialize data from JSON 
             var data = ({
            	collabFirstName : x.collabFirstName,
 				date : x.date,
 				project : x.project,
 				agency : x.agency,
 				division : x.division,
 				status : "Cerrada"
             });

             $http.put('/collaboraters/' + x.id, data)
             .success(function (data, status, headers, config) {
                 //$scope.PostDataResponse = data;
             })
             .error(function (data, status, header, config) {
                 $scope.ResponseDetails = "Data: " + data +
                     "<hr />status: " + status +
                     "<hr />headers: " + header +
                     "<hr />config: " + config;
             });
         };
        
         
         $scope.reload = function()
         {
            location.reload(); 
         }

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
      
      
      
      