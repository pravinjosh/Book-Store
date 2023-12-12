package com.example.demoIntellij.services;

import com.example.demoIntellij.dao.BookDao;
import com.example.demoIntellij.models.Book;
import com.example.demoIntellij.repository.BookRepository;
import com.example.demoIntellij.responseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookServices {
    @Autowired
    private BookDao bookDao;
    public ResponseDTO getAllBooks(){
        List<Book> books=bookDao.getAllBooks();
        if (!books.isEmpty()){
            return new ResponseDTO(HttpStatus.FOUND.value(),"Books Found","SUCCESS",books);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(),"No Books are Available","FAILED",null);
    }
    public ResponseDTO saveBook(Book book){
        if (book!=null){
           Book book2= bookDao.saveBook(book);
           if (book2!=null){
               return new ResponseDTO(HttpStatus.CREATED.value(), "Book Saved Successfully","SUCCESS",book2);
           }
           return new ResponseDTO(HttpStatus.RESET_CONTENT.value(), "Book not saved","FAILED",null);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Book you are trying to save is empty","FAILED",null);
    }
    public ResponseDTO deleteBook(String id){
        BookRepository bookRepository=bookDao.deleteBook(id);
        try {
            Book book=bookRepository.findById(id).get();
            return new ResponseDTO(HttpStatus.EXPECTATION_FAILED.value(), "Book not deleted","FAILED",book);
        }catch (Exception exception){
            return new ResponseDTO(HttpStatus.OK.value(), "Book Deleted Successfully","SUCCESS",null);
        }

    }
    public ResponseDTO findBookById(String id){
        try {
            Book book=bookDao.findBookById(id).get();
            return new ResponseDTO(HttpStatus.FOUND.value(), "Book Found","SUCCESS",book);
        }catch (Exception exception){
            return new ResponseDTO(HttpStatus.NOT_FOUND.value(), "Book with this ID is not Available","FAILED",null);
        }

    }
    public ResponseDTO updateCastWithBookId(String id,Book book){
        try {
            Book book1= (Book) findBookById(id).getData();
            book1.setCast(book.getCast());
            Book book2=bookDao.updateCastWithBookId(book1);
            if (book2.getCast().equals(book1.getCast())){
                return new ResponseDTO(HttpStatus.CREATED.value(), "Cast Updated Successfully","SUCCESS",book2);
            }
            return new ResponseDTO(HttpStatus.NOT_MODIFIED.value(), "Cast Not Updated","FAILED",book);

        }catch (Exception exception){
            return new ResponseDTO(HttpStatus.NOT_FOUND.value(), "Book With this ID not Found","FAILED",null);
        }

    }
    public ResponseDTO getBookByAuthorAndRating(String name, Double rating){
        List<Book> books=bookDao.getBookByAuthorAndRating(name,rating);
        if (!books.isEmpty()){
            return new ResponseDTO(HttpStatus.FOUND.value(), "Books Found based on Author Name and Rating","SUCCESS",books);
        }
        books=bookDao.getBookByAuthor(name);
        if (!books.isEmpty()){
            return new ResponseDTO(HttpStatus.FOUND.value(), "Books Found Based on Author Name there are no books based on the given rating","SUCCESS",books);
        }
        books=bookDao.getBookByRating(rating);
        if (!books.isEmpty()){
            return new ResponseDTO(HttpStatus.FOUND.value(), "Books Found based on Rating there are no books based on the given Author Name","SUCCESS",books);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(), "There are no books Present with the Given Author name and Rating","FAILED",null);
    }
    public ResponseDTO updateAParticularField(String id, Book book) {
        Book book1=bookDao.updateAParticularField(id,book);
        if (book1!=null){
            return new ResponseDTO(HttpStatus.ACCEPTED.value(), "Field Updated Successfully","SUCCESS",book1);
        }
        return new ResponseDTO(HttpStatus.NOT_MODIFIED.value(), "Failed to Modify the field","Failed",book);
    }
    public ResponseDTO getByAuthorGender(String authorGender1){
        List<Book> books=bookDao.getByAuthorGender(authorGender1);
        if (!books.isEmpty()){
            return new ResponseDTO(HttpStatus.FOUND.value(), "Books Found By Gender","SUCCESS",books);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(), "No Books found Based On the Given Gender","FAILED",null);
    }
}
