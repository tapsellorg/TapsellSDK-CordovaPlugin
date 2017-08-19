#import "TapsellCordovaInterface.h"
#import <TapsellSDKv3/TapsellSDKv3.h>

@implementation TapsellCordovaInterface

- (void) pluginInitialize
{
	[[TapsellExtraPlatformsController sharedInstance] setPlatformControllerOnAdAvailable:^(TapsellAd * _Nullable ad) {
		if(self.zoneCallBackIds != nil)
		{
			if([[self.zoneCallBackIds allKeys] containsObject:ad.zoneId])
			{
				NSMutableDictionary *dict = [NSMutableDictionary dictionary];
				[dict setValue:@"onAdAvailable" forKey:@"action"];
				[dict setValue:ad.zoneId forKey:@"zoneId"];
				[dict setValue:[ad getId] forKey:@"adId"];
				CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
				[self.commandDelegate sendPluginResult:pluginResult callbackId:[self.zoneCallBackIds valueForKey:ad.zoneId]];
				//[self.zoneCallBackIds removeObjectForKey:ad.zoneId];
			}
		}
	} onNoAdAvailable:^(NSString * _Nullable zoneId) {
		if(self.zoneCallBackIds != nil)
		{
			if([[self.zoneCallBackIds allKeys] containsObject:zoneId])
			{
				NSMutableDictionary *dict = [NSMutableDictionary dictionary];
				[dict setValue:@"onNoAdAvailable" forKey:@"action"];
				[dict setValue:zoneId forKey:@"zoneId"];
				CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
				[self.commandDelegate sendPluginResult:pluginResult callbackId:[self.zoneCallBackIds valueForKey:zoneId]];
				//[self.zoneCallBackIds removeObjectForKey:zoneId];
			}
		}
	} onError:^(NSString * _Nullable error, NSString * _Nullable zoneId) {
		if(self.zoneCallBackIds != nil)
		{
			if([[self.zoneCallBackIds allKeys] containsObject:zoneId])
			{
				NSMutableDictionary *dict = [NSMutableDictionary dictionary];
				[dict setValue:@"onError" forKey:@"action"];
				[dict setValue:zoneId forKey:@"zoneId"];
				[dict setValue:error forKey:@"error"];
				CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
				[self.commandDelegate sendPluginResult:pluginResult callbackId:[self.zoneCallBackIds valueForKey:zoneId]];
				//[self.zoneCallBackIds removeObjectForKey:zoneId];
			}
		}
	} onExpiring:^(TapsellAd * _Nullable ad) {
		if(self.zoneCallBackIds != nil)
		{
			if([[self.zoneCallBackIds allKeys] containsObject:ad.zoneId])
			{
				NSMutableDictionary * dict = [NSMutableDictionary dictionary];
				[dict setValue:@"onExpiring" forKey:@"action"];
				[dict setValue:ad.zoneId forKey:@"zoneId"];
				[dict setValue:[ad getId] forKey:@"adId"];
				CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
				[self.commandDelegate sendPluginResult:pluginResult callbackId:[self.zoneCallBackIds valueForKey:ad.zoneId]];
				//[self.zoneCallBackIds removeObjectForKey:ad.zoneId];
			}
		}
	} onOpened:^(TapsellAd * _Nullable ad) {
		if(self.showCallback != nil)
		{
            NSMutableDictionary * dict = [NSMutableDictionary dictionary];
			[dict setValue:@"onOpened" forKey:@"action"];
			[dict setValue:ad.zoneId forKey:@"zoneId"];
			[dict setValue:[ad getId] forKey:@"adId"];
			CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
			[self.commandDelegate sendPluginResult:pluginResult callbackId:self.showCallback.callbackId];
			//[self.showCallback removeObjectForKey:ad.zoneId];

		}
	} onClosed:^(TapsellAd * _Nullable ad) {
		if(self.showCallback != nil)
		{
						NSMutableDictionary *dict = [NSMutableDictionary dictionary];
			[dict setValue:@"onClosed" forKey:@"action"];
			[dict setValue:ad.zoneId forKey:@"zoneId"];
			[dict setValue:[ad getId] forKey:@"adId"];
			CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
			[self.commandDelegate sendPluginResult:pluginResult callbackId:self.showCallback.callbackId];
			self.showCallback = nil;
			//[self.showCallback removeObjectForKey:ad.zoneId];

		}
	}];
	[TapsellExtraPlatformsController setAdShowFinishedCallback:^(TapsellAd *ad, BOOL completed) {
		if(self.rewardCallback != nil)
		{
			NSMutableDictionary * dict = [NSMutableDictionary dictionary];
			[dict setValue:@"onAdShowFinished" forKey:@"action"];
			[dict setValue:ad.zoneId forKey:@"zoneId"];
			[dict setValue:[ad getId] forKey:@"adId"];
			[dict setValue:[NSNumber numberWithBool:completed] forKey:@"completed"];
			[dict setValue:[NSNumber numberWithBool:[ad isRewardedAd]] forKey:@"rewarded"];
			CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dict];	
			[self.commandDelegate sendPluginResult:pluginResult callbackId:self.rewardCallback.callbackId];
			//self.rewardCallback = nil;
		}
	}];
}

