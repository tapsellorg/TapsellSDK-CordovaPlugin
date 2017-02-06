
window.tapsell_rotation_locked_portrait = 1;
window.tapsell_rotation_locked_landscape = 2;
window.tapsell_rotation_unlocked = 3;
window.tapsell_rotation_locked_reversed_portrait = 4;
window.tapsell_rotation_locked_reversed_landscape = 5;

module.exports = {
	initialize: function(appKey) {
		cordova.exec(function (result) {console.log("Tapsell initialized successfully.");},null,"TapsellCordovaInterface","initialize",[appKey]);
	},
	requestAd: function(zoneId, icCached, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "requestAd", [ zoneId , icCached]);		
	},
	isAdReadyToShow: function(zoneId, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "isAdReadyToShow", [ zoneId ]);		
	},
	showAd: function(adId,back_disabled,immersive_mode,rotation_mode, show_dialog, callback) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "showAd", [ adId,back_disabled,immersive_mode,rotation_mode,show_dialog ]);		
	},
	getVersion: function( callback ) {
		cordova.exec(callback, null, "TapsellCordovaInterface", "getVersion", [ 0 ]);		
	},
	setDebugMode: function( debug ) {
		cordova.exec(null, null, "TapsellCordovaInterface", "setDebugMode", [ debug ]);
	},
	setAppUserId: function ( appUserId) {
		cordova.exec(null, null, "TapsellCordovaInterface", "setAppUserId", [ appUserId ]);
	},
	setAutoHandlePermissions: function( handle ) {
		cordova.exec(null, null, "TapsellCordovaInterface", "setAutoHandlePermissions", [ handle ]);
	},
	setMaxAllowedBandwidthUsage: function( maxBpsSpeed ) {
		cordova.exec(null, null, "TapsellCordovaInterface", "setMaxAllowedBandwidthUsage", [ maxBpsSpeed ]);
	},
	setMaxAllowedBandwidthUsagePercentage: function( maxPercentage ) {
		cordova.exec(null, null, "TapsellCordovaInterface", "setMaxAllowedBandwidthUsagePercentage", [ maxPercentage ]);
	},
	clearBandwidthUsageConstrains: function( ) {
		cordova.exec(null, null, "TapsellCordovaInterface", "clearBandwidthUsageConstrains", [ 0 ]);
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