package cn.focusspot.zhongchengbus.lib_net.helper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 字节转换
 */
public class ByteConverter extends Converter.Factory {
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new Converter<byte[], RequestBody>() {
            @Override
            public RequestBody convert(byte[] value) throws IOException {
                return RequestBody.create(null, value);
            }
        };
    }
}
