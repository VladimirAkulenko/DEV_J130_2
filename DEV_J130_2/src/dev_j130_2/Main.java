/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dev_j130_2;

import java.sql.*;
import java.util.*;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     ProductsRepository repository = new ProductsRepository();
        List<Products> products = repository.getProducts();
        
        for(Products p: products){
            String color = p.getColor();
            if(color == null){
                color ="";
            }
            System.out.printf("%s;%s;%s;%d;%d%n", p.getVender(), p.getName(),
                    color,p.getPrice(),p.getStockBalance());
        }
           printOrderGivenId(1,2,3);
           
           registrationOrder("Barac Obama", "(950)452-73-09", null, "Nevskiy 19-12-145",
                   new String[]{"3251619", "3251620"},
                   new int[]{6,6});
         
           
    }
    static void printOrderGivenId(int... orderIds){
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev_j130","root","root");
                PreparedStatement prst = con.prepareStatement("SELECT \"NAME\", COLOR FROM PRODUCTS PR JOIN POSITIONS PP ON PR.vender = PP.vender WHERE PP.ORDERS_IDENTIFIER =?")){
            for(int id: orderIds){
                prst.setInt(1, id);
                try(ResultSet rs = prst.executeQuery()){
                    System.out.println("Содержимое заказа с identifier =" + id);
                    while(rs.next()){
                        String name = rs.getString(1), color = rs.getString(2);
                        if(color != null)
                            name += "("+color+")";
                        System.out.println(name);
                    }
                    System.out.println("\n");
                }
                
            }
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }   
    }
    
    static void registrationOrder(String nameCustomer, String contactTelephon, 
               String email, String deliveryAddress, String [] vender, int [] amount){
           if(vender == null){
               throw new IllegalArgumentException("Артикл не может быть пустым");
           }
           if(amount == null){
               throw new IllegalArgumentException("Колличество не может быть пустым");
           }
           if(amount.length != vender.length){
               throw new IllegalArgumentException("Массивы 'vender' и 'amount' должны иметь одинаковый размер");
           }
           try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev_j130","root","root")){
               con.setAutoCommit(false);
               int maxOrderIdentifier;
               try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT MAX (IDENTIFIER) FROM ORDERS")){
                   rs.next();
                   maxOrderIdentifier= rs.getInt(1);
               } 
               try(PreparedStatement prst = con.prepareStatement( "INSERT INTO ORDERS (IDENTIFIER,DATE_CREATION, NAME_CUSTOMER, CONTACT_TELEPHON, EMAIL, DELIVERY_ADDRESS, ORDER_STATUS)\n" +
                       "VALUES (?, CURRENT_DATE, ?,?,?,?,'P')")){
                   prst.setInt(1, maxOrderIdentifier+1);
                   prst.setString(2, nameCustomer);
                   prst.setString(3, contactTelephon);
                   prst.setString(4, email);
                   prst.setString(5, deliveryAddress);
                   prst.executeUpdate();
               }catch(SQLException sqle){
                   System.out.println("Произошла ошибка 1");
               }
               try(PreparedStatement prst = con.prepareStatement("INSERT INTO POSITIONS\n" +
                       "SELECT ?, VENDER, PRICE, ? FROM PRODUCTS WHERE VENDER = ?")){
                   prst.setInt(1, maxOrderIdentifier+1);
                   for(int i =0; i< vender.length; i++){
                       prst.setInt(2, amount[i]);
                       prst.setString(3, vender[i]);
                       prst.addBatch();
                   }
                   prst.executeBatch();
               }catch(SQLException sqle){
                   System.out.println("Произошла ошибка 2");
                   System.out.println(sqle.getMessage());
               }
               try{
                   con.commit();
               }
                catch (SQLException sqe){
                    con.rollback();
                }
           } catch(SQLException sqle){
               System.out.println(sqle.getMessage());
           }
    }
}
