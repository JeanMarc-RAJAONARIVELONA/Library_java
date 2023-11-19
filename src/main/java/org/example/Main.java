package org.example;

import Connection.Db_connection;

import static Mock_test.Book_test.testCrudOperations;

public class Main {
    public static void main(String[] args) {
        Db_connection.getConnection();
        testCrudOperations();
    }
}