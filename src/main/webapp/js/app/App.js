(function() {
    'use strict';

    var app = angular.module('app', [
        'ngRoute'
    ]);

    app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/', {
                    templateUrl: 'js/home/Home.html',
                    controller: 'HomeController'
                }).
                otherwise({
                    redirectTo: '/'
                });
        }]);

    app.constant("HOST", location.protocol + "//" + location.host);
})();