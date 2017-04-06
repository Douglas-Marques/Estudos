var myModule = angular.module('MeuModulo', []);

/*let json = [
      { "nome":"Eduardo Dornel", "email":"eduardodornel@hotmail.com"}, 
      { "nome":"Nycoli Reis", "email":"nycolireis@hotmail.com"},
      { "nome":"Clair Kenne Dornel", "email":"clairdornel@hotmail.com"},
      { "nome":"Amanda Dornel Ribas", "email":"amandadornel@hotmail.com"}
    ];
*/

myModule.controller('MeuController', function($http, $scope){
  $http.get('./module/agenda.json')
    .then(function(result){
      $scope.registros = result.data;
    });    
  });




      //$scope.registros = json;

