package pad.yiqianziben.com.mvplogin.imp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;

import org.kymjs.aframe.http.KJHttp;
import org.kymjs.aframe.http.KJStringParams;
import org.kymjs.aframe.http.StringCallBack;

import java.util.ArrayList;

import pad.yiqianziben.com.mvplogin.logininterface.UserModel;
import pad.yiqianziben.com.mvplogin.model.AllEquipment;
import pad.yiqianziben.com.mvplogin.model.EPModel;
import pad.yiqianziben.com.mvplogin.tools.SysConfig;
import pad.yiqianziben.com.mvplogin.tools.ToastTools;
import pad.yiqianziben.com.mvplogin.tools.Tools;


/**
 * Created by Administrator on 2016/12/26.
 */

//实际数据请求在此层面
public class LoginModelImpl implements UserModel {
Context mContext;

    @Override
    public void login(Context context, String username, String password, StringCallBack callback) {
        mContext = context;
        Toast.makeText(context, "登录姓名：" + username + "   密码：" + password, Toast.LENGTH_SHORT).show();
        getLoginPost(username,password,callback);
    }

    @Override
    public void refreshData(Context context, int pageSize, int pageNum, String cmd, StringCallBack callback) {
     //   Toast.makeText(context,"pagesize  "+pageSize+"pageNum  "+pageNum,Toast.LENGTH_SHORT).show();
        mContext = context;
        getEListNetData(pageSize,pageNum,callback);
    }

    @Override
    public void loadMoreData(Context context, int pageSize, int pageNum, String cmd, StringCallBack callback) {
        getEListNetData(pageSize,pageNum,callback);
    }


    public void getLoginPost(String username, String password, StringCallBack callback) {
        if (username.equals("")) {
            Toast.makeText(mContext, "手机号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        KJHttp kjh = new KJHttp();
        KJStringParams params = new KJStringParams();
        String cmdPara = "{\"cmd\":\"4\",\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        params.put("sendMsg", cmdPara);
        kjh.urlPost(SysConfig.ServerUrl, params, callback);
    }

    EPModel mEPmodel;
    private void getEListNetData(int pageSize, int pageNum, StringCallBack callback) {
        KJStringParams params = new KJStringParams();

        String cmdPara = new AllEquipment(SysConfig.token, "7", SysConfig.uid, "0", pageSize, pageNum).toJsonString();
        params.put("sendMsg", cmdPara);
        KJHttp kjh = new KJHttp();
        kjh.urlPost(SysConfig.ServerUrl, params, callback);
    }


}
