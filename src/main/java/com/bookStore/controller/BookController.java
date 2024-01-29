package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

//import ch.qos.logback.core.model.Model;


@Controller
public class BookController {
		@Autowired
		private BookService service;
		
		
		@Autowired
		private MyBookListService myBookService;
		
		@GetMapping("/")
		public String home() {
			return "home.html";
		}
		@GetMapping("/book_register")
		public String bookRegister() {
			return "bookRegister.html";
		}
		
		@GetMapping("/available_books")
		public ModelAndView getAllBook() {
			List<Book>list=service.getAllBook();
			
			
//			ModelAndView m=new ModelAndView();
//			m.setViewName("BookList");
//			m.addObject("Book",list);
			
			//this tree line in single line
			
			
			
			return new ModelAndView("bookList","book",list);
			
		}
		@PostMapping("/save")
		public String addBook(@ModelAttribute Book b) {
			service.save(b);
			return "redirect:/available_books";
		}
		@GetMapping("/my_books")
		public String getMyBooks(Model model) {
			
			List<MyBookList>list=myBookService.getAllMyBooks();
			model.addAttribute("book",list);
			return "myBooks.html";
		}
		@RequestMapping("/mylist/{id}")
		public String getMyList(@PathVariable("id") int id) {//in this line we retrieving the id from the url	//}
			Book b=service.getBookById(id);																		//	}
			MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());						//	  }used to add data to
			myBookService.saveMyBook(mb);																		//	  }the data base
			return"redirect:/my_books";																			// 	}
		}																										//}
		
		@RequestMapping("/editBook/{id}")
		public String editBook(@PathVariable("id") int id,Model model) {
			Book b=service.getBookById(id);
			model.addAttribute("book",b);
			return"bookEdite.html";
		}
		@RequestMapping("/deleteBook/{id}")
		public String deleteBook(@PathVariable("id") int id){
		
		service.deletById(id);
		return"redirect:/available_books";
		
		}
		
		
}
