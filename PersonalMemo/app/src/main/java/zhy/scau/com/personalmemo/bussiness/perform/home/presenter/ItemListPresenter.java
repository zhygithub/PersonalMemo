package zhy.scau.com.personalmemo.bussiness.perform.home.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zhy.scau.com.personalmemo.bussiness.model.MemoItem;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.presenter.CommonPresenter;
import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IItemsListContract;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;

/**
 * Created by ZhengHy on 2017-02-23.
 */

public class ItemListPresenter extends CommonPresenter<IItemsListContract.IItemsListView> implements IItemsListContract.IItemsListPresenter {

    /**
     * 备忘数据列表
     */
    private List<MemoItem> mListMemos;

    public ItemListPresenter(@NonNull IHostControl control, CommonView view) {
        super(control, view);

        mListMemos = getData();

    }

    @Override
    public void onCreate(Bundle savedInstanceState, View root) {
        super.onCreate(savedInstanceState, root);
        getView().updateData(mListMemos);
    }

    @Override
    public void selectMemo(int position) {

    }

    @Override
    public void addMemo() {

        mListMemos.add(new MemoItem());
        getView().addItem(mListMemos.size()-1 , mListMemos);
    }

    public List<MemoItem> getData() {
        List<MemoItem> data = new ArrayList<>();
        data.add(new MemoItem());
        data.add(new MemoItem());
        data.add(new MemoItem());
        data.add(new MemoItem());

        return data;
    }
}
