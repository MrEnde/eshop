angular.module('app', []).controller('indexController', function ($scope, $http) {
    const mainPath = 'http://localhost:8189/api/v1';

    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: mainPath + '/products',
            method: 'GET',
            params: {
                'page': pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
            console.log(response.data);
        });
    };

    $scope.loadCart = function () {
        $http({
            url: mainPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http({
            url: mainPath + '/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId) {
        $http({
            url: mainPath + '/cart/delete/' + productId,
            method: 'DELETE'
        }).then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.clearCart = function () {
        $http({
            url: mainPath + '/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = null;
        });
    }

    $scope.loadOrders = function () {
        $http({
            url: mainPath + '/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.createOrder = function () {
        $http({
            url: mainPath + '/orders',
            method: 'POST'
        }).then(function (response) {
            alert('Заказ создан');
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

    $scope.loadPage();
    $scope.loadCart();
    $scope.loadOrders();
});