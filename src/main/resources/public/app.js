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
        })
        .when("/classi", {
            templateUrl : "classi.html"
        })
        .when("/classiQuest", {
            templateUrl : "classiQuest.html"
        })
        .when("/classiReq", {
            templateUrl : "classiReq.html"
        });
});

mfem.controller('Controller', function($scope, $http) {
    var resultCheckReq = [];

    $http.get('http://localhost:8080/req').
    then(function(response) {
        $scope.req = response.data._embedded.requirements;
    });
    $http.get('http://localhost:8080/metric').then(function(response){
        $scope.metrics = response.data._embedded.metrics;
    });
    $http.get('http://localhost:8080/classi').then(function(response){
        $scope.classis = response.data._embedded.classifications;
    });
    $http.get('http://localhost:8080/req').
    then(function(response) {
        var resultReqs =[];
        var reqs = response.data._embedded.requirements;
        var classis = [];
        var getClassis = function (i) {
            $http.get(reqs[i]._links.classification.href).then(function (response) {
                classis.push(response.data._links.self.href);
            })
        };
        for (var i = 0; i < reqs.length; i++) {
            getClassis(i);
        }
        //Callback
        for (var j = 0; j < reqs.length; j++) {
            if(classis[j]==sessionStorage.getItem('classiFrame')) {
                resultReqs.push('a'+classis[j] + ' '+j);
            }
            else {
                resultReqs.push('b'+classis[j] + ' '+j);
            }
        }
        $scope.classiReqs = resultReqs;
    });

    //Anforderung mit zugehöriger Klassifizierung hinzufügen
    $scope.saveReq=function (cont) {
        var e = document.getElementById("classis");
        var classi = e.options[e.selectedIndex].value;
        data={content:cont,
              classification:classi};
        $http.post('http://localhost:8080/req',data).then(function (response){
            var req = response.data._links.self.href;
            sessionStorage.setItem('req', req);
        });
    };

    //Anforderung ohne extra Angabe der Klassifizierung hinzufügen
    $scope.saveClassiReq=function (content) {
        var classi = sessionStorage.getItem('classi');
        data={content:content,
            classification:classi};
        $http.post('http://localhost:8080/req',data).then(function (response){
            var req = response.data._links.self.href;
            sessionStorage.setItem('req', req);
        });
    };

    //Frage mit zugehöriger Metrik ohne extra Angabe der Anforderung speichern
    $scope.saveClassiQuest=function (question) {
        var e = document.getElementById("metrics");
        var metric = e.options[e.selectedIndex].value;
        data={question:question,
            metric: metric};
        $http.post('http://localhost:8080/quest',data).then(function(response){
            var quest = response.data._links.self.href;
            var req = sessionStorage.getItem('req');
            $http.get(req+'/questionList').then(function (response) {
                var responseQuest = response.data._embedded.questions;
                var allQuests = [];
                for (var i = 0; i < responseQuest.length; i++) {
                    allQuests.push(responseQuest[i]._links.self.href);
                }
                allQuests.push(quest);
                $http.get(req).then(function (response) {
                    var content = response.data.content;
                    data={content:content,
                        questionList:allQuests};
                    $http.patch(req, data);
                });
            });
        });
    };

    //Frage mit zugehöriger Metrik ohne extra Angabe der Anforderung speichern
    $scope.saveReqQuest=function (question) {
        var e = document.getElementById("metri");
        var metric = e.options[e.selectedIndex].value;
        data={question:question,
            metric: metric};
        $http.post('http://localhost:8080/quest',data).then(function(response){
            var quest = response.data._links.self.href;
            var req = sessionStorage.getItem('req');
            $http.get(req+'/questionList').then(function (response) {
                var responseQuest = response.data._embedded.questions;
                var allQuests = [];
                for (var i = 0; i < responseQuest.length; i++) {
                    allQuests.push(responseQuest[i]._links.self.href);
                }
                allQuests.push(quest);
                $http.get(req).then(function (response) {
                    var content = response.data.content;
                    data={content:content,
                        questionList:allQuests};
                    $http.patch(req, data);
                });
            });
        });
    };

    //Frage mit zugehöriger Metrik und Anforderung speichern
    $scope.saveQuest=function (question) {
        var e = document.getElementById("metrics");
        var metric = e.options[e.selectedIndex].value;
        var e2 = document.getElementById("reqs");
        var req = e2.options[e2.selectedIndex].value;
        data={question:question,
              metric: metric};
        $http.post('http://localhost:8080/quest',data).then(function(response){
            var quest = response.data._links.self.href;
            $http.get(req+'/questionList').then(function (response) {
                var responseQuest = response.data._embedded.questions;
                var allQuests = [];
                for (var i = 0; i < responseQuest.length; i++) {
                    allQuests.push(responseQuest[i]._links.self.href);
                }
                allQuests.push(quest);
                $http.get(req).then(function (response) {
                    var content = response.data.content;
                    data={content:content,
                        questionList:allQuests};
                    $http.patch(req, data);
                });
            });
        });
    };

    //Fragen zu ausgewählten Metriken anzeigen
    $scope.saveReqs=function () {
        var inputs = document.getElementsByName("reqR");
        for (var i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) {
                resultCheckReq.push(inputs[i].value);
            }
            resultCheckReq.push("hallo");
        }
    };
    $scope.showQuests=function () {
        var quests = [];
        var metricLinks = [];
        // for (var i = 0; i < resultCheckReq.length; i++) {
            $http.get(resultCheckReq[0]+"/questionList").
            then(function(response) {
                var questList =(response.data._embedded.questions);
                for(var j = 0; j < questList.length; j++){
                    quests.push(questList[j].question)
                }
            });
        // }
        $scope.questio = resultCheckReq;
    };

    //Metrik mit Antwortmöglichkeiten speichern
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
        var e = document.getElementById("classisFrame");
        var classi = e.options[e.selectedIndex].value;
        sessionStorage.setItem('classiFrame', classi);
        data={nameFW:name,
              descriptionFW:description};
        $http.post('http://localhost:8080/frame',data);
    };

    //Klassifizierung speichern
    $scope.saveClassi=function (name, description) {
        data={name:name,
            description:description};
        $http.post('http://localhost:8080/classi',data).then(function (response) {
            var classi = response.data._links.self.href;
            sessionStorage.setItem('classi', classi);
        });
    };
});