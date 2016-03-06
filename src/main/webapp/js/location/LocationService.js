(function () {
    'use strict';

    angular.module('app').service('LocationService',  service);

    service.$inject = ['$location', '$route'];

    function service($location, $route) {

        return {
            getPath: function() {
                return $location.path();
            },
            refresh: function() {
                $route.reload();
            }
        };
    }

})();