package pkgLibrary;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import pkgMain.BookException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;

	@XmlElement(name = "book")
	ArrayList<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Book getBook(String id) throws BookException {
		for (Book book : this.getBooks()) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		throw new BookException("A book with id " + id + " was not found in the catalog.");
	}

	// if the book is not found .. add it . otherwise return error
	public void AddBook(Book b) throws BookException {
		if (this.getBook(b.getId()) == null) {
			this.books.add(b);
		}
		else {
			throw new BookException("Book exists");
		}
	}
}