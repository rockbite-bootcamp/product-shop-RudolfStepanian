package com.company;

public class Main {

    public static void main(String[] args) {

		/**
		 * creating user and adding him money (two types) to buy from shop
		 */
		Resources userResources = new Resources();
    	User R = new User(userResources);
	    Currency c = new Currency("c-dollar");
	    Currency l = new Currency("l-dollar");
		userResources.add(c,500);
		userResources.add(l,5000);
		userResources.remove(l,300);


		/**
		 * creating product to fill shop storage
		 */
	    Category category = new Category("first category");
	    Resources r1 = new Resources();
	    r1.add(c,150);
	    Resources r2 = new Resources();
	    r2.add(l, 1000);
	    r2.add(c, 200);
	    Product p1 = new Product("product 1", category, r1, r2);
	    Product p2 = new Product("product 2", category, r2, r1);


		/**
		 * creating shop and filling it with products
		 */
		Shop shop = new Shop();
	    Storage a = new Storage();
	    shop.addProduct(p1,20);
	    shop.addProduct(p2,30);
	    shop.removeProduct(p2,25);


		/**
		 * User buying 2 p2 products
		 */
		R.showMoney();
		System.out.println(shop.getProductList().toString());
	    R.buyProduct(shop,p2, 2);
		R.showMoney();
		System.out.println(shop.getProductList().toString());
    }
}
