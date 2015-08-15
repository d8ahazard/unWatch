package com.digitalhigh.unwatch;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class unWatchMain implements IXposedHookLoadPackage {

    public unWatchMain(){

    }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.airwatch.androidagent")) {
            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.d", lpparam.classLoader,
                    "isDeviceRooted", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.IntelManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.IntelManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            param.setResult(false);

                        }
                    });

            XposedHelpers.findAndHookMethod("com.airwatch.agent.enterprise.SonyManager", lpparam.classLoader,
                    "isDeviceCompromised", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws
                                Throwable {
                            param.setResult(false);

                        }
                    });

        }
    }

}