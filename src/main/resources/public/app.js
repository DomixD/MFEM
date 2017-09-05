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

mfem.controller('Controller', function($scope, $http, $q) {

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
    $http.get(sessionStorage.getItem('classiFrame')+'/requirementList').then(function(response) {
        $scope.classiReqs=response.data._embedded.requirements;
    });
    $scope.show=function () {
        var getQuestions=[];
        var evaReqs = sessionStorage.getItem('evaReqs');
        var temp = new Array();
        temp = evaReqs.split(",");
        evaReqs = temp;
        for (var i = 0; i<evaReqs.length;i++) {
            getQuestions.push($http.get(evaReqs[i]+'/questionList'));
        }
        $q.all(getQuestions).then(function(questionArray){
            var questio = [];
            var metricLinks = [];
            var innerQuestio = [];
            for(var j = 0; j < questionArray.length;j++) {
                questio.push(questionArray[j].data._embedded.questions);
            }
            var questio2 = [];
            for (var k = 0; k<questio.length;k++) {
                innerQuestio = questio[k];
                for (var l = 0; l < innerQuestio.length; l++) {
                    metricLinks.push(innerQuestio[l]._links.metric.href);
                    questio2.push(innerQuestio[l]);
                }
            }
            $scope.questions = questio2;
            var getMetrics = [];
            for (var m = 0; m<metricLinks.length;m++) {
                getMetrics.push($http.get(metricLinks[m]));
            }
            $q.all(getMetrics).then(function (metricArray) {
               var answerList = [];
               for (var n = 0; n<metricArray.length;n++) {
                   answerList.push(metricArray[n].data._links.answerList.href);
               }
               var getAnswers = [];
               for (var o = 0; o<answerList.length;o++) {
                   getAnswers.push($http.get(answerList[o]));
               }
               $q.all(getAnswers).then(function (answerArray) {
                   var answ = [];
                   var innerAnswer = [];
                   for (var p = 0;p < answerArray.length; p++) {
                       answ.push(answerArray[p].data._embedded.answers);
                   }
                   $scope.answers = answ;
                   var res = [];
                   for (var s = 0; s<questio2.length;s++) {
                       var t = [];
                       t.push(questio2[s]);
                       t.push(answ[s]);
                       res.push(t);
                   }
                   $scope.result=res;
               });
            });
        });
    };


    //Anforderung mit zugehöriger Klassifizierung hinzufügen
    $scope.saveReq=function (cont) {
        var e = document.getElementById("classis");
        var classi = e.options[e.selectedIndex].value;
        data={content:cont};
        $http.post('http://localhost:8080/req',data).then(function (response){
            var req = response.data._links.self.href;
            $http.get(classi+'/requirementList').then(function (response) {
                var responseReq = response.data._embedded.requirements;
                var allReqs = [];
                for (var i = 0; i < responseReq.length; i++) {
                    allReqs.push(responseReq[i]._links.self.href);
                }
                allReqs.push(req);
                sessionStorage.setItem('req',req);
                $http.get(classi).then(function (response) {
                    var name = response.data.name;
                    var description = response.data.description;
                    data={name:name,
                        description:description,
                        requirementList:allReqs};
                    $http.patch(classi, data);
                });
            });
        });
    };

    //Anforderung ohne extra Angabe der Klassifizierung hinzufügen
    $scope.saveClassiReq=function (content) {
        data={content:content};
        $http.post('http://localhost:8080/req',data).then(function (response){
            var req = response.data._links.self.href;
            var classi = sessionStorage.getItem('classi');
            $http.get(classi+'/requirementList').then(function (response) {
                var responseReq = response.data._embedded.requirements;
                var allReqs = [];
                for (var i = 0; i < responseReq.length; i++) {
                    allReqs.push(responseReq[i]._links.self.href);
                }
                allReqs.push(req);
                sessionStorage.setItem('req',req);
                $http.get(classi).then(function (response) {
                    var name = response.data.name;
                    var description = response.data.description;
                    data={name:name,
                        description:description,
                        requirementList:allReqs};
                    $http.patch(classi, data);
                });
            });
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
        $http.post('http://localhost:8080/frame',data).then(function (response) {
            var frame = response.data._links.self.href;
            sessionStorage.setItem('frame', frame);
        });
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

    //Prioritäten zu Anforderungen speichern
    $scope.saveReqPrio=function () {
        var promiseArray = [];
        var classi = sessionStorage.getItem('classiFrame');
        $http.get(classi+'/requirementList').then(function(response) {
            var classReqs = response.data._embedded.requirements;
            var evaReqs = [];
            for (var j = 0; j<classReqs.length;j++) {
                evaReqs.push(classReqs[j]._links.self.href);
            }
            sessionStorage.setItem('evaReqs', evaReqs);
            var e = document.getElementsByName("opts");
            var frame=sessionStorage.getItem('frame');
            for(var i = 0; i <classReqs.length;i++) {
                var prio = e[i].options[e[i].selectedIndex].value;
                var req =classReqs[i]._links.self.href;
                data={
                    framework: frame,
                    requirement: req,
                    classification: classi,
                    priority:prio
                };
                promiseArray.push($http.post('http://localhost:8080/feva', data));
            }
            $q.all(promiseArray).then(function(dataArray){

            });
        });
    };
});