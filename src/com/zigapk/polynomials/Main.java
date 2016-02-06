package com.zigapk.polynomials;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                System.out.print("polynomial a: ");
                Polynomial a = new Polynomial().fromStr(scanner.nextLine());
                System.out.print("operator: ");
                String operator = scanner.nextLine();
                System.out.print("polynomials b: ");
                Polynomial b = new Polynomial().fromStr(scanner.nextLine());

                if (operator.equals("+")) {
                    System.out.println("  " + a.toStr() + "\n+ " + b.toStr()+"\n--------------------------------");
                    Polynomial c = a.add(b);
                    System.out.println("  "+ c.toStr());
                }else if (operator.equals("*")) {
                    System.out.println("  " + a.toStr() + "\n* " + b.toStr()+"\n--------------------------------");
                    Polynomial c = a.multiply(b);
                    System.out.println("  "+ c.toStr());
                }else {
                    System.out.println("ERROR: Unknown operator!");
                }
            }catch (Exception e){
                System.out.println("ERROR: " + e.toString());
            }
            System.out.println();
        }
    }
}
