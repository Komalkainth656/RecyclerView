package com.dgl114.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CARD_ID = "com.dgl114.assignment4.recyclerview.card_id";
    private TextView cardLabel;
    private TextView cardDetail;
    private long cardId;
    private CardDatabase mCardDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        cardId = getIntent().getExtras().getLong(EXTRA_CARD_ID);
        // 1. Get the Intent that brought you here - don't create a new Intent!

        mCardDb = CardDatabase.getInstance(getApplicationContext());

        // 2. Get a single card - use the CardDao and make sure you get the right Card!


        // 3. Get references to your view widgets so that the Card data can be displayed.
        cardLabel = findViewById(R.id.cardLabel);
        cardDetail = findViewById(R.id.cardDetail);

        // 4. Show the card by calling showCard()
            // Unlike in StudyHelper there is no need for showCard to have a parameter here,
            // since you've already picked the right card above.
            // (Remember that in StudyHelper choosing a Subject displayed numerous Questions;
            // in this app choosing a card displays more details about *that* card).
        try {
            showCard();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showCard() throws ExecutionException, InterruptedException {
        // 5. Set values as appropriate for the views, based on the Card instance
        new getCardData().execute().get();
    }

    private class getCardData extends AsyncTask<Void, Void, Card>
    {
        @Override
        protected Card doInBackground(Void... url) {
            return mCardDb.cardDao().getCard(cardId);
        }

        @Override
        protected void onPostExecute(Card card) {
            super.onPostExecute(card);
            cardLabel.setText(card.getText());
            cardDetail.setText(card.getDetail());
        }
    }
}