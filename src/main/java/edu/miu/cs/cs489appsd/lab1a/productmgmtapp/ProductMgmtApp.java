package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ProductMgmtApp {

    private static Product[] products = new Product[3];
       static {
           products[0] = new Product(
                   3128874119L,
                   "Banana",
                   LocalDate.parse("2023-01-24"),
                   124,
                   new BigDecimal("0.55")
           );
           products[1] = new Product(
                   2927458265L,
                   "Apple",
                   LocalDate.parse("2022-12-09"),
                   18,
                   new BigDecimal("1.09")
           );
           products[2] = new Product(
                   9189927460L,
                   "Carrot",
                   LocalDate.parse("2023-03-31"),
                   89,
                   new BigDecimal("2.99")
           );
        }
    public static void main(String[] args) throws JsonProcessingException {
            System.out.println("Printed in JSON Format");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String json = objectMapper.writeValueAsString(products);
            System.out.println(json);

            System.out.println("-------------------------------");

            System.out.println("Printed in XML Format");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            String xml = xmlMapper.writeValueAsString(products);
            System.out.println(xml);


            System.out.println("-------------------------------");

    }
}