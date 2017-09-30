var app = angular.module('app', ['ngRoute']);

app
    .config(function ($httpProvider, $routeProvider) {

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        $routeProvider
            .when('/brokers-market', {
                templateUrl: 'pages/brokers-market.html',
                controller: 'PriceController'
            })
            .when('/brokers-trades', {
                templateUrl: 'pages/brokers-trades.html',
                controller: 'PriceController'
            })
            .when('/traders-market', {
                templateUrl: 'pages/traders-market.html',
                controller: 'PriceController'
            })
            .when('/traders-trades', {
                templateUrl: 'pages/traders-trades.html',
                controller: 'PriceController'
            })

    })
    .service('SharedProperties', function () {
        var traderId;

        return {
            getTraderId: function () {
                return property;
            },
            setTraderId: function (value) {
                property = value;
            }
        };
    })
    .controller('LoginController',

        function ($http, SharedProperties) {

            var self = this;

            var authenticate = function (credentials, callback) {

                var headers = credentials ? {
                    authorization: "Basic "
                    + btoa(credentials.username + ":"
                        + credentials.password)
                } : {};

                self.user = '';
                $http.get('user', {
                    headers: headers
                }).then(function (response) {
                    var data = response.data;
                    if (data.name) {
                        self.authenticated = true;
                        self.user = data.name;
                        self.broker = data && data.roles && data.roles.indexOf("ROLE_ADMIN") > -1;
                        self.trader = data && data.roles && data.roles.indexOf("ROLE_USER") > -1;
                        SharedProperties.setTraderId(data.traderId)
                    } else {
                        self.authenticated = false;
                        self.broker = false;
                        self.trader = false;
                    }
                    callback && callback(true);
                }, function () {
                    self.authenticated = false;
                    callback && callback(false);
                });

            };

            authenticate();

            self.credentials = {};
            self.login = function () {
                authenticate(self.credentials, function (authenticated) {
                    self.authenticated = authenticated;
                    self.error = !authenticated;
                })
            };

            self.logout = function () {
                $http.post('logout', {}).finally(function () {
                    self.authenticated = false;
                    self.broker = false;
                    self.trader = false;
                });
            }

        })
    .controller('PriceController', function ($scope, $interval, PriceService, SharedProperties) {

        var interval;

        $scope.realTimeStateChanged = function (checked) {
            if (checked) {
                interval = $interval($scope.getAllPrices, 1000);
            }
            else {
                $interval.cancel(interval);
            }
        };

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
            PriceService.buy(priceRow.id, quantity, SharedProperties.getTraderId())
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
            PriceService.sell(priceRow.id, quantity, SharedProperties.getTraderId())
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

        $scope.getAllTrades = function () {
            PriceService.getAllTrades()
                .then(function success(response) {
                        $scope.trades = response.data.trades;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.message = '';
                        if (response.data.errorCode == "101") {
                            $scope.errorMessage = 'No trades found!';
                        }
                        else {
                            $scope.errorMessage = 'Error getting trades!';
                        }
                    });
        };

        $scope.getTradesByTraderId = function () {
            PriceService.getTradesByTraderId(SharedProperties.getTraderId())
                .then(function success(response) {
                        $scope.trades = response.data.trades;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.message = '';
                        if (response.data.errorCode == "101") {
                            $scope.errorMessage = 'No trades found!';
                        }
                        else {
                            $scope.errorMessage = 'Error getting trades!';
                        }
                    });
        };
    });

app.service('PriceService', function ($http) {

    this.getAllPrices = function getAllPrices() {
        return $http({
            method: 'GET',
            url: 'api/v1/prices'
        });
    };

    this.buy = function buy(id, quantity, traderId) {
        var tradeData = {
            securityId: id,
            traderId: traderId,
            quantity: quantity,
            side: "BUY"
        };
        return $http({
            method: 'POST',
            url: 'api/v1/trades',
            data: tradeData
        });
    };

    this.sell = function sell(id, quantity, traderId) {
        var tradeData = {
            securityId: id,
            traderId: traderId,
            quantity: quantity,
            side: "SELL"
        };
        return $http({
            method: 'POST',
            url: 'api/v1/trades',
            data: tradeData
        });
    };

    this.updateSpread = function updateSpread(id, spread) {
        var spreadData = {
            spread: spread
        };
        return $http({
            method: 'POST',
            url: 'api/v1/prices/' + id,
            data: spreadData
        });
    };

    this.getAllTrades = function getAllTrades() {
        return $http({
            method: 'GET',
            url: 'api/v1/trades'
        });
    };

    this.getTradesByTraderId = function getTradesByTraderId(traderId) {
        return $http({
            method: 'GET',
            url: 'api/v1/trades/' + traderId
        });
    };
});