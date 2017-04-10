require('es6-promise');

var promise = new Promise(function(fulfill, reject){
    setTimeout(reject, 300, new Error('REJECTED!'));
});

//function onReject(error){
//}
promise.then(null, function(err){
    console.log(err.message);
});

