package org.example;

import Connection.Db_connection;

import static Mock_test.Author_test.testCrudOperationsAuthor;
import static Mock_test.Book_test.testCrudOperationsBook;
import static Mock_test.Subscribers_test.testCrudOperationsSubscribers;

public class Main {
    public static void main(String[] args) {
        Db_connection.getConnection();
        /*testCrudOperationsBook();
        testCrudOperationsAuthor();*/
        testCrudOperationsSubscribers();
    }

}