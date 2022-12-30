package com.example.myandroidapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Activities.CurrentProfile;
import com.example.myandroidapp.Activities.EmployeelistActivity;
import com.example.myandroidapp.Activities.EmployeesDetails;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>  implements Filterable  {

    List<Employee> EmployeeList;
    private List<Employee> PostListFull;
    private List<Employee> PostSearchName;
    private List<Employee> PostEmployees;
    Context context;
    ApiInterface apiInterface;
    SharedPreferences sh;
    HashMap<Employee, Integer> lisFav= new HashMap<>();


    public EmployeeAdapter(Context context, List<Employee> EmployeeList) {
        this.context = context;
        this.EmployeeList = EmployeeList;
        PostListFull = new ArrayList<>(EmployeeList); //create a copy of postList
        PostSearchName = new ArrayList<>(EmployeeList);
        PostEmployees = new ArrayList<>(EmployeeList);
        for (Employee emp:
                EmployeeList) {
            lisFav.put(emp, R.drawable.ic_baseline_favorite_border_24);
        }
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee post = EmployeeList.get(position);
        holder.nom.setText(post.getFirst_name());
        holder.ville.setText(post.getCity());
        holder.descritpion.setText(post.getTel());

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
                i.putExtra("image", EmployeeList.get(position).getImage());
                i.putExtra("rating", EmployeeList.get(position).getRating());

                context.startActivity(i);
            }
        });
             holder.btnHeart.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    String p2 = EmployeeList.get(position).getId();
                    int p1= sh.getInt("id", 0);
                    apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
                    // System.out
                    if(lisFav.get(EmployeeList.get(position))==R.drawable.fav){
                        holder.btnHeart.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        lisFav.replace(EmployeeList.get(position),R.drawable.fav, R.drawable.ic_baseline_favorite_border_24);
                        Call<Void> call2=apiInterface.DeleteFav(p1,Integer.parseInt(p2));
                        call2.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                    }else{
                        holder.btnHeart.setImageResource(R.drawable.fav);
                        lisFav.replace(EmployeeList.get(position),R.drawable.ic_baseline_favorite_border_24, R.drawable.fav);
                        Call<Person> call1 = apiInterface.addFav(p1, Integer.parseInt(p2));
                        call1.enqueue(new Callback<Person>() {
                            @Override
                            public void onFailure(Call<Person> call, Throwable t) {
                            }
                            @Override
                            public void onResponse(Call<Person> call, Response<Person> response) {
                            }
                        });
                    }
                }
            }
        );
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  id, nom, ville, descritpion, tel;
        ImageView image, btnHeart;
        Button btnVoir;

        public ViewHolder(@NonNull View itemLayoutView) {
            super(itemLayoutView);
            image = itemView.findViewById(R.id.img_headline);
            nom = itemView.findViewById(R.id.text_nom);
            ville = itemView.findViewById(R.id.text_ville);
            descritpion = itemView.findViewById(R.id.text_description);
            btnVoir = itemView.findViewById(R.id.btnVoir);
            btnHeart=itemView.findViewById(R.id.btnHeart);
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