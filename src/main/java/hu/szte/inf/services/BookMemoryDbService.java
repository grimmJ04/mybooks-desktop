package hu.szte.inf.services;

import hu.szte.inf.models.Book;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookMemoryDbService extends ModelService<Map<Long, Book>> {

    private static BookMemoryDbService instance;

    private BookMemoryDbService() {
        super(new LinkedHashMap<>());
    }

    public static BookMemoryDbService getInstance() {
        if (instance == null) instance = new BookMemoryDbService();
        return instance;
    }
}