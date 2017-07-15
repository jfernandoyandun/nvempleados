(function() {
    'use strict';

    angular
        .module('nvempleadosApp')
        .controller('EmpleadoDetailController', EmpleadoDetailController);

    EmpleadoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Empleado'];

    function EmpleadoDetailController($scope, $rootScope, $stateParams, previousState, entity, Empleado) {
        var vm = this;

        vm.empleado = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('nvempleadosApp:empleadoUpdate', function(event, result) {
            vm.empleado = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
