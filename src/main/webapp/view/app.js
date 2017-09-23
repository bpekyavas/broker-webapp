var app = angular.module('app',[]);

app.controller('PriceCtrl', ['$scope','PriceService', function ($scope,PriceService) {
    
    $scope.getAllPrices = function () {
        PriceService.getAllPrices()
          .then(function success(response){
              $scope.prices = response.data.prices;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting prices!';
          });
    }

}]);

app.service('PriceService',['$http', function ($http) {

    this.getAllPrices = function getAllPrices(){
        return $http({
          method: 'GET',
          url: 'api/v1/prices'
        });
    }

}]);