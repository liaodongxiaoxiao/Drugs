package com.ldxx.drug.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ldxx.drug.R;
import com.ldxx.drug.model.bean.MenuList;

import java.util.List;

/**
 * Created by liaodongxiaoxiao
 * on 2016/9/1.
 */

public class MenuListAdapter extends BaseQuickAdapter<MenuList> {
    public MenuListAdapter(List<MenuList> data) {
        super(R.layout.item_menu_list,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MenuList menuList) {
        baseViewHolder.setText(R.id.menu_name,menuList.getName())
                .setText(R.id.menu_description,menuList.getDescription())
                .setText(R.id.menu_keywords,menuList.getKeywords());
        setImageUri(baseViewHolder,menuList.getImg());
    }

    private void setImageUri(BaseViewHolder holder, String url) {
        SimpleDraweeView view = holder.getView(R.id.menu_img);
        view.setImageURI(Uri.parse("http://tnfs.tngou.net/img"+url));
    }
}
