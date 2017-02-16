package zhy.scau.com.mylibrarylib_utils.other;

/**
 * Created by ZhengHy on 2017-01-19
 *
 * 等于工具类
 *
 */
public class EqualsUtils {
    /**
     * 多个对象 是否相等
     *
     * @param values
     * @return
     */
    public static boolean equalsMulti(Object... values) {
        // 2个一对的比较
        if (values != null && values.length % 2 == 0) {
            boolean isEquals = true;
            for (int i = 0; i < values.length; i = i + 2) {
                if (!equals(values[i], values[i+1])) {
                    isEquals = false;
                    break;
                }
            }

            return isEquals;
        }
        return false;
    }

    /**
     * 是否相等
     *
     * @param thisValue
     * @param thatValue
     * @return
     */
    public static boolean equals(Object thisValue, Object thatValue) {
        return thisValue == thatValue || (thisValue == null ? thatValue == null : thisValue.equals(thatValue));
    }

    /**
     * 比较
     *
     * @param v1
     * @param v2
     * @param <V>
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <V> int compare(V v1, V v2) {
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable)v1).compareTo(v2));
    }
}
