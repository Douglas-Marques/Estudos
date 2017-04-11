function attachTitle(texto){
    return 'DR. ' + texto;
}

  var promise = Promise.resolve('MANHATTAN');

promise.then(attachTitle).then(console.log);
