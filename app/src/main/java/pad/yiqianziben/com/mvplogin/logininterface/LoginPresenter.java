package pad.yiqianziben.com.mvplogin.logininterface;

/**
 * Created by Administrator on 2016/12/23.
 */

/**
 * Presenter负责做View和Model的中间人
 */
public interface LoginPresenter {
    void loginPost(String emial,String pwd);
    void refreshData(int pageSize, int pageNum);
    void loadMoreData(int pageSize, int pageNum);

}
