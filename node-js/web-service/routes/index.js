var express = require('express');
var router = express.Router();
var model = require('./../model/carros')();

var total = 10;

/* GET home page. */
router.get('/', function(req, res, next) {
  model.find(null, function(err, carros){
    if(err){
      throw err;
    }
    var vagas = total - carros.length;

      res.render('index', { title: 'Centro comercial', carros: carros, total: total});
  });
});


router.post('/add', function(req, res, next){
  var body = req.body;
  body.pago = false;
  body.date = new Date();
    model.create(body, function(err, carro){
      if(err){
        throw err;
      }
        res.redirect('/')
   });
})

router.get('/pagar/:id', function(req, res, next){
  var id = req.params.id;

  model.findById(id, function(err, carro){
    if (err){
      throw err;
    }
    carro.pago = true;
    carro.save(function(){
       res.redirect('/');
     });
  });
});

router.get('/sair/:id', function(req, res, next){
  var id = req.params.id;

  model.findByIdAndRemove(id, function(err, data){
    if(err){
      throw err;
    }
    res.redirect('/');
  });
});

module.exports = router;