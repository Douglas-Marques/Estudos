
var express = require('express');
var router = express.Router();
var timesController = require('../times/times.controller');

//localhost:3000
router.get('/',function(req, res){
    res.send("Hello World");
})

//declarar rotas
router.post('/times', timesController.salvarTime);
router.get('/times', timesController.obterTodosTimes);
router.patch('/vitoria', timesController.obterVitoria);
router.patch('/derrota', timesController.obterDerrota);
router.patch('/empate', timesController.obterEmpate);
module.exports = router;