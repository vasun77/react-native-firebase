package in.nvasu.FirebaseBridge;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavascriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.uimanager.ViewManager;

import java.util.ArrayList;
import Java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirebaseBridgePackage implements ReactPackage {

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    modules.add(new RNFirebaseModule(reactContext));
    return modules;
  }  

}
