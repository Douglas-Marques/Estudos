var myApp = angular.module('myApp.list.service', []);
myApp.factory('PeopleListService', function($http){
    return {
        getPeople: function(){
          return $http.get('http://localhost:3000/api/people').then(function(response){
                return response.data;
            })
        },
        getPeopleById: function(id){
          return $http.get('http://localhost:3000/api/people/' + id).then(function(response){
                return response.data;
            })
        },
        deletePeopleById: function(id){
          return $http.delete('http://localhost:3000/api/people/' + id).then(function(response){
                return response.data;
            })
        },

        updatePeople: function(id, newName){
            var data = {
              newName : newName
            }
            var url = 'http://localhost:3000/api/people/' + id;
            var req = {
                method: 'PATCH',
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
