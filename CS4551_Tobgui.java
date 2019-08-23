import java.util.Scanner;

public class CS4551_Tobgui {

	public static void main(String[] args) {

		// the program expects one commandline argument
		// if there is no commandline argument, exit the program

		if (args.length != 1) {

			usage();
			System.exit(1);
		}

		System.out.println("--Welcome to Multimedia Software System--");

		// Create an Image object with the input PPM file name.
		MImage img = new MImage(args[0]);
		MImage img2 = new MImage(args[0]);	

		System.out.println(img);
			
		// Save it into another PPM file.
		img.write2PPM("out.ppm");

		// Save it into another PPM file.
		img.write2PPM("out2.ppm");

		
		img2.write2PPM("out3.ppm");
		img2.write2PPM("out4.ppm");
		
		System.out.println("--Good Bye--");
				
		int option = 0;
		ImageHandler handle = new ImageHandler(img);
		
		ImageHandler handleAnother =new ImageHandler(img2);

		do {

			System.out.println("Main Menu-----------------------------------");
			System.out.println("1. Conversion to Gray-scale Image (24bits->8bits)");
			System.out.println(
					"2. Conversion to 8bit Indexed Color Image using Uniform Color Quantization (24bits->8bits)");
			System.out.println("3. Quit ");
			System.out.println();
			System.out.println("Please enter the task number [1-3]:");

			Scanner reader = new Scanner(System.in); // Reading from System.in

			option = reader.nextInt();

			switch (option) {

			case 1:
				System.out.println("Gray Scale");
				handle.grayScale();
				img.write2PPM(img.getName() + "-gray");
				//img.write2PPM(img.getName() + "-Gray");
				//img.readPPM("out2.ppm");
				break;

			case 2:
				System.out.println("UCQ");

				handleAnother.generateLookUpTable();
				handleAnother.UCQ();
				img2.write2PPM(img2.getName() + "-QT8.ppm");
				
				handleAnother.indexValue();
				img2.write2PPM(img2.getName() + "-index.ppm"); //index one
				
				//img.readPPM("out2.ppm");
				
				break;

			case 3:
				System.out.println("Quit");
				reader.close();
				break;
			}
			
		} while (option != 3);
	}

	public static void usage() {
		System.out.println("\nUsage: java CS4551_Main [input_ppm_file]\n");

	}
}
