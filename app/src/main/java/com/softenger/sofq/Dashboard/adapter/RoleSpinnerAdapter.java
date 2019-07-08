package com.softenger.sofq.Dashboard.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softenger.sofq.R;
import com.softenger.sofq.api.response.RolesResponse;

import java.util.List;

public class RoleSpinnerAdapter extends ArrayAdapter<RolesResponse.Role> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<RolesResponse.Role> items;
    private final int mResource;

    public RoleSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<RolesResponse.Role> objects) {
        super(context, resource,textViewResourceId, objects);
        items = objects;
        mResource = resource;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return createItemView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = view.findViewById(R.id.tvSpinnerItem);

        RolesResponse.Role offerData = items.get(position);

        offTypeTv.setText(offerData.getRoleDesc());

        return view;
    }
}
