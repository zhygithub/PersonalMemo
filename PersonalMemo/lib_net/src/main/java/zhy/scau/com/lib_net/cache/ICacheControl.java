package zhy.scau.com.lib_net.cache;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 缓存控制接口
 */
public interface ICacheControl {

    /**
     * 缓存文件大小
     *
     * @return
     */
    long size();

    /**
     * 清空缓存
     */
    void clear();
}
