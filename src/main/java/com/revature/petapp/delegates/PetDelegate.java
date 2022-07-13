package com.revature.petapp.delegates;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetDelegate implements FrontControllerDelegate {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		
		switch (method) {
		case "GET":
			get();
			break;
		case "POST":
			post();
			break;
		case "PUT":
			put();
			break;
		case "DELETE":
			delete();
			break;
		default:
		}
	}

	private void get() {
		
	}
	
	private void post() {
		
	}
	
	private void put() {
		
	}
	
	private void delete() {
		
	}
}
