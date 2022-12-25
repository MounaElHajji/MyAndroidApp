package com.example.myandroidapp.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.ListFavoris;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> implements Filterable{

   List<Employee> EmployeeList;
    Context context;
    ApiInterface apiInterface;
    SharedPreferences sh;
    int res;
    HashMap<Employee, Integer> lisFav= new HashMap<>();
   List<Employee> filteredList;
   private List<Employee> PostListFull;
    private List<Employee> PostSearchName;
    //private List<Employee> PostEmployees;


    public EmployeeAdapter(Context context, List<Employee> posts) {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int id= sh.getInt("id", 0);
        Call<List<ListFavoris>> call = apiInterface.getFav(id);
        call.enqueue(new Callback<List<ListFavoris>>() {
            @Override
            public void onResponse(Call<List<ListFavoris>> call, Response<List<ListFavoris>> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                List<ListFavoris> postList = response.body();
                List<Employee> emp= new ArrayList<>();
                for (Employee e:
                    posts ) {
                    for (ListFavoris f:
                            postList ) {
                        if(e.equals(f.getEmp())){
                            lisFav.put(e, R.drawable.fav);
                        }else{
                            lisFav.put(e, R.drawable.ic_baseline_favorite_border_24);
                        }
                    }
                }

//                postAdapter.getFilter().filter("Employee");
            }
            @Override
            public void onFailure(Call<List<ListFavoris>> call, Throwable t) {
            }
        });
        this.context = context;
        EmployeeList = posts;
        this.PostListFull=posts;//create a copy of postList
        PostSearchName = new ArrayList<>(EmployeeList);
    }


    public List<Employee> getEmployeeList() {
        return EmployeeList;
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
        holder.btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p2 = EmployeeList.get(position).getId();
                int p1= sh.getInt("id", 0);
                if(res==R.drawable.fav){
                    holder.btnHeart.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    Call<Void> call2=apiInterface.DeleteFav(p2,p1);
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
                    res=R.drawable.fav;
                    Call<Person> call1 = apiInterface.addFav(p1, p2);
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
        });}


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nom, ville, descritpion, tel;
        ImageView image, btnHeart;
        Button btnVoir;

        public ViewHolder(@NonNull View itemLayoutView) {
            super(itemLayoutView);
            image = itemView.findViewById(R.id.img_headline);
            nom = itemView.findViewById(R.id.text_nom);
            ville = itemView.findViewById(R.id.text_ville);
            descritpion = itemView.findViewById(R.id.text_description);
            btnVoir = itemView.findViewById(R.id.btnVoir);
            btnHeart = itemView.findViewById(R.id.btnHeart);
            // btnHeart.setImageResource(R.drawable.fav);
            /*Iterator iterator = lisFav.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapentry = (Map.Entry) iterator.next();
                System.out.println("clé: "+mapentry.getKey()
                        + " | valeur: " + mapentry.getValue());
            }
            for (Employee e:
                 getEmployeeList()) {
                /*if(lisFav.get(e)==R.drawable.fav){
                    btnHeart.setImageResource(R.drawable.fav);
                }
            }*/
        }
    }

        public int getItemCount() {
        return EmployeeList.size();
    }



    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().toLowerCase().trim();
                if (charString.isEmpty()) {
                    //filteredList = EmployeeList;
                   //filteredList.addAll(PostListFull);//3
                   filteredList.addAll(PostSearchName);
                    //filteredList.addAll(EmployeeList);
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


