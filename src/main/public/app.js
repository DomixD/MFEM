var mfem = angular.module('demo', [])
    mfem.controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/req').
        then(function(response) {
            $scope.req = response.data._embedded.requirements;
        });
    });

mfem.controller('Hello', function($scope) {
    $scope.title1 = 'Anforderung';
    $scope.title4 = 'Warn';
    $scope.isDisabled = true;

    $scope.googleUrl = 'http://google.com';

});