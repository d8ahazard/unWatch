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
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.airwatch.androidagent")) {
            XposedBridge.log("unWatch: Hooking application");
            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.d", lpparam.classLoader,
                    "isDeviceRooted", new XC_MethodHook() {

                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking agent.enterprise.d");
                            param.setResult(false);

                        }
                    });


            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.IntelManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking IntelManager");
                            param.setResult(false);

                        }
                    });


            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.SonyManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking SonyManager");
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.core.AirWatchDevice", lpparam.classLoader,
                    "a", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking AWD");
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.core.AirWatchDevice", lpparam.classLoader,
                    "c", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking Sense check");
                            param.setResult("");

                        }
                    });
            XposedHelpers.findAndHookMethod("com.airwatch.core.AirWatchDevice", lpparam.classLoader,
                    "AirWatchEnum.OemId d", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            Log.d("unWatch: ", "Hooking Sense check 2");
                            param.setResult("AirWatchEnum.OemId.a");

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
                                Log.d("unWatch: ", "Hooking JSON");

                            }
                        });
            } catch (Throwable e) {
                Log.e("Unwatch: ", "Error " + e);
            }

        } else if (lpparam.packageName.equals("com.airwatch.email")) {
            XposedBridge.log("unWatch: Hooking Email");
            XposedHelpers.findAndHookMethod("com.airwatch.sdk.SDKManager", lpparam.classLoader,
                    "isCompromised", new XC_MethodHook() {
                        @Override

                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {


                            param.setResult(false);


                        }
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("unWatch: ", "Telling email to shut up.");
                        }
                    });

        } else if (lpparam.packageName.contains("com.airwatch")) {
            XposedBridge.log("unWatch: Forcing universal hook");
            try {
                XposedBridge.hookAllMethods(Boolean.class, "isCompromised", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws
                            Throwable {


                        param.setResult(false);
                    }

                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("unWatch: ", "hooked universal method in package " + lpparam.packageName + " and class " + lpparam.getClass());
                    }
                });

            } catch (Throwable e) {
                XposedBridge.log("unWatch: error " + e);
            }

        }

    }
}