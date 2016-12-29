package pad.yiqianziben.com.mvplogin.model;

/**
 * Created by lubiao on 2016/11/9.
 */

public  abstract class BaseRequstBean {


    /**
     * cmd : 9
     * uid : 1
     * token : 20111222
     */

    private String cmd;
    private String uid;
    private String token;


    public BaseRequstBean(String token, String cmd, String uid) {
        this.token = token;
        this.cmd = cmd;
        this.uid = uid;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
