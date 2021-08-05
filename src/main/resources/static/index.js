angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/api/v1';

    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                'page': pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.loadCart = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/cart',
            method: 'GET',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'GET',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function(productId) {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/cart/delete/' + productId,
            method: 'GET',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/cart/clear',
            method: 'GET',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            $scope.cart = null;
        });
    }

    $scope.loadOrders = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders',
            method: 'GET',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.createOrder = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders',
            method: 'POST',
            params: {
                'username': $localStorage.summerUser.username
            }
        }).then(function (response) {
            alert('Order created');
            $scope.loadCart();
            $scope.loadOrders();
        });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.summerUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.loadOrders();
                }
            }, function errorCallback(response) {
            });
    };

    $scope.clearUser = function () {
        delete $localStorage.summerUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.orders = null
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.summerUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadPage();

    if ($localStorage.summerUser) {
        let username = $localStorage.summerUser.username

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.summerUser.token;

        $scope.loadOrders(username);
        $scope.loadCart(username);
    }
});