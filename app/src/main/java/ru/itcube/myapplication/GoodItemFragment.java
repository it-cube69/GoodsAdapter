package ru.itcube.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GoodItemFragment extends Fragment {

    private final Good good;

    public GoodItemFragment(Good good) {
        this.good = good;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.good_item_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title);
        TextView price = view.findViewById(R.id.price);

        if (good != null) {
            int imageId = getResources().getIdentifier(good.getImage(), "drawable", getContext().getPackageName());
            imageView.setImageResource(imageId);

            title.setText(good.getTitle());

            price.setText(good.getPrice().toString());
        }

        return view;
    }
}
