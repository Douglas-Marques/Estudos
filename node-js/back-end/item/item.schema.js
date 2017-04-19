
var mongoose = require('mongoose');

var itemSchema = new mongoose.Schema({
  item: Number,
  name: String
});
//  people: Number,
//  name: String

itemSchema.pre('save', function(next){
  //get current date
  var currentDate = new Date();

  //change the updated_at fielt to current date
  this.updatedAt = currentDate;

  // if createdAt doesn't exist
  if(!this.createdAt)
  this.createdAt = currentDate;
  next();

});


module.exports = mongoose.model('Item', itemSchema);