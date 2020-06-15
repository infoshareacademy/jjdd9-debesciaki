package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.api.PlaceJSON;
import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.domain.entity.Place;
import com.infoshareacademy.mapper.CategoryMapper;
import com.infoshareacademy.mapper.EventMapper;
import com.infoshareacademy.mapper.OrganizerMapper;
import com.infoshareacademy.mapper.PlaceMapper;

import com.infoshareacademy.mapper.RootCategoryMapper;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ListJsonToDaoBean {
    @Inject
    private PlaceMapper placeMapper;

    @Inject
    private  EventMapper eventMapper;

    @Inject
    private CategoryMapper categoryMapper;

    @Inject
    private OrganizerMapper organizerMapper;

    public List<Place> place(List<PlaceJSON> in) {
        List<Place> out = new ArrayList<>();
        for (PlaceJSON p : in) {
            out.add(placeMapper.jsonToDao(p));
        }
        return out;
    }

    public List<Event> event(List<EventJSON> in) {
        List<Event> out = new ArrayList<>();
        for (EventJSON e : in) {
            out.add(eventMapper.jsonToDao(e));
        }
        return out;
    }

    public List<Organizer> organizer(List<OrganizerJSON> in) {
        List<Organizer> out = new ArrayList<>();
        for (OrganizerJSON o : in) {
            out.add(organizerMapper.jsonToDao(o));
        }
        return out;
    }

    public List<Category> category(List<CategoryJSON> in) {
        List<Category> out = new ArrayList<>();
        for (CategoryJSON c : in) {

            if (c.getId() < 100) {
              Category rootCategory = categoryMapper.jsonToDao(c);
              out.add(rootCategory);

            } else {
              Long rootCategoryid = c.getRootCategory().getId();
              Category category = categoryMapper.jsonToDao(c);
              Category rootCategory = findCategoryByApiId(out, rootCategoryid);

              if(rootCategory != null) {
                rootCategory.getCategories().add(category);
                category.setRootCategory(rootCategory);
              }
            }
        }

        return out;
    }

    private Category findCategoryByApiId(List<Category> categories, Long id) {
        return categories.stream().filter(c -> id.equals(c.getApiId())).findFirst().orElse(null);
    }

}
