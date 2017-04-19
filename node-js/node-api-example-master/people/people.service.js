var People = require('./people.schema'),
constants = require('./people.constants.json'),
service = {},
Q = require('q');

service.registerPeople = registerPeople;
service.getPeopleById = getPeopleById;
service.getAllPeople = getAllPeople;
service.addPeopleWithProperty = addPeopleWithProperty;
service.deletePeople = deletePeople;
service.updatePeople = updatePeople;
module.exports = service;

function getPeopleById(id, callback){
	People.find({'people':id}, function(err, people) {
		if (err) callback({status:500, error: err });
		if (people == null){
			callback(people);
		}else {
			callback(people);
		}
	});
}

function getAllPeople(callback){
	People.find({}, function(err, people) {
		if (err) callback({status:500, error: err });
		if (people == null){
			callback(people);
		}else {
			callback(people);
		}
	});
}

function registerPeople (id, callback){
	People.find({'people':id}, function(err, people){
		if (err) callback({status:500, error: err });
		if(people[0]){
			callback([constants.error.msg_people_conflict]);
		}else{
			var newPeople = new People({
				'people': id
			});
			newPeople.save(function(err, response){
				if (err) {callback(false);}
				else {
					callback([constants.success.msg_people_created, {response}]);
				}
			})
		}
	})
}

function addPeopleWithProperty(id, name, callback){
	People.find({'people':id}, function(err, people){
		if (err) callback({status:500, error: err });
		if(people[0]){
			callback([constants.error.msg_people_conflict]);
		}else{
			var newPeople = new People({
				'people': id,
				'name': name
			});

			newPeople.save(function(err, response){
				if (err) {callback(false);}
				else {
					callback([constants.success.msg_people_ok, {response}]);
				}
			})
		}
	})
}

function updatePeople(id, newName, callback){
	People.findOneAndUpdate({ 'people': id }, { $set: { name: newName } }, { new: true }, function(err, response) {
		if (err) {
			callback(false);
		}else if(response){
			callback([constants.success.msg_people_ok, {response}]);
		}
		else {
			callback([constants.error.msg_people_update]);
		}
	});
}

function deletePeople(id, callback){
      People.findOneAndRemove({'people': id}, function (err,response){
				console.log(response);
        if (err) {
					callback({status:500, error: err });
				}else if(response){
					callback([constants.success.msg_people_ok, {}]);
				}else{
					callback([constants.error.msg_no_people]);
				}
      });
}
