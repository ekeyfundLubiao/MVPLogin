package pad.yiqianziben.com.mvplogin.logininterface;

/**
 * Created by Administrator on 2016/12/23.
 */

import java.util.ArrayList;

import pad.yiqianziben.com.mvplogin.model.EPModel;
import pad.yiqianziben.com.mvplogin.model.UserBean;

/**
 * View负责展示数据
 */
public interface LoginView {
    void showLoginSuccess(String bean);
    void showLoginFailMsg(String failMsg);
    void showRefreshSuccess(ArrayList<EPModel.ListBean> successMsg);
    void showRefreshFaild(String faildMsg);
    void showLoadMoreSuccess(ArrayList<EPModel.ListBean>  successMsg);
    void showLoadMoreFaild(String faildMsg);
}
