var taskManagerModule = angular.module('orderMisionManagerApp', ['ui.router']);

taskManagerModule.controller('orderMisionManagerController', function($scope, $http) {

	var urlBase = "";
	$http.defaults.headers.post["Content-Type"] = "application/json";
	$scope.status = "Abierta";
	$scope.date = new Date();
	$scope.patternNombre=/^([a-zA-ZÁÉÍÓÚñáéíóú]{1,}[\s]*)+$/ ;
	$scope.patternNombre=/^[0-9]+([,.][0-9]+)?$/; ;
	
	$scope.trajects = [];
	$scope.addNewTraject = function() {
		//console.log($scope.dateTraject);


		$scope.trajects.push({ /*'traject':'traject'+newItem, 'Trip': tripObject*/ });
	};

	$scope.removeTraject = function() {
		var lastItem = $scope.trajects.length - 1;
		$scope.trajects.splice(lastItem);
	};

	$scope.rents = [];
	$scope.addNewRent = function() {
		
		$scope.rents.push({});
	};
	
	$scope.removeRent = function() {
		var lastRent = $scope.rents.length -1;
		$scope.rents.splice(lastRent);
	};
	
	$scope.accommodations = [];
	$scope.addNewAccommodation = function() {
		
		$scope.accommodations.push({});
	};
	
	$scope.removeAccommodation = function() {
		var lastAccommodation = $scope.accommodations.length -1;
		$scope.accommodations.splice(lastAccommodation);
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
	
	$scope.transport = {
            model : null,
            availableOptions : [
                   {
                          id : '1',
                          name : 'Avión'
                   },
                   
                   {
                          id : '2',
                          name : 'Tren'
                   },
                   
                   {
                          id : '3',
                          name : 'Seleccione medio de transporte'
                   }
                   
            ]
     };
	
	//add a new colab
		$scope.addCollab = function addCollab() {

			if ($scope.project == null || $scope.agency.model == null || $scope.division.model == null || $scope.date == null || $scope.project == "") {
				
				
				
				swal("Error", "No se han introducido los campos necesarios para generar una nueva misión", "error");
				//alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
			
			} else if(!$scope.collabFirstName){
				swal("Error", "Los datos de usuario son incorrectos.", "error");
			}
			else {
				
				for(var i = 0; i < $scope.trajects.length; i++){
					if($scope.trajects[i].arrivalHour == null || $scope.trajects[i].company == null ||
							$scope.trajects[i].date == null || $scope.trajects[i].departureHour == null ||
							$scope.trajects[i].destination == null || $scope.trajects[i].origin == null ||
							$scope.trajects[i].price == null || $scope.trajects[i].transport == null ||
							$scope.trajects[i].arrivalHour == '' || $scope.trajects[i].company == '' ||
							$scope.trajects[i].date == '' || $scope.trajects[i].departureHour == '' ||
							$scope.trajects[i].destination == ''|| $scope.trajects[i].origin == '' ||
							$scope.trajects[i].price == '' || $scope.trajects[i].transport == ''){
						swal("Error", "No se han introducido los campos necesarios para generar una nueva misión", "error");
						return 0;
					}
				}
				
				$http.post(urlBase + '/missionSave', {
					collabFirstName : $scope.collabFirstName,
					date : $scope.date,
					project : $scope.project,
					agency : $scope.agency.model,
					division : $scope.division.model,
					status : $scope.status,
					itineraries : $scope.trajects,	
					rents : $scope.rents,
					accommodations : $scope.accommodations
				}

				).success(function(data, status, headers) {
					swal("Nueva emisión creada", "Se ha generado una nueva misión con los datos introducidos", "success");
					//alert("Nueva orden añadida");
					var newColabUri = headers()["location"];
					console.log("Might be good to GET " + newColabUri + " and append the task.");
				});
				
			}

			
		};

});


taskManagerModule.controller('collaCtrl', function ($scope, $http){
  
    $http.get('/missions').success(function(data) {

      $scope.colla = data._embedded.missions;
      
    });
    
    
    $scope.SendData = function (x) {
        // use $.param jQuery function to serialize data from JSON 
    	
    	if(x.status === "Abierta"){
    		
    		console.log("Entra cerrada");
    		
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
            	x.status = "Cerrada";
            	$scope.buttonState = "Abrir";
            })
            .error(function (data, status, header, config) {
                $scope.ResponseDetails = "Data: " + data +
                    "<hr />status: " + status +
                    "<hr />headers: " + header +
                    "<hr />config: " + config;
            });
    		
    	} else if(x.status === "Cerrada") {
    		
    		console.log("Entra Abierrta");
    		
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
    	        	  x.status = "Abierta";
    	        	  $scope.buttonState = "Cerrar";
    	          })
    	          .error(function (data, status, header, config) {
    	              $scope.ResponseDetails = "Data: " + data +
    	                  "<hr />status: " + status +
    	                  "<hr />headers: " + header +
    	                  "<hr />config: " + config;
    	          });
    		
    	}
         
     };  
});


taskManagerModule.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise("/addcolb");

    $stateProvider
        .state('addcolb', {
            url: "/addcolb",
            templateUrl: "addcolb",
        }) 
        .state('listar', {
            url: "/listar",
            templateUrl: "listar",
        }) 
        .state('listar_close', {
            url: "/listar_close",
            templateUrl: "listar_close",
        }) 


}]);

