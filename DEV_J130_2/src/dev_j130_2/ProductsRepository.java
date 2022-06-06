/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j130_2;

import java.sql.*;
import java.util.*;
/**
 *
 * @author USER
 */
public class ProductsRepository {
    public List<Products> getProducts() {
        List<Products> list = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev_j130","root","root");
                Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM PRODUCTS");
            while (rs.next()) {                
                Products products = new Products();
                products.setVender(rs.getString(1));
                products.setName(rs.getString(2));
                products.setColor(rs.getString(3));
                products.setPrice(rs.getInt(4));
                products.setStockBalance(rs.getInt(5));
                list.add(products);
                
            }
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }  
        return list;
    }
}
