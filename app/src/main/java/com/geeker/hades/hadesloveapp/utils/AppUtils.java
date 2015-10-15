package com.geeker.hades.hadesloveapp.utils;

import com.geeker.hades.hadesloveapp.entity.APPEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * Created by Hades on 10/12/15.
 */
public class AppUtils {

    private static String TAG = "AppUtils";

    /**
     * Use reflect to get Package Usage Statistics data.<br>
     */
    public static LinkedList<APPEntity> getPkgUsageStats() {
        LogUtils.d(TAG, "[getPkgUsageStats]");
        LinkedList<APPEntity> appEntitys = new LinkedList<APPEntity>();
        try {

            Class<?> cServiceManager = Class.forName("android.os.ServiceManager");
            Method mGetService = cServiceManager.getMethod("getService",
                    java.lang.String.class);
            Object oRemoteService = mGetService.invoke(null, "usagestats");

            // IUsageStats oIUsageStats =
            // IUsageStats.Stub.asInterface(oRemoteService)
            Class<?> cStub = Class
                    .forName("com.android.internal.app.IUsageStats$Stub");
            Method mUsageStatsService = cStub.getMethod("asInterface",
                    android.os.IBinder.class);
            Object oIUsageStats = mUsageStatsService.invoke(null,
                    oRemoteService);

            // PkgUsageStats[] oPkgUsageStatsArray =
            // mUsageStatsService.getAllPkgUsageStats();
            Class<?> cIUsageStatus = Class.forName("com.android.internal.app.IUsageStats");
            Method mGetAllPkgUsageStats = cIUsageStatus.getMethod("getAllPkgUsageStats", (Class[]) null);
            Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats.invoke(oIUsageStats, (Object[]) null);
            LogUtils.d(TAG, "[getPkgUsageStats] oPkgUsageStatsArray = "+oPkgUsageStatsArray);

            Class<?> cPkgUsageStats = Class.forName("com.android.internal.os.PkgUsageStats");
            StringBuffer sb = new StringBuffer();

            appEntitys = dealWithAllAppInfos(appEntitys, oPkgUsageStatsArray, cPkgUsageStats, sb);

            LogUtils.d(TAG, "[getPkgUsageStats] " + sb.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return appEntitys;
    }

    private static LinkedList<APPEntity> dealWithAllAppInfos(LinkedList<APPEntity> appEntitys, Object[] oPkgUsageStatsArray, Class<?> cPkgUsageStats, StringBuffer sb) throws IllegalAccessException, NoSuchFieldException {
        for (Object pkgUsageStats : oPkgUsageStatsArray) {
            // get pkgUsageStats.packageName, pkgUsageStats.launchCount,
            // pkgUsageStats.usageTime
            String packageName = (String) cPkgUsageStats.getDeclaredField(
                    "packageName").get(pkgUsageStats);
            int launchCount = cPkgUsageStats
                    .getDeclaredField("launchCount").getInt(pkgUsageStats);
            long usageTime = cPkgUsageStats.getDeclaredField("usageTime")
                    .getLong(pkgUsageStats);
            if (launchCount > 0)
                LogUtils.d(TAG, "[getPkgUsageStats] " + packageName + "  count: "
                        + launchCount + "  time:  " + usageTime);
            else {
                sb.append(packageName + "; ");
            }

            APPEntity appEntity = new APPEntity(packageName,launchCount,usageTime);
            appEntitys.add(appEntity);
        }

        return appEntitys;
    }


}
