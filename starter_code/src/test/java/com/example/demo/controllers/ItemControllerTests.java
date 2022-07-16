package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTests {

    private ItemController itemController;

    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp () {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void get_items () {
        ResponseEntity<List<Item>> items = itemController.getItems();
        assertNotNull(items);
        assertEquals(200, items.getStatusCodeValue());
    }

    @Test
    public void get_items_by_name () {
        List<Item> i = new ArrayList<Item>(); i.add(new Item());
        when(itemRepository.findByName("Round Widget")).thenReturn(i);
        ResponseEntity<List<Item>> items = itemController.getItemsByName("Round Widget");
        assertNotNull(items);
        assertEquals(200, items.getStatusCodeValue());

        assertEquals(1, items.getBody().size());
    }

    @Test
    public void get_items_by_invalid_name () {
        List<Item> i = new ArrayList<Item>();
        when(itemRepository.findByName("missing Widget")).thenReturn(i);
        ResponseEntity<List<Item>> items = itemController.getItemsByName("missing Widget");
        assertNotNull(items);
        assertEquals(404, items.getStatusCodeValue());
    }

    @Test
    public void get_items_by_id () {
        Item i = new Item();
        i.setId(1l);
        when(itemRepository.findById(1l)).thenReturn(Optional.of(i));
        ResponseEntity<Item> item = itemController.getItemById(1l);
        assertNotNull(item);
        assertEquals(200, item.getStatusCodeValue());
    }

    @Test
    public void get_items_by_invalid_id () {
        when(itemRepository.findById(2l)).thenReturn(Optional.ofNullable(null));
        ResponseEntity<Item> item = itemController.getItemById(2l);
        assertNotNull(item);
        assertEquals(404, item.getStatusCodeValue());
    }
}