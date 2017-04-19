var express = require('express');
var router = express.Router();
var itemController = require('../item/item.controller');

router.get('/',function(req, res){
    res.send("Hello World");
})

//Item
router.get('/item/:id', itemController.getItemById);
router.get('/item', itemController.getAllItems);
router.post('/item',itemController.registerItem);
router.put('/item/:id',itemController.addItemWithProperty);
router.patch('/item/:id',itemController.updateItem);
router.delete('/item/:id',itemController.deleteItem);


module.exports = router;