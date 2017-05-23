
var express = require('express');
var router = express.Router();
var carsController = require('../cars/cars.controller');

router.get('/',function(req, res){
    res.send("Hello World");
})

router.get('/cars/:id', carsController.getCarById);
router.get('/cars', carsController.getAllCars);
router.get('/vagas', carsController.pesquisarVagas);
router.get('/paymentValue/:id', carsController.paymentValue);
router.post('/cars', carsController.registerCar);
router.delete('/cars/:id', carsController.deleteCar);

//router.patch('/cars/:id', carsController.payParking);
router.patch('/cars', carsController.payParking);


module.exports = router;