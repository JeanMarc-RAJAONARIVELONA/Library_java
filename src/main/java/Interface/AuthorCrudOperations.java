package Interface;

import Model.Author_Model;

import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author_Model>{
    @Override
    public List<Author_Model> findAll() {
        return null;
    }

    @Override
    public List<Author_Model> saveAll(List toSave) {
        return null;
    }

    @Override
    public Author_Model save(Author_Model toSave) {
        return null;
    }

    @Override
    public Author_Model delete(Author_Model toDelete) {
        return null;
    }
}
