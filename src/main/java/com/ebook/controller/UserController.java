package com.ebook.controller;



import com.ebook.model.CartItem;
import com.ebook.model.Product;
import com.ebook.model.UserDtls;
import com.ebook.service.CartService;
import com.ebook.service.CategoryService;
import com.ebook.service.ProductService;
import com.ebook.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

//

    @GetMapping("/product")
    public String loadViewProduct(Model m) {
        m.addAttribute("products", productService.getAllProducts());
        return "user/view-books";
    }




    @GetMapping("/addItem/{productId}")
    public String showAddItemForm(@PathVariable("productId") int productId, Model model) {
        CartItem item = new CartItem();
        Product product = productService.getProductById(productId);
        item.setName(product.getTitle());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
        model.addAttribute("item", item);
        return "user/add-item";  // Ensure this template exists
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("item") CartItem item, HttpSession session) {
        // Get the current user from session or database, assuming you have a session attribute 'userId'
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            // Handle the case where user is not logged in
            return "redirect:/login"; // Redirect to login page or handle as per your application flow
        }

        UserDtls user = userService.getUserById(userId);
        item.setUser(user);
        cartService.saveItem(item);
        return "redirect:/user/view-cart";
    }

//    @GetMapping("/cart")
//    public String viewCart(Model model, HttpSession session) {
//        Integer userId = (Integer) session.getAttribute("userId");
//
//        UserDtls user = userService.getUserById(userId);
//        List<CartItem> cartItems = cartService.getCartItemsById(user);
//        model.addAttribute("cartItems", cartItems);
//        return "user/view-cart"; // Ensure this template exists
//    }









}