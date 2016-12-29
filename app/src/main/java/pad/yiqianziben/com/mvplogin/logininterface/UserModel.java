package pad.yiqianziben.com.mvplogin.logininterface;

/**
 * Created by Administrator on 2016/12/23.
 */
import android.content.Context;

import com.squareup.okhttp.Callback;

import org.kymjs.aframe.http.StringCallBack;

public interface UserModel {
    void login(Context context,String username, String password, StringCallBack callback);
    void refreshData(Context context,int pageSize,int pageNum,String cmd,StringCallBack callback);
    void loadMoreData(Context context,int pageSize,int pageNum,String cmd,StringCallBack callback);
}
