var module = angular.module('myApp.register.controller', ['myApp.register.service']);
module.controller('RegisterCtrl', function($scope, RegisterService, $location){

  $scope.registerPeople = function(people){
    if(people.id && people.name){
      var id = people.id;
      var name = people.name;
      RegisterService.registerPeopleWithProperty(id, name).then(function(response){
        console.log(response);
        if(response[0].status == 200){
          alert("Successfully registered!");
          $location.path('/people-list');
        }else{
          alert("ID already registered!");
        }
      })
    }else if (people.id && !people.name){
      var id = people.id;
      RegisterService.registerPeople(id).then(function(response){
        console.log(response);
        if(response[0].status == 201){
          alert("Successfully registered!");
          $location.path('/people-list');
        }else{
          alert("ID already registered!");
        }


      })
    }
  }
});
