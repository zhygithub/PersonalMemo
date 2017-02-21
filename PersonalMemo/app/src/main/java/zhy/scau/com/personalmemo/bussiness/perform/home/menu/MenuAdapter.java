package zhy.scau.com.personalmemo.bussiness.perform.home.menu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;
import zhy.scau.com.personalmemo.R;

/**
 * Created by ZhengHy on 2017-02-21.
 */

public class MenuAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<String> mListName;

    public MenuAdapter(Context mContext, List<String> mListName) {
        this.mContext = mContext;
        this.mListName = mListName;
    }

    public void addItem(String name){
        if(mListName == null){
            mListName = new ArrayList<>();
        }
        mListName.add(name);
        notifyDataSetChanged();
    }

    public boolean removeItem(int position){
        if(!NullPointUtils.isEmpty(mListName) && position >= 0 && position < mListName.size()){
            mListName.remove(position);
            notifyDataSetChanged();
            return true;
        }

        return false;
    }

    @Override
    public int getCount() {
        return mListName == null? 0 : mListName.size();
    }

    @Override
    public Object getItem(int i) {
        return mListName.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = new TextView(mContext);
            TextView tv = (TextView) view;
            tv.setTextColor(mContext.getColor(R.color.xj_color_white));
            tv.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.xj_text_size_20));
            tv.setGravity(Gravity.CENTER);
        }
        TextView tv = (TextView) view;
        tv.setText(mListName.get(i));


        return view;
    }
}
