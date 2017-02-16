package zhy.scau.com.mylibrarylib_utils.io;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;

/**
 * Created by ZhengHy on 2017-01-19
 *
 * 文件读写流工具
 */
public class ReadUtils {

    private static final String TAG = ReadUtils.class.getName();
    /**
     * 读取文件
     *
     * @param filePath
     * @return
     */
    public static StringBuilder read(String filePath) {
        return read(filePath, DefaultUtils.DEFAULT_CHARSET_VALUE);
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @param charsetName
     * @return
     */
    public static StringBuilder read(String filePath, String charsetName) {
        return read(filePath, charsetName, "\r\n");
    }
    /**
     * 读取文件
     *
     * @param filePath
     * @param charsetName
     * @param lineSplit
     * @return
     */
    public static StringBuilder read(String filePath, String charsetName, String lineSplit) {
        if (NullPointUtils.isEmpty(filePath)) {
            return null;
        }

        File file = new File(filePath);
        if (!file.isFile()) {
            return null;
        }

        StringBuilder fileContent = new StringBuilder("");
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line + lineSplit);
            }
            return fileContent;
        } catch (Exception e) {
            Log.e(TAG, "read file fail .", e);
            return null;
        } finally {
            IoOptUtils.closeQuietly(reader);
        }
    }

    /**
     * 读取文件
     *
     * @param in
     * @return
     */
    public static StringBuilder read(InputStream in) {
        return read(in, DefaultUtils.DEFAULT_CHARSET_VALUE);
    }
    /**
     * 读取文件
     *
     * @param in
     * @param charsetName
     * @return
     */
    public static StringBuilder read(InputStream in, String charsetName) {
        return read(in, charsetName, "\r\n");
    }
    /**
     * 读取文件
     *
     * @param in
     * @param charsetName
     * @param lineSplit
     * @return
     */
    public static StringBuilder read(InputStream in, String charsetName, String lineSplit) {
        BufferedReader br = null;

        StringBuilder fileContent = new StringBuilder("");
        try {
            br = new BufferedReader(new InputStreamReader(in, charsetName));
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line + lineSplit);
            }
            return fileContent;
        } catch (Exception e) {
            Log.e(TAG, "read file fail .", e);
            return null;
        } finally {
            IoOptUtils.closeQuietly(br);
        }
    }
}
