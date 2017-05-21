'use strict';

var config = {};

config.environment = process.env.NODE_ENV || 'development';

// Preencher o BD com amostras, se for necessário
config.seedDB = false;

// Configuração do servidor
config.server = {
    host: process.env.IP || 'localhost',
    port: process.env.PORT || 3000
};

// MongoDB configurações
config.mongodb = {
      dbURI: "mongodb://localhost:27017/estacionamento"
};

// Exportar configurações
module.exports = config;