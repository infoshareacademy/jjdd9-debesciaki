package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository {
    private static List<Category> allCategories = new ArrayList<>();
    private static Map<Integer, Category> allCategoriesMap = new HashMap<>();
    private static Map<Integer, Integer> CategoriesRelationalMap = new HashMap<>();

    private CategoryRepository() {
    }

    public static List<Category> getAllCategories() {
        return allCategories;
    }

    public static void setAllCategories(List<Category> allCategories) {
        CategoryRepository.allCategories = allCategories;
    }

    public static Map<Integer, Category> getAllCategoriesMap() {
        return allCategoriesMap;
    }

    public static void setAllCategoriesMap() {
        for (int i = 0; i < allCategories.size(); i++) {
            CategoryRepository.allCategoriesMap.put(i+1, allCategories.get(i));
        }
    }
    public static void setRelationForCategories(){
        for (int i = 0; i < allCategories.size(); i++) {
            CategoryRepository.CategoriesRelationalMap.put(i+1,allCategories.get(i).getId());
        }
    }
}
