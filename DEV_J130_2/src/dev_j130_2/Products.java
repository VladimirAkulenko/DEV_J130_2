/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j130_2;

/**
 *
 * @author USER
 */
public class Products {
    String vender;
    String name;
    String color;
    int price;
    int stockBalance;

    public Products (){     
    }
    public Products(String vender, String name, String color, int price, int stockBalance) {
        this.vender = vender;
        this.name = name;
        this.color = color;
        this.price = price;
        this.stockBalance = stockBalance;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }
}
