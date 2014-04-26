var app = angular.module('movieStats', []);

app.controller('MovieStatsController', ['$scope', '$http',
    function ($scope, $http) {
        $scope.showDirector = function(directorId) {
            $http.get('/director/'+directorId).success(function(data) {
                $scope.director = data;
            });
        }
    }]);