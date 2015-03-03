var mongodb = require('mongodb');
var config = require('./config.json');

var mongoUrl = config.database.url + config.database.db.users.name;
var mongoCollection = config.database.db.users.collections.device;

console.log('mongoUrl: ' + mongoUrl);

module.exports.get = function (deviceid, next) {
    mongodb.MongoClient.connect(mongoUrl, function (err, db) {
        var collection = db.collection(mongoCollection);
        // find in database first.
        collection.find({'deviceid': deviceid}).toArray(function (err, deviceids) {
            if (deviceids && deviceids.length != 0) {
                // if found, return the values.
                db.close();

                next({
                    'deviceid': deviceids[0].deviceid,
                    'userid': deviceids[0].userid,
                    'token': deviceids[0].token
                });
            } else {
                // not found, new a record.
                collection.insert({
                    'deviceid': deviceid
                }, {w: 1}, function (err, results) {
                    console.log('insert result: ' + JSON.stringify(results));

                    collection.findAndModify({'deviceid': deviceid}, [], {
                        $set: {
                            'userid': results[0]._id,
                            'token': results[0]._id
                        }
                    }, {new: true}, function (err, doc) {
                        db.close();

                        next({
                            'deviceid': doc.deviceid,
                            'userid': doc.userid,
                            'token': doc.token
                        });
                    });
                });
            }
        });
    });
};