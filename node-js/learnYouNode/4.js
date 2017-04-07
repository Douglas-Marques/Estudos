var array = process.argv[2];
var fs = require('fs');


fs.readFile(array, function(err, data){
			if(err) console.log('Arquivo não encontrado.');
		    var number = data.toString().split('\n').length - 1;
            console.log(number);
        });