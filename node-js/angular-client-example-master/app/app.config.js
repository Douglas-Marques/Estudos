'use strict';

var myApp = angular.module('myApp.routes', []);

    myApp.config(function($routeProvider) {
        $routeProvider

            .when('/register', {
              templateUrl : 'register/register.html',
              controller  : 'RegisterCtrl'
            })

            .when('/people-list', {
              templateUrl : 'people-list/people-list.html',
              controller  : 'PeopleListCtrl'
            })

            .otherwise({redirectTo:'/register'});

    });
