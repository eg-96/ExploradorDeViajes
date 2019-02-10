import * as admin from 'firebase-admin';

var serviceAccount = require('../accountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

module.exports.database = db;