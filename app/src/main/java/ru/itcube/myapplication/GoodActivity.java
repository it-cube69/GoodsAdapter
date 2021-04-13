//package ru.itcube.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class GoodActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_good);
//
//        ImageView imageView = findViewById(R.id.image);
//        TextView title = findViewById(R.id.title);
//        TextView price = findViewById(R.id.price);
//
//        Intent intent = getIntent();
//        Good good = (Good) intent.getExtras().get("good");
//        if (good != null) {
//            int imageId = getResources().getIdentifier(good.getImage(), "drawable", getPackageName());
//            imageView.setImageResource(imageId);
//
//            title.setText(good.getTitle());
//
//            price.setText(good.getPrice().toString());
//        }
//    }
//}