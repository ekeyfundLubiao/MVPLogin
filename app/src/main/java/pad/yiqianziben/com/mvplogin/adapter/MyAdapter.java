package pad.yiqianziben.com.mvplogin.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pad.yiqianziben.com.mvplogin.R;
import pad.yiqianziben.com.mvplogin.model.EPModel;
import pad.yiqianziben.com.mvplogin.model.UserBean;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflatLayout;
    private Context mContext;
    private ArrayList<EPModel.ListBean> arrayList = new ArrayList<EPModel.ListBean>();

    public void setDate(ArrayList<EPModel.ListBean> list) {
        arrayList.clear();
        arrayList.addAll(list);
        notifyDataSetChanged();
    }

    public void AddDate(ArrayList<EPModel.ListBean> list) {

        arrayList.addAll(list);
        notifyDataSetChanged();
    }
    public MyAdapter( Context context) {
        this.mContext = context;
        this.mInflatLayout = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        if (arrayList != null && arrayList.size() > 0) {
            return (EPModel.ListBean) arrayList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHold mViewHold=null;
        if (mViewHold == null) {
            mViewHold = new ViewHold();
            view = mInflatLayout.inflate(R.layout.item_list, null);
            mViewHold.email = (TextView) view.findViewById(R.id.tvemial);
            mViewHold.psd = (TextView) view.findViewById(R.id.tvpwd);
            view.setTag(mViewHold);
        } else {
            mViewHold=(ViewHold)view.getTag();
        }

        EPModel.ListBean bean = arrayList.get(position);
        mViewHold.email.setText(bean.getGroup_id());
        mViewHold.psd.setText(bean.title);
        return view;
    }

    class ViewHold {
        private TextView email;
        private TextView psd;
    }
}
