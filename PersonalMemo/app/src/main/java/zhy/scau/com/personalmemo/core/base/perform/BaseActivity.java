package zhy.scau.com.personalmemo.core.base.perform;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import zhy.scau.com.personalmemo.R;

/**
 * Created by ZhengHy on 2016-12-15.
 *
 * activity 基类
 */

public class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
