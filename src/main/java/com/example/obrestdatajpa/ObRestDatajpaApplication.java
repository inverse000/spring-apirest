package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD
		//crear libro
		Book book1 = new Book(null, "1984", "George Orwell", 180, 29.99, LocalDate.of(1950,10,5), true);
		Book book2 = new Book(null, "Brave new World", "Aldous Huxley", 190, 29.99, LocalDate.of(1951,11,5), true);
		//almacenar un libro
		System.out.println("Num de libros en la base de datos: "+ repository.findAll().size());

		repository.save(book1);
		repository.save(book2);
		System.out.println("Los libros son:");
		System.out.println(repository.findAll());
		//recuperar todos los libros
		System.out.println("Num de libros en la base de datos: "+ repository.findAll().size());
		//borrar un libro
		//repository.deleteById(1L);
		//System.out.println("Num de libros en la base de datos: "+ repository.findAll().size());
	}

}
