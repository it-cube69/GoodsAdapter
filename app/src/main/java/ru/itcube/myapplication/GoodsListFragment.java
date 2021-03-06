package ru.itcube.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class GoodsListFragment extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_list_fragment, container, false);

        listView = view.findViewById(R.id.listView);
        List<Good> goods = generateGoods();

        GoodsAdapter adapter = new GoodsAdapter(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, goods);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Good good = goods.get(position);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, new GoodItemFragment(good), "goodItem")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private List<Good> generateGoods() {
        List<Good> goods = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getContext().openOrCreateDatabase("goods", MODE_PRIVATE, null);

        Cursor c = sqLiteDatabase.rawQuery("select title,price,image from goods", null);
        int titleIndex = c.getColumnIndex("title");
        int priceIndex = c.getColumnIndex("price");
        int imageIndex = c.getColumnIndex("image");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Good good = new Good(c.getString(titleIndex),
                    new BigDecimal(c.getLong(priceIndex)).setScale(2),
                    c.getString(imageIndex));
            goods.add(good);
            c.moveToNext();
        }

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
