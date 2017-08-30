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
        })
        .when("/main", {
            templateUrl : "main.html"
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
        //data={content:cont};
        var data = "{\"content\":\""+cont+"\"}";
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
                var result = inputs[i].value;
            }
        }
        data={content:result};
        $http.post('http://localhost:8080/questToReq',data)
    };
    $scope.saveMetric=function (description, a1, a2, a3) {
        var answer1;
        var answer2;
        var answer3;
        data={content:a1,value:1.0};
        $http.post('http://localhost:8080/answer',data).then(function(response){
            answer1 = response.data;
        });
        data={content:a2,value:0.5};
        $http.post('http://localhost:8080/answer',data).then(function(response){
            answer2 = response.data._links.self.href;
        });
        data={content:a3,value:0.0};
        $http.post('http://localhost:8080/answer',data).then(function(response){
            answer3 = response.data.content;
        });
        data={description:description,
              answerList: [/*answer1, answer2, answer3,*/ "http://localhost:8080/answer/1"]};
        /*data="{\"description\":\""+description+"\",\"answerList\":[\"http://localhost:8080/answer/1\"]}";*/
        $http.post('http://localhost:8080/metric',data);
    };
});