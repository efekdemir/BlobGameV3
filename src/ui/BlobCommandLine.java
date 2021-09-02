package ui;

import model.BlobCommand;
import model.FoodPiece;

import java.util.Scanner;

public class BlobCommandLine {
    private BlobCommand blob;
    private FoodPiece foodPiece;
    private Scanner input;
    private int firstEvolutionSizeThreshold = 50;

    public BlobCommandLine() {
        runBlob();
    }

    private void runBlob() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                System.out.println("\nGoodbye!");
                keepGoing = false;

            } else {
                processCommand1(command);
            }
        }
    }


    private void processCommand1(String command) {
        switch (command) {
            case "w":
                moveBlobUp();
                break;
            case "s":
                moveBlobDown();
                break;
            case "a":
                moveBlobLeft();
                break;
            case "d":
                moveBlobRight();
                break;
            case "e":
                eatFoodPiece();
                break;
            case "v":
                evolveBlob();
                break;
            case "r":
                returnBodySize();
                break;
            default:
                processCommand2(command);
                break;
        }
    }


    public void processCommand2(String command) {
        switch (command) {
            case "x":
                getX();
                break;
            case "y":
                getY();
                break;
            case "c":
                returnColor();
                break;
            case "u":
                save(blob);
                break;
            case "l":
                load(blob);
                break;
            case "z":
                clearData();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    private void moveBlobRight() {
        if (blob.getX1() <= 1000) {
            blob.moveBlobRight();
            System.out.println("Blob has moved by 1 unit to the right!");
        } else {
            System.out.println("Blob hit the wall, you can't go further...");
        }
    }

    private void moveBlobLeft() {
        if (blob.getX1() >= 0) {
            blob.moveBlobLeft();
            System.out.println("Blob has moved by 1 unit to the left!");
        } else {
            System.out.println("Blob hit the wall, you can't go further...");
        }
    }


    private void moveBlobUp() {
        if (blob.getY1() >= 0) {
            blob.moveBlobUp();
            System.out.println("Blob has moved up by 1 unit!");
        } else {
            System.out.println("Blob hit the wall, you can't go further...");
        }
    }

    private void moveBlobDown() {
        if (blob.getY1() <= 1000) {
            blob.moveBlobDown();
            System.out.println("Blob has moved down by 1 unit!");
        } else {
            System.out.println("Blob hit the wall, you can't go further...");
        }
    }

    private void getX() {
        System.out.println("Here is the current x-coordinate of Blob:");
        System.out.println(blob.getX1());
    }


    private void getY() {
        System.out.println("Here is the current y-coordinate of Blob:");
        System.out.println(blob.getY1());
    }


    private void eatFoodPiece() {
        blob.eatFoodPiece(foodPiece);
        System.out.println("Blob has eaten one food piece!");
    }


    private void evolveBlob() {
        if (blob.getBlobSize() >= firstEvolutionSizeThreshold) {
            blob.evolveBlob();
            System.out.println("Blob has evolved and changed color!");
        } else {
            System.out.println("Blob has not evolved because it is still too small");
        }
    }


    private void returnBodySize() {
        System.out.println("Here is the blob's body size:");
        System.out.println(blob.getBlobSize());
    }


    private void returnColor() {
        System.out.println("Here is the blob's current color:");
        System.out.println(blob.getColor());
    }


    public void save(BlobCommand blob1) {
        System.out.println("Saving current blob!");
        blob.save(blob1);
    }


    public void load(BlobCommand blob1) {
        System.out.println("Loading up saved blob file!");
        blob.load(blob1);
    }


    public void clearData() {
        System.out.println("Overwriting saved data...");
        init();
        save(blob);
    }


    private void init() {
        blob = new BlobCommand(BlobCommand.startingX, BlobCommand.startingY, BlobCommand.initialBlobSize);
        foodPiece = new FoodPiece(BlobCommand.startingX, BlobCommand.startingY, FoodPiece.foodCommandSize);

    }


    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> eat food piece");
        System.out.println("\tr -> return blob body size");
        System.out.println("\tc -> return blob color");
        System.out.println("\tv -> evolve blob");
        System.out.println("\ta -> move blob left");
        System.out.println("\td -> move blob right");
        System.out.println("\tw -> move blob up");
        System.out.println("\ts -> move blob down");
        System.out.println("\tx -> retrieve x-coordinate of blob");
        System.out.println("\ty -> retrieve y-coordinate of blob");
        System.out.println("\tu -> save current blob object");
        System.out.println("\tl -> load existing blob object");
        System.out.println("\tz -> clear existing data and reset blob");
        System.out.println("\tq -> quit");
    }
}

