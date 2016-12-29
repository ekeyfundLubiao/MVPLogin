package pad.yiqianziben.com.mvplogin.imp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;
import org.kymjs.aframe.http.StringCallBack;

import java.io.IOException;
import java.util.ArrayList;

import pad.yiqianziben.com.mvplogin.logininterface.LoginPresenter;
import pad.yiqianziben.com.mvplogin.logininterface.LoginView;
import pad.yiqianziben.com.mvplogin.logininterface.UserModel;
import pad.yiqianziben.com.mvplogin.model.EPModel;
import pad.yiqianziben.com.mvplogin.model.UserBean;
import pad.yiqianziben.com.mvplogin.tools.Tools;

/**
 * Created by Administrator on 2016/12/26.
 */

public class UserPresenter implements LoginPresenter {

    private LoginView mLoginView;
    private UserModel mUserModel;
    protected Context mContext;

    public UserPresenter(Context context, LoginView mLoginView, UserModel mUserModel) {
        this.mLoginView = mLoginView;
        this.mUserModel = mUserModel;
        mContext = context;
    }


    @Override
    public void loginPost(String emial, String pwd) {
        mUserModel.login(mContext, emial, pwd, new StringCallBack() {


            @Override
            public void onSuccess(String t) {

                Log.d("TAG", "onSuccess: " + t);
                try {
                    JSONObject jsonObject = new JSONObject(t);

                    int result = Integer.parseInt(jsonObject.getString("result"));
                    if (result >= 1) {
                        String temp = Tools.parseLoginMsg(jsonObject);
                        if (!temp.equals("")) {
                            mLoginView.showLoginSuccess("登录成功");
                        } else {
                            mLoginView.showLoginSuccess(jsonObject.getString("retmsg"));
                        }

                    }else{
                        mLoginView.showLoginSuccess(jsonObject.getString("retmsg"));
                    }
                } catch (Exception ex) {
                    Toast.makeText(mContext, "数据请求失败", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
               // Toast.makeText(mContext, strMsg, Toast.LENGTH_LONG).show();
                mLoginView.showLoginFailMsg(strMsg);

            }
        });
    }
    EPModel mEPmodel;
    @Override
    public void refreshData(int pageSize, int pageNum) {
       // addData();
      //  mLoginView.showRefreshSuccess(dataSource);
        mUserModel.refreshData(mContext, pageSize, 1, "", new StringCallBack() {
            @Override
            public void onSuccess(String s) {
                Gson mGson = new Gson();
                ArrayList<EPModel.ListBean> templist=null;
                try {
                    mEPmodel = mGson.fromJson(s, EPModel.class);

                    int status = mEPmodel.getResult();
                    if (status < 0) {

                        Tools.showToast(mContext, mEPmodel.getRetmsg());
                    } else {


                        if (mEPmodel.getList()!=null) {
                            templist = (ArrayList<EPModel.ListBean>) mEPmodel.getList();
                            int templistSize = templist.size();
                            mLoginView.showRefreshSuccess(templist);
                        } else {
                            mLoginView.showRefreshFaild("暂无数据");
                        }
                    }

                } catch (JsonSyntaxException e) {
                    mLoginView.showRefreshFaild("解析异常");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadMoreData(int pageSize, int pageNum) {
        mUserModel.loadMoreData(mContext, pageSize, pageNum, "", new StringCallBack() {
            @Override
            public void onSuccess(String s) {
                Gson mGson = new Gson();
                ArrayList<EPModel.ListBean> templist=null;
                try {
                    mEPmodel = mGson.fromJson(s, EPModel.class);

                    int status = mEPmodel.getResult();
                    if (status < 0) {
                        Tools.showToast(mContext, mEPmodel.getRetmsg());
                    } else {
                        if (mEPmodel.getList()!=null) {
                            templist = (ArrayList<EPModel.ListBean>) mEPmodel.getList();
                            int templistSize = templist.size();
                            mLoginView.showLoadMoreSuccess(templist);
                        } else {
                            mLoginView.showLoadMoreFaild("暂无数据");
                        }
                    }

                } catch (JsonSyntaxException e) {
                    mLoginView.showLoadMoreFaild("解析异常");
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                mLoginView.showLoadMoreFaild("解析异常");
            }
        });
    }



    ArrayList<UserBean> dataSource = new ArrayList<UserBean>();

    public void addData() {
        for (int i = 0; i < 10; i++) {
            dataSource.add(new UserBean("7632" + i + "678@qq.com", "12213" + i));
        }
    }
}
