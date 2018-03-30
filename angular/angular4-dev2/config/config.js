'use strict';

var config = {};

config.environment = process.env.NODE_ENV || 'development';

// Preencher o BD com amostras, se for necess�rio
config.seedDB = false;

// Configura��o do servidor
config.server = {
    host: process.env.IP || 'localhost',
    port: process.env.PORT || 3000
};

// MongoDB configura��es
config.mongodb = {
    dbURI: "mongodb://localhost:27017/projeto"
};

// Exportar configura��es
module.exports = config;
