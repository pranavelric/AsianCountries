package com.asia.asiancountries.ui.home.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asia.asiancountries.R;
import com.asia.asiancountries.data.dataBase.entities.Country;
import com.asia.asiancountries.ui.home.adapter.LanguageAdapter;
//import com.asia.asiancountries.utils.GlideApp;
//import com.bumptech.glide.request.target.CustomTarget;
//import com.bumptech.glide.request.transition.Transition;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

public class MyDialogFragment extends DialogFragment {
    private Country country;
    private TextView name;
    private TextView capital;
    private TextView population;
    private TextView region;
    private TextView subRegion;
    private TextView borders;
    private RecyclerView languages;
    private LanguageAdapter languageAdapter;
    private ImageView flag;
    private Toolbar toolbar;


    public MyDialogFragment(Country country) {
        this.country = country;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.country_detail_dialog, container, false);


        name = v.findViewById(R.id.name);
        capital = v.findViewById(R.id.capital);
        population = v.findViewById(R.id.population);
        region = v.findViewById(R.id.region);
        subRegion = v.findViewById(R.id.subregion);
        borders = v.findViewById(R.id.borders);
        languages = v.findViewById(R.id.languages);
        flag = v.findViewById(R.id.flag);
        toolbar = v.findViewById(R.id.dialog_toolbar);


        //set data
        toolbar.setTitle(country.getName());
        name.setText(country.getName());
        capital.setText(country.getCapital());
        population.setText(country.getPopulation() + "");
        region.setText(country.getRegion());
        subRegion.setText(country.getSubregion());


        for (String border : country.getBorders()) {
            borders.append("" + border + ", ");
        }
        GlideToVectorYou
                .init()
                .with(v.getContext())
                .withListener(new GlideToVectorYouListener() {
                    @Override
                    public void onLoadFailed() {

                    }

                    @Override
                    public void onResourceReady() {

                    }
                })

                .load(Uri.parse(country.getFlag()), flag);

        languages.setLayoutManager(new LinearLayoutManager(getContext()));
        languageAdapter = new LanguageAdapter(country.getLanguages());
        languages.setAdapter(languageAdapter);
        return v;


    }


    @Override
    public void onResume() {
        super.onResume();

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(params);

    }


}
