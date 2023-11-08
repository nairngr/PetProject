/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Grace Nairn
 */
public class PetProject {
    private static final Pet[] pets = new Pet[5];
    public static void main(String[] args) throws IOException {
        
        loadPets();
        boolean repeat = true;
        while (repeat) {
            int userInput = getUserChoice();
            switch (userInput) {
                case 1 -> displayPets();
                case 2 -> addPets();
                case 3 -> removePets(); 
                case 5 -> {
                    System.out.println("Goodbye!");
                    savePets();
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
        System.out.printf("3) Remove a Pet%n");
        System.out.printf("4) Exit Program%n");
        
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
        
        while (index < pets.length) {
            System.out.println("Add Pet: (name, age)");
            petInput = var.nextLine();

            if (petInput.equalsIgnoreCase("done")){
                break;
            }
            
            if (index == pets.length) {
            System.out.println("Error: Database is full.");
            break;
            }
        
            String[] parts = petInput.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter the pet's name and age (e.g., Fido 5).");
                continue;
            }

            String name = parts[0];
            int age;
            try {
                age = Integer.parseInt(parts[1]);
                if (age < 1 || age > 20) {
                    System.out.println("Invalid age. Age must be between 1 and 20.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age format. Please enter a valid age.");
                continue;
            }

            Pet pet = new Pet(name, age);
            pets[index] = pet;
            index++;
            System.out.println("Pet added.");
        }
    }
    
        private static void removePets() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the pet you want to remove: ");
        int petID = scanner.nextInt();

        if (petID >= 0 && petID < pets.length && pets[petID] != null) {
            pets[petID] = null;
            System.out.println("Pet removed successfully.");
        } else {
            System.out.println("Error: " + petID + " does not exist.");
        }
    }
    
    private static void savePets() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pets.txt"))) {
            for (Pet pet : pets) {
                if (pet != null) {
                    writer.write(pet.getName() + " " + pet.getAge());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }
    
    private static void loadPets() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pets.txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                pets[index] = new Pet(name, age);
                index++;
            }
        } catch (IOException e) {
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
