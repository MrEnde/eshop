angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET',
            params: {
                'address': $scope.address,
                'phone': $scope.phone
            }
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.loadOrders();
});