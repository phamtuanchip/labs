package lab9;

public abstract class Product {
	private int ID;
	private String name ;
	private int quantity ;
	private float price ;
	int getID() {
		return ID;
	}
	void setID(int iD) {
		ID = iD;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	int getQuantity() {
		return quantity;
	}
	void setQuantity(int quantity) throws MyException {
		if(quantity < 0) throw new MyException("Quantity is <0");
		this.quantity = quantity;
	}
	float getPrice() {
		return price;
	}
	void setPrice(float price) throws MyException{
		if(price < 0) throw new MyException("Price is <0");
		this.price = price;
	}
	
	public Product(){
		ID = 0;
		name = "" ;
		quantity = 0 ;
		price = 0f;
	}
	
	public Product(int id, String name, int quantity, float price){
		this.ID = id;
		this.name = name ;
		this.quantity = quantity ;
		this.price = price;
	}
	abstract void displayProduct();

}
