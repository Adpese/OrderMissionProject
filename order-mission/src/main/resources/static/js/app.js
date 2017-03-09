var taskManagerModule = angular.module('orderMisionManagerApp', ['ui.router']);

taskManagerModule.controller('orderMisionManagerController', function($scope, $http) {

	var urlBase = "";
	$http.defaults.headers.post["Content-Type"] = "application/json";
	$scope.status = "Abierta";
	$scope.date = new Date();
	$scope.patternNombre=/^([a-zA-ZÁÉÍÓÚñáéíóú]{1,}[\s]*)+$/ ;
	


	
	
	
	var tripObject = $scope.trajects = [
		
//		{
//			"Date" : '',
//			"Origin" : '',
//			"Destiny" : '',
//			"Departure" : '',
//			"Arrival" : '',
//			"Transport" : '',
//			"Company" : ''
//		}
		
	];
//	$scope.tripfields = ["Date", "Origin", "Destiny", "Departure", "Arrival", "Transport", "Company"];
//	var tripObject = $scope.trip = {
//			tripfields: {
//				"Date" : '',
//				"Origin" : '',
//				"Destiny" : '',
//				"Departure" : '',
//				"Arrival" : '',
//				"Transport" : '',
//				"Company" : ''
//			}
//	}

	

	

	  
	  $scope.addNewTraject = function() {
		  //console.log($scope.dateTraject);
		 
	    var newItem = $scope.trajects.length+1;
	    $scope.trajects.push({/*'traject':'traject'+newItem, 'Trip': tripObject*/});   
	    
	    console.log($scope.trajects);
	  };
	    
	  $scope.removeTraject = function() {
	    var lastItem = $scope.trajects.length-1;
	    $scope.trajects.splice(lastItem);
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
	
	//add a new colab
		$scope.addCollab = function addCollab() {

			if ($scope.project == null || $scope.agency.model == null || $scope.division.model == null || $scope.date == null) {
				
				swal("Error", "No se han introducido los campos necesarios para generar una nueva emisión", "error");
				//alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
			
			} else if(!$scope.collabFirstName){
				swal("Error", "Los datos de usuario son incorrectos.", "error");
			}
			else {
				$http.post(urlBase + '/missions', {
					collabFirstName : $scope.collabFirstName,
					date : $scope.date,
					project : $scope.project,
					agency : $scope.agency.model,
					division : $scope.division.model,
					status : $scope.status,
					itineraries : $scope.trajects			
				}

				).success(function(data, status, headers) {
					swal("Nueva emisión creada", "Se ha generado una nueva emisión con los datos introducidos", "success");
					//alert("Nueva orden añadida");
					var newColabUri = headers()["location"];
					console.log("Might be good to GET " + newColabUri + " and append the task.");
				});
				
			}

			
		};
	
	
	
//	$scope.addCollab = function addCollab() {

//		if ($scope.project == null || $scope.agency.model == null || $scope.division.model == null || $scope.date == null) {
//			
//			swal("Error", "No se han introducido los campos necesarios para generar una nueva emisión", "error");
//			//alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
//			
//		} else if(!$scope.collabFirstName){
//			swal("Error", "Los datos de usuario son incorrectos.", "error");
//		}
//		else {
//			$http.post(urlBase + '/missions', {
//				collabFirstName : $scope.collabFirstName,
//				date : $scope.date,
//				project : $scope.project,
//				agency : $scope.agency.model,
//				division : $scope.division.model,
//				status : $scope.status
//				
//			}
//
//			).success(function(data, status, headers) {
//				swal("Nueva emisión creada", "Se ha generado una nueva emisión con los datos introducidos", "success");
//				//alert("Nueva orden añadida");
//				var newColabUri = headers()["location"];
//				console.log("Might be good to GET " + newColabUri + " and append the task.");
//			});
//			
//		}
//				
//		var array = $scope.testArray = [{
//			collabFirstName : $scope.collabFirstName,
//			date : $scope.date,
//			project : $scope.project,
//			agency : $scope.agency.model,
//			division : $scope.division.model,
//			status : $scope.status,
//			itineraries : $scope.trajects
//			
//		}];
//		
//		
//		console.log(array);
//		
//		
//		
//	};
	
	
	
	

});


taskManagerModule.controller('collaCtrl', function ($scope, $http){

	  
	 
	  
    $http.get('/missions').success(function(data) {

      $scope.colla = data._embedded.missions;

      
      
      
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
         
         

         $http.put('/missions/' + x.id, data)
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
    
     $scope.OpenData = function (x) {
         // use $.param jQuery function to serialize data from JSON 
          var data = ({
         	collabFirstName : x.collabFirstName,
 				date : x.date,
 				project : x.project,
 				agency : x.agency,
 				division : x.division,
 				status : "Abierta"
          });
          
          

          $http.put('/missions/' + x.id, data)
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


taskManagerModule.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){



    $stateProvider
        .state('addcolb', {
            url: "/addcolb",
            templateUrl: "addcolb",
        }) 
        .state('listar', {
            url: "/listar",
            templateUrl: "listar",
        }) 

}]);

