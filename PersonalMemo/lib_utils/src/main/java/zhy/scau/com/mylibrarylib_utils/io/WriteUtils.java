package zhy.scau.com.mylibrarylib_utils.io;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;

/**
 * Created by ZhengHy on 2017-01-19
 *
 * 文件写入流工具
 */
public class WriteUtils {

    private static final String TAG = WriteUtils.class.getName();

    public static final int BUFFER_SIZE = 1024;
    /**
     * 写入文件
     *
     * @param filePath
     * @return
     */
    public static boolean write(String filePath, byte[] datas) {
        return write(filePath, new String(datas, DefaultUtils.DEFAULT_CHARSET), false);
    }

    /**
     * 写入文件
     *
     * @param filePath
     * @param content
     * @return
     */
    public static boolean write(String filePath, String content) {
        return write(filePath, content, false);
    }

    /**
     * 写入文件 通过append属性判断是否追加
     *
     * @param filePath
     * @param content
     * @param append
     * @return
     */
    public static boolean write(String filePath, String content, boolean append) {
        if (NullPointUtils.isEmpty(filePath, content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "write file fail .", e);
        } finally {
            IoOptUtils.closeQuietly(fileWriter);
        }

        return false;
    }


    /**
     *
     * 文件写入
     *
     * 注 不会主动关闭读写流，请调用者 自行关闭
     *
     * @param filePath
     * @param stream
     * @return
     */
    public static boolean write(String filePath, InputStream stream) {
        return write(filePath, stream, false);
    }


    /**
     * 文件写入 通过append属性判断是否追加
     *
     * 注 不会主动关闭读写流，请调用者 自行关闭
     *
     * @param filePath
     * @param stream
     * @param append
     * @return
     */
    public static boolean write(String filePath, InputStream stream, boolean append) {
        return write(!NullPointUtils.isEmpty(filePath) ? new File(filePath) : null, stream, append);
    }

    /**
     *
     * 文件写入
     *
     * 注 不会主动关闭读写流，请调用者 自行关闭
     *
     * @param file
     * @param stream
     * @return
     */
    public static boolean write(File file, InputStream stream) {
        return write(file, stream, false);
    }

    /**
     * 文件写入 通过append属性判断是否追加
     *
     * 注 不会主动关闭读写流，请调用者 自行关闭
     *
     * @param file
     * @param stream
     * @param append
     * @return
     */
    public static boolean write(File file, InputStream stream, boolean append) {
        if (NullPointUtils.isEmpty(file, stream)) {
            return false;
        }

        OutputStream o = null;
        try {
            o = new FileOutputStream(file, append);
            byte data[] = new byte[BUFFER_SIZE];
            int length;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "write file fail .", e);
        } finally {
            IoOptUtils.close(o);
        }

        return false;
    }


    /**
     * 写入文件
     *
     * 注 不会主动关闭读写流，请调用者 自行关闭
     *
     * @param out
     * @param in
     * @return
     */
    public static boolean write(OutputStream out, InputStream in) {
        if (NullPointUtils.isEmpty(out, in)) {
            return false;
        }

        try {
            byte data[] = new byte[BUFFER_SIZE];
            int length;
            while ((length = in.read(data)) != -1) {
                out.write(data, 0, length);
            }
            out.flush();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "write file fail .", e);
        }

        return false;
    }
}
