var mongoose = require('mongoose');
mongoose.connect('localhost:27017/projeto');

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    var pessoaSchema = mongoose.Schema({
      name: String
    });
    var Pessoa = mongoose.model('pessoa', pessoaSchema);
    var eduardo = new Pessoa({name: 'Eduardo'});
    eduardo.save(function (err) {
      if (err) {
        console.log(err);
      } else {
        console.log('Usuario cadastrado');
      }
  });
});