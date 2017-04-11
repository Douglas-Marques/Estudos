var promise = Promise.resolve('Deu certo');
var promiseError = Promise.reject(new Error('Erro'));

promiseError.catch(function(err){
    console.error('ocorreu algo inesperado pelo sistema');
    console.error(err.message);
})

/*
for reject erros in promises

promise.then(null, function (err) {
      console.error('THERE IS AN ERROR!!!');
      console.error(err.message);
    });

You could simply write

    promise.catch(function (err) {
      console.error('THERE IS AN ERROR!!!');
      console.error(err.message);
    });
 */