var app = angular.module('app', []);

app.controller('PriceCtrl', ['$scope', 'PriceService', function ($scope, PriceService) {

    $scope.getAllPrices = function () {
        PriceService.getAllPrices()
            .then(function success(response) {
                    $scope.prices = response.data.prices;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    $scope.errorMessage = 'Error getting prices!';
                });
    };

    $scope.buy = function (priceRow, quantity) {
        PriceService.buy(priceRow.id, quantity)
            .then(function success(response) {
                    $scope.message = 'Trade successful!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error in matching!';
                    $scope.message = '';
                });

    };

    $scope.sell = function (priceRow, quantity) {
        PriceService.sell(priceRow.id, quantity)
            .then(function success(response) {
                    $scope.message = 'Trade successful!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error in matching!';
                    $scope.message = '';
                });

    };

    $scope.updateSpread = function (priceRow, spread) {
        PriceService.updateSpread(priceRow.id, spread)
            .then(function success(response) {
                    $scope.message = 'Spread updated!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error in spread update!';
                    $scope.message = '';
                });
    };

    // // Run function every second
    // setInterval($scope.getAllPrices, 10000);

}]);

app.service('PriceService', ['$http', function ($http) {

    this.getAllPrices = function getAllPrices() {
        return $http({
            method: 'GET',
            url: 'api/v1/prices'
        });
    };

    this.buy = function buy(id, quantity) {
        var tradeData = {
            securityId: id,
            traderId: 1,
            quantity: quantity,
            side: "BUY"
        };
        return $http({
            method: 'POST',
            url: 'api/v1/trades',
            data: tradeData
        });
    };

    this.sell = function sell(id, quantity) {
        var tradeData = {
            securityId: id,
            traderId: 1,
            quantity: quantity,
            side: "SELL"
        };
        return $http({
            method: 'POST',
            url: 'api/v1/trades',
            data: tradeData
        });
    }

    this.updateSpread = function updateSpread(id, spread) {
        var spreadData = {
            spread: spread,
        };
        return $http({
            method: 'POST',
            url: 'api/v1/prices/' + id,
            data: spreadData
        });
    }
}]);