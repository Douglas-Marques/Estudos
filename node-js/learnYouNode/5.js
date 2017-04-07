var fs = require('fs');

fs.readdir(process.argv[2], function(err, lista){
    if(err) console.log('Erro');
    lista.forEach(function(arquivo){
        if(arquivo.indexOf('.' + process.argv[3]) >= 0){
            console.log(arquivo);
        }
    });
});