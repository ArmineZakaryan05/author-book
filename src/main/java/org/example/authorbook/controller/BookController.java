package org.example.authorbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.authorbook.entity.Author;
import org.example.authorbook.entity.Book;
import org.example.authorbook.service.AuthorService;
import org.example.authorbook.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String bookPage(ModelMap modelMap) {
        List<Book> books = bookService.findAll();
        modelMap.put("books", books);
        return "book/books";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap) {
        List<Author> authors = authorService.findAll();
        modelMap.put("authors", authors);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam("image") MultipartFile multipartFile) {
        bookService.save(book, multipartFile);
        return "redirect:/books";
    }

}