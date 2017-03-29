var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('Users page');
});

module.exports = router;

//set DEBUG=myapp:* & npm start

//comentário acima deve ser colado no CMD para o servidor ser criado.