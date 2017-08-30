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
        })
        .when("/checkReq", {
            templateUrl : "checkReq.html"
        })
        .when("/questReq", {
            templateUrl : "questReq.html"
        })
        .when("/checkReqQuest", {
            templateUrl : "checkReqQuest.html"
        })
        .when("/checkQuest", {
            templateUrl : "checkQuest.html"
        })
        .when("/metric", {
            templateUrl : "metric.html"
        });
});

mfem.controller('Controller', function($scope, $http) {
    $http.get('http://localhost:8080/req').
    then(function(response) {
        $scope.req = response.data._embedded.requirements;
    });
    $http.get('http://localhost:8080/metric').then(function(response){
        $scope.metrics = response.data._embedded.metrics;
    });
    $scope.saveReq=function (cont) {
        data={content:cont};
        $http.post('http://localhost:8080/req',data);
    };
    $scope.saveQuest=function (question) {
        data={question:question};
        $http.post('http://localhost:8080/quest',data)
    };
    $scope.checkSelectedReq=function() {
        var inputs = document.getElementsByName("Requirement");
        for (var i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) {
                result = inputs[i].value;
            }
        }
        data={content:result};
        $http.post('http://localhost:8080/questToReq',data)
    };
    $scope.saveMetric=function(description, a1, a2, a3) {
        data={description:description}
        $http.post('http://localhost:8080/metric', data)
    }
});