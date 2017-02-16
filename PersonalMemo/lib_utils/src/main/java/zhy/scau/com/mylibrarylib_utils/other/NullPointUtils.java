package zhy.scau.com.mylibrarylib_utils.other;

import android.text.TextUtils;

import java.io.File;
import java.util.List;

/**
 * Created by ZhengHy on 2017-01-19
 *
 * 空指针判断工具类
 *
 */
public class NullPointUtils {

    /**
     * 判断是否非空
     *
     * 注意 Object = String.class 将按照String的判空行为， Object = List.class  将按照List的判空行为，以此类推
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return isEmpty((String) object);
        } else if (object instanceof List) {
            return isEmpty((List) object);
        } else if (object instanceof File) {
            return isEmpty((File) object);
        }
        return false;
    }

    /**
     * 判断 多个对象是否为空，只要有一个为空 则返回True，只有全部不为空，才返回False
     *
     * 注意 Object = String.class 将按照String的判空行为， Object = List.class  将按照List的判空行为，以此类推
     *
     * 注意 不要使用 数组，如果要判断数组非空，请调用isEmptyArray
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        boolean isEmpty = false;
        for (Object object : objects) {
            if (isEmpty(object)) {
                isEmpty = true;
                break;
            }
        }

        return isEmpty;
    }

    /**
     *
     * 判断 多个对象是否为空，只要有一个为空 则返回True，只有全部不为空，才返回False
     *
     * @param objects
     * @return
     */
    public static boolean isEmptyArray(Object[] objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        boolean isEmpty = false;
        for (Object object : objects) {
            if (isEmpty(object)) {
                isEmpty = true;
                break;
            }
        }

        return isEmpty;
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value);
    }

    /**
     * 判断列表是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断文件是否为空
     *
     * @param file
     * @return
     */
    public static boolean isEmpty(File file) {
        return file == null || !file.exists();
    }
}
