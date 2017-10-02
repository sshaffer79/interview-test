
var fakePosModule = angular.module('fake-pos', ['ngMaterial'])

fakePosModule.controller('orderEntryScreen', function($scope, $http) {
    // Load Order
    $http.get('/api/order').then(function(response) {
        $scope.order = response.data;
    })

    $scope.$on('itemSelected', function(event, message) {
        console.log(event);
        console.log(message);
        $scope.$broadcast('addToOrder', message);
    });

    $scope.void = function() {
        console.log("Void order")
        $http.delete('/api/order/clear').then(function(response) {
            $scope.order = response.data;
        })
    };

    $scope.clear = function() {
        console.log("Clear order")
        $http.delete('/api/order/clear').then(function(response) {
            $scope.order = response.data;
        })
    };

    $scope.paynow = function() {
        console.log("Pay now")
        $http.get('/api/order/pay').then(function(response) {
            $scope.order = response.data;
        })
    };
});

fakePosModule.controller('availableItems', function($scope, $http) {

    // Load available items
    $http.get('/api/items/getAllItems').then(function(response) {
        $scope.availableItems = response.data;
    })

    $scope.itemSelected = function(itemName) {
        $scope.$emit('itemSelected', itemName);
    };

});