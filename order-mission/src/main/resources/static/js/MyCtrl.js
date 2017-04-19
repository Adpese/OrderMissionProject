//app.controller("MyCtrl", function($scope, $modal) {
//    $scope.showModal = function(){
//        $modal.open({
//              templateUrl: 'cosarara.html',
//              controller: 'ModalDialogController', 
//         })
//        .result.then(
//            function () {
//                alert("OK");
//            }, 
//            function () {
//                alert("Cancel");
//            }
//        );
//    }
//})

app.controller("ModalDialogController", function ($scope, $modalInstance) {
  $scope.ok = function () {
    $modalInstance.close();
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});