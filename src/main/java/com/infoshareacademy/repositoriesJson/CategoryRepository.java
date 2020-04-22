package com.infoshareacademy.repositoriesJson;

import com.infoshareacademy.parseJson.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static List<Category> allCategorys = new ArrayList<>();

    private CategoryRepository() {
    }

    public static List<Category> getAllCategorys() {
        return allCategorys;
    }

    public static void setAllCategorys(List<Category> allCategorys) {
        CategoryRepository.allCategorys = allCategorys;
    }
}
