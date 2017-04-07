module.exports = function findFziles(diretorio, extensao, callback) {
  var fs = require('fs');
  fs.readdir(diretorio, function(err, list) {
    if (err) {
      return callback(err);
    } else {
      var data = list.filter(function(filename) {
        return filename.indexOf('.' + extensao) >= 0;
      });
      return callback(null, data);
    }
  });
};