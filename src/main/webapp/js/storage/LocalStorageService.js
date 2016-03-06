(function () {
    'use strict';

    angular.module('app').service('LocalStorageService', service);

    service.$inject = [];

    function service() {
        return {
            get: function(key) {
                return sessionStorage.getItem(key)
            },
            getObject: function(key) {
                return JSON.parse(sessionStorage.getItem(key));
            },
            set: function(key, value) {
                sessionStorage.setItem(key, value);
            },
            setObject: function(key, value) {
                sessionStorage.setItem(key, JSON.stringify(value));
            },
            has: function(key) {
                return get(key) != null;
            },
            clear: function(key) {
                if(key) {
                    sessionStorage.removeItem(key);
                } else {
                    sessionStorage.clear();
                }
            }
        };
    }
})();