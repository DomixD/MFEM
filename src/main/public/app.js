var mfem = angular.module("demo", ["ngRoute"]);
mfem.config(function($routeProvider) {
    $routeProvider

        .when("/", {
            templateUrl : "main.html"
        })
        .when("/frame", {
            templateUrl : "frame.html"
        });
});

mfem.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/req').
    then(function(response) {
        $scope.req = response.data._embedded.requirements;
    });
});