'use strict';

var app = angular.module('main', [
    'ngAnimate',
    'ui.router',
    'ui.bootstrap'
]);

app.run(['$rootScope', '$state',
    function ($rootScope, $state) {
        $rootScope.$state = $state;

        $rootScope.activeTab;

        $rootScope.username;
        $rootScope.token;
    }
]);

app.config(
    function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('payments', {
                url: '/',
                templateUrl: 'app/view/payments.html',
                controller: 'PaymentsCtrl'
            })
            .state('customers', {
                url: '/customers',
                templateUrl: 'app/view/customers.html',
                controller: 'CustomersCtrl'
            })
            .state('billers', {
                url: '/billers',
                templateUrl: 'app/view/billers.html',
                controller: 'BillersCtrl'
            })
    }
);

app.factory('DataService', ['$http',
    function ($http) {
        return {
            getPayments: function () {
                return $http.get('payments').then(function (response) {
                    return response.data;
                });
            },
            getCustomers: function () {
                return $http.get('customers').then(function (response) {
                    return response.data;
                });
            },
            getBillers: function () {
                return $http.get('billers').then(function (response) {
                    return response.data;
                });
            },
            addPayment: function (customerId, billerId, account, amount) {
                return $http.post('payments/add', {customerId: customerId, billerId: billerId, account: account, amount: amount}).then(function (response) {
                    return response.data;
                });
            },
            addCustomer: function (firstName, lastName, dateOfBirth, adress) {
                return $http.post('customers/add', {firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth, adress: adress}).then(function (response) {
                    return response.data;
                });
            },
            addBiller: function (name) {
                return $http.post('billers/add', {name: name}).then(function (response) {
                    return response.data;
                });
            },
            removeCustomer: function (id) {
                return $http.delete('customers/remove/'+id).then(function (response) {
                    return response.data;
                });
            },
            removeBiller: function (id) {
                return $http.delete('billers/remove/'+id).then(function (response) {
                    return response.data;
                });
            },
            updateCustomer: function (id, firstName, lastName, dateOfBirth, adress) {
                return $http.post('customers/update', {id: id, firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth, adress: adress}).then(function (response) {
                    return response.data;
                });
            },
            updateBiller: function (id, name) {
                return $http.post('billers/update', {id: id, name: name}).then(function (response) {
                    return response.data;
                });
            }
        }
    }
]);

app.controller('PaymentsCtrl', ['$rootScope', '$scope', 'DataService', '$uibModal', '$location',
    function ($rootScope, $scope, DataService, $uibModal, $location) {
        $rootScope.activeTab = 'payments';
        $scope.error = "";
        $scope.newEntryAdded = false;

        DataService.getPayments().then(function (data) {
            if (data.error) {
                $scope.error = data.error;
            } else {
                $scope.payments = data;
            }
        });

        $scope.showPaymentDialog = function() {
            showNewPaymentDialog($scope, $uibModal);
        };

        function showNewPaymentDialog($scope, $uibModal){
            var showNewPaymentInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/view/dialogs/new-payment.html',
                controller : showNewPaymentDialogInstanceCtrl,
                size: 'lr'
            });
        }

        var showNewPaymentDialogInstanceCtrl = ['$scope', '$uibModalInstance', function($scope, $uibModalInstance) {
            $scope.error = "";
            $scope.newPayment = null;

            DataService.getCustomers().then(function (data) {
                if (data.error) {
                    $scope.error = data.error;
                } else {
                    $scope.customers = data;
                }
            });

            DataService.getBillers().then(function (data) {
                if (data.error) {
                    $scope.error = data.error;
                } else {
                    $scope.billers = data;
                }
            });

            $scope.close = function () {
                $uibModalInstance.close();
                if ($scope.newEntryAdded) {
                    location.reload();
                }
            };

            $scope.addPayment = function () {
                if ($scope.newPayment == null ||
                    $scope.newPayment.customer == null ||
                    $scope.newPayment.biller == null ||
                    $scope.newPayment.account == null ||
                    $scope.newPayment.amount == null) {
                    $scope.error = "error";
                } else {
                    DataService.addPayment($scope.newPayment.customer, $scope.newPayment.biller, $scope.newPayment.account, $scope.newPayment.amount).then(function (data) {
                        $scope.newEntryAdded = true;
                        $scope.error = "";

                        $scope.newPayment.customer = null;
                        $scope.newPayment.biller = null;
                        $scope.newPayment.account = null;
                        $scope.newPayment.amount = null;
                        $scope.newPayment = null;
                    });
                }
            };
        }];
    }
]);

