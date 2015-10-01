package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import prog.Triangle;

public class TriangleTest {
	Triangle triangle;
	File inputFile;
	FileWriter fwriter;

	private static String FILENAME = "src/data.txt";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		triangle = new Triangle();
		inputFile = new File(FILENAME);
		fwriter = new FileWriter(inputFile);

		if(!inputFile.exists())
			inputFile.createNewFile();
	}

	@After
	public void tearDown() throws Exception {
		if(inputFile.exists())
			inputFile.delete();

		fwriter.close();
	}

	/*
	 * Tests
	 */

	@Test
	public void testReadAndCheck() throws IOException, ParseException {
		fwriter.write("1");
		fwriter.write("1");
		fwriter.write("1");

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=IOException.class)
	public void testReadAndCheckFileDoesNotExist() throws ParseException, IOException{
		if(inputFile.exists())
			inputFile.delete();

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=IOException.class)
	public void testReadAndCheckIsNotReadable() throws IOException, ParseException{
		inputFile.setReadable(false);

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=ParseException.class)
	public void testReadAndCheckFileHasLessThan3Lines() throws IOException, ParseException{
		fwriter.write("1");

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=ParseException.class)
	public void testReadAndCheckFileHasMoreThan3Lines() throws IOException, ParseException{
		fwriter.write("1");
		fwriter.write("2");
		fwriter.write("3");
		fwriter.write("4");

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=ParseException.class)
	public void testReadAndCheckFileIsEmpty() throws IOException, ParseException{
		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=ParseException.class)
	public void testReadAndCheckFileContainsNonNumeric() throws AssertionError, IOException, ParseException{
		fwriter.write("1a");
		fwriter.write("2");
		fwriter.write("3");
		fwriter.write("4");

		triangle.readAndCheck(FILENAME);
	}

	@Test(expected=ParseException.class)
	public void testReadAndCheckFileContainsNegative() throws IOException, NumberFormatException, ParseException {
		fwriter.write("-3");
		fwriter.write("-3");
		fwriter.write("-3");

		triangle.readAndCheck(FILENAME);
	}

	@Test
	public void testTypeTriangleEquilateral() {
		assertEquals(3, triangle.typeTriangle(3, 3, 3));
	}

	@Test
	public void testTypeTriangleIsoscelesA() {
		assertEquals(2, triangle.typeTriangle(4, 3, 3));
	}

	@Test
	public void testTypeTriangleIsoscelesB() {
		assertEquals(2, triangle.typeTriangle(3, 4, 3));
	}

	@Test
	public void testTypeTriangleIsoscelesC() {
		assertEquals(2, triangle.typeTriangle(3, 3, 4));
	}

	@Test
	public void testTypeTriangleScalene() {
		assertEquals(0, triangle.typeTriangle(2, 3, 4));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleValuesEqualZero() {
		triangle.typeTriangle(0, 0, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleValuesLessThanZero() {
		triangle.typeTriangle(-1, -1, -1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleOverDoubleBoundaries() {
		triangle.typeTriangle(Double.MAX_VALUE + 1, Double.MAX_VALUE + 1, Double.MAX_VALUE + 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleUnderDoubleBoundaries() {
		triangle.typeTriangle(Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleImpossibleA() {
		triangle.typeTriangle(7, 2, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleImpossibleB() {
		triangle.typeTriangle(2, 7, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTypeTriangleImpossibleC() {
		triangle.typeTriangle(2, 2, 7);
	}

}