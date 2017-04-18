var mongoose = require('mongoose');

mongoose.connect('localhost:27017/projeto');

module.exports = mongoose.connection;