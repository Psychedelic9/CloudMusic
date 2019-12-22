package com.bai.psychedelic.autoshell;

import com.bai.psychedelic.autoshell.Book;

interface BookController {
    List<Book> getBookList();

    void addBookInOut(inout Book book);
}
