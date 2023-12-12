package com.example.demoIntellij.controller;

import com.example.demoIntellij.models.Book;
import com.example.demoIntellij.responseDTO.ResponseDTO;
import com.example.demoIntellij.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookServices bookServices;
    @GetMapping("/books")
    private ResponseDTO  getAllBooks(){
       return bookServices.getAllBooks();
    }
    @PostMapping("/books")
    private ResponseDTO saveBook(@RequestBody Book book){
        return bookServices.saveBook(book);
    }
    @DeleteMapping("/books/{id}")
    private ResponseDTO deleteBook(@PathVariable String id){
        return  bookServices.deleteBook(id);
    }
    @GetMapping("/findBookById/{id}")
    private ResponseDTO findBookById(@PathVariable String id){
        return bookServices.findBookById(id);
    }
    @PutMapping("/updateCastWithBookId/{id}")
    private ResponseDTO updateCastWithBookId(@PathVariable String id,@RequestBody Book book){
        return bookServices.updateCastWithBookId(id,book);
    }
    @GetMapping("/books/{name}/{rating}")
    public ResponseDTO getBookByAuthorAndRating(@PathVariable String name,@PathVariable double rating){
        return bookServices.getBookByAuthorAndRating(name,rating);
    }
    @PutMapping("/updateAParticularField/{id}")
    public ResponseDTO updateAParticularField(@PathVariable String id, @RequestBody Book book) {
        return bookServices.updateAParticularField(id,book);
    }
    @GetMapping("getByAuthorGender/{authorGender1}")
    public ResponseDTO getByAuthorGender(@PathVariable String authorGender1){
        return bookServices.getByAuthorGender(authorGender1);
    }
}
