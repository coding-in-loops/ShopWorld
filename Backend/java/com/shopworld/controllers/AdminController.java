package com.shopworld.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopworld.entities.Category;
import com.shopworld.entities.Product;
import com.shopworld.entities.UserDtls;
import com.shopworld.services.CategoryService;
import com.shopworld.services.ProductService;
import com.shopworld.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	UserService userService;


	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String index() {
		return "/admin/index";
	}
	
	@ModelAttribute
	public void getUserDetails(Principal p,Model m) {
		if(p!=null) {
			String email=p.getName();
			UserDtls user=userService.getUserByEmail(email);
			m.addAttribute("user",user);
		}
		List<Category> allActiveCategory=categoryService.getAllActiveCategory();
		m.addAttribute("categories",allActiveCategory);
	}
	
	
	@GetMapping("/products")
	public String loadViewProduct(Model m,@RequestParam(defaultValue="") String ch,@RequestParam(name="pageNo",defaultValue="0")Integer pageNo,
			@RequestParam(name="pageSize",defaultValue="10")Integer pageSize) {
		Page<Product> page=null;
		if(ch!=null && ch.length()>0) {
			page=productService.searchProductPagination(pageNo,pageSize,ch);
		}else {
			page=productService.getAllProductsPagination(pageNo,pageSize);
		}
		m.addAttribute("products",page.getContent());
		
		m.addAttribute("pageNo", page.getNumber());
		m.addAttribute("pageSize", pageSize);
		m.addAttribute("totalElements", page.getTotalElements());
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("isFirst", page.isFirst());
		m.addAttribute("isLast", page.isLast());
		return "admin/products";
	}
	
	@GetMapping("/users")
	public String getAllUsers(Model m,@RequestParam Integer type) {
		List<UserDtls> users=null;
		if(type==1) {
			users=userService.getUsers("ROLE_USER");
		}else {
			users=userService.getUsers("ROLE_SELLER");
		}
		m.addAttribute("userType", type);
		m.addAttribute("users",users);
		return "/admin/user";
	}
	
	
	@GetMapping("/add-seller")
	public String addAdmin() {
		return "/admin/add_seller";
	}
	
	@PostMapping("/save-seller")
	public String saveSeller(@ModelAttribute UserDtls user,@RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		String imageName=file.isEmpty()? "default.jpg":file.getOriginalFilename() ;
		user.setProfileImage(imageName);
		UserDtls saveUser=userService.saveSeller(user);
		
		if(!ObjectUtils.isEmpty(saveUser)) {
			if(!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/img").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
						+ file.getOriginalFilename());

				 System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				
			}session.setAttribute("successMessage", "Saved successfully!");
		}else {
			session.setAttribute("errorMessage", "Something went wrong on server!");
		}
		return "redirect:/admin/add-seller";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "/admin/profile";
	}
	
	@PostMapping("/update-profile")
	public String updateProfile(@ModelAttribute UserDtls user,@RequestParam MultipartFile img,HttpSession session) {
		UserDtls updateUser=userService.updateUserProfile(user, img);
		if(ObjectUtils.isEmpty(updateUser)) {
			session.setAttribute("errorMessage","Profile not updated!");
		}else {
			session.setAttribute("successMessage", "Profile Updated!");
		}
		return "redirect:/admin/profile";
	}
	
	private UserDtls getLoggedInUserDetails(Principal p) {
		String email=p.getName();
		UserDtls userDtls=userService.getUserByEmail(email);
		return userDtls;
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam String newPassword,@RequestParam String oldPassword,
			Principal p,HttpSession session){
		UserDtls loggedInUserDetails = getLoggedInUserDetails(p);
		Boolean f=passwordEncoder.matches(oldPassword, loggedInUserDetails.getPassword());
		if(f) {
			String encodePassword=passwordEncoder.encode(newPassword);
			loggedInUserDetails.setPassword(encodePassword);
			userService.updateUser(loggedInUserDetails);
			if(ObjectUtils.isEmpty(encodePassword)) {
				session.setAttribute("errorMessage", "Password not Updated!! Error in server");
			}else {
				session.setAttribute("successMessage", "Password Updated Successfully!");
			}
		}else {
			session.setAttribute("errorMessage", "Current password incorrect!");
		}
		return "redirect:/admin/profile";
	}
	
	@GetMapping("/category")
	public String category(Model m,@RequestParam(name="pageNo",defaultValue="0")Integer pageNo,
			@RequestParam(name="pageSize",defaultValue="5")Integer pageSize) {
		//m.addAttribute("categories",categoryService.getAllCategories());
		
		Page<Category> page=categoryService.getAllCategoryPagination(pageNo, pageSize);
		 
		List<Category> categories=page.getContent();
		
		m.addAttribute("categories", categories);
		m.addAttribute("category", new Category());
		m.addAttribute("pageNo", page.getNumber());
		m.addAttribute("pageSize", pageSize);
		m.addAttribute("totalElements", page.getTotalElements());
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("isFirst", page.isFirst());
		m.addAttribute("isLast", page.isLast());
		return "/admin/category";
	}
	
	
}


