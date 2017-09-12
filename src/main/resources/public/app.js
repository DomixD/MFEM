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
        .when("/questReq", {
            templateUrl : "questReq.html"
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
        })
        .when("/result", {
            templateUrl : "result.html"
        });
});


mfem.controller('Controller', function($scope, $http, $q, $rootScope, $location) {

    $scope.getReqs=function () {
        $http.get('http://localhost:8080/req').then(function(response) {
            $scope.req = response.data._embedded.requirements;
        });
    };

    $scope.getMetrics=function () {
        $http.get('http://localhost:8080/metric').then(function(response){
            $scope.metrics = response.data._embedded.metrics;
        });
    };

    $scope.getClassis=function () {
        $http.get('http://localhost:8080/classi').then(function(response){
            $scope.classis = response.data._embedded.classifications;
        });
    };

    $scope.getCats=function () {
        $http.get('http://localhost:8080/cat').then(function(response){
            $scope.catego = response.data._embedded.categories;
        });
    };

    var questi = [];

    //Fragen mit den zugehörigen Antwortmöglichkeiten anzeigen
    $scope.show=function () {
        var getQuestions=[];
        var classi = sessionStorage.getItem('classiFrame');
        $http.get(classi+'/requirementList').then(function (response) {
           var evaReqs = response.data._embedded.requirements;
            for (var i = 0; i<evaReqs.length;i++) {
                getQuestions.push($http.get(evaReqs[i]._links.questionList.href));
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
                        questi = res;
                    });
                });
            });
        });
    };

    //Ergebnis der Evaluation berechnen
    $scope.evaluation=function () {
        var getRequire = [];
        var promiseArray = [];
        var getEvaID = [];
        var e = document.getElementsByName("selectAns");
        var chosenAnswers = [];
        var frameID = sessionStorage.getItem("frame");
        frameID = frameID.substring(frameID.length-1);
        for(var i = 0; i <questi.length;i++) {
            chosenAnswers.push(e[i].options[e[i].selectedIndex].value);
        }
        var answerIndex = [];
        var questLink = [];
        for(var k = 0; k<chosenAnswers.length; k++) {
            questLink.push(questi[k][0]._links.self.href);
            answerIndex.push(k);
        }
                for (var m = 0; m < questLink.length; m++) {
                    var feva = sessionStorage.getItem('feva');
                    data = {
                        frameworkEvaluation: feva,
                        answer: chosenAnswers[answerIndex[m]],
                        question: questLink[m]
                    };
                    promiseArray.push($http.post('http://localhost:8080/result', data));
                }
        $q.all(promiseArray).then(function (resArray) {
            var frameID = sessionStorage.getItem("frame");
            frameID = frameID.substring(frameID.length-1);
            var classiID = sessionStorage.getItem("classiFrame");
            classiID = classiID.substring(classiID.length-1);
            $http.get("http://localhost:8080/getRes/" + frameID + "/" + classiID).then(function (response) {
                $rootScope.resultEva = response.data;
                sessionStorage.clear();
            });
        });
    };

    //Anforderung mit zugehöriger Klassifizierung hinzufügen
    $scope.saveReq=function (cont) {
        var e = document.getElementById("classis");
        var classi = e.options[e.selectedIndex].value;
        var e2 = document.getElementById("prio");
        var prio = e2.options[e2.selectedIndex].value;
        var e3 = document.getElementById("category");
        var cat = e3.options[e3.selectedIndex].value;
        data={content:cont,
        classi:classi,
        category:cat,
        priority:prio};
        $http.post('http://localhost:8080/req',data).then(function (response) {
            var req = response.data._links.self.href;
            sessionStorage.setItem('req',req);
        });
    };

    //Anforderung ohne extra Angabe der Klassifizierung hinzufügen
    $scope.saveClassiReq=function (content) {
        var classi = sessionStorage.getItem('classi');
        var e2 = document.getElementById("prio");
        var prio = e2.options[e2.selectedIndex].value;
        var e3 = document.getElementById("category");
        var cat = e3.options[e3.selectedIndex].value;
        data={content:content,classi:classi, category:cat ,priority:prio};
        $http.post('http://localhost:8080/req',data).then(function (response) {
            var req = response.data._links.self.href;
            sessionStorage.setItem('req',req);
        });
    };

    //Frage mit zugehöriger Metrik ohne extra Angabe der Anforderung speichern
    $scope.saveClassiQuest=function (question,view) {
        var e = document.getElementById("metrics");
        var metric = e.options[e.selectedIndex].value;
        var req = sessionStorage.getItem('req');
        data={question:question,
            require:req,
            metric: metric};
        $http.post('http://localhost:8080/quest',data);
        if(view=='main'){
            sessionStorage.clear();
        }
        $location.path(view);
        };



    // //Frage mit zugehöriger Metrik ohne extra Angabe der Anforderung speichern
    // $scope.saveReqQuest=function (question) {
    //     var e = document.getElementById("metri");
    //     var metric = e.options[e.selectedIndex].value;
    //     var req = sessionStorage.getItem('req');
    //     data={question:question,
    //         require:req,
    //         metric: metric};
    //     $http.post('http://localhost:8080/quest',data);
    // };

    //Frage mit zugehöriger Metrik und Anforderung speichern
    $scope.saveQuest=function (question) {
        var e = document.getElementById("metrics");
        var metric = e.options[e.selectedIndex].value;
        var e2 = document.getElementById("reqs");
        var req = e2.options[e2.selectedIndex].value;
        data={question:question,
              require:req,
              metric: metric};
        $http.post('http://localhost:8080/quest',data);
    };

    //Metrik mit Antwortmöglichkeiten speichern
    $scope.saveMetric=function (description, a1, a2, a3) {
        data4={description:description};
        $http.post('http://localhost:8080/metric',data4).then(function (response) {
            var metric = response.data._links.self.href;
            data1 = {content: a1, value: 1.0, metri: metric};
            data2 = {content: a2, value: 0.5, metri: metric};
            data3 = {content: a3, value: 0.0, metri: metric};
            $http.post('http://localhost:8080/answer', data1);
            $http.post('http://localhost:8080/answer', data2);
            $http.post('http://localhost:8080/answer', data3);
        });
    };

    //Framework speichern und die ausgewählte Klassifizierung mit dem Framework in FrameworkEvaluation speichern
    $scope.saveFrame=function (name, description) {
        var e = document.getElementById("classisFrame");
        var classi = e.options[e.selectedIndex].value;
        sessionStorage.setItem('classiFrame', classi);
        data={nameFW:name,
              descriptionFW:description};
        $http.post('http://localhost:8080/frame',data).then(function (response) {
            var frame = response.data._links.self.href;
            sessionStorage.setItem('frame',frame);
            data={framework:frame, classification: classi};
            $http.post('http://localhost:8080/feva', data).then(function (response) {
                var feva = response.data._links.self.href;
                sessionStorage.setItem('feva', feva);
            });
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

    //Funktionen für Antwortmöglichkeiten hinzufügen bzw. löschen
    $scope.choices = [{id: '1'}, {id: '2'}];

    $scope.addNewChoice = function() {
        var newItemNo = $scope.choices.length+1;
        $scope.choices.push({'id':newItemNo});
    };

    $scope.removeChoice = function() {
        var lastItem = $scope.choices.length-1;
        $scope.choices.splice(lastItem);
    };

});