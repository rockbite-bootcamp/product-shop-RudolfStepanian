package com.company;

public class Main {

    public static void main(String[] args) {

	    Resources r = new Resources();
    	User R = new User(r);
	    Currency c = new Currency("c-dollar");
	    r.add(c,500);
	    r.add(c,250);
	    r.remove(c,300);


	    Storage a = new Storage();

	    Category category = new Category("first category");
	    Product p1 = new Product("product 1", category,new Price(c,500));
	    Product p2 = new Product("product 2", category,new Price(c,500));
	    Shop shop = new Shop();
	    shop.addProduct(p1,20);
	    shop.addProduct(p2,30);
	    shop.removeProduct(p2,25);

		R.getMoney();
		System.out.println(shop.getProductList().toString());
	    R.buyProduct(shop,p1,2);
		R.getMoney();
		System.out.println(shop.getProductList().toString());
    }
}
