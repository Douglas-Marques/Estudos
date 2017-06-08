var requestify = require('requestify');
var isbn = require('node-isbn');

var url = "https://slack.com/api/";

var service = {};

service.enviarMsgSlack = enviarMsgSlack;
service.lerIsbn = lerIsbn;
module.exports = service;

function lerIsbn(numeroIsbn, callback){
  isbn.resolve(numeroIsbn, function (err, book) {
    if (err) {
        callback('Book not found', err);
    } else {
        callback(book);
    }
  });
}

function enviarMsgSlack(texto, icon, callback){
  console.log(icon);
  requestify.request(url + "chat.postMessage", {
    method: 'POST',
    body: {
        token: 'xoxp-62107491250-177744055633-194275747395-6db2707608d331cb50b47c2791143dc1',
        channel: 'C5LL6K5PA',
        text: texto,
        icon_url: icon
    },    
    dataType: 'form-url-encoded'        
})
.then(function(response) {
    // get the response body
    //callback(response.getBody().message.text);
    callback(response.getBody());
    // get the response headers
  //  response.getHeaders();

    // get specific response header
  //  response.getHeader('Accept');

    // get the code
   // response.getCode();

    // Get the response raw body
  //  response.body;
  });
}