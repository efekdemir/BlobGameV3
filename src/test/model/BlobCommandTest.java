//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class BlobCommandTest {
//
//    private BlobCommand b;
//    private FoodPiece f1;
//    private FoodPiece f2;
//    private FoodPiece f3;
//    private FoodPiece f4;
//    private FoodPiece f5;
//    private FoodPiece f6;
//    private FoodPiece f7;
//    private FoodPiece f8;
//    private FoodPiece f9;
//    private FoodPiece f10;
//    private int x1;
//    private int y1;
//    private int radius;
//
//    public BlobCommandTest() {
//    }
//
//    @BeforeEach
//    public void runBefore() {
//        b = new BlobCommand(x1, y1, radius);
//        f1 = new FoodPiece(x1, y1, radius);
//        f2 = new FoodPiece(x1, y1, radius);
//        f3 = new FoodPiece(x1, y1, radius);
//        f4 = new FoodPiece(x1, y1, radius);
//        f5 = new FoodPiece(x1, y1, radius);
//        f6 = new FoodPiece(x1, y1, radius);
//        f7 = new FoodPiece(x1, y1, radius);
//        f8 = new FoodPiece(x1, y1, radius);
//        f9 = new FoodPiece(x1, y1, radius);
//        f10 = new FoodPiece(x1, y1, radius);
//
//    }
//
//    @Test
//    public void testGetX() {
//        assertEquals(b.startingX, b.getX1());
//    }
//
//    @Test
//    public void testGetY() {
//        assertEquals(b.startingY, b.getY1());
//    }
//
//    @Test
//    public void testMoveBlobRight() {
//        b.moveBlobRight();
//        assertEquals(451, b.getX1());
//        b.moveBlobRight();
//        assertEquals(452, b.getX1());
//    }
//
//    @Test
//    public void testMoveBlobLeft() {
//        b.moveBlobLeft();
//        assertEquals(449, b.getX1());
//        b.moveBlobLeft();
//        assertEquals(448, b.getX1());
//        b.moveBlobRight();
//        assertEquals(449, b.getX1());
//
//    }
//
//    @Test
//    public void testMoveBlobUp() {
//        b.moveBlobUp();
//        assertEquals(451, b.getY1());
//        b.moveBlobUp();
//        assertEquals(452, b.getY1());
//        b.moveBlobDown();
//        assertEquals(451, b.getY1());
//
//    }
//
//    @Test
//    public void testMoveBlobDown() {
//        b.moveBlobDown();
//        assertEquals(449, b.getY1());
//        b.moveBlobUp();
//        assertEquals(450, b.getY1());
//        b.moveBlobUp();
//        assertEquals(451, b.getY1());
//        b.moveBlobDown();
//        assertEquals(450, b.getY1());
//
//    }
//
//    @Test
//    public void testEatFoodPiece() {
//        assertEquals(5, f1.getRadius());
//        assertEquals(10, b.getBlobSize());
//        b.eatFoodPiece(f1);
//        assertEquals(15, b.getBlobSize());
//    }
//
//    @Test
//    public void testEvolveBlob() {
//        b.eatFoodPiece(f1);
//        assertFalse(b.evolveBlob());
//        b.eatFoodPiece(f2);
//        b.eatFoodPiece(f3);
//        b.eatFoodPiece(f4);
//        b.eatFoodPiece(f5);
//        b.eatFoodPiece(f6);
//        b.eatFoodPiece(f7);
//        assertFalse(b.evolveBlob());
//        b.eatFoodPiece(f8);
//        b.eatFoodPiece(f9);
//        b.eatFoodPiece(f10);
//        assertTrue(b.evolveBlob());
//    }
//
//
//    @Test
//    public void testReturnBodySize() {
//        b.eatFoodPiece(f1);
//        b.eatFoodPiece(f5);
//        b.eatFoodPiece(f9);
//        assertEquals(25, b.getBlobSize());
//        b.eatFoodPiece(f4);
//        assertEquals(30, b.getBlobSize());
//    }
//
//    @Test
//    public void testReturnColor() {
//        assertTrue(b.getColor() == BlobCommand.startingColor);
//        assertFalse(b.getColor() == BlobCommand.firstEvolutionColor);
//        b.eatFoodPiece(f1);
//        b.eatFoodPiece(f3);
//        b.eatFoodPiece(f4);
//        b.eatFoodPiece(f5);
//        b.eatFoodPiece(f6);
//        b.eatFoodPiece(f7);
//        b.eatFoodPiece(f8);
//        b.eatFoodPiece(f9);
//        b.eatFoodPiece(f10);
//        b.eatFoodPiece(f10);
//        assertTrue(b.evolveBlob());
//        assertTrue(b.getColor() == BlobCommand.firstEvolutionColor);
//        assertFalse(b.getColor() == BlobCommand.startingColor);
//
//    }
//
//    @Test
//    public void saveLoadTest() {
//        assertEquals(b.initialBlobSize, b.getBlobSize());
//        b.eatFoodPiece(f1);
//        assertEquals(15, b.getBlobSize());
//        b.save(b);
//        b.load(b);
//        assertEquals(15, b.getBlobSize());
//        assertEquals(b.startingColor, b.getColor());
//    }
//
//    @Test
//    void testSaveIOException() {
//        b.eatFoodPiece(f1);
//        b.save(b);
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(b.gameDisk));
//            out.writeObject(b);
//            out.close();
//            assertTrue(b.gameDisk.exists());
//        } catch (IOException e) {
//            System.out.println("IO Exception" + e);
//        }
//    }
//
//    @Test
//    void testLoadClassNotFoundAndIOException() {
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(b.gameDisk));
//            b = (BlobCommand) in.readObject();
//            in.close();
//            assertTrue(b.gameDisk.exists());
//        } catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFound Exception" + e);
//        } catch (IOException e) {
//            System.out.println("IOException" + e);
//        }
//    }
//}
