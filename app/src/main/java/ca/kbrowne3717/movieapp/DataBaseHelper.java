package ca.kbrowne3717.movieapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class DataBaseHelper {
    private static final String TAG = "MainActivity";

    public void addOne(MovieModel movieModel){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> movie = new HashMap<>();
        movie.put("title", movieModel.getTitle());
        movie.put("description", movieModel.getDescription());
        movie.put("imdb", movieModel.getImdb());

        db.collection("movies")
                .add(movie)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public List<MovieModel> getAllMovies(){
        final List<MovieModel> returnList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("movie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " READALL=> " + document.getData());
                                String title = document.get("title").toString();
                                String description = document.get("description").toString();
                                String imdb = document.get("imdb").toString();
                                MovieModel movieModel = new MovieModel(title,description,imdb);
                                returnList.add(movieModel);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return returnList;
    }
}
