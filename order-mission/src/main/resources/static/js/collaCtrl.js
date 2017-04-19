app.controller('collaCtrl', function($scope, $http, $cookies, $modal) {

	sessionStorage.removeItem('showTables');
	

	//	$http.get('/misiones').success(function(data) {
	//
	//		$scope.colla = data;
	//
	//	});
	
	
	// Generamos el modal que lanza el teamplate en cuestión
	
    $scope.showModal = function(x){
    	sessionStorage.setItem('updateMissionTrue', JSON.stringify(x));
        $modal.open({
              templateUrl: 'modalUpdate.html',
              controller: 'ModalDialogController', 
         })
        .result.then(
        );
    }


    // A continuación cargamos las misiones scorrespondientes para el usuario en cuestión, en base a las consultas hechas en el ServicesImp.java
    
    switch($cookies.role) {
    case "Assistant":
    	$http.get('/assistant/' + $cookies.agency+ '/'+$cookies.username).success(function(data){
			$scope.colla = data;
		});
        break;
    case "Director":
    	$http.get('/director/' + $cookies.agency).success(function(data){
			$scope.colla = data;
		});
        break;
    case "Jefe":
    	$http.get('/jefe/' + $cookies.username).success(function(data){
			$scope.colla = data;
		});
        break;
    default:
    	$http.get('/colaborador/' + $cookies.username).success(function(data){
			$scope.colla = data;
		});
	    break;
}
    
    
    
//	if ($cookies.role == 'Jefe') {
//		$http.get('/jefe/' + $cookies.username).success(function(data) {
//
//			$scope.colla = data;
//			console.log($scope.colla);
//
//		});
//	} else if ($cookies.role == 'Director') {
//		$http.get('/director/' + $cookies.agency).success(function(data) {
//
//			$scope.colla = data;
//			console.log($scope.colla);
//
//		});
//	} else if ($cookies.role == 'Assistant') {
//		$http.get('/director/' + $cookies.agency).success(function(data) {
//
//			$scope.colla = data;
//			console.log($scope.colla);
//
//		});
//	} 
//	else if ($cookies.role != 'Director' && $cookies.role != 'Assistant'){
//		$http.get('/colaborador/' + $cookies.username).success(function(data) {
//
//			$scope.colla = data;
//			console.log($scope.colla);
//		});
//	}
//	else {
//		$http.get('/misiones').success(function(data) {
//
//			$scope.colla = data;
//		});
//		console.log('Ha entrado en la parte de NO jefe');
//	}
	
	

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
			  swal("Éxito!", "Se ha revertido la orden correctamente", "success");
			

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
		
		var showTable = sessionStorage.getItem('showTables');
		
		
		if (showTable != x){
		sessionStorage.setItem('showTables', x);
		

		$http.get('/busquedaMission/'+ x).success(function(data) {

			$scope.collaitin = data.itineraries;
			$scope.collarents = data.rents;
			$scope.collaccom= data.accommodations;

		});
		}else {sessionStorage.setItem('showTables', -1);}
		
	}
	
	$scope.callShowTables = function(){  // Funcion para cargar la id, que en la lista me permite comprobar qué mision se desplega
		
		var showTable = sessionStorage.getItem('showTables');
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
		
		$scope.buttonselect = 'Cerrar';
	} else {
		$scope.buttonselect = 'Validar';
	}
	
	
});