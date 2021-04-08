package com.dgl114.assignment4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// 1. Add additional Room annotations as necessary to this file.
@Dao
public interface CardDao {
    // 2. Include @Query methods here (you will need at least two
            // one to get a single Card
            // one to get a List<Card>
        // Use StudyHelper as a guide - you can use nearly identical SELECT queries,
        // but you will need to change parameter names.
    @Query("Select * from Card")
    List<Card> getCardList();

    @Query("Select * from Card where mId = (:mId) ")
    Card getCard(long mId);

    // 3. Include an @Insert method - don't forget the OnConflictStrategy!
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCard(Card card);

    // 4. You may include @Update and @Delete methods,
        // though they are not required for a minimum submission.

    @Delete
    void deleteCard(Card card);

}
