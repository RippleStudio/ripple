var express = require('express');
var url = require('url');
var tokens = require('../tokens');
var router = express.Router();

router.get('/', function (req, res) {
    var pathname = url.parse(req.url).pathname;
    var arg = url.parse(req.url, true).query;
    console.log('pathname: ' + pathname);
    console.log('arg: ' + JSON.stringify(arg));
    if (arg.deviceid) {
        // request from a mobile device.
        // in: deviceid
        // out: userid, token
        tokens.get(arg.deviceid, function(out) {
            res.json(out);
        });
    } else {
        res.send('login');
    }
});

module.exports = router;