#import <Cordova/CDVPlugin.h>
#import <Foundation/Foundation.h>


@interface TapsellCordovaInterface : CDVPlugin

@property(nonatomic, strong, readwrite) NSMutableDictionary<NSString *, NSString *> * zoneCallBackIds;
@property(nonatomic, strong, readwrite) CDVInvokedUrlCommand * rewardCallback;
@property(nonatomic, strong, readwrite) CDVInvokedUrlCommand * showCallback;

- (void)initialize:(CDVInvokedUrlCommand*)command;
- (void)getVersion:(CDVInvokedUrlCommand*)command;
- (void)setDebugMode:(CDVInvokedUrlCommand*)command;
- (void)isDebugMode:(CDVInvokedUrlCommand*)command;
- (void)setAppUserId:(CDVInvokedUrlCommand*)command;
- (void)requestAd:(CDVInvokedUrlCommand*)command;
- (void)showAd:(CDVInvokedUrlCommand*)command;
- (void)setRewardCallback:(CDVInvokedUrlCommand*)command;

- (void)setAutoHandlePermissions:(CDVInvokedUrlCommand*)command;
- (void)setMaxAllowedBandwidthUsage:(CDVInvokedUrlCommand*)command;
- (void)setMaxAllowedBandwidthUsagePercentage:(CDVInvokedUrlCommand*)command;
- (void)clearBandwidthUsageConstrains:(CDVInvokedUrlCommand*)command;
- (void)startIntent:(CDVInvokedUrlCommand*)command;
- (void)close:(CDVInvokedUrlCommand*)command;
- (void)replay:(CDVInvokedUrlCommand*)command;



@end
