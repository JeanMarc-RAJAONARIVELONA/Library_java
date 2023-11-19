package Mock_test;

import Interface.SubscribersCrudOperations;
import Model.Subscribers_Model;

import java.util.ArrayList;
import java.util.List;

public class Subscribers_test {
    public static void testCrudOperationsSubscribers() {
        SubscribersCrudOperations crudOperations = new SubscribersCrudOperations();

        // Test de findAll
        System.out.println("Testing findAll:");
        List<Subscribers_Model> allSubscribers = crudOperations.findAll();
        for (Subscribers_Model subscriber : allSubscribers) {
            System.out.println(subscriber);
        }

        // Test de save
        System.out.println("\nTesting save:");
        Subscribers_Model newSubscriber = new Subscribers_Model(0, "New Subscriber", "new@example.com", "Reference X");
        crudOperations.save(newSubscriber);

        // Afficher tous les abonnés après l'ajout
        allSubscribers = crudOperations.findAll();
        for (Subscribers_Model subscriber : allSubscribers) {
            System.out.println(subscriber);
        }

        // Test de saveAll
        System.out.println("\nTesting saveAll:");
        List<Subscribers_Model> subscribersToAdd = new ArrayList<>();
        subscribersToAdd.add(new Subscribers_Model(0, "Subscriber A", "a@example.com", "Reference A"));
        subscribersToAdd.add(new Subscribers_Model(0, "Subscriber B", "b@example.com", "Reference B"));
        crudOperations.saveAll(subscribersToAdd);

        // Afficher tous les abonnés après l'ajout multiple
        allSubscribers = crudOperations.findAll();
        for (Subscribers_Model subscriber : allSubscribers) {
            System.out.println(subscriber);
        }

        // Test de delete
        System.out.println("\nTesting delete:");
        Subscribers_Model subscriberToDelete = allSubscribers.get(1);
        crudOperations.delete(subscriberToDelete);

        // Afficher tous les abonnés après la suppression
        allSubscribers = crudOperations.findAll();
        for (Subscribers_Model subscriber : allSubscribers) {
            System.out.println(subscriber);
        }
    }
}
