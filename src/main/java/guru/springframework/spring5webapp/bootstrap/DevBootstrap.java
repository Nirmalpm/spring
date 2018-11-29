package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	private void initData() {
		Author eric = new Author("Eric","Evans");
		Publisher publisher = new Publisher("Harper Collins","Church Street, California");
		Book book1 = new Book("Domain Driven Design","1234", publisher );
		eric.getBooks().add(book1);
		book1.getAuthors().add(eric);
		authorRepository.save(eric);
		bookRepository.save(book1);
		publisherRepository.save(publisher);
		
		Author rod = new Author("Rod","Johnson");
		publisher = new Publisher("Worx","Cannon Street, London");
		Book book2 = new Book("J2EE Develpoment","2344", publisher );
		rod.getBooks().add(book2);
		book2.getAuthors().add(rod);
		authorRepository.save(rod);
		bookRepository.save(book2);
		publisherRepository.save(publisher);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	
}
