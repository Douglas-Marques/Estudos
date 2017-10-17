var ActiveDirectory = require('activedirectory');
var service = {}

service.conectarAd = conectarAd
module.exports = service;

var config = { url: 'ldap://cidc01.cipucrs.pucrs.br',
               baseDN: 'dc=cipucrs,dc=pucrs.br',
               username: 'aluno@cipucrs.pucrs.br',
               password: 'pass@word1' }
var ad = new ActiveDirectory(config);

function conectarAd(dados, callback){
  var username = dados.email;
  var password = dados.senha;

  ad.authenticate(username, password, function(err, auth) {
    if (err) {
      callback('ERROR: '+JSON.stringify(err));
    }
    if (auth) {
      callback('Authenticated!');
    }
    else {
      callback('Authentication failed!');
    }
  });
}