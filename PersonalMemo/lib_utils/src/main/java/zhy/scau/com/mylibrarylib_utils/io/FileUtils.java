package zhy.scau.com.mylibrarylib_utils.io;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;

/**
 * Created by ZhengHy on 2017-01-19
 *
 * 文件操作工具
 *
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getName();
    private final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * 按照最后修订日期来获取文件列表
     *
     * @param directory
     * @return
     */
    public static List<File> getLruListFiles(File directory) {
        List<File> result = new LinkedList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            result = Arrays.asList(files);
            Collections.sort(result, new LastModifiedComparator());
        }
        return result;
    }

    /**
     * 设置文件的最后修改时间到当前时间
     *
     * @param file
     * @return
     */
    public static boolean setLastModifiedNow(File file) {
        boolean result = false;
        if (file.exists()) {
            long now = System.currentTimeMillis();
            boolean modified = file.setLastModified(now);
            if (!modified) {
                modify(file);
                if (file.lastModified() >= now) {
                    result = true;
                }
            } else {
                result = true;
            }
        }

        return result;
    }

    /**
     * 修改一个文件，使其最后修改时间更新
     *
     * @param file
     */
    private static void modify(File file) {
        try {
            long size = file.length();
            if (size == 0) {
                recreateZeroSizeFile(file);
                return;
            }

            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
            accessFile.seek(size - 1);
            byte lastByte = accessFile.readByte();
            accessFile.seek(size - 1);
            accessFile.write(lastByte);
            accessFile.close();
        } catch (Exception e) {
        }
    }


    /**
     * 重构建一个0长度的文件
     *
     * @param file
     * @throws IOException
     */
    private static void recreateZeroSizeFile(File file) throws IOException {
        if (!file.delete() || !file.createNewFile()) {
            throw new IOException("Error recreate zero-size file " + file);
        }
    }

    /**
     *
     * 移动文件
     *
     * @param sourceFilePath
     * @param destFilePath
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (NullPointUtils.isEmpty(sourceFilePath, destFilePath)) {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(sourceFilePath), new File(destFilePath));
    }


    /**
     * 移动文件
     *
     * @param srcFile
     * @param destFile
     */
    public static void moveFile(File srcFile, File destFile) {
        if (NullPointUtils.isEmpty(srcFile)) {
            throw new RuntimeException("srcFile cannot be null.");
        }

        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            delete(srcFile.getAbsolutePath());
        }
    }


    /**
     * 文件拷贝
     *
     * @param sourceFilePath
     * @param destFilePath
     * @return
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
            return WriteUtils.write(destFilePath, inputStream);
        } catch (Exception e) {
            Log.e(TAG, "copyFile fail .", e);
        } finally {
            IoOptUtils.closeQuietly(inputStream);
        }

        return false;
    }

    /**
     * 递归 删除文件 或 目录
     *
     * @return
     */
    public static boolean delete(ArrayList<File> files) {
        if (NullPointUtils.isEmpty(files)) {
            return true;
        }
        for (File tmp : files) {
            delete(tmp.getAbsolutePath());
        }

        return true;
    }
    /**
     * 递归 删除文件 或 目录
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        if (NullPointUtils.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        // 文件
        if (file.isFile()) {
            return file.delete();
        }
        // 目录
        else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (!NullPointUtils.isEmptyArray(files)) {
                for (File tmp : files) {
                    delete(tmp.getAbsolutePath());
                }
            }
            return file.delete();
        }

        return false;
    }

    /**
     * 构建目录
     *
     * @param filePath
     * @return
     */
    public static boolean makeDir(String filePath) {
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }

    /**
     * 判断文件/ 目录 存不存在
     *
     * @param filePath
     * @return
     */
    public static boolean isExist(String filePath) {
        if (NullPointUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 获取文件、目录的大小
     *
     * @param path 文件路径
     * @return
     */
    public static long getSize(String path) {
        if (NullPointUtils.isEmpty(path)) {
            return DefaultUtils.DEFAULT_INVAILD_INT;
        }

        File file = new File(path);
        return file.exists() ? file.length() : DefaultUtils.DEFAULT_INVAILD_INT;
    }

    /**
     * 获取文件名，不包括 后缀
     *
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     *
     * @param filePath
     * @return file name from path, not include suffix
     * @see
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (NullPointUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * 最后修改时间排序器
     */
    private static final class LastModifiedComparator implements Comparator<File> {

        @Override
        public int compare(File lhs, File rhs) {
            return compareLong(lhs.lastModified(), rhs.lastModified());
        }

        private int compareLong(long first, long second) {
            return (first < second) ? -1 : ((first == second) ? 0 : 1);
        }
    }
}
