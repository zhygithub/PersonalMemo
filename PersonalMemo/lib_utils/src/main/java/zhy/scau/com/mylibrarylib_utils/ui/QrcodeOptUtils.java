package zhy.scau.com.mylibrarylib_utils.ui;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;


/**
 * 条码生成工具类-用于生成二维码，一维码
 */
public class QrcodeOptUtils {

    private static final String TAG = QrcodeOptUtils.class.getName();
    /**
     * 用于将给定的内容生成成一维码 FIXME 注：目前生成内容为中文的话将直接报错，要修改底层jar包的内容
     *
     * @param str 将要生成一维码的内容
     * @param widthExp
     * @param heightExp
     * @return 返回生成好的一维码bitmap
     */
    public static Bitmap createOneDCode(String str, int widthExp, int heightExp) {
        try {
            // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            int size = str.length();
            for (int i = 0; i < size; i++) {
                int c = str.charAt(i);
                if ((19968 <= c && c < 40623)) {
                    throw new IllegalStateException("生成条形码的内容不能是中文.");
                }
            }
            BitMatrix matrix = new MultiFormatWriter().encode(str,
                BarcodeFormat.CODE_128, widthExp, heightExp);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
            // 通过像素数组生成bitmap,具体参考api
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (Exception e) {
            Log.e(TAG, "createOneDCode fail .", e);
        }

        return null;
    }

    /**
     * 生成二维码
     *
     * @param text 需要生成二维码的文字、网址等
     * @param size 需要生成二维码的大小（）
     * @return bitmap
     */
    public static Bitmap createQRCode(String text, int size) {
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, DefaultUtils.DEFAULT_CHARSET_VALUE);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size, hints);
            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * size + x] = 0xff000000;
                    } else {
                        pixels[y * size + x] = 0xffffffff;
                    }

                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
            return bitmap;
        } catch (Exception e) {
            Log.e(TAG, "createQRCode fail .", e);
        }

        return null;
    }


    /**
     * 生成带logo的二维码
     *
     * @param text 需要生成二维码的文字、网址等
     * @param size 需要生成二维码的大小（）
     * @param logoPercent    logo的比例
     * @param bitmap    log文件
     * @return bitmap
     */
    public static Bitmap createQRCodeWithLogo(String text, int size, float logoPercent, Bitmap bitmap) {
        try {
            int logoHalfSize = (int) (size / (logoPercent * 2));
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, DefaultUtils.DEFAULT_CHARSET_VALUE);
            // 设置容错级别，默认为ErrorCorrectionLevel.L
            // 因为中间加入logo所以建议你把容错级别调至H,否则可能会出现识别不了
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                BarcodeFormat.QR_CODE, size, size, hints);
            // 矩阵高度
            int width = bitMatrix.getWidth();
            // 矩阵宽度
            int height = bitMatrix.getHeight();
            int halfW = width / 2;
            int halfH = height / 2;

            Matrix m = new Matrix();
            float sx = (float) 2 * logoHalfSize / bitmap.getWidth();
            float sy = (float) 2 * logoHalfSize / bitmap.getHeight();
            m.setScale(sx, sy);
            // 设置缩放信息
            // 将logo图片按martix设置的信息缩放
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);

            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (x > halfW - logoHalfSize && x < halfW + logoHalfSize
                        && y > halfH - logoHalfSize
                        && y < halfH + logoHalfSize) {
                        // 该位置用于存放图片信息
                        // 记录图片每个像素信息
                        pixels[y * width + x] = bitmap.getPixel(x - halfW
                            + logoHalfSize, y - halfH + logoHalfSize);
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * size + x] = 0xff000000;
                        } else {
                            pixels[y * size + x] = 0xffffffff;
                        }
                    }
                }
            }
            Bitmap qrCodeBm = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            qrCodeBm.setPixels(pixels, 0, size, 0, 0, size, size);
            return qrCodeBm;
        } catch (Exception e) {
            Log.e(TAG, "createQRCode with logo fail .", e);
        }

        return null;
    }
}
