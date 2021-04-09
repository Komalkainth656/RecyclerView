package com.dgl114.assignment4;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutionException;

// 1. Add Room annotations here.
@Database(entities = Card.class, exportSchema = false, version = 1)
public abstract class CardDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "card.db";
    private static CardDatabase mCardDatabase;

    // Singleton
    public static CardDatabase getInstance(Context context) {
        if (mCardDatabase == null) {
            // 2. Complete this method. Consider the approach used in StudyHelper.
                // Don't neglect to handle database schema changes,
                // and don't forget to add starter data!
            mCardDatabase = Room.databaseBuilder(context.getApplicationContext(), CardDatabase.class, DATABASE_NAME).build();
        }

        return mCardDatabase;
    }

    public abstract CardDao cardDao();

    // 3. Complete the implementation of addStarterData.
        // You can follow StudyHelper very closely here,
        // but! the specific model methods will differ.
        // Don't forget that in StudyHelper both Subject and Questions were handled here
        // you only need to handle Cards, but you will need to use runInTransaction()
    public void addStarterData() {
        try {
            new addCard().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private class addCard extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            Card card = new Card("Red", "Red is violence, anger, and aggression, and it frequently indicates danger.");
            mCardDatabase.cardDao().insertCard(card);
            return null;
        }
    }

}
