function prompt(question, callback){
	var stdin = process.stdin;
	var stdout = process.stdout;

	stdin.resume();
	stdout.write(question);

	stdin.once('data', function(data){
		callback(data.toString().trim());
	});
}

prompt('Qual o seu nome?', function(data){
	console.log(data);
})