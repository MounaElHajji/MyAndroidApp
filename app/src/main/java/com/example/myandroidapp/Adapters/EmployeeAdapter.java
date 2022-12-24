package com.example.myandroidapp.Adapters;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Activities.CurrentProfile;
import com.example.myandroidapp.Activities.EmployeelistActivity;
import com.example.myandroidapp.Activities.EmployeesDetails;
import com.example.myandroidapp.Activities.PlombrieList;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>  implements Filterable{

   List<Employee> EmployeeList;
    Context context;
   List<Employee> filteredList;
   private List<Employee> PostListFull;
    private List<Employee> PostSearchName;
    //private List<Employee> PostEmployees;


    public EmployeeAdapter(Context context, List<Employee> posts) {
        this.context = context;
        EmployeeList = posts;
        this.PostListFull=posts;//create a copy of postList
        PostSearchName = new ArrayList<>(EmployeeList);
       // PostEmployees = new ArrayList<>(EmployeeList);

    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
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

        holder.btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EmployeesDetails.class);
                i.putExtra("firstName",  EmployeeList.get(position).getFirst_name());
                i.putExtra("city",  EmployeeList.get(position).getCity());
                i.putExtra("description",  EmployeeList.get(position).getDescription());
                i.putExtra("tel",  EmployeeList.get(position).getTel());
                i.putExtra("image", EmployeeList.get(position).getImage());
                context.startActivity(i);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  id, nom, ville, descritpion, tel;
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



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().toLowerCase().trim();
                if (charString.isEmpty()) {
                    //filteredList = EmployeeList;
                    filteredList.addAll(PostListFull);
                    filteredList.addAll(PostSearchName);
                    //filteredList.addAll(PostEmployees);
                } else {
                    ArrayList<Employee> filterList = new ArrayList<>();

                    for (Employee androidVersion : PostSearchName) {

                        if (androidVersion.getCity().toLowerCase().contains(charString)||androidVersion.getFirstName().toLowerCase().contains(charString) || androidVersion.getLast_name().toLowerCase().contains(charString) ) {
                            filterList.add(androidVersion);

                        }

                    }
                    filteredList = filterList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
           //filteredList = (ArrayList<Employee>) results.values;

                EmployeeList.clear();
                EmployeeList.addAll((List) results.values);
                notifyDataSetChanged();
                //Log.e(PlombrieList.TAG, "results=" + results);
            }
        };
    }

}


