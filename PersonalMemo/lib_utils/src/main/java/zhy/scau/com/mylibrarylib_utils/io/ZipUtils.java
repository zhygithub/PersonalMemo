package zhy.scau.com.mylibrarylib_utils.io;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * Created by ZhengHy on 2017-01-19
 *
 * 压缩工具类
 */
public class ZipUtils {

    private static final String TAG = ZipUtils.class.getName();

    public static final int BUFFER_SIZE = 1024;

    /**
     * 对文件进行压缩
     *
     * @param files
     * @param zipFile
     * @throws IOException
     */
    public static void zip(File[] files, String zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            byte data[] = new byte[BUFFER_SIZE];
            for (int i = 0; files != null && i < files.length; i++) {
                FileInputStream fi = null;
                BufferedInputStream origin = null;
                try {
                    fi = new FileInputStream(files[i]);
                    origin = new BufferedInputStream(fi, BUFFER_SIZE);

                    ZipEntry entry = new ZipEntry(files[i].getName());
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "zip fail.", e);
                } finally {
                    IoOptUtils.close(fi);
                    IoOptUtils.close(origin);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "zip fail.", e);
        } finally {
            IoOptUtils.close(out);
        }
    }

    /**
     * 解压缩功能.
     * 将ZIP_FILENAME文件解压到ZIP_DIR目录下.
     *
     * @throws Exception
     */
    public static void upZip(File zipFile, String folderPath) {
        ZipFile zfile = null;
        try {
            zfile = new ZipFile(zipFile);
            Enumeration zList = zfile.entries();
            ZipEntry ze;
            byte[] buf = new byte[BUFFER_SIZE];
            while (zList.hasMoreElements()) {
                ze = (ZipEntry) zList.nextElement();
                String path = folderPath + File.separator + ze.getName();
                // 目录
                if (ze.isDirectory()) {
                    FileUtils.makeDir(path);
                } else {
                    OutputStream os = null;
                    InputStream is = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(path));
                        is = new BufferedInputStream(zfile.getInputStream(ze));
                        WriteUtils.write(os, is);
                    } catch (Exception e) {
                        Log.e(TAG, "unzip fail.", e);
                    } finally {
                        IoOptUtils.closeQuietly(os);
                        IoOptUtils.closeQuietly(is);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "unzip fail.", e);
        } finally {
            if (zfile != null) {
                try {
                    zfile.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
