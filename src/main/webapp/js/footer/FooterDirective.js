(function () {
    'use strict';

    angular.module('app').directive('footer', function () {
        return {
            restrict: 'E',
            scope: {
            },
            templateUrl: 'js/footer/Footer.html'
        };
    });

})();