let app = angular.module('myApp', ['ng-route']);

app.config(function($routeProvider){
  $routeProvider
    .when('/', {
      templateUrl: 'carrinho-de-compras.html',
      controller: 'ListaComprasController'
    })
    .otherwise({
      redirectTo: '/'
    });
})