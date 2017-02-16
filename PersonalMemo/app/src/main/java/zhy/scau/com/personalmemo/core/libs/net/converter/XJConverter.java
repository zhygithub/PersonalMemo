package zhy.scau.com.personalmemo.core.libs.net.converter;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import zhy.scau.com.lib_net.converter.BaseConverter;
import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;


/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * Ry 网络 body转换器
 */
public class XJConverter<T> extends BaseConverter<T> {
    @Override
    public byte[] requestBodyConverter(T body) {
        Gson gson = new Gson();
        return gson.toJson(body).getBytes(DefaultUtils.DEFAULT_CHARSET);
    }

    @Override
    public HashMap<String, Object> bodyToParamsConverter(T body) {
        Gson gson = new Gson();

        try {
            String gsonValue = gson.toJson(body);

            JSONObject jsonObject = new JSONObject(gsonValue);
            return jsonToMap(jsonObject);

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 将json对象转换成 map
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static HashMap<String, Object> jsonToMap(JSONObject json) throws JSONException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    /**
     * 转换为map
     *
     * @param object
     * @return
     * @throws JSONException
     */
    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * 转换为list
     *
     * @param array
     * @return
     * @throws JSONException
     */
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
