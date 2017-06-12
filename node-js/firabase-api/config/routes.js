var express = require('express');
var router = express.Router();

//localhost:3000
router.get('/',function(req, res){
    res.send("Hello World");
});

module.exports = router;