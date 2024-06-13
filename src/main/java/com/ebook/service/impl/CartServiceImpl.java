package com.ebook.service.impl;

import com.ebook.model.CartItem;
import com.ebook.model.Product;
import com.ebook.repository.CartItemRepository;
import com.ebook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem saveItem(CartItem item) {
        return cartItemRepository.save(item);
    }



    @Override
    public List<CartItem> getItemsByUserId(int userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public CartItem getItemById(int id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItem(int id) {
        cartItemRepository.deleteById(id);
    }

}