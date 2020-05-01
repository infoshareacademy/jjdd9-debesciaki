package com.infoshareacademy.display;

import com.infoshareacademy.parser.Category;
import com.infoshareacademy.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DisplayCategories {
    Map<Integer, Category> categoryMap;
    List<Category> categoryList;
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public DisplayCategories() {
        this.categoryMap = CategoryRepository.getAllCategoriesMap();
        this.categoryList = CategoryRepository.getAllCategories();
    }

    public void displyGenres() {
        for (Category c: categoryList) {
           if(c.getId()<=10){
               STDOUT.info("{} {}\n",c.getId(),c.getName());
           }
        }
    }
}
