angular.module('polls', ['ngRoute', 'ngResource', 'smart-table', 'polls.services']);
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
            .otherwise('/');
    })
    .controller('navigation', function($scope, $location) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
    })
    .controller('home', function($scope, $http, $log, Poll) {
        $scope.displayPoll = []
        Poll.get().$promise.then(function(data) {
            $scope.polls = data["_embedded"]["polls"]
            return data["_embedded"]["polls"]
        })
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
