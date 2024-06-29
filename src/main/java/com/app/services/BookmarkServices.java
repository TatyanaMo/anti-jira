package com.app.services;

import com.app.dao.BookmarkDAO;
import com.app.model.Bookmark;
import com.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServices {

    @Autowired
    private BookmarkDAO bookmarkDAO;

    public List<Bookmark> getUserBookmarks() {
        return bookmarkDAO.getUserBookmarks(getCurrentUserId());
    }

    public void storeBookmark(long ticketId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(getCurrentUserId());
        bookmark.setTicketId(ticketId);

        bookmarkDAO.storeBookmark(bookmark);
    }

    public void deleteBookmark(long bookmarkId) {
        bookmarkDAO.deleteBookmark(bookmarkId);
    }

    public Integer getBookmarkCount() {
        return bookmarkDAO.getBookmarksCount(getCurrentUserId());
    }

    private long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();

        return customUser.getUser().getId();
    }
}
