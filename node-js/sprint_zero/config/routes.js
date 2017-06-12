var express = require('express');
var router = express.Router();
var controller = require('../controller/teste.controller')

router.get('/',function(req, res){
    res.send("Hello World");
});

router.post('/slack', controller.enviarMsgSlack);
router.get('/isbn/:isbn', controller.lerIsbn);
router.post('/notification', controller.concetarFirebase);

module.exports = router;


//token celular
//eR2pOFDnppQ:APA91bHAQy1fyMgKQ52hWNNNkyCxGalgLiIYDWYVhQqfyTBnYB3aMIRdbWhtquVrMUPwQGfkzvuag_9AjhZKXpbcGv2o_F3IotWssm6wV9DvyhUYyfrQsvj9j68gTS48QAEIeXF6uZ_Q
