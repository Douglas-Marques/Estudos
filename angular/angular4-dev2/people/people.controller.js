var express = require('express');
var peopleService = require('./people.service');
var constants = require('./people.constants');


module.exports = {
  getPeopleById:function(req, res, next){
    var id = req.params.id;
    peopleService.getPeopleById(id, function(response){
      if (response){
        res.json(response);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
  },

  getAllPeople:function(req, res, next){
    peopleService.getAllPeople(function(response){
      if (response){
        res.json(response);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
  },

  registerPeople:function(req, res, next){
    if (req.body.id){
      var id = req.body.id;
      peopleService.registerPeople(id,function(response){
        if (response){
          res.json(response);
          res.status(response[0].status);
        }else {
          res.json(constants.error.msg_bad_request);
          res.status(400);
        }
      })
    }else {
      res.status(400);
    }
  },

  addPeopleWithProperty:function(req, res, next){
    var id = req.params.id;
    var name = req.body.name;

    if (id && name){
      peopleService.addPeopleWithProperty(id, name, function(response){
        if (response){
          res.json(response);
          res.status(response[0].status);
        }else {
          res.json(constants.error.msg_bad_request);
          res.status(400);
        }
      })
    }else {
      res.json(constants.error.msg_no_property);
      res.status(400);
    }
  },

  updatePeople:function(req, res, next){
    var id = req.params.id;
    var newName = req.body.newName;
    peopleService.updatePeople(id, newName, function(response){
      if(response){
        res.json(response);
        res.status(response[0].status);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
  },

  deletePeople:function(req, res, next){
    if (req.params.id){
      var id = req.params.id;
      peopleService.deletePeople(id, function(response){
        if (response){
          res.json(response);
          res.status(response[0].status);
        }else {
          res.json(constants.error.msg_bad_request);
          res.status(400);
        }
      })
    }else {
      res.json(constants.error.msg_no_property);  
      res.status(400);
    }
  }

}
