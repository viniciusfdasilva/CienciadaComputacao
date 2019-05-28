package com.example.vinic.tp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Atividade extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override 
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        Intent i = getIntent();
        String[] estados = i.getStringArrayExtra("Estados");

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> list = new ArrayList<>(0);
        mAdapter = new MyAdapter((list));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        for(String uf : estados){
            mAdapter.insertItem(uf);
        }// End for
    }// End onCreate()

    public void startFragmentMenu(){
        startActivity(new Intent(this,FragmentMenu.class));
    }// End startFragmentMenu()

    class MyAdapter extends RecyclerView.Adapter<LineHolder>{
        private List<String> mUsers;

        public MyAdapter(ArrayList<String> myDataset){
            mUsers = myDataset;
        }// End MyAdapter()

        @Override
        public LineHolder onCreateViewHolder(ViewGroup parent, int viewType){
            return new LineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false));
        }// End onCreateViewHolder()

        @Override
        public void onBindViewHolder(final LineHolder holder, final int position){
            holder.title.setText(String.format(Locale.getDefault(), "%s",mUsers.get(position)));
            holder.setItemClickListener(new ItemClickListener(){
                @Override
                public void onClick(View v, int i, boolean bool){
                    startFragmentMenu();
                }});
        }// End onBindViewHolder()


        @Override
        public int getItemCount(){
            return this.mUsers != null ?  mUsers.size() : 0;
        }// End getItemCount()

        public void insertItem(String user){
            mUsers.add(user);
            notifyItemInserted(getItemCount());
        }
    }// End MyAdapter

    class LineHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ItemClickListener i;

        public LineHolder(View itemView){
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.main_line_title);
            itemView.setOnClickListener(this);
        }// End LineHolder()

        public void setItemClickListener(ItemClickListener i){
            this.i = i;
        }// End setItemClickListener()

        @Override
        public void onClick(View v){
            i.onClick(v,getAdapterPosition(),false);
        }// End onClick()
    }// End class LineHolder
}// End Atividade