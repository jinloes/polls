angular.module('polls.services', [])
    .constant("ENDPOINT_HOST", "http://localhost:8080/api/v1")
    .service('Poll', function($resource, ENDPOINT_HOST) {
        var currentPolls = [];
        var resource = $resource(ENDPOINT_HOST + "/polls")
        this.get = function(callback) {
            var results = resource.get();
            currentPolls = results.$promise.then(function(data) {
                var response = data["_embedded"]["polls"]
                callback(response)
                return response
            })
        }

        this.save = function(poll, callback) {
            resource.save(poll, callback)
        }
    });