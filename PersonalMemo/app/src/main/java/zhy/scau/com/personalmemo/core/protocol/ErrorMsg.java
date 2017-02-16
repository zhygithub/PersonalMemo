package zhy.scau.com.personalmemo.core.protocol;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.personalmemo.core.base.bean.BaseEntity;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 错误信息
 */
public class ErrorMsg extends BaseEntity {

    /**
     * 错误码
     */
    private int mErrCode = DefaultUtils.DEFAULT_INVAILD_INT;

    /**
     * 错误信息
     */
    private String mErrMsg = DefaultUtils.DEFAULT_INVAILD_STRING;

    /**
     * 内部显示的信息
     */
    private String mInnerMsg = DefaultUtils.DEFAULT_INVAILD_STRING;

    public ErrorMsg(int errCode, String errMsg, String innerMsg) {
        mErrCode = errCode;
        mErrMsg = errMsg;
        mInnerMsg = innerMsg;
    }


    public int getErrCode() {
        return mErrCode;
    }

    public void setErrCode(int errCode) {
        mErrCode = errCode;
    }

    public String getErrMsg() {
        return mErrMsg;
    }

    public void setErrMsg(String errMsg) {
        mErrMsg = errMsg;
    }

    public String getInnerMsg() {
        return mInnerMsg;
    }

    public void setInnerMsg(String innerMsg) {
        mInnerMsg = innerMsg;
    }


    @Override
    public String toString() {
        return mErrCode + "," + mErrMsg + "," + mInnerMsg;
    }
}
