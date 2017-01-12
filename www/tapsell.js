
window.tapsell_rotation_locked_portrait = 1;
window.tapsell_rotation_locked_landscape = 2;
window.tapsell_rotation_unlocked = 3;

module.exports = {
	initialize: function(appKey) {
		cordova.exec(function (result) {console.log("Tapsell initialized successfully.");},null,"TapsellCordovaInterface","initialize",[appKey]);
	},
	requestAd: function(zoneId, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "requestAd", [ zoneId ]);		
	},
	isAdReadyToShow: function(zoneId, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "isAdReadyToShow", [ zoneId ]);		
	},
	showAd: function(adId,back_disabled,immersive_mode,rotation_mode, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "showAd", [ adId,back_disabled,immersive_mode,rotation_mode ]);		
	},
	startIntent: function(actionType,uri,isService,requestCode,packageName) {
		cordova.exec(null,null,"TapsellCordovaInterface","startIntent",[ actionType,uri,isService,requestCode,packageName ]);
	},
	close: function() {
		cordova.exec(null,null,"TapsellCordovaInterface","close",[ 0 ]);
	},
	replay: function() {
		cordova.exec(null,null,"TapsellCordovaInterface","replay",[ 0 ]);
	}
};