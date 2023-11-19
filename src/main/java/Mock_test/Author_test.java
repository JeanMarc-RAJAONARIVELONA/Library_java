package Mock_test;

import Interface.AuthorCrudOperations;
import Model.Author_Model;

import java.util.ArrayList;
import java.util.List;

public class Author_test {
    public static void testCrudOperationsAuthor() {
        AuthorCrudOperations crudOperations = new AuthorCrudOperations();

        // Test de findAll
        System.out.println("Testing findAll:");
        List<Author_Model> allAuthors = crudOperations.findAll();
        for (Author_Model author : allAuthors) {
            System.out.println(author);
        }

        // Test de save
        System.out.println("\nTesting save:");
        Author_Model newAuthor = new Author_Model(0, "New Author", "Male");
        crudOperations.save(newAuthor);

        // Afficher tous les auteurs après l'ajout
        allAuthors = crudOperations.findAll();
        for (Author_Model author : allAuthors) {
            System.out.println(author);
        }

        // Test de saveAll
        System.out.println("\nTesting saveAll:");
        List<Author_Model> authorsToAdd = new ArrayList<>();
        authorsToAdd.add(new Author_Model(0, "Author A", "Female"));
        authorsToAdd.add(new Author_Model(0, "Author B", "Male"));
        crudOperations.saveAll(authorsToAdd);

        // Afficher tous les auteurs après l'ajout multiple
        allAuthors = crudOperations.findAll();
        for (Author_Model author : allAuthors) {
            System.out.println(author);
        }

        // Test de delete
        System.out.println("\nTesting delete:");
        Author_Model authorToDelete = allAuthors.get(1);
        crudOperations.delete(authorToDelete);

        // Afficher tous les auteurs après la suppression
        allAuthors = crudOperations.findAll();
        for (Author_Model author : allAuthors) {
            System.out.println(author);
        }
    }
}
