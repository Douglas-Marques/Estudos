var express = require('express');
var itemService = require('./item.service');
var constants = require('./item.constants');
var async = require('async');

module.exports = {
  getItemById:function(req, res, next){
    var id = req.params.id;
    itemService.getItemById(id, function(response){
      if (response){
        res.json(response);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
},

  getAllItem:function(req, res, next){
    itemService.getAllItem(function(response){
      if (response){
        res.json(response);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
},

registerItem:function(req, res, next){
    if (req.body.id){
      var id = req.body.id;
      itemService.registerItem(id,function(response){
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

addItemWithProperty:function(req, res, next){
    var id = req.params.id;
    var name = req.body.name;

    if (id && name){
      itemService.addItemWithProperty(id, name, function(response){
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

updateItem:function(req, res, next){
    var id = req.params.id;
    var newName = req.body.newName;
    itemService.updateItem(id, newName, function(response){
      if(response){
        res.json(response);
        res.status(response[0].status);
      }else {
        res.json(constants.error.msg_bad_request);
        res.status(400);
      }
    })
  },

  deleteItem:function(req, res, next){
    if (req.params.id){
      var id = req.params.id;
      itemService.deleteItem(id, function(response){
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