- (void)initialize:(CDVInvokedUrlCommand*)command
{
    NSString* appKey = [[command arguments] objectAtIndex:0];
    [TapsellExtraPlatformsController initializeWithAppKey:appKey];

	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];	
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getVersion:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[TapsellExtraPlatformsController getVersion]];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setDebugMode:(CDVInvokedUrlCommand*)command
{
	BOOL debugMode = [[command arguments] objectAtIndex:0];
    [TapsellExtraPlatformsController setDebugMode:debugMode];
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];	
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)isDebugMode:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:[TapsellExtraPlatformsController isDebugMode]];	
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setAppUserId:(CDVInvokedUrlCommand*)command
{
	NSString* appUserId = [[command arguments] objectAtIndex:0];
	[TapsellExtraPlatformsController setAppUserId:appUserId];
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];	
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)requestAd:(CDVInvokedUrlCommand*)command
{
	NSString* zoneId = [[command arguments] objectAtIndex:0];
	BOOL cached = [[command arguments] objectAtIndex:1];
	[TapsellExtraPlatformsController requestAdForZone:zoneId isCached:cached];
	if(self.zoneCallBackIds == nil)
	{
		self.zoneCallBackIds = [NSMutableDictionary<NSString *, NSString *> dictionary];
	}
	[self.zoneCallBackIds setValue:command.callbackId forKey:zoneId];
}

- (void)showAd:(CDVInvokedUrlCommand*)command
{
	NSString* adId = [[command arguments] objectAtIndex:0];
	BOOL backDisabled = [[command arguments] objectAtIndex:1];
	//BOOL immersivMode = [[command arguments] objectAtIndex:2];
	NSNumber* rotationMode = [[command arguments] objectAtIndex:3];
	BOOL showDialog = [[command arguments] objectAtIndex:4];
	
	TSAdShowOptions* showOptions = [[TSAdShowOptions alloc] init];
	[showOptions setBackDisabled:backDisabled];
    [showOptions setShowDialoge:showDialog];
    [showOptions setOrientationNumber:rotationMode];
    [[TapsellExtraPlatformsController sharedInstance] showAd:adId withOptions:showOptions];
	_showCallback = command;
}

- (void)setRewardCallback:(CDVInvokedUrlCommand*)command
{
	_rewardCallback = command;
}

// Unsupported functions

- (void)setAutoHandlePermissions:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setMaxAllowedBandwidthUsage:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setMaxAllowedBandwidthUsagePercentage:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)clearBandwidthUsageConstrains:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)startIntent:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)close:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)replay:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Not Supported"];
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
