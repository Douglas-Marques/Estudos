'use strict';

var logger  = require('mm-node-logger')(module);
var pkg     = require('./package.json');
var config  = require('./config/config');
var express = require('./config/express');
var mongodb = require('./config/mongoose');



mongodb(function startServer() {
    var app = express.init();

    app.listen(3000, function () {
        logger.info('App is running');
    });
});
