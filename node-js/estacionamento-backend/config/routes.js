
var express = require('express');
var router = express.Router();
var carsController = require('../cars/cars.controller');

//localhost:3000
router.get('/',function(req, res){
    res.send("Hello World");
})

//declarar rotas
router.get('/cars/:id', carsController.getCarById);
router.get('/cars', carsController.getAllCars);
router.get('/vagas', carsController.pesquisarVagas);
router.get('/paymentValue/:id', carsController.paymentValue);
router.post('/cars', carsController.registerCar);
router.delete('/cars', carsController.deleteCar);
router.patch('/cars', carsController.payParking);


module.exports = router;