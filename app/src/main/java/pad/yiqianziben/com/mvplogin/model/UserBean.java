package pad.yiqianziben.com.mvplogin.model;

/**
 * Created by Administrator on 2016/12/23.
 */

public class UserBean {
    public String email;
    public String password;
    public String group_id;
    public String title;
    public String user_id;
    public UserBean() {
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
