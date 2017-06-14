var FCM = require('fcm-node');

var serverKey = 'AAAAtXA-06c:APA91bFWbFxd1_jYJDQWeD-eWpEUNFYKXYqsvCdi-ccRUDpn6ANVWeESiyLyEUpfeaT0_3rvMUWYgrvqLhxcief8d5e9aujGSP2aPtf9hplxAyR3KCleen2kuV-lnkFkDolRPIyp4SWK'; //put your server key here
var fcm = new FCM(serverKey);

var service = {};
service.concetarFirebase = concetarFirebase;
module.exports = service;

function concetarFirebase(tokenAndroid, callback){
  var message = { //this may vary according to the message type (single recipient, multicast, topic, et cetera)
    to: tokenAndroid,

    notification: {
      title: 'Titulo',
      body: 'Primeira notificação'
    }
  };

  fcm.send(message, function(err, response){
    if (err) {
      callback("Algo de errado não está certo");
    } else {
      callback("Sucesso obtendo a resposta: ", response);
    }
  });
}