app.controller('CustomersCtrl', ['$rootScope', '$scope', 'DataService', '$uibModal', '$location',
    function ($rootScope, $scope, DataService, $uibModal, $location) {
        $rootScope.activeTab = 'customers';
        $scope.error = "";
        $scope.newEntryAdded = false;
        $scope.newEntryUpdated = false;
        $scope.customer = null;

        DataService.getCustomers().then(function (data) {
            if (data.error) {
                $scope.error = data.error;
            } else {
                $rootScope.customers = data;
            }
        });

        $scope.showCustomerDialog = function(customer) {
            showNewCustomerDialog($scope, $uibModal, customer);
        };

        function showNewCustomerDialog($scope, $uibModal, customer){
            $scope.customer = customer;
            var showNewCustomerInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/view/dialogs/new-customer.html',
                controller : showNewCustomerDialogInstanceCtrl,
                size: 'lr',
                resolve: {
                    newCustomer: function () {
                        return $scope.customer;
                    }
                }
            });
        }

        var showNewCustomerDialogInstanceCtrl = ['$scope', '$uibModalInstance', 'newCustomer', function($scope, $uibModalInstance, newCustomer) {
            $scope.error = "";
            $scope.newCustomer = newCustomer;
            $scope.newEntryUpdated = false;

            $scope.close = function () {
                $uibModalInstance.close();
                if ($scope.newEntryAdded) {
                    location.reload();
                }
            };

            $scope.addCustomer = function () {
                if ($scope.newCustomer == null ||
                    $scope.newCustomer.firstName == null ||
                    $scope.newCustomer.lastName == null ||
                    $scope.newCustomer.dateOfBirth == null ||
                    $scope.newCustomer.adress == null) {
                    $scope.error = "error";
                    // TODO add date format check
                } else {
                    DataService.addCustomer($scope.newCustomer.firstName, $scope.newCustomer.lastName, $scope.newCustomer.dateOfBirth, $scope.newCustomer.adress).then(function (data) {
                        $scope.newEntryAdded = true;
                        $scope.error = "";

                        $scope.newCustomer.firstName = null;
                        $scope.newCustomer.lastName = null;
                        $scope.newCustomer.dateOfBirth = null;
                        $scope.newCustomer.adress = null;
                        $scope.newCustomer = null;
                    });
                }
            };

            $scope.removeCustomer = function() {
                DataService.removeCustomer($scope.newCustomer.id).then(function (data) {
                    $uibModalInstance.close();
                    location.reload();
                });
            }

            $scope.updateCustomer = function() {
                if ($scope.newCustomer == null ||
                    $scope.newCustomer.firstName == null ||
                    $scope.newCustomer.lastName == null ||
                    $scope.newCustomer.dateOfBirth == null ||
                    $scope.newCustomer.adress == null) {
                    $scope.error = "error";
                    // TODO add date format check
                } else {
                    DataService.updateCustomer($scope.newCustomer.id, $scope.newCustomer.firstName, $scope.newCustomer.lastName, $scope.newCustomer.dateOfBirth, $scope.newCustomer.adress).then(function (data) {
                        $scope.newEntryUpdated = true;
                        $scope.error = "";
                    });
                }
            }
        }];
    }
]);

app.controller('BillersCtrl', ['$rootScope', '$scope', 'DataService', '$uibModal', '$location',
    function ($rootScope, $scope, DataService, $uibModal, $location) {
        $rootScope.activeTab = 'billers';
        $scope.error = "";
        $scope.newEntryAdded = false;
        $scope.newEntryUpdated = false;
        $scope.biller = null;

        DataService.getBillers().then(function (data) {
            if (data.error) {
                $scope.error = data.error;
            } else {
                $rootScope.billers = data;
            }
        });

        $scope.showBillerDialog = function(biller) {
            showNewBillerDialog($scope, $uibModal, biller);
        };

        function showNewBillerDialog($scope, $uibModal, biller){
            $scope.biller = biller;
            var showNewBillerInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/view/dialogs/new-biller.html',
                controller : showNewBillerDialogInstanceCtrl,
                size: 'lr',
                resolve: {
                    newBiller: function () {
                        return $scope.biller;
                    }
                }
            });
        }

        var showNewBillerDialogInstanceCtrl = ['$scope', '$uibModalInstance', 'newBiller', function($scope, $uibModalInstance, newBiller) {
            $scope.error = "";
            $scope.newBiller = newBiller;
            $scope.newEntryUpdated = false;

            $scope.close = function () {
                $uibModalInstance.close();
                if ($scope.newEntryAdded) {
                    location.reload();
                }
            };

            $scope.addBiller = function () {
                if ($scope.newBiller == null ||
                    $scope.newBiller.name == null) {
                    $scope.error = "error";
                } else {
                    DataService.addBiller($scope.newBiller.name).then(function (data) {
                        $scope.newEntryAdded = true;
                        $scope.error = "";

                        $scope.newBiller.name = null;
                        $scope.newBiller = null;
                    });
                }
            };

            $scope.removeBiller = function() {
                DataService.removeBiller($scope.newBiller.id).then(function (data) {
                    $uibModalInstance.close();
                    location.reload();
                });
            }

            $scope.updateBiller = function() {
                if ($scope.newBiller == null ||
                    $scope.newBiller.name == null) {
                    $scope.error = "error";
                } else {
                    DataService.updateBiller($scope.newBiller.id, $scope.newBiller.name).then(function (data) {
                        $scope.newEntryUpdated = true;
                        $scope.error = "";
                    });
                }
            }
        }];
    }
]);