var http = require('http');

// Slice the arguments from command line to get only the urls.
var URLs = process.argv.slice(2);

// Counter to keep track of async responses.
var count = 0;

// Arry to store completed responses
var responses = [];
URLs.forEach(getData);

// Makes the call and handles everythign.
function getData(url, index) {
  http.get(url, function(response) {
    var str = '';
    response.setEncoding('utf8');
    response.on("data", function(data) {
      str += data;
    });
    response.on('end', function() {
      responses[index] = str;
      count++;
      if (count === URLs.length) {
        responses.forEach(function(msg) {
          console.log(msg);
        });
      }
    });
  });
}