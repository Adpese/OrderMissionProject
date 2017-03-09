


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