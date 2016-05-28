"use strict";

const FirebaseBridge = require("react-native").NativeModules.FirebaseBridge;

class RNFirebase {
  static handleFacebookAccessToken(token) {
    console.log("javascript handleFacebookAccessToken", token);
  }
}

module.exports = RNFirebase;
