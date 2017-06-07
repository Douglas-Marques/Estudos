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

function enviarMsgSlack(texto, callback){
  requestify.request(url + "chat.postMessage", {
    method: 'POST',
    body: {
        token: 'xoxp-62107491250-170933007798-194235539811-317e289e56d366b6d049afbc470fa435',
        channel: 'C5LL6K5PA',
        text: texto
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