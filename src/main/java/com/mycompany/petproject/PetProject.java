/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Grace Nairn
 */
public class PetProject {
    private static final Pet[] pets = new Pet[50];
    public static void main(String[] args) throws IOException {
        
        boolean repeat = true;
        while (repeat) {
            int userInput = getUserChoice();
            switch (userInput) {
                case 1 -> displayPets();
                case 2 -> addPets();
                //case 3 
                //case 4 
                //case 5
                //case 6
                case 7 -> {
                    System.out.println("Goodbye!");
                    repeat = false;
                }
            }
        }
    }
    
    //shows the user their choices and get their input
    private static int getUserChoice() {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.printf("What would you like to do?%n");
        System.out.printf("1) View all pets%n");
        System.out.printf("2) Add more pets%n");
        //System.out.printf("3) Update a Pet%n");
        //System.out.printf("4) Remove a Pet%n);
        //System.out.printf("5) Search pets by name%n);
        //System.out.printf("6) Search pets by age%n);
        System.out.printf("7) Exit Program%n");
        
        int userInput = scan.nextInt();
        
        System.out.println("Your Choice: " + userInput);
        
        return userInput;
    }
    
     private static void displayPets() throws IOException {
        
        printTableHeader();
        
        for (int i = 0; i <pets.length; i++) {
            Pet pet = pets[i];
            if (pet != null){
                printTableRow(i, pet.getName(), pet.getAge());
            }
        }
        printTableFooter(pets.length);
    }
    
      private static void printTableHeader() {
        System.out.printf("+--------------------+%n");
        System.out.printf("|ID |NAME       |AGE |%n");
        System.out.printf("+--------------------+%n");
    }
    
    private static void printTableRow(int id, String name, int age){
        
        System.out.printf("|%3d|%-10s|%4d|%n", id, name, age);
   
    }
    
    private static void printTableFooter(int rowcount){
        System.out.printf("+--------------------+%n");
        System.out.printf("%d rows in set.%n", rowcount);
    }
    
    private static void addPets() throws FileNotFoundException {
        
        Scanner var = new Scanner(System.in);
        String petInput;
        int index = 0;
        
        while (true) {
                System.out.println("Add Pet: (name, age)");
                petInput = var.nextLine();

                if (petInput.equalsIgnoreCase("done")){
                    break;
                }
 
                String[] parts = petInput.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);

                Pet pet = new Pet(name, age);
                
                if(index <pets.length){
                    pets[index] = pet;
                    index++;
                }else{
                    System.out.println("Array is full.");
                    break;
            }
        }
    }
}

class Pet {

    private final String name;
    private final int age;
    
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    
    public int getAge(){
       return age;
    }
}