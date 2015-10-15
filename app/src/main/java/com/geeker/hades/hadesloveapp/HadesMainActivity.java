package com.geeker.hades.hadesloveapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.geeker.hades.hadesloveapp.entity.APPEntity;
import com.geeker.hades.hadesloveapp.utils.AppUtils;

import java.util.LinkedList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HadesMainActivity extends AppCompatActivity {

    private static String TAG = "HadesMainActivity";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv_hades_app_list)
    ListView lvHadesAppList;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private LinkedList<APPEntity> appListInfos = new LinkedList<APPEntity>();

    private int getLayoutId() {
        return R.layout.activity_hades_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        appListInfos = AppUtils.getPkgUsageStats();


        initOtherView();
    }

    private void initOtherView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hades_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public PkgUsageStats[] getAllPkgUsageStats() {
//        this.enforceCallingOrSelfPermission(
//                android.Manifest.permission.PACKAGE_USAGE_STATS, null);
//        synchronized (mStatsLock) {
//            int size = mLastResumeTimes.size();
//            if (size <= 0) {
//                return null;
//            }
//            PkgUsageStats retArr[] = new PkgUsageStats[size];
//            int i = 0;
//            for (Map.Entry<String, Map<String, Long>> entry : mLastResumeTimes.entrySet()) {
//                String pkg = entry.getKey();
//                long usageTime = 0;
//                int launchCount = 0;
//
//                PkgUsageStatsExtended pus = mStats.get(pkg);
//                if (pus != null) {
//                    usageTime = pus.mUsageTime;
//                    launchCount = pus.mLaunchCount;
//                }
//                retArr[i] = new PkgUsageStats(pkg, launchCount, usageTime, entry.getValue());
//                i++;
//            }
//            return retArr;
//        }
//    }


//
//    private void getPkgUsageStats()
//    {
//        IUsageStats statsService = (IUsageStats) IUsageStats.Stub.
//                asInterface(ServiceManager.getService("usagestats"));
//
//        PkgUsageStats[] pkgStats = null;
//        try {
//            pkgStats = statsService.getAllPkgUsageStats();
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        if(pkgStats != null)
//        {
//            StringBuffer sb = new StringBuffer();
//            sb.append("nerver used : ");
//            for(PkgUsageStats usageStats : pkgStats)
//            {
//                String packageName = usageStats.packageName;
//                int launchCount = usageStats.launchCount;
//                long usageTime = usageStats.usageTime;
//                if(launchCount > 0)
//                    Log.v("getPkgUsageStats", packageName + "  count: " + launchCount + "  time:  "
//                            + usageTime);
//                else{
//                    sb.append(packageName+" ");
//                }
//            }
//
//            Log.v("getPkgUsageStats",sb.toString());
//        }
//    }
//


}
