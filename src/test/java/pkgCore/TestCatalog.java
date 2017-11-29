package pkgCore;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.Catalog;
import pkgMain.BookException;
import pkgMain.XMLReader;

public class TestCatalog {

	@Test
	// Test that the title of a given book is returned successfully
	public void TestGetBook() throws BookException {
		
		XMLReader rdr = new XMLReader();
		Catalog cat = rdr.ReadCatalog();
		assertEquals("Visual Studio 7: A Comprehensive Guide", cat.getBook("bk112").getTitle());
	}

	@Test
	// Test that the author of a given book is returned successfully
	public void TestGetBook2() throws BookException {
		
		XMLReader rdr = new XMLReader();
		Catalog cat = rdr.ReadCatalog();
	    assertEquals("O'Brien, Tim", cat.getBook("bk111").getAuthor());
	}

	@Test
	// Test that an exception is thrown when a book is not found in the catalog
	public void TestGetBook3() throws BookException {
		
		XMLReader rdr = new XMLReader();
		Catalog cat = rdr.ReadCatalog();
		try {
			cat.getBook("mk112");
			fail(); // no exception was thrown
		}
		catch (BookException e) {
			final String expected = "A book with id mk112 was not found in the catalog.";
			assertEquals(expected, e.getMessage());
		}
	}	
	
	@Test
	// Test that an exception is thrown when a book can't be added to the catalog because it is already in it
	public void TestAddBook1() throws BookException {
		
		XMLReader rdr = new XMLReader();
		Catalog cat = rdr.ReadCatalog();
		Book book = new Book("bk111", 
				"O'Brien, Tim", 
				"MSXML3: A Comprehensive Guide",
				"Computer",
				54.11, 
				29.56, 
				null,
				"The Microsoft MSXML3 parser is covered in detail, with attention to XML DOM interfaces, XSLT processing, SAX and more.");
		try {
			
			cat.AddBook(book);
			fail(); // no exception was thrown
		}
		catch (BookException e) {
			final String expected = "Book exists";
			assertEquals(expected, e.getMessage());
		}
	}	
	
	@Test
	// Test that a book was successfully added 
	public void TestAddBook2() throws BookException {
		
		XMLReader rdr = new XMLReader();
		Catalog cat = rdr.ReadCatalog();
		Book book = new Book("bk1261", 
				"Kleinhomer", 
				"MSXML3: A Comprehensive Guide",
				"Computer",
				54.11, 
				29.56, 
				null,
				"The Microsoft MSXML3 parser is covered in detail, with attention to XML DOM interfaces, XSLT processing, SAX and more.");
		try {
			
			cat.AddBook(book);
		    assertEquals("Kleinhomer", cat.getBook("bk1261").getAuthor());
		}
		catch (BookException e) {
		}
	}	

	
}

