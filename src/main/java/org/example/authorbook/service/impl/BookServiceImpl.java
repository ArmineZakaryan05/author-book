package org.example.authorbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.authorbook.entity.Book;
import org.example.authorbook.repository.BookRepository;
import org.example.authorbook.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Value("${author.book.upload.path}")
    private String uploadPath;

    @SneakyThrows
    @Override
    public Book save(Book book, MultipartFile multipartFile) {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            book.setImageName(fileName);
        }
        book.setCreatedAt(new Date());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}