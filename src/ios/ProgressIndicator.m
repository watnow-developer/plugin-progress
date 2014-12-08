#import <Cordova/CDV.h>
#import "ProgressIndicator.h"
#import "MBProgressHUD.h"

@implementation ProgressIndicator
@synthesize progressIndicator;


/**
 * Indicator
 */

- (void)showIndicator:(CDVInvokedUrlCommand *)command {
    
    // obtain commands
    NSString* text = [command.arguments objectAtIndex:0];
    NSString* detail = [command.arguments objectAtIndex:1];
    bool dim = [[command.arguments objectAtIndex:2] boolValue];
    //UIColor* color = [command.arguments objectAtIndex:1];
    
    // initialize indicator with options, text, detail
    self.progressIndicator = nil;
    self.progressIndicator = [MBProgressHUD showHUDAddedTo:self.webView.superview animated:YES];
    self.progressIndicator.mode = MBProgressHUDModeIndeterminate;
    self.progressIndicator.labelText = text;
    self.progressIndicator.detailsLabelText = detail;
    
    
    // Check if dim : true ? false
    if (dim == true) {
        self.progressIndicator.dimBackground = YES;
    }
    
    // Cordova success
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

/**
 * Change Indicator Labels
 */
- (void)changeLabels:(CDVInvokedUrlCommand *)command {
    
    // obtain commands
    NSString* text = [command.arguments objectAtIndex:0];
    NSString* detail = [command.arguments objectAtIndex:1];
    
    // initialize indicator with options, text, detail
    if (self.progressIndicator) {
        if([text length] != 0)
            self.progressIndicator.labelText = text;
        if([detail length] != 0)
            self.progressIndicator.detailsLabelText = detail;
    }
    
    // Cordova success
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

/**
 * Show Progress Bar
 */
- (void)showProgress:(CDVInvokedUrlCommand *)command {
    
    // obtain commands
    
    // obtain commands
    NSString* text = [command.arguments objectAtIndex:0];
    NSString* detail = [command.arguments objectAtIndex:1];
    bool dim = [[command.arguments objectAtIndex:2] boolValue];
    //int increment = [[command.arguments objectAtIndex:1] intValue];
    //NSNumber* incrementValue = @(increment);
    
    // initialize indicator with options, text, detail
    self.progressIndicator = nil;
    self.progressIndicator = [MBProgressHUD showHUDAddedTo:self.webView.superview animated:YES];
    self.progressIndicator.mode = MBProgressHUDModeDeterminateHorizontalBar;
    self.progressIndicator.labelText = text;
    self.progressIndicator.detailsLabelText = detail;
    self.progressIndicator.progress = 0.0f;
    
    // Check for dim : true ? false
    if (dim == true) {
        self.progressIndicator.dimBackground = YES;
    }
    
    // Load Progress bar with ::incrementValue
    //[self.progressIndicator showWhileExecuting:@selector(progressTask:) onTarget:self withObject:incrementValue animated:YES];
    
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

/**
 * Update Progress Value
 */
- (void)updateValue:(CDVInvokedUrlCommand *)command{
    int value = [[command.arguments objectAtIndex:0] intValue];
    if (self.progressIndicator) {
        self.progressIndicator.progress = (float)value/100.0f;
    }
}

/**
 * HIDE
 */

- (void)hide:(CDVInvokedUrlCommand*)command
{
    if (self.progressIndicator != nil) {
            [self.progressIndicator hide:YES];
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
            self.progressIndicator = nil;
    }else{
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
             self.progressIndicator = nil;
            return;
    }

}

@end
