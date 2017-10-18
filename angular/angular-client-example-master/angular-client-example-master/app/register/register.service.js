var module = angular.module('myApp.register.service', [])
module.factory('RegisterService',function($http){
    return {
      registerPeopleWithProperty: function(id, name){
          var data = {
            name : name
          }
          var url = 'http://localhost:3000/api/people/' + id;
          var req = {
              method: 'PUT',
              url: url,
              data: data
          }

          return $http(req).then(function(response){
              var responses = response.data;
              return responses;
          })
      },

      registerPeople: function(id){
          var data = {
            id : id
          }
          var url = 'http://localhost:3000/api/people/';
          var req = {
              method: 'POST',
              url: url,
              data: data
          }

          return $http(req).then(function(response){
              var responses = response.data;
              return responses;
          })
      }
    }
})
