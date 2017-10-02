
var fakePosModule = angular.module('fake-pos', ['ngMaterial'])

fakePosModule.controller('orderEntryScreen', function($scope, $http) {

    $scope.$on('itemSelected', function(event, message) {
        console.log(event);
        console.log(message);
        $scope.$broadcast('addToOrder', message);
    });

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