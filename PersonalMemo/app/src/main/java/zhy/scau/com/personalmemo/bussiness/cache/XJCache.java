package zhy.scau.com.personalmemo.bussiness.cache;


import zhy.scau.com.lib_net.cache.BaseCache;
import zhy.scau.com.lib_net.http.HttpRequest;
import zhy.scau.com.lib_net.http.HttpResponse;

/**
 * Created by ZhengHy on 2017-01-03
 * <p>
 * 默认的缓存操作
 */
public class XJCache extends BaseCache {
    @Override
    public HttpResponse get(HttpRequest key) {
        return null;
    }

    @Override
    public void put(HttpRequest key, HttpResponse value) {

    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
