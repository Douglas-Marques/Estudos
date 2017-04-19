var Item = require('./people.schema'),
constants = require('./item.constants.json'),
service = {},
Q = require('q');

service.registerItem = registerItem;
service.getItemById = getItemById;
service.getAllItem = getAllItem;
service.addItemWithProperty = addItemWithProperty;
service.deleteItem = deleteItem;
service.updateItem = updateItem;
module.exports = service;