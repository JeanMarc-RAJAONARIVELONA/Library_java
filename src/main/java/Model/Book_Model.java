package Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book_Model {
    private int id_books;
    private String bookName;
    private int pageNumber;
    private String topic;
    private boolean is_borrowed;
    private int id_author;
}
