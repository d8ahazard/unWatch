package com.digitalhigh.unwatch;

import android.util.Log;

import org.json.JSONObject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class unWatchMain implements IXposedHookLoadPackage {

    public unWatchMain(){

    }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.airwatch.androidagent")) {
            XposedBridge.log("unWatch: Hooking application");
            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.d", lpparam.classLoader,
                    "isDeviceRooted", new XC_MethodHook() {

                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            XposedBridge.log("unWatch: Hooking agent.enterprise.d");
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.IntelManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            XposedBridge.log("unWatch: Hooking IntelManager");
                            param.setResult(false);

                        }
                    });


            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.SonyManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            XposedBridge.log("unWatch: Hooking SonyManager");
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.core.AirWatchDevice", lpparam.classLoader,
                    "a", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            XposedBridge.log("unWatch: Hooking AWD");
                            param.setResult(false);

                        }
                    });
            try {
                XposedHelpers.findAndHookMethod("com.airwatch.agent.b.a", lpparam.classLoader,
                        "f", JSONObject.class, new XC_MethodHook() {
                            @Override

                            protected void afterHookedMethod(MethodHookParam param) throws
                                    Throwable {
                                JSONObject jo = (JSONObject) param.args[0];
                                jo.put("IsCompromised", "false");
                                XposedBridge.log("unWatch: Hooking JSON");
                                return;

                            }
                        });
            } catch (Throwable e) {
                Log.e("Unwatch: ", "Error " + e);
            }

        } else if (lpparam.packageName.equals("com.airwatch.email")) {
            XposedHelpers.findAndHookMethod("com.airwatch.sdk.SDKManager", lpparam.classLoader,
                    "isCompromised", new XC_MethodHook() {
                        @Override

                        protected void afterHookedMethod(MethodHookParam param) throws
                                Throwable {
                            XposedBridge.log("unWatch: Hooking Email");

                            param.setResult(false);


                        }
                    });
        }
    }

}