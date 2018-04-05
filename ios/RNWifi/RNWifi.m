#include "RNWifi.h"
#import "React/RCTBridge.h"
#import <NetworkExtension/NetworkExtension.h>

@implementation RNWifi

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getWifiList:(RCTResponseSenderBlock)callback) {
    
    NSMutableDictionary* result = [NSMutableDictionary new];
    
    for (NEHotspotNetwork *hotspotNetwork in [NEHotspotHelper supportedNetworkInterfaces]) {
        result[@"SSID"] = hotspotNetwork.SSID;
        result[@"BSSID"] = hotspotNetwork.BSSID;
        result[@"isSecure"] = @(hotspotNetwork.secure);
        result[@"level"] = @(hotspotNetwork.signalStrength);
    }
    return callback(@[NSNull, result]);
}

@end
