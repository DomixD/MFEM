var mfem = angular.module("demo", ["ngRoute"]);
mfem.config(function($routeProvider) {
    $routeProvider

        .when("/", {
            templateUrl : "main.html"
        })
        .when("/frame", {
            templateUrl : "frame.html"
        })
        .when("/req", {
            templateUrl : "req.html"
        })
        .when("/quest", {
            templateUrl : "quest.html"
        })
        .when("/evaReq", {
            templateUrl : "evaReq.html"
        });
});

mfem.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/req').
    then(function(response) {
        $scope.req = response.data._embedded.requirements;
    });
    $scope.saveReq=function (cont) {
        data={content:cont};
        $http.post('http://localhost:8080/req',data);
    };

});