package com.ugurhan.trello.test;
import com.ugurhan.trello.functions.BaseTest;
import org.junit.jupiter.api.Test;

public class trelloApiCase extends BaseTest {

    @Test
    public void trelloAPI() {
        createBoard("Board Create Test");
        getBoardId();
        getBoardLists();
        createCard("Card Oluştur","Api kullanarak board a yeni bir kart oluşturulur.");
        createCard("Card-2 Oluştur","Api kullanarak board a yeni bir kart oluşturulur.");
        randomGetCards();
        updateCard("update","desc");
        deleteCard();
        randomGetCards();
        deleteCard();
        deleteBoard();




    }
}