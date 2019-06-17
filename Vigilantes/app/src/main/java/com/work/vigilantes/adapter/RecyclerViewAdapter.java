package com.work.vigilantes.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.work.vigilantes.R;
import com.work.vigilantes.model.Incidente;
import com.work.vigilantes.view.ViewIncidente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<LineHolder>{
    private Context context;
    private List<String> texts;
    private List<Incidente> incidentes;

    public RecyclerViewAdapter(Context context, ArrayList<String> texts, List<Incidente> incidentes){
        this.texts = texts;
        this.context = context;
        this.incidentes = incidentes;
    }// End MyAdapter()

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new LineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.textview, parent, false));
    }// End onCreateViewHolder()

    @Override
    public void onBindViewHolder(@NonNull final LineHolder holder, int position){
        holder.title.setText(String.format(Locale.getDefault(), "%s",texts.get(position)));
        if(incidentes.get(position).isBonificacao() && incidentes.get(position).isVerificado()){
            holder.title.setTextColor(Color.GREEN);
        }else if(!incidentes.get(position).isBonificacao() && !incidentes.get(position).isVerificado()){
            holder.title.setTextColor(Color.YELLOW);
        }else if(incidentes.get(position).isBonificacao() && !incidentes.get(position).isVerificado()){
            holder.title.setTextColor(Color.RED);
        }else{
            holder.title.setTextColor(Color.BLACK);
        }// End else
        holder.setItemClickListener(new ItemClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v, int position, boolean bool){
                Date date = incidentes.get(position).getData();
                String data = date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
                context.startActivity(new Intent(context, ViewIncidente.class)
                        .putExtra("incidente",incidentes.get(position))
                        .putExtra("data",data)
                        .putExtra("local",incidentes.get(position).getLocal()));
            }});
    }// End onBindViewHolder()

    @Override
    public int getItemCount(){
        return this.texts != null ? this.texts.size() : 0;
    }// End getItemCount()

    public void insertItem(String user,Incidente incidente){
        texts.add(user);
        incidentes.add(incidente);
        notifyItemInserted(getItemCount());
    }// insertItem()
}// End RecyclerViewAdapter

class LineHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView title;
    private ItemClickListener itemClickListener;

    public LineHolder(View itemView){
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.view);
        itemView.setOnClickListener(this);
    }// End LineHolder()

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }// End setItemClickListener()

    @Override
    public void onClick(View v){
        this.itemClickListener.onClick(v,getAdapterPosition(),false);
    }// End onClick()
}// End class LineHolder
