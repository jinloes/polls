var app = angular.module('polls', ['ngRoute'])
        .config(function ($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'home.html',
                    controller: 'home'
                })
                .when('/create_poll', {
                    templateUrl: 'create_poll.html'
                })
                .otherwise('/');
        })
        .controller('navigation', function($rootScope, $scope, $http, $location, $route) {
            $scope.tab = function (route) {
                return $route.current && route === $route.current.controller;
            };
        })
        .controller('home', function($scope, $http) {
        })
        .controller('CreatePollController', function($scope, $window, $http) {
            $scope.create = function(poll) {
                $window.alert(poll.name);
            }
        })
    ;