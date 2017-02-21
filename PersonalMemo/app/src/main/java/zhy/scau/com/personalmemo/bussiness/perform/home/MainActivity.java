package zhy.scau.com.personalmemo.bussiness.perform.home;

import android.os.Bundle;

import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.perform.home.view.HomeView;
import zhy.scau.com.personalmemo.core.base.perform.XJBaseActivity;


public class MainActivity extends XJBaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xj_main_activity);
        getDelegateManager().addItems(new HomeView(this));

    }


}
