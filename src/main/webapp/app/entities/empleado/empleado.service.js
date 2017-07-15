(function() {
    'use strict';
    angular
        .module('nvempleadosApp')
        .factory('Empleado', Empleado);

    Empleado.$inject = ['$resource', 'DateUtils'];

    function Empleado ($resource, DateUtils) {
        var resourceUrl =  'api/empleados/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.entryDate = DateUtils.convertLocalDateFromServer(data.entryDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.entryDate = DateUtils.convertLocalDateToServer(copy.entryDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.entryDate = DateUtils.convertLocalDateToServer(copy.entryDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
