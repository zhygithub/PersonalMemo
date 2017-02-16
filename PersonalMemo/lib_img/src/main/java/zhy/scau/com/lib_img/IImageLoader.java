package zhy.scau.com.lib_img;

import android.widget.ImageView;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * ImageLoader
 */
public interface IImageLoader {
    /**
     * 显示图片 （保持兼容）
     *
     * @param uri       图片地址
     * @param imageView 图片ImageView
     */
    @Deprecated
    void displayImage(String uri, ImageView imageView);

    /**
     * ImageLoaderBuilder
     * <p>
     * 默认是Application Context
     *
     * @return IImageLoaderBuilder
     */
    IImageLoaderBuilder with();
}
