
var express = require('express');
var router = express.Router();
var carsController = require('../cars/cars.controller');

router.get('/',function(req, res){
    res.send("Hello World");
})

router.get('/cars/:id', carsController.getCarById);
router.get('/cars', carsController.getAllCars);
router.post('/cars', carsController.registerCar);
router.patch('/cars/:id', carsController.payParking);
router.delete('/cars/:id', carsController.deleteCar);


module.exports = router;