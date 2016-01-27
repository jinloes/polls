angular.module('polls.services', [])
    .factory('Poll', function($resource) {
        return $resource("/api/polls")
    });