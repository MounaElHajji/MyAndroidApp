package com.example.myandroidapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Activities.EmployeesDetails;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>  implements Filterable  {

    List<Employee> EmployeeList;
    private List<Employee> PostListFull;
    private List<Employee> PostSearchName;
    private List<Employee> PostEmployees;
    Context context;


    public EmployeeAdapter(Context context, List<Employee> EmployeeList) {
        this.context = context;
        this.EmployeeList = EmployeeList;
        PostListFull = new ArrayList<>(EmployeeList); //create a copy of postList
        PostSearchName = new ArrayList<>(EmployeeList);
        PostEmployees = new ArrayList<>(EmployeeList);
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
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Employee post = EmployeeList.get(position);
        holder.nom.setText(post.getFirst_name());
        holder.ville.setText(post.getCity());
        holder.descritpion.setText(post.getTel());

            //Picasso.get().load(EmployeeList.get(position).getImage()).into(holder.image);
            Picasso.get()
                    .load(Uri.parse(EmployeeList.get(position).getImageP()))
                    .centerCrop()
                    .resize(150,150)
                    .into(holder.imageP);




        holder.btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EmployeesDetails.class);
                i.putExtra("id", EmployeeList.get(position).getId());
                i.putExtra("firstName", EmployeeList.get(position).getFirst_name());
                i.putExtra("typeProfil", EmployeeList.get(position).getTypeProfil());
                i.putExtra("city", EmployeeList.get(position).getCity());
                i.putExtra("description", EmployeeList.get(position).getDescription());
                i.putExtra("tel", EmployeeList.get(position).getTel());
                i.putExtra("imagep", EmployeeList.get(position).getImageP());
                i.putExtra("rating", EmployeeList.get(position).getRating());

                context.startActivity(i);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  id, nom, ville, descritpion, tel;
        ImageView image;
        ImageView imageP;
        Button btnVoir;

        public ViewHolder(@NonNull View itemLayoutView) {
            super(itemLayoutView);
            image = itemView.findViewById(R.id.img_headline);
            nom = itemView.findViewById(R.id.text_nom);
            ville = itemView.findViewById(R.id.text_ville);
            descritpion = itemView.findViewById(R.id.text_description);
            btnVoir = itemView.findViewById(R.id.btnVoir);

           imageP= itemView.findViewById(R.id.img_headline);
        }
    }

    @Override
    public int getItemCount() {
        return EmployeeList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Employee> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(PostListFull);
                filteredList.addAll(PostSearchName);
                filteredList.addAll(PostEmployees);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Employee item : PostListFull) {
                    if (item.getCity().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
                for(Employee item: PostSearchName){
                    if(item.getLast_name().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }

//                for(Employee item: PostEmployees){
//                    if(item.getType().toLowerCase().contains(filterPattern))
//                    {
//                        filteredList.add(item);
//                    }
//                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            EmployeeList.clear();
            EmployeeList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}