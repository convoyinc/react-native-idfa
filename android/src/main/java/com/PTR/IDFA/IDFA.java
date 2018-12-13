package com.PTR.IDFA;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class IDFA extends ReactContextBaseJavaModule {

    public IDFA(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "PTRIDFA";
    }

    @ReactMethod
    public void getIDFA(Promise promise) {
        try {
            AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.getReactApplicationContext());
            String advertisingIdentifier = adInfo.getId();
            Boolean isAdvertisingTrackingEnabled = adInfo.isLimitAdTrackingEnabled();

            WritableMap response = new WritableNativeMap();
            response.putString("advertisingIdentifier", advertisingIdentifier);
            response.putBoolean("isAdvertisingTrackingEnabled", isAdvertisingTrackingEnabled);
            
            promise.resolve(response);
        } catch (Exception e) {
            promise.reject(e);
        }
    }
}
