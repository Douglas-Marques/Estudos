
var express = require('express');
var router = express.Router();
var timesController = require('../times/times.controller');

//localhost:3000
router.get('/',function(req, res){
    res.send("Hello World");
})

//declarar rotas da controller times
router.get('/times', timesController.obterTodosTimes);
router.get('/times/:nome', timesController.obterTimePeloNome);
router.get('/nomes', timesController.obterArrayDeNomeDosTimes);
router.post('/times', timesController.salvarTime);
router.patch('/jogo', timesController.obterResultadoJogo);
router.patch('/zerarTabela', timesController.zerarRegistrosTimes);

module.exports = router;