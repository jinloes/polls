angular.module('polls', ['ngRoute', 'ngResource', 'smart-table', 'polls.services', 'ui.bootstrap.datetimepicker']);
angular.module('polls')
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home.html',
                controller: 'home'
            })
            .when('/create_poll', {
                templateUrl: 'create_poll.html'
            })
            .when('/polls/:id', {
                templateUrl: "poll.html"
            })
            .otherwise('/');
    })
    .controller('navigation', function($scope, $location) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
    })
    .controller('home', function($scope, $http, $log, $location, Poll) {
        $scope.displayPoll = []
        $scope.polls = Poll.get(function(data) {
            $scope.polls = data;
        })
        $scope.viewPoll = function(poll) {
            $scope.currentPoll = poll
        }
    })
    .controller('CreatePollController', function($scope, $window, $location, Poll) {
        $scope.poll = { choices: []}
        $scope.addPollChoice = function() {
            $scope.poll.choices.push('');
        }
        $scope.create = function(poll) {
            Poll.save(poll, function() {
                $location.path("/")
            },
            function(error) {
            })
        }
    });
