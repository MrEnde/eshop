angular.module('app', []).controller('indexController', function ($scope, $http) {
    const mainPath = 'http://localhost:8189/';

    $scope.loadProducts = function () {
        $http({
            url: mainPath + 'products/',
            method: 'GET',
            params: {}
        }).then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: mainPath + 'products/page',
            method: 'GET',
            params: {
                'page': pageIndex
            }
        }).then(function (response) {
            $scope.products = response.data.content;
        });
    };

    $scope.showProductInfo = function (productIndex) {
        $http({
            url: mainPath + 'products/' + productIndex,
            method: 'GET'
        }).then(function (response) {
            alert(response.data.name);
        });
    };

    $scope.deleteProduct = function (productIndex) {
        console.log($scope.products)
        $http({
            url: mainPath + 'products/delete/' + productIndex,
            method: 'GET'
        }).then(function (response) {
            let index = -1;

            for (const product of $scope.products) {
                if (product.id === productIndex) {
                    index = $scope.products.indexOf(product)
                }
            }
            if (index !== -1) {
                $scope.products.splice(index, index + 1)
            }
        });
    }

    $scope.loadPage();
});