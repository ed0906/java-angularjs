(function () {
    'use strict';

    angular.module('app').directive('header', function () {
        return {
            restrict: 'E',
            scope: {
            },
            templateUrl: 'js/header/Header.html'
        };
    });

})();