//constants.error.msg_bad_request PARA CHAMAR OS ERROS DOS CONSTANTS;

var express = require('express');
var carsService = require('./cars.service');


module.exports = {
  getCarById:function(req, res, next){
    var placa = req.params.id;
    carsService.getCarById(placa, function(response){
      if (response){
        res.json(response);
      }else {
        res.json('Bad request');
        res.status(400);
      }
    });
},


getAllCars:function(req, res, next){
    carsService.getAllCars(function(response){
      if (response){
        res.json(response);
        res.status(200);
      }else {
        res.json('Bad request');
        res.status(400);
      }
    });
  },

  pesquisarVagas:function(req, res, next){
    carsService.pesquisarVagas(function(response){
      //se o estacionamento estiver lotado o serviço retorna 0, a controller identificava isso como erro e pulava pro else
      if(response || response === 0){
        res.json(response);
      } else {
          res.json('Bad request');
          res.status(400);
        }
    });
  },


  registerCar:function(req, res, next){
    if (req.body.placa){
      var placa = req.body.placa;
      carsService.registerCar(placa, function(response){
        if (response){
          res.json(response);
          res.status(response[0].status);
        }else {
          res.json('Bad request');
          res.status(400);
        }
      })
    }else {
      res.json("Placa vazia");
      res.status(400);
    }
},

    payParking:function(req, res, next){
      if(req.body.placa){
        var placa = req.body.placa;
        carsService.payParking(placa, function(response){
          if(response){
            res.json(response);
            res.status(response[0].status);
          }else{
            res.json('Bad request');
            res.status(400);
          }
        });
      }else{
        res.status(400);
      }
    }
,

  deleteCar:function(req, res, next){
    if (req.body.placa){
      var placa = req.body.placa;
      carsService.deleteCar(placa, function(response){
        if (response){
          res.json(response);
          res.status(response[0].status);
        }else {
          res.json('Bad request');
          res.status(400);
        }
      });
    }else {
      res.json('Not found');  
      res.status(400);
    }
  },

  paymentValue:function(req, res, next){
    var placa = req.params.id;
    carsService.paymentValue(placa, function(response){
      if(response){
        res.json(response);
      } else {
          res.json('Bad request');
          res.status(400);
        }
    });
  }
}