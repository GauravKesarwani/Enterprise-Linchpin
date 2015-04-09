package com.example.cmpe295b.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cmpe295b.R;
import com.example.cmpe295b.beans.EmployeeRowItem;
import com.example.cmpe295b.beans.EmployerRowItem;
import com.example.cmpe295b.utils.ImageLoader;

/**
 * Created by gaurav on 4/4/15.
 */
public class EmployerAdapter extends ArrayAdapter<EmployerRowItem>{
    Context context;
    EmployerRowItem[] rowItems;

    public EmployerAdapter(Context context, EmployerRowItem[] values) {
        super(context, R.layout.employer_item_row,values);
        this.context = context;
        this.rowItems = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = null;

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        listItem = inflater.inflate(R.layout.employer_item_row, parent, false);

        TextView employerNo = (TextView) listItem.findViewById(R.id.employerNo);
        TextView employerName = (TextView) listItem.findViewById(R.id.employerName);
        TextView empStartDate = (TextView) listItem.findViewById(R.id.empStartDate);
        TextView empEndDate = (TextView) listItem.findViewById(R.id.empEndDate);
        TextView empDesg = (TextView) listItem.findViewById(R.id.empDesg);

        EmployerRowItem rowItem = (EmployerRowItem) getItem(position);
        int empNo = position + 1;
        employerNo.setText("Employer " + empNo);
        employerName.setText(rowItem.getEmployerName());
        empStartDate.setText(rowItem.getStartDate());
        empEndDate.setText(rowItem.getEndDate());
        empDesg.setText(rowItem.getDesignation());
        //empimageView.setImageResource(rowItem.getImageId());
        return listItem;
    }
}
