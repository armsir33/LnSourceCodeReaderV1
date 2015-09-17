package main;

import java.io.File;
import java.util.Scanner;
import file.FileProcesser;

public class Main {
	private FileProcesser fp;
	public static String path;
	private static Scanner scanner;

	public Main() {
		scanner = new Scanner(System.in);
		fp = new FileProcesser();
		usage();
	}

	public static void main(String[] args) {
		initAndSetupDBFolder();
		
		new Main();
	}
	
	private static void initAndSetupDBFolder() {
		File path = new File(System.getProperty("user.home")+File.separator + "LnSourceCodeReader");
		if(!path.exists()) {
			path.mkdirs();
		}
	}
	
	private void load() {
		boolean quit = false;
		String isExist;
		while (true) {
			String convertedPath = inputVariable();
			File file = new File(convertedPath);
			if (!file.exists()) {
				System.out.println("The path " + convertedPath + " does not exist.");
				do {
					System.out.println("Exist(0) or Re-input(1)");
					isExist = scanner.nextLine();
				} while (!isExist.equals("1") && !isExist.equals("0"));
				if (isExist.equals("0")) {
//					System.exit(0);
					quit = true;
					break;
				} else {
					continue;
				}
			} else
				break;
		}
		
		if(!quit)
			fp.process();
	}

	private void find() {
		String isContinue;
		String api;
		String impl;
		do {
			System.out.println("Input API name [Example, read.tssoc200]");
			api = scanner.nextLine();
			impl = fp.get(api);
			// output
			if(impl != null) {
				System.out.println("function " + api);
				System.out.println(impl);	
			} else
				System.out.println("NO function " + api + " found");
			System.out.println("--------------------------------------------");
			System.out.println("Press Enter to continue");
			try {
				scanner.nextLine();
			} catch (Exception e) {
			}

			do {
				System.out.println("Whether continue or not (YES or NO)");
				isContinue = scanner.nextLine();
			} while (!isContinue.equals("YES") && !isContinue.equals("NO"));

			if (isContinue.equals("NO"))
				break;
		} while (true);
	}

	private void list() {
		fp.list();
	}

	private void flush() {
		fp.clear();
	}

//	private String inputVariable() {
//		System.out.println("Input VRC [Example: B61C_a_kal7]");
//		VRC = scanner.nextLine();
//		System.out.println("Input Package [Example: ts]");
//		PACKAGE = scanner.nextLine();
//		System.out.println("Input Module [Example: soc]");
//		MODULE = scanner.nextLine();
//		System.out.println("Input Root File [Example: psoc91010]");
//		ROOT_FILE = scanner.nextLine();
//		do {
//			System.out.println("Input File Type [Example: REPORT(R) | PROGRAM(P)]");
//			TYPE = scanner.nextLine();
//		} while (!TYPE.equals("REPORT") && !TYPE.equals("R") && !TYPE.equals("PROGRAM") && !TYPE.equals("P"));
//
//		PropertyUtil pptUtil = new PropertyUtil();
//		String bsePath = pptUtil.getValidProperty("BSE_PATH");
//		if (TYPE.equals("P")) {
//			path = bsePath + "/application/" + PACKAGE + VRC + "/p" + PACKAGE + MODULE + "/" + ROOT_FILE;
//		} else {
//			path = bsePath + "/application/" + PACKAGE + VRC + "/r" + PACKAGE + MODULE + "/" + ROOT_FILE;
//		}
//		return path;
//	}
	
	private String inputVariable() {
		System.out.println("Input file path: ");
		String path = scanner.nextLine();
		return path;
	}

	private void usage() {
		System.out.println("Welcome to LN Source Code Reader");
		do {
			System.out.println("===============================================");
			System.out.println("1. Load a file");
			System.out.println("2. Find implementation of specific API");
			System.out.println("3. List all of APIs");
			System.out.println("4. Flush database");
			System.out.println("0. Exit");
			System.out.println("-----------------------------------------------");

			String option = scanner.nextLine();
			switch (option) {
			case "1":
				load();
				break;
			case "2":
				find();
				break;
			case "3":
				list();
				break;
			case "4":
				flush();
				break;
			case "0":
				scanner.close();
				fp.close();
				System.exit(0);
			default:
				System.out.println("Input wrong option index.");
			}
		} while (true);
	}
}
