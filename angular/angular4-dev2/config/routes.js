
var express = require('express');
var router = express.Router();
var peopleController = require('../people/people.controller');

router.get('/',function(req, res, next){
    res.send("Hello World");
})

//People
router.get('/people/:id', peopleController.getPeopleById);
router.get('/people', peopleController.getAllPeople);
router.post('/people',peopleController.registerPeople);
router.put('/people/:id',peopleController.addPeopleWithProperty);
router.patch('/people/:id',peopleController.updatePeople);
router.delete('/people/:id',peopleController.deletePeople);


module.exports = router;
