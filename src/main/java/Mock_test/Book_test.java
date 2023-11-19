package Mock_test;

import Interface.BookCrudOperations;
import Model.Book_Model;

import java.util.ArrayList;
import java.util.List;

public class Book_test {
    public static void testCrudOperations() {
        BookCrudOperations crudOperations = new BookCrudOperations();
        // Test de findAll
        System.out.println("Testing findAll:");
        List<Book_Model> allBooks = crudOperations.findAll();
        for (Book_Model book : allBooks) {
            System.out.println(book);
        }

        // Test de save
        System.out.println("\nTesting save:");
        Book_Model newBook = new Book_Model(0, "New Book", 150, "Science Fiction", false, 1);
        crudOperations.save(newBook);

        // Afficher tous les livres après l'ajout
        allBooks = crudOperations.findAll();
        for (Book_Model book : allBooks) {
            System.out.println(book);
        }

        // Test de saveAll
        System.out.println("\nTesting saveAll:");
        List<Book_Model> booksToAdd = new ArrayList<>();
        booksToAdd.add(new Book_Model(0, "Book A", 200, "COMEDY", true, 2));
        booksToAdd.add(new Book_Model(0, "Book B", 180, "OTHER", false, 3));
        crudOperations.saveAll(booksToAdd);

        // Afficher tous les livres après l'ajout multiple
        allBooks = crudOperations.findAll();
        for (Book_Model book : allBooks) {
            System.out.println(book);
        }

        // Test de delete
        System.out.println("\nTesting delete:");
        Book_Model bookToDelete = allBooks.get(1);
        crudOperations.delete(bookToDelete);

        // Afficher tous les livres après la suppression
        allBooks = crudOperations.findAll();
        for (Book_Model book : allBooks) {
            System.out.println(book);
        }
    }

}
