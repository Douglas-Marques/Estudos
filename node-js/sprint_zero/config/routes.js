var express = require('express');
var router = express.Router();
var controller = require('../controller/teste.controller')

router.get('/',function(req, res){
    res.send("Hello World");
});

router.post('/slack', controller.enviarMsgSlack);
router.get('/isbn/:isbn', controller.lerIsbn);

module.exports = router;