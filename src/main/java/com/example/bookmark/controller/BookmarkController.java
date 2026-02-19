package com.example.bookmark.controller;

import com.example.bookmark.model.Bookmark;
import com.example.bookmark.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public List<Bookmark> getBookmarks() {
        return bookmarkService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bookmark createBookmark(@Valid @RequestBody Bookmark bookmark) {
        return bookmarkService.create(bookmark);
    }

    @PutMapping("/{id}")
    public Bookmark updateBookmark(@PathVariable Long id, @Valid @RequestBody Bookmark bookmark) {
        return bookmarkService.update(id, bookmark);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookmark(@PathVariable Long id) {
        bookmarkService.delete(id);
    }
}
