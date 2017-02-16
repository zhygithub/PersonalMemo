package zhy.scau.com.mylibrarylib_utils.io;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import zhy.scau.com.mylibrarylib_utils.encrypt_decrypt.Base64Utils;

/**
 * Created by kinger on 2016/12/8.
 * <p>
 * 序列化工具类-Serialize
 */
public class SerializeUtils {
    private static final String TAG = SerializeUtils.class.getName();

    /**
     * 序列化成字符串
     *
     * @param object
     * @return
     */
    public static String serialize(Object object) {
        // 创建字节输出流
        ByteArrayOutputStream baos = null;
        // 创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            baos = new ByteArrayOutputStream();
            // 然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            return new String(Base64Utils.encode(baos.toByteArray()));
        } catch (Exception e) {
            Log.e(TAG, "serialize msg fail .", e);
        } finally {
            IoOptUtils.closeQuietly(baos);
            IoOptUtils.closeQuietly(out);
        }

        return null;
    }

    /**
     * 反序列号成对象
     *
     * @param objectVal
     * @return
     */
    public static Object antiSerialize(String objectVal) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            byte[] buffer = Base64Utils.decode(objectVal);
            // 一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            bais = new ByteArrayInputStream(buffer);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, "antisSerialize msg fail .", e);
        } finally {
            IoOptUtils.closeQuietly(bais);
            IoOptUtils.closeQuietly(ois);
        }

        return null;
    }

}
