package com.ldxx.drug.base;

/**
 * 请求数据的回调<br>
 * Presenter用于接受model获取（加载）数据后的回调
 * Created by Administrator on 2016/3/23.
 */
public interface IBaseRequestCallBack<T> {

    /**
     * 请求失败
     *
     * @param e 失败的原因
     */
    void requestError(Throwable e);

    /**
     * 请求结束
     */
    void requestComplete();

    /**
     * 请求成功
     *
     * @param callBack 根据业务返回相应的数据
     */
    void requestSuccess(T callBack);
}
