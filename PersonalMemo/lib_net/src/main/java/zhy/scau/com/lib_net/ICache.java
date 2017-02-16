package zhy.scau.com.lib_net;


import zhy.scau.com.lib_net.http.HttpRequest;
import zhy.scau.com.lib_net.http.HttpResponse;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 缓存接口
 */
public interface ICache {
    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return
     */
    HttpResponse get(HttpRequest key);

    /**
     * 设置缓存
     *
     * @param key   缓存的key
     * @param value 缓存的值
     */
    void put(HttpRequest key, HttpResponse value);
}
