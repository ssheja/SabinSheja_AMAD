package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;

public class ItemAdapter extends FirestoreRecyclerAdapter<Item, ItemViewHolder>  {
    private Context mContext;
    private AppRepository mappRepository;

    public ItemAdapter(FirestoreRecyclerOptions<Item> options, Context context, AppRepository appRepository){
        super(options);
        this.mContext = context;
        this.mappRepository = appRepository;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ItemViewHolder itemHolder, int i, @NonNull final Item item) {
        itemHolder.setRecipeName(item.getName());

        //onclick listener
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get recipe that was pressed
                int position = itemHolder.getAdapterPosition();
                //get snapshot
                DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(position);
                //get recipe url
                String recipeURL = documentSnapshot.toObject(Item.class).getPhone();
                //create new intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //add url to intent
                intent.setData(Uri.parse(recipeURL));
                //start intent
                mContext.startActivity(intent);
            }
        });

        //context menu
        itemHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                //set the menu title
                menu.setHeaderTitle("Delete " + item.getName());
                //add the choices to the menu
                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //get recipe that was pressed
                        int position = itemHolder.getAdapterPosition();
                        //get document id
                        String id = getSnapshots().getSnapshot(position).getId();
                        //delete from repository
                        mappRepository.deleteRecipe(id);

                        Snackbar.make(v, "Item removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ItemViewHolder recipeHolder = new ItemViewHolder(itemView);
        return recipeHolder;
    }
}
