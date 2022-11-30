package com.example.myandroidapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myandroidapp.Models.Service1;
import com.example.myandroidapp.R;


import java.util.ArrayList;


public class ServiceAdapter extends ArrayAdapter<Service1> {
    private ArrayList<Service1> services;

    public ServiceAdapter(Context context,int ressource, ArrayList<Service1> services){
        super(context, ressource, services);
        this.services=services;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater= (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.layout_cellule_service, parent, false);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.imageSer);
        imageView.setBackgroundResource(services.get(position).getImage());
        TextView textView=(TextView) convertView.findViewById(R.id.labelSer);
        textView.setText(services.get(position).getLabel());
        return  convertView;
    }
}
