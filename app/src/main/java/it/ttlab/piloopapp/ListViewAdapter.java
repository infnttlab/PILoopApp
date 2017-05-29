package it.ttlab.piloopapp;

/**
 * Created by Andrea Ferraro on 25/05/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


class ListViewAdapter extends BaseAdapter
{
    private ArrayList<HashMap<String,String>> list;
    private List<Pair<String,String>> keyValueList;
    private Activity activity;
    private boolean isKeyValueList;

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String,String>> list) {
        super();
        isKeyValueList=false;
        this.activity = activity;
        this.list = list;
    }

    ListViewAdapter(Activity activity, List<Pair<String,String>> keyValueList) {
        super();
        isKeyValueList=true;
        this.activity = activity;
        this.keyValueList = keyValueList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(isKeyValueList)
            return keyValueList.size();
        else
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if(isKeyValueList)
            return keyValueList.get(position);
        else
            return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if(isKeyValueList){
            Pair<String,String> keyValue = keyValueList.get(position);
            holder.txtFirst.setText(keyValue.first);
            holder.txtSecond.setText(keyValue.second);
        }else {
            HashMap<String, String> map = list.get(position);
            holder.txtFirst.setText(map.get(MainActivity.LISTVIEW_FIRST_COLUMN));
            holder.txtSecond.setText(map.get(MainActivity.LISTVIEW_SECOND_COLUMN));
        }
        return convertView;
    }


}
