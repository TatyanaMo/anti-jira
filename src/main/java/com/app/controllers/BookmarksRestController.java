package com.app.controllers;

import com.app.model.Bookmark;
import com.app.services.BookmarkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmarksRestController {
    @Autowired
    private BookmarkServices bookmarkServices;

    @GetMapping("/bookmarks/count")
    private Integer getBookmarksCount() {
        return bookmarkServices.getBookmarkCount();
    }

    @GetMapping("/bookmarks/all")
    private List<Bookmark> getUserBookmarks() {
        return bookmarkServices.getUserBookmarks();
    }

    @DeleteMapping("/bookmarks/delete/{id}")
    private void deleteBookmark(@PathVariable("id") long id) {
        bookmarkServices.deleteBookmark(id);
    }

    @PostMapping("/bookmarks/add/{id}")
    private void addBookmark(@PathVariable("id") long id) {
        bookmarkServices.storeBookmark(id);
    }
}
