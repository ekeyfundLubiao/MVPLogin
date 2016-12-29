package pad.yiqianziben.com.mvplogin.model;

import com.google.gson.Gson;

/**
 * Created by lubiao on 2016/11/9.
 */

public class AllEquipment extends BaseRequstBean {


    /**
     * groupid : 0
     * pageSize : 3
     * pageNo : 1
     */

    private String groupid;
    private String pageSize;
    private String pageNo;
    private AllEquipment mAllEquipment;

    public AllEquipment(String token, String cmd, String uid) {
        super(token, cmd, uid);
        mAllEquipment = this;
    }

    public AllEquipment(String token, String cmd, String uid, String groupid, int pageSize, int pageNo) {
        super(token, cmd, uid);
        this.groupid = groupid;
        this.pageSize = Integer.toString(pageSize);
        this.pageNo = Integer.toString(pageNo);
        mAllEquipment = this;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Integer.toString(pageSize);;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = Integer.toString(pageNo);;
    }


    public String toJsonString() {
        String msg = "fail";
        if (mAllEquipment == null) {
            msg="fail";
        } else {
            Gson  mGson=new Gson();

            msg=mGson.toJson(mAllEquipment);
        }
        return msg;
    }
}
