package com.example.regformwithdb.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.regformwithdb.Activity.UpdateActivity;
import com.example.regformwithdb.Database.DBHandler;
import com.example.regformwithdb.ModelCLass.ModelClass;
import com.example.regformwithdb.R;

import java.util.ArrayList;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.ViewHolder> {

    private ArrayList<ModelClass> modelClassArrayList;
    private Context context;
    public static DBHandler dbHandler;


    public RecycleviewAdapter(ArrayList<ModelClass> modelClassArrayList,Context context)
    {
        this.modelClassArrayList = modelClassArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelClass modelClass = modelClassArrayList.get(position);
        holder.nametxt.setText(modelClass.getStudentName());
        holder.emailtxt.setText(modelClass.getStudentEmail());
        holder.passtxt.setText((modelClass.getStudentPass()));

        holder.edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name",modelClass.getStudentName());
                intent.putExtra("email",modelClass.getStudentEmail());
                intent.putExtra("pass",modelClass.getStudentPass());

                context.startActivity(intent);
            }
        });


        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setMessage("Do you want to delete ?");
               builder.setTitle("Alert!");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       dbHandler = new DBHandler(context);
                       dbHandler.delete(modelClass.getStudentName());
                       modelClassArrayList.remove(position);
                       Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                       notifyList(modelClassArrayList);
                   }
               });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       dialogInterface.cancel();
                   }
               });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nametxt,emailtxt,passtxt;
        ImageView edit_icon,delete_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nametxt = itemView.findViewById(R.id.name_txtView);
            emailtxt = itemView.findViewById(R.id.email_txtView);
            passtxt = itemView.findViewById(R.id.pass_txtView);
            edit_icon = itemView.findViewById(R.id.edit_icon);
            delete_icon = itemView.findViewById(R.id.delete_icon);

        }
    }

    public void notifyList(ArrayList<ModelClass> filteredlist) {
        modelClassArrayList = filteredlist;
        this.notifyDataSetChanged();

    }

}
