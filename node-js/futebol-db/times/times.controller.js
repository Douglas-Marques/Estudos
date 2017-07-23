
var express = require('express');
var timesService = require('./times.service');

module.exports = {
  salvarTime:function(req, res, next){
    var nome = req.body.nome;
    timesService.salvarTime(nome, function(response){
      if(response){
        res.json(response);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    })
  },

  obterTodosTimes:function(req, res, next){
    timesService.obterTodosTimes(function(response){
      if(response){
        res.json(response);
        res.status(200);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    });
  },

  obterResultadoJogo:function(req, res, next){
    var mandante = req.body.mandante;
    var visitante = req.body.visitante;
    var golsMandante = parseInt(req.body.golsMandante);
    var golsVisitante = parseInt(req.body.golsVisitante);

      timesService.obterResultadoJogo(mandante, golsMandante, visitante, golsVisitante, function(response){
        if(response){
            res.json(response);
            res.status(200);
          }else{
            res.json('Bad request');
            res.status(400);
          }
      });
  },

  obterTimePeloNome:function(req, res, next){
    var nome = req.params.nome;
    timesService.obterTimePeloNome(nome, function(response){
      if(response){
        res.json(response);
        res.status(response[0].status);
      }else{
        res.json('Bad request');
        res.status(400);
      }
    })
  },

  obterArrayDeNomeDosTimes:function(req, res, next){
    timesService.obterArrayDeNomeDosTimes(function(response){
      if(response){
        res.json(response);
        res.status(response[0].status);
      }else{
        res.json('Bad request');
        res.status(400);
      }
    });
  },

  zerarRegistrosTimes:function(req, res, next){
    timesService.zerarRegistrosTimes(function(response){
      if(response){
        res.json(response);
        res.status(200);
      }else{
        res.json('Bad request');
        res.status(400);
      }
    });
  }
}