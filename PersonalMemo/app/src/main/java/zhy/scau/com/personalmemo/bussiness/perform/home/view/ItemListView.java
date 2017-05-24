package zhy.scau.com.personalmemo.bussiness.perform.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.model.MemoItem;
import zhy.scau.com.personalmemo.bussiness.perform.home.adapter.ItemListAdapter;
import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IHomeContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IItemsListContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.enums.ItemListShowType;
import zhy.scau.com.personalmemo.bussiness.perform.home.presenter.ItemListPresenter;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;

/**
 * Created by ZhengHy on 2017-02-23.
 */

public class ItemListView extends CommonView<IItemsListContract.IItemsListPresenter>  implements IItemsListContract.IItemsListView{

    /**
     * 备忘列表
     */
    private RecyclerView mRlItems;

    /**
     * 备忘列表 适配器
     */
    private ItemListAdapter mItemListAdapter;

    /**
     * 备忘列表点击事件
     */
    private ItemListAdapter.OnItemClickListener mEvent;



    public ItemListView(@NonNull IHostControl control) {
        super(control);
        mItemListAdapter = new ItemListAdapter(getActivityContext(), null);
        mEvent = new ItemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                getPresenter().selectMemo(position);

                List<MemoItem> data = mItemListAdapter.getData();
                MemoItem memoItem = data.get(position);

                data.remove(position);
                removeItem(position, data);
            }
        };

    }

    @Override
    public void onCreate(Bundle savedInstanceState, View root) {
        super.onCreate(savedInstanceState, root);

        initList(root);
    }


    private void initList(View root) {
        mRlItems = (RecyclerView) root.findViewById(R.id.xj_main_activity_rv_items);
        mRlItems.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }




    @Override
    protected ItemListPresenter init() {
        return new ItemListPresenter(getHostControl(),this);
    }

    @Override
    public void updateData(List<MemoItem> data) {
        mItemListAdapter = new ItemListAdapter(getActivityContext(), data);
        mItemListAdapter.setItemClickListener(mEvent);
        mRlItems.setAdapter(mItemListAdapter);
    }

    @Override
    public void addItem(int position, List<MemoItem> data) {
        if(position >= 0){
            mItemListAdapter.setData(data);
            mItemListAdapter.notifyItemInserted(position);
        }
    }

    @Override
    public void updateItem(int position, List<MemoItem> data) {
        if(position >= 0 && position< data.size()){
            mItemListAdapter.setData(data);
            mItemListAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void removeItem(int position, List<MemoItem> data) {
            //以下三个顺序不能缺少和替换，先删除源数据，然后展示动画，最后对于以后的项目进行范围更新
            mItemListAdapter.setData(data);
            mItemListAdapter.notifyItemRemoved(position);
            mItemListAdapter.notifyItemRangeChanged(position,data.size());


    }

    @Override
    public void changeShowType(ItemListShowType showType) {

    }


}
