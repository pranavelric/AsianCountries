package com.asia.asiancountries.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asia.asiancountries.R;
import com.asia.asiancountries.data.dataBase.entities.Country;
import com.asia.asiancountries.ui.home.dialog.MyDialogFragment;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
//import com.asia.asiancountries.utils.GlideApp;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private Context context;
    private static List<Country> countries;

    public CountriesAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }


    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);

        return new CountriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {

        holder.setName(countries.get(position).getName());
        holder.setRegion(countries.get(position).getRegion());
        holder.setFlag(countries.get(position).getFlag());

        DialogFragment dialog = new MyDialogFragment(countries.get(position));
        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(fm, "Country");

            }
        });


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setAllCountries(List<Country> countries) {
        this.countries = countries;
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

        }


        public void setName(String name) {
            TextView t = itemView.findViewById(R.id.name);
            t.setText(name);
        }

        public void setRegion(String region) {
            TextView t = itemView.findViewById(R.id.region);
            t.setText(region);
        }

        public void setFlag(String url) {
            ImageView t = itemView.findViewById(R.id.flag);

           RequestBuilder<PictureDrawable> req =  GlideToVectorYou
                    .init()
                    .with(itemView.getContext())
                    .getRequestBuilder();

            req.load(Uri.parse(url))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(new RequestOptions()
                          .centerInside())
                    .into(t);

        }


    }

}
