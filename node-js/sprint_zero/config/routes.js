var express = require('express');
var router = express.Router();
var emailController = require('../controller/email.controller');
var firebaseController = require('../controller/firebase.controller');
var isbnController = require('../controller/isbn.controller');
var slackController = require('../controller/slack.controller');


router.get('/',function(req, res){
    res.send("Hello World");
});

//ISBN
router.get('/isbn/:isbn', isbnController.lerIsbn);
router.get('/livros', isbnController.obterTodosLivros);
router.get('/livros/:array', isbnController.pesquisarPorVoz);
router.post('/isbn', isbnController.salvarLivro);
//FIREBASE
router.post('/notification', firebaseController.concetarFirebase);
//EMAIL
router.post('/email', emailController.sendEmail);
//SLACK
router.post('/slack', slackController.enviarMsgSlack);

module.exports = router;

//token celular
//eR2pOFDnppQ:APA91bHAQy1fyMgKQ52hWNNNkyCxGalgLiIYDWYVhQqfyTBnYB3aMIRdbWhtquVrMUPwQGfkzvuag_9AjhZKXpbcGv2o_F3IotWssm6wV9DvyhUYyfrQsvj9j68gTS48QAEIeXF6uZ_Q
