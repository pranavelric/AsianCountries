package com.asia.asiancountries.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asia.asiancountries.R;
import com.asia.asiancountries.data.dataBase.entities.Languages;


import java.util.ArrayList;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private ArrayList<Languages> languages;

    public LanguageAdapter(ArrayList<Languages> languages) {
        this.languages = languages;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item, parent, false);
        return new LanguageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {

        holder.setName(languages.get(position).getName());
        holder.setNativeName(languages.get(position).getNativeName());
        holder.setIso1(languages.get(position).getIso639_1());
        holder.setIso2(languages.get(position).getIso639_2());

    }


    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {


        private View itemView;
        private TextView name;
        private TextView nativeName;
        private TextView iso1;
        private TextView iso2;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.name);
            nativeName = itemView.findViewById(R.id.native_name);
            iso1 = itemView.findViewById(R.id.iso1);
            iso2 = itemView.findViewById(R.id.iso2);

        }

        public void setName(String mName) {
            name.setText(mName);
        }

        public void setNativeName(String nn) {
            nativeName.setText(nn);

        }

        public void setIso1(String str) {
            iso1.setText(str);

        }


        public void setIso2(String str) {
            iso2.setText(str);

        }


    }

}
