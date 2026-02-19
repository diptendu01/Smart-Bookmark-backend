package com.example.bookmark.service;

import com.example.bookmark.model.Bookmark;
import com.example.bookmark.repository.BookmarkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<Bookmark> getAll() {
        return bookmarkRepository.findAll();
    }

    public Bookmark create(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public Bookmark update(Long id, Bookmark updatedBookmark) {
        Bookmark existing = bookmarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bookmark not found: " + id));

        existing.setTitle(updatedBookmark.getTitle());
        existing.setUrl(updatedBookmark.getUrl());

        return bookmarkRepository.save(existing);
    }

    public void delete(Long id) {
        if (!bookmarkRepository.existsById(id)) {
            throw new EntityNotFoundException("Bookmark not found: " + id);
        }
        bookmarkRepository.deleteById(id);
    }
}
