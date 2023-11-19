package Interface;

import Model.Book_Model;

import java.util.List;

public class BookCrudOperations implements CrudOperations<Book_Model>{

    @Override
    public List<Book_Model> findAll() {
        return null;
    }

    @Override
    public List<Book_Model>saveAll(List toSave) {
        return null;
    }

    @Override
    public Book_Model save(Book_Model toSave) {
        return null;
    }

    @Override
    public Book_Model delete(Book_Model toDelete) {
        return null;
    }
}
