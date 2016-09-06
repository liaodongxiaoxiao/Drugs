package com.ldxx.drug.api;



import com.ldxx.drug.model.bean.MenuDetail;
import com.ldxx.drug.model.bean.MenuList;
import com.ldxx.drug.model.bean.ResultBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liaodongxiaoxiao on
 * 2016/8/31.
 */

public interface MenuApi {

    @GET("/tngou/drug/list?id=0&rows=10")
    Observable<ResultBean<List<MenuList>>> getMenuList(@Query("page") int page);

    @GET("/tngou/drug/show")
    Observable<MenuDetail> getDetail(@Query("id") String page);
}
