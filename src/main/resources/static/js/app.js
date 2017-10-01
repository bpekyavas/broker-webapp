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
            .when('/traders-signup', {
                templateUrl: 'pages/traders-signup.html',
                controller: 'RegisterController'
            })

    })
    .service('SharedProperties', function () {
        var traderId;
        var signupClicked = false;

        return {
            getTraderId: function () {
                return traderId;
            },
            setTraderId: function (value) {
                traderId = value;
            },
            getSignUpClicked: function () {
                return signupClicked;
            },
            setSignUpClicked: function (value) {
                signupClicked = value;
            }
        };
    })
    .controller('LoginController',

        function ($http, SharedProperties, $location) {

            var self = this;

            self.signupClicked = SharedProperties.getSignUpClicked();

            self.signup = function () {
                self.signupClicked = true;
            };

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
                        SharedProperties.setTraderId(data.traderId);
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
                $location.path("/");
                authenticate(self.credentials, function (authenticated) {
                    self.authenticated = authenticated;
                    self.error = !authenticated;
                })
            };

            self.logout = function () {
                $location.path("/");
                $http.post('logout', {}).finally(function () {
                    self.authenticated = false;
                    self.broker = false;
                    self.trader = false;
                });
            }

        })
    .controller('PriceController', function ($scope, $interval, PriceService, SharedProperties) {

        var interval;
        $scope.quantities = [];


        $scope.realTimeStateChanged = function (checked) {
            if (checked) {
                interval = $interval($scope.getAllPrices, 5000);
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
                        $scope.errorMessage = response.data.errorMessage;
                    });
        };

        $scope.buy = function (priceRow, quantity) {
            PriceService.buy(priceRow.id, quantity, SharedProperties.getTraderId())
                .then(function success(response) {
                        $scope.message = 'Trade successful!';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
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
                        $scope.errorMessage = response.data.errorMessage;
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
                        $scope.errorMessage = response.data.errorMessage;
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
                        $scope.errorMessage = response.data.errorMessage;
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
                        $scope.errorMessage = response.data.errorMessage;
                    });
        };
    })
    .controller('RegisterController',

        function ($http, $window, $scope, RegisterService, SharedProperties) {

            var self = this;
            self.inputs = {};

            self.register = function () {
                RegisterService.register(self.inputs.username, self.inputs.password, self.inputs.name)
                    .then(function success(response) {
                            $scope.message = 'Registration successful!';
                            $scope.errorMessage = '';
                            SharedProperties.setSignUpClicked(false);
                            $window.location.href = "/";
                        },
                        function error(response) {
                            $scope.errorMessage = response.data.errorMessage;
                            $scope.message = '';
                        });
            };
        });

app.service('RegisterService', function ($http) {

    this.register = function (username, password, name) {
        var registerData = {
            userName: username,
            password: password,
            name: name,
        };
        return $http({
            method: 'POST',
            url: 'api/v1/register',
            data: registerData
        });
    }
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