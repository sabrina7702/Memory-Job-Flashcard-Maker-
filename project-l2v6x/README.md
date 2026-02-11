# Memory Jog

## Project Overview:

Memory Jog will be an flashcard based application where students can make and sort their flashcards into different 
folders. More specifically, students can make and add as many cards as they would like into folders and remove an
arbitrary amount of cards (until the folders have zero cards left). The learning style will not be a basic 
flashcard style where if the user answers correctly it marks it as reviewed. Instead, the flashcards are presented 
in a jeopardy game show style, timed multiple choice quizzes, or matching games. Also, the flashcards themselves 
would be regular flashcards styles or fill in the blanks. Flashcards that are chosen for review are going to be 
based on a 5-times-in-a-row scale which determines whether or not the user still needs to review a card. A 1 in 
the scale means the user still needs review, and a 5 means the user does not need review. The scale will flucuate 
depending on how many times in a row the user can correctly answer the flashcard.

The application will have a point system. Essentially, after each game the user will earn points based on how well
they did in correctly answering their flashcards. Points will accumulate to levels and there are 10 levels. Every 2 
or so levels the user unlocks the perks of having a new revision game or type of flashcards they can use (regular/fill
in the blank). I want to pursue this project because I find it difficult to find a revision app that suits my style
in engagement and customization. I hope to use this outside of CPSC 210 in my other courses I'll take in the future!

## User Stories:
- As a user, I want to create flashcards.
- As a user, I want to categorize my flashcards.
- As a user, I want to remove flashcards.
- As a user, I want to take quizzes based on my flashcards.
- As a user, I want to costumize my flashcard's format.
- As a user, I want to unlock perks as I level up.
- As a user, I want to play matching game with my flashcards.
- As a user, I want to play jeopardy with my flashcards.
- As a user, I want to view all my reviewed flashcards.
- As a user, I want to view cards I still need practice on.
- As a user, I want to be able to save my flashcards (If I choose so)
- As a user, I want to be able to load my flashcards from file (If I choose so)
- As a user, when I select the quit option from the application menu, I want to be reminded to save my 
    flashcards to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my flashcards from file.

## Instructions for End User
- How to add flashcard to your deck
    1. Click view flashcards/deck from main menu
    2. Click the button with the "+" on the bottom of screen
    3. Fill in the question and answer dialog box that will appear
    4. Click ok once card is added

- How to remove flashcard from deck
    1. In the view flashcards/deck page, click "remove card" button
    2. Type in the number corresponding to the card you want to remove
    3. Press enter!

- How to change status of a flashcard
    1. In the view flashcards/deck page, select a card in the menu
    2. Answer "yes" or "no" on the window that then appears
        - Yes: marks flashcard as reviewed
        - No: marks flashcard as unreviewed

- How to see your progress
    - A progress bar will display at the top of the flashcards/deck page!
        - This progress bar updates as you add/remove/change status of the cards

- How to save cards you made when exiting app
    1. When you exit any page, a window will prompt asking if you want to save a file
        - Yes: All the cards you made, and the progress made will be saved
        - No: None of the cards you made nor the progress made will be saved

- How to load the cards you saved when opening app
    1. When the app first opens, it will ask you if you want to load your saved file
        - Yes: Your most currently saved cards will load
        - No: You will start a clean slate in the app with no previous cards present
 