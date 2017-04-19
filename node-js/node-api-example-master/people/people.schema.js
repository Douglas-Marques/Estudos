var mongoose = require('mongoose');

var peopleSchema = new mongoose.Schema({
  people: Number,
  name: String
});

peopleSchema.pre('save', function(next){
  //get current date
  var currentDate = new Date();

  //change the updated_at fielt to current date
  this.updatedAt = currentDate;

  // if createdAt doesn't exist
  if(!this.createdAt)
  this.createdAt = currentDate;
  next();

});


module.exports = mongoose.model('People', peopleSchema);
