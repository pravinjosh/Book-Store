package com.example.demoIntellij.dao;

import com.example.demoIntellij.models.Book;
import com.example.demoIntellij.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public BookRepository deleteBook(String id){
        bookRepository.deleteById(id);
        return bookRepository;
    }
    public Optional<Book> findBookById(String id){
       Optional<Book> book=bookRepository.findById(id);
       return book;
    }
    public Book updateCastWithBookId(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getBookByAuthorAndRating(String name, Double rating){
        Query query=Query.query(Criteria.where("bookAuthor").is(name).and("rating").gte(rating));
        List<Book> books=mongoTemplate.find(query,Book.class);
        return books;
    }
    public List<Book> getBookByAuthor(String name){
        Query query=Query.query(Criteria.where("bookAuthor").is(name));
        List<Book> books=mongoTemplate.find(query,Book.class);
        return books;
    }
    public List<Book> getBookByRating(Double rating){
        Query query=Query.query(Criteria.where("rating").gte(rating));
        List<Book> books=mongoTemplate.find(query,Book.class);
        return books;
    }
    public Book updateAParticularField(String id,Book book){
        Query query=Query.query(Criteria.where("_id").is(id));
        mongoTemplate.findAndModify(query,getupdate(book),Book.class);
        query.fields().exclude("_id","_class");
        Book book1=mongoTemplate.findOne(query, Book.class,"book");
        return book1;
    }
    private Update getupdate(Book book){
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> map=objectMapper.convertValue(book, Map.class);
        Update update=new Update();
        for(Map.Entry<String,Object> entries:map.entrySet()){
            if (!entries.getKey().equals("id")&&entries.getValue()!=null){
                update.set(entries.getKey(), entries.getValue());
            }
        }
        return update;
    }
    public List<Book> getByAuthorGender(String authorGender1){
        Collection<Book> collection=getAllBooks();
        List<Book> books=collection.stream().filter(gender->gender.getAuthorGender().equalsIgnoreCase(authorGender1)).toList();
        return books;
    }
}
