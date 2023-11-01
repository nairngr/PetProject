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
                case 3 -> updatePet();
                case 4 -> removePet();
                case 5 -> searchPetsByName(); 
                case 6 -> searchPetsByAge();
                case 7 -> {
                    System.out.println("Goodbye!");
                    repeat = false;
                }
            } 
        }
    }
    
    private static int getUserChoice() {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.printf("What would you like to do?%n");
        System.out.printf("1) View all Pets%n");
        System.out.printf("2) Add more Pets%n");
        System.out.printf("3) Update a Pet%n");
        System.out.printf("4) Remove a Pet%n");
        System.out.printf("5) Search Pets by name%n");
        System.out.printf("6) Search Pets by age%n");
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
                    System.out.println("Pets added.");
                }else{
                    System.out.println("Array is full.");
                    break;
            }
        }
    }
    
    private static void searchPetsByName() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the pets name: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        for (Pet pet : pets) {
            if (pet != null && pet.getName().equalsIgnoreCase(searchName)) {
                printTableHeader();
                printTableRow(0, pet.getName(), pet.getAge());
                printTableFooter(1);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(searchName + " does not exist.");
        }
    }
    
    private static void searchPetsByAge() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the pets name: ");
        int searchAge = scanner.nextInt();

        boolean found = false;

        for (Pet pet : pets) {
            if (pet != null && pet.getAge() == searchAge) {
                printTableHeader();
                printTableRow(0, pet.getName(), pet.getAge());
                printTableFooter(1);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pets found with the age: " + searchAge);
        }
    }
    
    private static void updatePet() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        displayPets();
        
        System.out.print("Enter the Pet ID of the pet you want to update: ");
        int petID = scanner.nextInt();

        if (petID >= 0 && petID < pets.length && pets[petID] != null) {
            System.out.print("Enter the pets new name: ");
            String newName = scanner.next();

            System.out.print("Enter the pets new age: ");
            int newAge = scanner.nextInt();

            pets[petID] = new Pet(newName, newAge);
            
        } else {
            System.out.println("Pet ID does not exist. Try again.");
        }
    }
    
    private static void removePet() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        displayPets();
            
        System.out.print("Enter the Pet ID of the pet you want to remove: ");
        int petID = scanner.nextInt();

        if (petID >= 0 && petID < pets.length && pets[petID] != null) {
            pets[petID] = null;
            System.out.println("Number " + petID + " has been removed.");
        } else {
            System.out.println("Pet ID does not exist. Try again.");
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
    
    public int getAge() {
       return age;
    }
}
