
package com.example.myandroidapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myandroidapp.Models.Service;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.IApiServices;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    List<Service> services;

    Context context;

    public ServiceAdapter(Context context, List<Service> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cellule_service, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.service_title.setText(service.getLabel());

       if(services.get(position).getImage() != null){
           Picasso.get().load(services.get(position).getImage()).into(holder.image);
       }

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  service_id, service_title;
        ImageView image;


        public ViewHolder(@NonNull View itemLayoutView) {
            super(itemLayoutView);
            image = itemView.findViewById(R.id.imageSer);
            service_title = itemView.findViewById(R.id.labelSer);

        }
    }
    @Override
    public int getItemCount() {

        return services.size();
    }


}
























