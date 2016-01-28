angular.module('polls.services', [])
    .factory('Poll', function($resource) {
        return $resource("/api/v1/polls")
    });