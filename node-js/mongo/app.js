var mongoose = require('mongoose'),
 uri = 'localhost:27017/projeto';
mongoose.connect(uri); 

var Car = mongoose.model('Car', { name: String });

var golf = new Car({ name: 'Golf' });
golf.save(function (err) {
  if (err) {
    console.log(err);
  } else {
    console.log('Golf cadastrado');
  }
});