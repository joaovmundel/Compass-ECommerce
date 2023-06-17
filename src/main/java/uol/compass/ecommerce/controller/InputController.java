package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.config.Messages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputController {

    private static String requestMessage = Main.getMessage(Messages.REQUEST_USER_INPUT);


    public static Integer requestUserInt(Scanner scan) {
        int input = -1;
            try{
                input = scan.nextInt();
            }catch (InputMismatchException e){
                inputError(scan);
            }
        return input;
    }

    public static Double requestUserDouble(Scanner scan) {
        double input = -1.0;
        try{
            input = scan.nextDouble();
        }catch (InputMismatchException e){
            inputError(scan);
        }
        return input;
    }

    public static String requestUserString(Scanner scan) {
        String input = "";
        try{
            input = scan.next();
        }catch (InputMismatchException e){
            inputError(scan);
        }
        return input;
    }

    public static String requestUserLongString(Scanner scan) {
        String input = "";
        try{
            scan.nextLine();
            input = scan.nextLine();
        }catch (InputMismatchException e){
            inputError(scan);
        }
        return input;
    }

    public static Float requestUserFloat(Scanner scan) {
        float input = -1f;
        try{
            input = scan.nextFloat();
        }catch (InputMismatchException e){
            inputError(scan);
        }
        return input;
    }

    private static void inputError(Scanner scan){
        System.err.println(Main.getMessage(Messages.INPUT_MISMATCH_ERROR));
        scan.nextLine();
        scan.nextLine();
        System.exit(0);
    }
}
