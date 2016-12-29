package pad.yiqianziben.com.mvplogin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;
import org.kymjs.aframe.http.KJHttp;
import org.kymjs.aframe.http.KJStringParams;
import org.kymjs.aframe.http.StringCallBack;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import pad.yiqianziben.com.mvplogin.adapter.MyAdapter;
import pad.yiqianziben.com.mvplogin.imp.LoginModelImpl;
import pad.yiqianziben.com.mvplogin.imp.UserPresenter;
import pad.yiqianziben.com.mvplogin.logininterface.LoginView;
import pad.yiqianziben.com.mvplogin.model.EPModel;
import pad.yiqianziben.com.mvplogin.model.UserBean;
import pad.yiqianziben.com.mvplogin.tools.SysConfig;
import pad.yiqianziben.com.mvplogin.tools.ToastTools;

public class MainActivity extends AppCompatActivity implements LoginView {

    private AutoCompleteTextView email;
    private StoreHouseHeader header;
    private PtrClassicFrameLayout ptrFrame;
    private EditText password;
    private Button email_sign_in_button;
    private ListView test_mvp_list;
    Context mContex;
    UserPresenter mUserPresenter;
    MyAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setAction();
        mContex = this;
    }

    public void initView() {
        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.login_form);
//        ptrFrame.setMode(PtrFrameLayout.Mode.BOTH);

        // the following are default settings
        ptrFrame.setResistance(1.7f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrame.setDurationToClose(200);
        ptrFrame.setDurationToCloseHeader(1000);
        // default is false
        ptrFrame.setPullToRefresh(false);
        // default is true
        ptrFrame.setKeepHeaderWhenRefresh(true);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        mUserPresenter = new UserPresenter(this, this, new LoginModelImpl());
        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserPresenter.loginPost(email.getText().toString(), password.getText().toString());
                //getLoginPost(email.getText().toString(), password.getText().toString());
            }
        });
        test_mvp_list = (ListView) findViewById(R.id.test_mvp_list);
        baseAdapter = new MyAdapter(this);
        test_mvp_list.setAdapter(baseAdapter);
    }

    int pageNum=1;
    public void setAction() {
        ptrFrame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(final PtrFrameLayout frame) {
                ptrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                        mUserPresenter.loadMoreData(11, ++pageNum);
                    }
                }, 1000);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ptrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNum=1;
                        frame.refreshComplete();
                        mUserPresenter.refreshData(11, 1);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void showLoginSuccess(String bean) {
        ToastTools.show(mContex,bean);
        ptrFrame.autoRefresh();
    }

    @Override
    public void showLoginFailMsg(String failMsg) {
        ToastTools.show(mContex,failMsg);
    }



    @Override
    public void showLoadMoreSuccess(ArrayList<EPModel.ListBean> successMsg) {
        if (successMsg.size() == 0) {
            ToastTools.show(mContex, "没有更多了~");
        } else {
            baseAdapter.AddDate(successMsg);
        }
    }

    @Override
    public void showLoadMoreFaild(String faildMsg) {
        ToastTools.show(mContex,faildMsg);
    }

    /*---刷新---*/
    @Override
    public void showRefreshSuccess(ArrayList successMsg) {
        baseAdapter.setDate(successMsg);
    }
    @Override
    public void showRefreshFaild(String faildMsg) {
        ToastTools.show(mContex,faildMsg);
    }

}
