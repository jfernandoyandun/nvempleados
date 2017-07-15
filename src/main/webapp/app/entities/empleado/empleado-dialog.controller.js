(function() {
    'use strict';

    angular
        .module('nvempleadosApp')
        .controller('EmpleadoDialogController', EmpleadoDialogController);

    EmpleadoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Empleado'];

    function EmpleadoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Empleado) {
        var vm = this;

        vm.empleado = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.empleado.id !== null) {
                Empleado.update(vm.empleado, onSaveSuccess, onSaveError);
            } else {
                Empleado.save(vm.empleado, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('nvempleadosApp:empleadoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.entryDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
