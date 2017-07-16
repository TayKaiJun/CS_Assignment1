/*
 * Name:
 * Class:
 *
 */

import DoublyLinkedList.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Navigation {

	final private static String defaultURL = "http://www.nushigh.edu.sg/";
	private static DoubleNode<String> currentNode = new DoubleNode<String>();

	public static void main(String[] args) throws IOException{

		System.out.print("Enter X: ");
		Scanner scanner = new Scanner(System.in);

		//checks that the input is an integer, else request for re-entry
		while (!scanner.hasNextInt()) {
			System.out.println("***** Please enter an integer *****");
			System.out.print("Enter X: ");
			scanner = new Scanner(System.in);
		}
		int Xvalue = scanner.nextInt();

		//for loop to loop through the files to be read
		for(int i = 1; i <= Xvalue; i++){
			BufferedReader reader = null;
			try{
				reader = new BufferedReader(new FileReader("navigation" + i + ".in"));

				//Browser opens
				int currentTab = 0;
				DoubleList<String> tabsURL = new DoubleList<String>();
				tabsURL.addToFront(defaultURL);
				String currentURL = defaultURL;
				currentTab++;

				//obtain no. of commands first
				scanner = new Scanner(reader.readLine());
				int n = 0;
				if(scanner.hasNextInt())
					n = scanner.nextInt();
				System.out.println("" + n); // to be deleted - for debugging purpose

				//loops through the specified no. of commands
				System.out.println("Output of navigation" + i + ".in:");
				for(int j = 0; j < n; j++){
					currentTab = executeCommand(reader.readLine(), tabsURL, currentTab);
					Iterator<String> iterator = tabsURL.iterator();
					for(int k = 0; k < currentTab; k++){
						if(iterator.hasNext()) {
							currentURL = iterator.next();
						}
					}
					System.out.println("Tab " + currentTab + ": " + currentURL);
				}
			}
			finally {
				if (reader != null) {
					reader.close();
				}
			}

			System.out.println();
		}

	}

	private static int executeCommand(String input, DoubleList<String> tabsURL, int currentTab){
		Scanner scanner = new Scanner(input);
		scanner.useDelimiter(" ");
		if (scanner.hasNext()) {
			String command = scanner.next();
			String url = null;
			if(scanner.hasNext()) {
				url = scanner.next();
				//System.out.println(command + " " + url);
			}
			else {
				//System.out.println(command);
			}

			switch (command){
				case "NEWTAB":
					if(tabsURL.isEmpty()) {
						tabsURL.addToRear(defaultURL);
					}
					else {
						tabsURL.addAfter(defaultURL, currentTab);
					}
					break;
				case "CLOSETAB":
					if(tabsURL.isEmpty()) {
						//System.out.println("No tabs to close");
					}
					else if(currentTab == tabsURL.size()) {
						tabsURL.remove(currentTab);
						currentTab--;
					}
					else {
						tabsURL.remove(currentTab);
					}
					break;
				case "NEXTTAB":
					if(tabsURL.isEmpty()){
						//System.out.println("No tabs opened");
					}
					else if(currentTab == tabsURL.size()) {
						//System.out.println("Currently at the last tab");
					}
					else {
						currentTab++;
					}
					break;
				case "PREVTAB":
					if(tabsURL.isEmpty()){
						//System.out.println("No tabs opened");
					}
					else if(currentTab == 1) {
						//System.out.println("Currently at the first tab");
					}
					else {
						currentTab--;
					}
					break;
				case "OPENHERE":
					if(tabsURL.isEmpty()) {
						tabsURL.addToRear(url);
					}
					else {
						tabsURL.addAfter(url, currentTab);
						tabsURL.remove(currentTab);
					}
					break;
				case "OPENNEW":
					if(tabsURL.isEmpty()) {
						tabsURL.addToRear(url);
					}
					else {
						tabsURL.addAfter(url, currentTab);
					}
					break;
			}

		}

		return currentTab;
	}
}