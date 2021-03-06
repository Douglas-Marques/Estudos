'use strict';

var config = {};

config.environment = process.env.NODE_ENV || 'development';

// Populate the DB with sample data
config.seedDB = false;

// Server settings
config.server = {
    host: process.env.IP || 'localhost',
    port: process.env.PORT || 3000
};

// MongoDB settings
config.mongodb = {
    dbURI: "mongodb://localhost:27017/projeto"
};

// Export configuration object
module.exports = config;
