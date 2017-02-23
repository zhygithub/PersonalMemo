package zhy.scau.com.personalmemo.bussiness.perform.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.model.MemoItem;

/**
 * Created by ZhengHy on 2017-02-23.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListHolder> {

    /***
     * 上下文
     */
    private final Context mContext;

    /**
     * 数据
     */
    private List<MemoItem> mData;

    /**
     * 布局翻译器
     */
    private final LayoutInflater mLayoutInflater;

    /**
     * 单元点击事件
     */
    private OnItemClickListener mOnItemClickListener;

    public ItemListAdapter(Context mContext, List<MemoItem> mData) {

        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public List<MemoItem> getData() {
        return mData;
    }

    public void setData(List<MemoItem> data) {
        this.mData = data;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemListHolder(mLayoutInflater.inflate(R.layout.xj_main_list_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemListHolder holder, final int position) {

        RxView.clicks(holder.root).throttleFirst(1 , TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ItemListHolder extends RecyclerView.ViewHolder {

        public View root;

        public ItemListHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.xj_main_lsit_cell_root);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
