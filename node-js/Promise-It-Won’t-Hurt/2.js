require('es6-promise');

'use strict'

var promise = new Promise(function(fulfill, reject){
    setTimeout(fulfill, 300, "FULFILLED!");
});

promise.then(console.log);


