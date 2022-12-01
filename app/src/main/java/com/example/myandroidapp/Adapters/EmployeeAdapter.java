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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    List<Employee> EmployeeList;
    Context context;

    public EmployeeAdapter(Context context, List<Employee> EmployeeList) {
        this.context = context;
        this.EmployeeList = EmployeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee post = EmployeeList.get(position);
        holder.nom.setText(post.getLast_name());
        holder.ville.setText(post.getCity());
        holder.descritpion.setText(post.getTel());

        if (EmployeeList.get(position).getImage() != null) {
            Picasso.get().load(EmployeeList.get(position).getImage()).into(holder.image);
        }

        holder.btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EmployeelistActivity.class);
                i.putExtra("nom", EmployeeList.get(position).getLast_name());
                i.putExtra("ville", EmployeeList.get(position).getCity());
                i.putExtra("description", EmployeeList.get(position).getTel());
                i.putExtra("image", EmployeeList.get(position).getImage());
//                i.putExtra("rating", EmployeeList.get(position).getRating());
                context.startActivity(i);
            }
        });
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, nom, ville, descritpion;
        ImageView image;
        Button btnVoir;

        public ViewHolder(@NonNull View itemLayoutView) {
            super(itemLayoutView);
            image = itemView.findViewById(R.id.img_headline);
            nom = itemView.findViewById(R.id.text_nom);
            ville = itemView.findViewById(R.id.text_ville);
            descritpion = itemView.findViewById(R.id.text_description);
            btnVoir = itemView.findViewById(R.id.btnVoir);
        }
    }

    @Override
    public int getItemCount() {
        return EmployeeList.size();
    }
}