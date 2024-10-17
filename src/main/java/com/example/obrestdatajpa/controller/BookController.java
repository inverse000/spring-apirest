package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    //atributos
    private BookRepository bookrepository;

    // constructores

    public BookController(BookRepository bookrepository) {
        this.bookrepository = bookrepository;
    }


    //CRUD sobre la entidad BOOK

    //Buscar todos los libros (lista de libros)
    //http://localhost:8080/api/books
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //recuperar y devolver los libros de base de datos}
        return bookrepository.findAll();
    }

    //Buscar un solo libro en base de datos segun su id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOp = bookrepository.findById(id);

        if(bookOp.isPresent())
            return ResponseEntity.ok(bookOp.get());
        else
            return ResponseEntity.notFound().build();
    }
    //public Book findOneById(Long id){

    //}
        //crear un nuevo libro en base de datos
    @PostMapping("api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        //guardar el libro recibido por parametro en la base de datos
        if (book.getId() != null){ //si existe el id entonces no es creación
            log.warn("Trying to create a book with id");
            System.out.println("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookrepository.save(book);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clase primaria
    }


    //actualizar un libro existente en base de datos, usualmente para actualizar se usa PUT
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){ //si no tiene id es creación no update
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookrepository.existsById(book.getId())){ //si se pone una id que no existe, por lo que no es bad request solo no puede hallarse
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        //el proceso de actualización
        Book result = bookrepository.save(book);
        return ResponseEntity.ok(result);
    }

    //borrar todos los libros
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(bookrepository.existsById(id)){
            bookrepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
    }
    //borrar libros
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){
      log.info("Rest request for delete all books");
      bookrepository.deleteAll();
      return ResponseEntity.notFound().build();
    }

    //primera versión de borrar que borra por id pero mandando todo el libro
    //este fue mi intento y si bien funciona no tiene mucho sentido mejor es
    //solo mandar el id que quieres borrar
    //otro error es que no te devuelve un ResponseEntity por lo que no podrás mapear estados o códigos http como 404 o lo que sea
    //@DeleteMapping("/api/books")
    //public void deleteById(@RequestBody Book book){
    //    bookrepository.deleteById(book.getId());
    //}

    //borrar all books
    //@DeleteMapping("/api/books")
    //public ResponseEntity<Book> deleteAll(){
        //log.info("Rest request Delete all books ");
        //bookrepository.deleteAll();
        //return ResponseEntity.noContent().build();
    //}
}
