import * as admin from 'firebase-admin';

var serviceAccount = require('../accountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://explorador-da8a4.firebaseio.com"
});

var db = admin.database();

module.exports.database = db;