package prog;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import javax.activation.MailcapCommandMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Triangle {

	private double a, b, c;

	public Triangle() { }

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public void readAndCheck(String fileName) throws NumberFormatException, IOException, ParseException {
		FileReader toRead = new FileReader(fileName);

		String sCurrentLine;

		BufferedReader br = new BufferedReader(toRead);

		int nLine = 0;

		if((sCurrentLine = br.readLine()) != null) {	
			nLine++;		
			sCurrentLine = sCurrentLine.replaceAll(" ", "");

			if(!sCurrentLine.matches("(\\d)+(\\.)?(\\d)+")) {
				throw new ParseException(sCurrentLine, nLine);
			}

			if(Double.parseDouble(sCurrentLine) <= 0) {
				throw new ParseException(sCurrentLine, nLine);
			}

			setA(Double.parseDouble(sCurrentLine));
		} else {
			throw new ParseException(sCurrentLine, nLine);
		}

		if((sCurrentLine = br.readLine()) != null) {
			nLine++;
			sCurrentLine = sCurrentLine.replaceAll(" ", "");

			if(!sCurrentLine.matches("(\\d)+(\\.)?(\\d)+")) {
				throw new ParseException(sCurrentLine, nLine);
			}

			if(Double.parseDouble(sCurrentLine) <= 0) {
				throw new ParseException(sCurrentLine, nLine);
			}

			setB(Double.parseDouble(sCurrentLine));
		}

		if((sCurrentLine = br.readLine()) != null) {	
			nLine++;
			sCurrentLine = sCurrentLine.replaceAll(" ", "");

			if(!sCurrentLine.matches("(\\d)+(\\.)?(\\d)*")) {
				throw new ParseException(sCurrentLine, nLine);
			}

			if(Double.parseDouble(sCurrentLine) <= 0) {
				throw new ParseException(sCurrentLine, nLine);
			}

			setC(Double.parseDouble(sCurrentLine));
		}

		while((sCurrentLine = br.readLine()) != null) {
			nLine++;
			if(!sCurrentLine.matches("\\s*")) {
				throw new ParseException(sCurrentLine, nLine);
			}
		}

		System.out.println(getA()+" "+getB()+" "+getC());
		System.out.println("Side were correctly allocated");

		return;
	}

	public int typeTriangle(double a, double b, double c) {
		if(a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException("Positive value is expected");
		}

		if(a == b && b  == c) {
			System.out.println("Equilateral");
			return 3;
		}

		if(a == b || a == c || b == c) {
			System.out.println("Isosceles");
			return 2;
		}

		System.out.println("Irregular");
		return 0;
	}

//	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
//		Triangle t = new Triangle();
//
//		t.readAndCheck("src/test.txt");
//
//	}
}
