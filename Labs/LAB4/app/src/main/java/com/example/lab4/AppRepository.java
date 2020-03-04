package com.example.lab4;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AppRepository {

    // Cloud Firestore instance
    private FirebaseFirestore db;

    //recipe collection
    private CollectionReference phoneref;

    public AppRepository(){
        db = FirebaseFirestore.getInstance();
        phoneref = db.collection("phoneNumbers");
    }

    //options to set up the adapter
    public FirestoreRecyclerOptions<Item> getOptions(){
        Query myquery = phoneref;
        FirestoreRecyclerOptions<Item> options = new FirestoreRecyclerOptions.Builder<Item>()
                .setQuery(myquery, Item.class)
                .build();
        return options;
    }

    public void insertRecipe(Item newItem){
        phoneref.add(newItem);
    }

    public void deleteRecipe(String id){
        phoneref.document(id).delete();
    }
}
