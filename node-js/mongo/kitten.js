var mongoose = require('mongoose');
mongoose.connect('localhost:27017/projeto');

mongoose.Promise = global.Promise;

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  var kittySchema = mongoose.Schema({
    name: String
  });
  var Kitten = mongoose.model('Kitten', kittySchema);
  var silence = new Kitten({ name: 'Silence' });

   silence.save(function (err, silence) {
    if (err) return console.error(err);
      console.log('silence cadastrado')
    });

    var fluffy = new Kitten({ name: 'fluffy' });

    fluffy.save(function (err, fluffy) {
    if (err) return console.error(err);
      console.log('fluffy cadastrado')
    });

    Kitten.find(function (err, kittens) {
      if (err) return console.error(err);
        kittens.forEach(function(kitty){
          console.log(kitty.name);
        })
    });


//pesquisar por algum termo
   Kitten.find({ name: /^fluff/ }, function(err, kittens){
      kittens.forEach(function(kitty){
        console.log(kitty.name);
      });
    });
});
