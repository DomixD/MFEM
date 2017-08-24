var mfem = angular.module('demo', [])
    mfem.controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/req').
        then(function(response) {
            $scope.req = response.data._embedded.requirements;
        });
    });