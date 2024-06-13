package com.ebook.repository;

import com.ebook.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserId(int userId);



    // Method to find CartItems by userId using custom query
//    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId")
//    List<CartItem> findByUserId(@Param("userId") int userId);

}