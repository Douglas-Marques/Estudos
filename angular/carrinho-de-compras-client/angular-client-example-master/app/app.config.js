'use strict';

var myApp = angular.module('myApp.routes', []);

    myApp.config(function($routeProvider) {
        $routeProvider

            .when('/lista', {
              templateUrl : 'lista-compras/lista-compras.html',
              controller  : 'ListaComprasController'
            })

            .otherwise({redirectTo:'/lista'});

    });
