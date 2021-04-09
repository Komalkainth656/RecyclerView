package com.dgl114.assignment4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AddItemInterface, ItemClickInterface {

    private CardAdapter mCardAdapter;
    private RecyclerView mRecyclerView;
    private CardDatabase mCardDb;
    private List<Card> list;
    AddItemFragment addItemFragment;
    private static final String TAG = "MainActivity";
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Singleton
        mCardDb = CardDatabase.getInstance(getApplicationContext());
        mCardDb.addStarterData();
        list = new ArrayList<>();
        // 1. Instantiate the RecyclerView object and set layout
        mRecyclerView = findViewById(R.id.cardRecyclerView);


        // 2. Instantiate the CardAdapter from the CardAdapter constructor
        // set it to the RecyclerView adapter
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCardAdapter = new CardAdapter(list, this);
        mRecyclerView.setAdapter(mCardAdapter);

        try {
            loadList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // 3. Write a private method (similar to loadSubjects from StudyHelper)
        // that returns a List<Card> from the database (use the CardDao!)
    private List<Card> loadList() throws ExecutionException, InterruptedException {
        return new getCardsAsyncTask().execute().get();
    }

    //Override method of interface to add the item in the list.
    @Override
    public void addItem(String name, String description) {
        Card card = new Card(name, description);
        try {
            new addCards(card).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Override method of interface to delete the item from the list. This method will trigger when person long press on the item
    @Override
    public void deleteItem(Card card) {
        try {
            new deleteCard(card).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //async task to get all the cards from the database and on post execution the list is refreshed
    private class getCardsAsyncTask extends AsyncTask<Void, Void,List<Card>>
    {
        @Override
        protected List<Card> doInBackground(Void... url) {
            return mCardDb.cardDao().getCardList();
        }

        @Override
        protected void onPostExecute(List<Card> cards) {
            super.onPostExecute(cards);
            list.clear();
            list.addAll(cards);
            mCardAdapter.notifyDataSetChanged();
        }
    }

    //async task to add the card in the database and on post execution the list is refreshed
    private class addCards extends AsyncTask<Void, Void,Void>
    {
        Card card;
        public addCards(Card card) {
            this.card = card;
        }

        @Override
        protected Void doInBackground(Void... url) {
            mCardDb.cardDao().insertCard(card);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                loadList();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //async task to delete the card from the database and on post execution the list is refreshed
    private class deleteCard extends AsyncTask<Void, Void,Void>
    {
        Card card;
        public deleteCard(Card card) {
            this.card = card;
        }

        @Override
        protected Void doInBackground(Void... url) {
            mCardDb.cardDao().deleteCard(card);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                loadList();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 4. Complete the CardHolder class
    private class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Card mCard;
        private TextView mTextView;
        private ItemClickInterface clickInterface;
        public CardHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recycler_view_items, parent, false));
            itemView.setOnClickListener(this);
            mTextView = itemView.findViewById(R.id.cardTextView);
            itemView.setOnLongClickListener(this);
        }

        // 5. Complete the bind method. Use this method to ensure mCard and mTextView
            // both have appropriate values.
            // You may also use this space to distinguish each Card using a method similar
            // to that in StudyHelper - or of your own design.
        public void bind(Card card, ItemClickInterface clickInterface) {
            mCard = card;
            this.clickInterface = clickInterface;
            mTextView.setText(mCard.getText());

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CardDetailsActivity.class);
            intent.putExtra(CardDetailsActivity.EXTRA_CARD_ID, mCard.getId());
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            clickInterface.deleteItem(mCard);
            return true;
        }
    }

    // 6. Complete the CardAdapter class
    private class CardAdapter extends RecyclerView.Adapter<CardHolder> {

        private List<Card> mCardList;
        private ItemClickInterface clickInterface;
        // 7. Write the CardAdapter constructor. Look to StudyHelper for inspiration.
        CardAdapter(List<Card> mCardList, ItemClickInterface clickInterface){
            this.mCardList = mCardList;
            this.clickInterface = clickInterface;
        }

        @NonNull
        @Override
        public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            return new CardHolder(layoutInflater, parent);
        }

        // 8. Complete the onBindViewHolder method. Look to StudyHelper for inspiration.
        @Override
        public void onBindViewHolder(CardHolder holder, int position) {
            holder.bind(mCardList.get(position), clickInterface);
        }

        @Override
        public int getItemCount() {
            return mCardList.size();
        }
    }

    //method used to inflate the menu to show the add icon in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Override method to handle the click on the menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
            {
                addItemFragment = new AddItemFragment(MainActivity.this);
                addItemFragment.show(this.getSupportFragmentManager(), TAG);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}