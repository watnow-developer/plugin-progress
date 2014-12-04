#import <Cordova/CDV.h>
#import "MBProgressHUD.h"

@interface ProgressIndicator: CDVPlugin {
}

@property (nonatomic, assign) MBProgressHUD* progressIndicator;

- (void)showIndicator:(CDVInvokedUrlCommand*)command;
- (void)changeLabels:(CDVInvokedUrlCommand*)command;
- (void)showProgress:(CDVInvokedUrlCommand*)command;
- (void)updateValue:(CDVInvokedUrlCommand*)command;
- (void)hide:(CDVInvokedUrlCommand*)command;
@end