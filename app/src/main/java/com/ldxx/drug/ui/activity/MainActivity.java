package com.ldxx.drug.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldxx.drug.R;
import com.ldxx.drug.adapter.MenuListAdapter;
import com.ldxx.drug.base.BaseActivity;
import com.ldxx.drug.component.AppComponent;
import com.ldxx.drug.component.DaggerMainComponent;
import com.ldxx.drug.model.bean.MenuList;
import com.ldxx.drug.module.MainModule;
import com.ldxx.drug.module.MenuListModule;
import com.ldxx.drug.presenter.MainPresenter;
import com.ldxx.drug.ui.view.MainView;
import com.ldxx.drug.utils.AppUtils;
import com.ldxx.drug.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Inject
    MainPresenter presenter;

    @Inject
    AppUtils appUtils;
    @Inject
    ToastUtil toastUtil;

    MenuListAdapter adapter;
    List<MenuList> data = new ArrayList<>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new MenuListAdapter(data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        View view = getLayoutInflater().inflate(R.layout.load_more,
                (ViewGroup) recycler.getParent(), false);
        adapter.setLoadingView(view);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getMenuList(page);
            }
        });

        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter bAdapter, View view, int position) {
                //toastUtil.showToast(adapter.getData().get(position).getName(), Toast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("id",adapter.getData().get(position).getId());
                startActivity(intent);
            }
        });


        Log.e(TAG, "onCreate: " + appUtils.getVersionName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getMenuList(page);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .menuListModule(new MenuListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setItems(List<MenuList> items) {
        adapter.addData(items);
        page++;
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
        recycler.setVisibility(View.VISIBLE);
    }
}
