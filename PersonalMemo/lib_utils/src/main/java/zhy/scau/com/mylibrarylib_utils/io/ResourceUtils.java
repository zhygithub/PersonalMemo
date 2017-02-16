package zhy.scau.com.mylibrarylib_utils.io;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;

/**
 * ResourceUtils 资源工具类 从Assets Raw中读取文件
 */
public class ResourceUtils {

    private static final String TAG = ResourceUtils.class.getName();
    /**
     * 从Assets文件中读取内容
     *
     * @param context Context
     * @param fileName 文件名称
     * @return
     */
    public static String getFileFromAssets(Context context, String fileName) {
        return getFileFromAssets(context, fileName, "\n");
    }

    /**
     * 从Assets文件中读取内容
     *
     * @param context Context
     * @param fileName 文件名称
     * @param lineSplit 每一行的换行符 常见的有 \n \r \r\n , 不填写不会有默认换行
     * @return
     */
    public static String getFileFromAssets(Context context, String fileName, String lineSplit) {
        if (NullPointUtils.isEmpty(context, fileName)) {
            return null;
        }

        StringBuilder sb = new StringBuilder("");
        BufferedReader br = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + lineSplit);
            }
            return sb.toString();
        } catch (Exception e) {
            Log.e(TAG, "getFileFromAssets fail .", e);
            return null;
        } finally {
            IoOptUtils.closeQuietly(br);
        }
    }


    /**
     * 从Raw文件中读取内容
     *
     * @param context
     * @param resId
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        return geFileFromRaw(context, resId, "\n");
    }

    /**
     * 从Raw文件中读取内容
     *
     * @param context
     * @param resId
     * @param lineSplit
     * @return
     */
    public static String geFileFromRaw(Context context, int resId, String lineSplit) {
        if (NullPointUtils.isEmpty(context)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + lineSplit);
            }
            return sb.toString();
        } catch (Exception e) {
            Log.e(TAG, "geFileFromRaw fail .", e);
            return null;
        } finally {
            IoOptUtils.closeQuietly(br);
        }
    }

}
