package ca.kbrowne3717.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn_add;
    EditText et_title, et_description, et_imdb;
    ListView movie_list;
    ArrayAdapter movieArrayAdapter;
    FirebaseFirestore db;
    DataBaseHelper dataBaseHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        et_imdb = findViewById(R.id.et_imdb);
        movie_list = findViewById(R.id.movie_list);
        dataBaseHelper = new DataBaseHelper();
        ShowMoviesOnListView(dataBaseHelper);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieModel movieModel;
                try{
                    movieModel = new MovieModel(et_title.getText().toString(),et_description.getText().toString(), et_imdb.getText().toString());
                    Toast.makeText(MainActivity.this, movieModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating movie entry", Toast.LENGTH_SHORT).show();
                    movieModel = new MovieModel("error","error","error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                dataBaseHelper.addOne(movieModel);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                ShowMoviesOnListView(dataBaseHelper);
            }
        });


    }


    private void ShowMoviesOnListView(DataBaseHelper dataBaseHelper2) {
        try {
            movieArrayAdapter = new ArrayAdapter<MovieModel>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, dataBaseHelper2.getAllMovies());
            movie_list.setAdapter(movieArrayAdapter);
        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Error reading list", Toast.LENGTH_SHORT).show();
        }
    }
}
