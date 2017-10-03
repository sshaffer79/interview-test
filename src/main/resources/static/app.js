
var fakePosModule = angular.module('fake-pos', ['ngMaterial'])

fakePosModule.controller('orderEntryScreen', function($scope, $http) {
    $scope.selectedOrderItem;
    // Load Order
    $http.get('/api/order').then(function(response) {
        $scope.order = response.data;
    })

    $scope.$on('itemSelected', function(event, message) {
        $scope.$broadcast('addToOrder', message);
        console.log("Select item " + message.id);
        $http.put('/api/order/item/'+message.id).then(function(response) {
            $scope.order = response.data;
            location.reload();
        });
    });

    $scope.orderItemSelect  = function(selectedOrderItem) {
        $scope.selectedOrderItem = selectedOrderItem;
    };

    $scope.itemVoided = function() {
        console.log("Void item " + $scope.selectedOrderItem.name);
        $http.delete('/api/order/item/'+$scope.selectedOrderItem.id).then(function(response) {
            $scope.order = response.data;
        })
    };

    $scope.clear = function() {
        $http.delete('/api/order/clear').then(function(response) {
            console.dir(response.data);
            $scope.order = response.data;
        })
    };

    $scope.paynow = function() {
        alert("Not yet implemented!");
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