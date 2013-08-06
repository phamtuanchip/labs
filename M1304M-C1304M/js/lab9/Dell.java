package lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dell extends Product {

	private int mark_id ;

	public Dell() {
		super();
	}
	public Dell(int id, String name, int quantity, float price, int markId){
		super(id,name,quantity,price);
		this.mark_id = markId;
	}


	@Override
	void displayProduct() {
		System.out.println(getID() +"\t"+ getName()+"\t"+getQuantity()+"\t"+getPrice()+"\t"+getCost());
	}

	void createProduct() {
		System.out.println("Nhap vao du lieu product");
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Id : ");
			String s = bufferRead.readLine();
			setID(Integer.parseInt(s));
			System.out.println("Name: ");
			s = bufferRead.readLine();
			setName(s);
			System.out.println("Quantity: ");
			s = bufferRead.readLine();
			setQuantity(Integer.parseInt(s));
			System.out.println("Price: ");
			setPrice(Float.parseFloat(s));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	float getCost(){
		return getQuantity()*getPrice();
	}

	void sortProduct(Product p[]){

	}

	Product findProduct(int quantity, Product p[]) {
		return null;
	}

	public static void main(String[] args) {
		int n = 0;
		System.out.println("Nhap vao so phan tu");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			String s = bufferRead.readLine();
			n = Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Dell pd[] = new Dell[n];
		for(int i =0; i <n ; i++) {
			Dell d = new Dell();
			d.createProduct();
			pd[i] = d;
		}



	}

	public int getMark_id() {
		return mark_id;
	}

	public void setMark_id(int mark_id) {
		this.mark_id = mark_id;
	}

}
