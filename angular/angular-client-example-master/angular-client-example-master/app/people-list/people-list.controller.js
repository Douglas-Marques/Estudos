var module = angular.module('myApp.list.controller', ['myApp.list.service']);
module.controller('PeopleListCtrl', function($scope, PeopleListService){
  $scope.flagUpdate = false;
  $scope.peopleUpdate = "";

  $scope.searchPeopleById = function(id){
    PeopleListService.getPeopleById(id).then(function(response){
      console.log(response);
      $scope.people = response;
    })
  }

  $scope.listAllPeople = function(){
    PeopleListService.getPeople().then(function(response){
      $scope.people = response;
    })
  }
  $scope.listAllPeople();

  $scope.deletePeople = function(id){
    PeopleListService.deletePeopleById(id).then(function(response){
      alert("Deleted successfully!");
      $scope.listAllPeople();
    })
  }

  $scope.openUpdate = function(people){
    $scope.peopleUpdate = people;
    $scope.flagUpdate = true;
  }

  $scope.updatePeople = function(id, name){
    PeopleListService.updatePeople(id, name).then(function(response){
      alert("Updated successfully!");
      $scope.flagUpdate = false;
      $scope.listAllPeople();
    })
  }
});
