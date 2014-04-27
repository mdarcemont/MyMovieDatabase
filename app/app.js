var app = angular.module('movieStats', ['ngAnimate']);

app.controller('MovieStatsController', ['$scope', '$http',
    function ($scope, $http) {
        $scope.showDirector = function(directorId) {
            $http.get('/director/'+directorId).success(function(data) {
                $scope.director = data;
            });
            $http.get('/director/'+directorId+'/movies').success(function(data) {
                $scope.filmography = data;
            });
        }
        $scope.showMovie = function(movieId) {
            $http.get('/movie/'+movieId).success(function(data) {
                $scope.fullMovie = data;
            });
        }
    }]);