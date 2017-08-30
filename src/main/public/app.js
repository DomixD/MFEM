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
    var quest;
    //var resultCheckReq = [];
    $scope.resultCheckReq = [];
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
        var e = document.getElementById("metrics");
        var metric = e.options[e.selectedIndex].value;
        data={question:question,
              metric: metric};
        $http.post('http://localhost:8080/quest',data).then(function(response){
            quest = response.data._links.self.href;
        });
    };
    $scope.checkReqSaveQuest=function() {
        var result;
        var inputs = document.getElementsByName("Requirement");
        for (var i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) {
                result = inputs[i].value;
            }
        }
        //TODO:Rene fragen
    };
    $scope.checkReqEvaFrame=function () {
        var inputs = document.getElementsByName("reqR");
        for (var i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) {
                resultCheckReq += inputs[i].value;
            }
        }
    };
    $scope.showQuests=function () {
        var quests = [];
        var metricLinks = [];
        for (var i = 0; i < resultCheckReq.length; i++) {
            $http.get(resultCheckReq[i]+"/questionList").
            then(function(response) {
                quests += response.data._embedded.questions.question;
                metricLinks += response.data._embedded.questions._links.metric.href;
            });
        }
        $scope.questions = quests;
    };
    $scope.saveMetric=function (description, a1, a2, a3) {
        var answer1;
        var answer2;
        var answer3;
        data1={content:a1,value:1.0};
        data2={content:a2,value:0.5};
        data3={content:a3,value:0.0};
        $http.post('http://localhost:8080/answer',data1).then(function(response){
            answer1 = response.data._links.self.href;
            $http.post('http://localhost:8080/answer',data2).then(function(response){
                answer2 = response.data._links.self.href;
                $http.post('http://localhost:8080/answer',data3).then(function(response){
                    answer3 = response.data._links.self.href;
                    data4={description:description,
                        answerList: [answer1, answer2, answer3]};
                    $http.post('http://localhost:8080/metric',data4);
                });
            });
        });
    };
    $scope.saveFrame=function (name, description) {
        data={nameFW:name,
              descriptionFW:description};
        $http.post('http://localhost:8080/frame',data);
    }
});