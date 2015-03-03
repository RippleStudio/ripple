var express = require('express');
var app = express();
var port = 3000;
var login = require('./routers/login');

/*
 Routes
 */
app.use('/login', login);

app.get('/', function(req, res) {
	res.send('It works!');
});

app.listen(port);

console.log('server is listening on port ' + port);