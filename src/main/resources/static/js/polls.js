var app = angular.module('polls', ['ngRoute'])
        .config(function ($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'home.html',
                    controller: 'home'
                })
                .otherwise('/');
        })
        .controller('navigation', function ($rootScope, $scope, $http, $location, $route) {
            $scope.tab = function (route) {
                return $route.current && route === $route.current.controller;
            };
        })
        .controller('home', function ($scope, $http) {
        })
    ;