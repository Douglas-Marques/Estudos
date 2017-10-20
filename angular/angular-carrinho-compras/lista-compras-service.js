let myApp = angular.module('myApp.list.service', []);
myApp.factory('ListaComprasService', function($http){
  return{
    getList: function(){
      return $http.get('http://localhost:3000/produtos').then(function(response){
            return response.data;
        });
    },
    deleteProductById: function(id){
      return $http.delete('http://localhost:3000/produtos/' + id).then(function(response){
            return response.data;
        });
    },
    updateProduct: function(id, newName, newQtd){
      let data = {
        "id": id,
        "nome": newName,
        "quantidade": newQtd
      };
      let req = {
        method: 'PATCH',
        url: 'http://localhost:3000/produtos',
        data: data
      };

      return $http(req).then(function(response){
        return response.data;
      });
    },
    postProduct: function(name, qtd){
      let data = {
        "nome": name,
        "quantidade": qtd
      };
      let req = {
        method: 'POST',
        url: 'http://localhost:3000/produtos',
        data: data
      };
      
      return $http(req).then(function(response){
        return response.data;
      });
    }
  }
});