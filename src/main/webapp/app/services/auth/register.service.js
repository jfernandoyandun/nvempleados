(function () {
    'use strict';

    angular
        .module('nvempleadosApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
