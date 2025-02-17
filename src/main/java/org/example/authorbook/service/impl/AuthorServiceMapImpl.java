package org.example.authorbook.service.impl;

import org.example.authorbook.entity.Author;
import org.example.authorbook.service.AuthorService;
import org.example.authorbook.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceMapImpl implements AuthorService {


    @Override
    public List<Author> findAll() {
        return List.of();
    }

    public Page<Author> findAll(Pageable pageable) {
        return new PageImpl<>(List.of());
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public List<Author> search(String keyword) {
        return List.of();
    }

    @Override
    public List<Author> filter(SearchCriteria searchCriteria) {
        return List.of();
    }
}
