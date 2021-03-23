package ru.itcube.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        List<Good> goods = generateGoods();


        GoodsAdapter adapter = new GoodsAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, goods);
        listView.setAdapter(adapter);
    }

    private List<Good> generateGoods() {
        List<Good> goods = new ArrayList<>();

        Good milk = new Good();
        milk.setTitle("Milk");
        milk.setPrice(new BigDecimal(65).setScale(2));
        milk.setImage("milk");

        Good bread = new Good();
        bread.setTitle("Bread");
        bread.setPrice(new BigDecimal(40).setScale(2));
        bread.setImage("bread");

        Good cheese = new Good();
        cheese.setTitle("Cheese");
        cheese.setPrice(new BigDecimal(800).setScale(2));
        cheese.setImage("cheese");

        Good eggs = new Good();
        eggs.setTitle("Eggs");
        eggs.setPrice(new BigDecimal(60).setScale(2));
        eggs.setImage("eggs");

        goods.add(bread);
        goods.add(eggs);
        goods.add(milk);
        goods.add(cheese);
        return goods;
    }

    public static class GoodsAdapter extends ArrayAdapter<Good> {


        public GoodsAdapter(@NonNull Context context, int resource, @NonNull List<Good> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Good good = getItem(position);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.good, null);

            ImageView picture = convertView.findViewById(R.id.picture);
            int pic = getContext().getResources().getIdentifier(good.getImage(), "drawable", getContext().getPackageName());
            picture.setImageResource(pic);

            TextView title = convertView.findViewById(R.id.title);
            title.setText(good.getTitle());

            TextView price = convertView.findViewById(R.id.price);
            price.setText(good.getPrice().toString());

            return convertView;
        }
    }
}