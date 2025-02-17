package org.example.authorbook.controller;

import lombok.RequiredArgsConstructor;
import org.example.authorbook.entity.Author;
import org.example.authorbook.service.AuthorService;
import org.example.authorbook.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private final AuthorService authorService;

    // private final List<AuthorService> authorServices;

    //private final Map<String, AuthorService> authorServiceMap;

    @GetMapping
    public String authorPage(ModelMap modelMap,
                             @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                             @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort );
        Page<Author> authorPage = authorService.findAll(pageRequest);
        int totalPages = authorPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        modelMap.put("authors", authorPage);
        return "author/authors";
    }

    @GetMapping("/search")
    public String searchAuthors(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        List<Author> searchResult = authorService.search(keyword);
        modelMap.addAttribute("authors", searchResult);
        return "author/authorsSearch";
    }

    @GetMapping("/filter")
    public String filterAuthors(@ModelAttribute SearchCriteria searchCriteria, ModelMap modelMap) {
        List<Author> searchResult = authorService.filter(searchCriteria);
        modelMap.addAttribute("authors", searchResult);
        modelMap.addAttribute("name", searchCriteria.getName());
        modelMap.addAttribute("surname", searchCriteria.getSurname());
        modelMap.addAttribute("phone", searchCriteria.getPhone());
        return "author/authorsSearch";
    }

    @GetMapping("/add")
    public String addAuthorPage() {
        return "author/addAuthor";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete")
    public String deleteAuthorPage(@RequestParam("id") int id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") int id, ModelMap modelMap) {
        Author author = authorService.findById(id);
        if (author != null) {
            modelMap.put("author", author);
            return "author/editAuthor";
        }
        return "redirect:/authors";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }
}