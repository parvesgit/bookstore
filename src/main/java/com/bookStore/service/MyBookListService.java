package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;

@Service
public class MyBookListService {
	
	@Autowired
	private MyBookRepository mybook;
	
	public void saveMyBook(MyBookList book) { //MyBookList is come from com.bookstore.entity
		mybook.save(book);
	}
	
	public List<MyBookList>getAllMyBooks(){
		return mybook.findAll();
	}
	public void deleteById(int id) {
		mybook.deleteById(id);
	}
	
}
