package com.ebook.service;

import com.ebook.model.CartItem;

import java.util.List;

public interface CartService {
   CartItem saveItem(CartItem item);
    List<CartItem> getItemsByUserId(int userId);
    CartItem getItemById(int id);
    void deleteItem(int id);


}