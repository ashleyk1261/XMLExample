package pkgMain;


import java.io.File;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pkgLibrary.Book;
import pkgLibrary.Catalog;
import pkgMain.BookException;

public class XMLReader {

	public static void main(String[] args) {
		
		// Create a listing of catalogs
        
		Catalog cat = null;
		
		//	Read the XML catalog into 'cat'
		cat = ReadCatalog();
				
		// Set the cost of each book to 20% of the book price
		SetInitialCost(cat, .20);
		
		//	Increase the price of each book
		IncreasePrice(cat,0.10);

		//	Write the XML file from 'cat' object
		WriteXMLFile(cat);
		
	}
	
	
public void addBook(int id, Book book) throws BookException {
	
		
		throw new BookException("The specified book already exists in the catalog.");
	}

	
	
	public static Catalog ReadCatalog() {
		Catalog cat = ReadXMLFile();
		
		System.out.println("cat ID " + cat.getId());
		System.out.println("Book count: " + cat.getBooks().size());

		return cat;		
	}

	private static Catalog IncreasePrice(Catalog cat, double PriceIncrease)
	{
		for (Book b : cat.getBooks()) {
			double newPrice = (b.getPrice() * PriceIncrease) + b.getPrice();			
			b.setPrice(Math.round(newPrice * 100.0) / 100.0);
		}
		
		return cat;
	}

	// Doing b.getCost()==0) makes it so that the initial cost function will only be 
	// run once not every time the price updates
	private static Catalog SetInitialCost(Catalog cat, double PercentOfPrice)
	{
		for (Book b : cat.getBooks()) {  
			if (b.getCost()==0) {
			double initialCost = b.getPrice() - (b.getPrice() * PercentOfPrice);			
			b.setCost(Math.round(initialCost * 100.0) / 100.0);
			}
		}
		
		return cat;
	}
	
		
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;
	}

		

}
