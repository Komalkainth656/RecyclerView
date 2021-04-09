# assignment4
 Requirements
 For your assignment to be considered complete you must do the following:
Documentation
Edit the README.md file with appropriate GitHub Markdown styles to summarize the changes made to the starter code. You should also include the names of any students that you collaborated with (collaboration is ok, but your code should be relatively distinct).
Include any *necessary* comments directly in .java files. Necessary comments include those needed to explain logic that may be unclear from context, or incomplete code. In general, I'm looking for you to spend more time making your code read cleanly, and less time with comments.
Note: Including more comments than are strictly necessary will not be penalized - you may use JavaDoc style documentation if you wish.
Note: I will however penalize completely unnecessary comments (such as comments that refer to code that has been deleted); commented out code that has no bearing on your final submission (i.e. unneeded test code, etc.); and poor formatting in general.
Code
Necessary code direction are present in the form of comments in all files you must complete.
In general, you are responsible for completing the implementation of RecyclerView and Room in the following files:


Card.java
CardDao.java
CardDatabase.java
CardDetailsActivity.java
MainActivity.java
xml and other files
No changes are required to xml or other files to complete the RecyclerView and Room implementation.
However, changes will be necessary to xml layouts (see Personalization below).
Personalization
Add a 'personal touch' to your app:  At present, the app contains only generic placeholder content. I expect that you will choose the content you would like the app to display and make layout and model changes accordingly. For example, perhaps you want your app to list books. Then your MainActivity could display book titles, and perhaps authors, while the CardDetailsActivity could display title, author, publication date, and a summary of the book.
Further changes to the app are welcome (though not required); ambition is always looked at favourably. Consider that there is no way to *add* data to the database at present, for example.
And, in general, please be careful to choose appropriate naming conventions for all identifiers (i.e. variables, methods, etc.). Make sure your code is well-formatted before submission (no unnecessary whitespace, good indenting and spacing). And, of course, make sure it runs without errors!

Changes made in the code
In the project, I have used colors and their description
1. AddItemFragment is added to allow the person to add the colors and the description in the room database.
2. AddItemInterface is added to send the color information to the main activity to save in the database. Two parameters are passed in the interface, color name and description
3. In the card.java class room annotation is added to give table name, primary keys and column name and constructor is added to get the values.
4. In card database queries are added to insert data, get single data, get list and delete row
5. In card database - instance of the database is created and the in the starter code, one row is added in the database with the async task
6. In card details activity - card id is passed as it is unique and with this the information of card is fetched from the database with async task.
7. Main activity changes -
    a. Async tasks are added to get list, add item and remove item to/from the database.
    b. Two interfaces are added to add and delete card.
    c. Menu is inflated in the toolbar on its click fragment is shown to add the item in the database.
    b. Interface is passed to the recycler view to implement the long press on item to delete the item from the database


