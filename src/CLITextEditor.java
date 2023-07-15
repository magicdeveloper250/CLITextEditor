import java.io.*;
import java.util.Scanner;

public class CLITextEditor {
    static {
        System.out.println("WELCOME TO CLI TEXT EDITOR");
        generateInstruction();

    }

    private String fileName;
    private String filePath;

    public CLITextEditor() {

    }

    public void setFilename() {
        Scanner inputScanner;
        inputScanner = new Scanner(System.in);
        System.out.print("Please, enter the filename: ");
        String userProvidedFileName = inputScanner.nextLine();
        inputScanner = new Scanner(System.in);
        System.out.print("Enter path where to open your file: ");
        String userProvidedFilePath = inputScanner.nextLine();
        if (userProvidedFilePath.equals("")) {
            userProvidedFileName = "/";
        } else if (userProvidedFileName.equals("")) {
            userProvidedFileName = "myfile";
        }
        fileName = userProvidedFilePath + "\\" + userProvidedFileName + ".txt";

    }

    public String getFileName() {
        return fileName;
    }

    public void createTextFile() {
        setFilename();
        File newFile = new File(getFileName());
        //boolean fileCreated = newFile.
        //if (fileCreated) {
        System.out.println("Your file created successfully");
        System.out.println(newFile.getName());

        //} else {
        //System.out.println("Failed to create new file");
        // }
        writeToFile();


    }

    public void writeToFile() {
        System.out.println();
        System.out.println("-----------------------------------CLI TEXT EDITOR (created by IMPANO MANZI Enock)----------------------------------");
        System.out.println();
        FileWriter fileWriter = null;
        short lineCounter = 1;
        Scanner textScanner;
        boolean fileIsReady = true;
        try {
            fileWriter = new FileWriter(getFileName());
            while (fileIsReady) {
                System.out.print(lineCounter + ":- ");
                textScanner = new Scanner(System.in);
                String currentTextLine = textScanner.nextLine() + "\n";
                fileWriter.write(currentTextLine);
                if (currentTextLine.replace("\n", "").equals("end")) {
                    fileIsReady = false;
                }
                lineCounter++;


            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Your file saved successfully");

        } catch (IOException e) {
            System.out.println("Failed to open a specified file");
        }

    }

    public void readTextFile() {
        setFilename();
        try {
            FileReader fileReader = new FileReader(getFileName());
            boolean readyToRead = true;
            System.out.println();
            System.out.println("--------------------------------Your file content________________________________________________________________-");
            System.out.println();
            while (readyToRead) {
                int characterCode = fileReader.read();
                System.out.print((char) characterCode);
                if (characterCode == -1) {
                    readyToRead = false;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            readTextFile();
        } catch (IOException e) {
            System.out.println("Problem happens while reading file.");
        }

    }

    public static void generateInstruction() {
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        StringBuilder instruction = new StringBuilder();
        instruction.append("Enter 1: for creating New File" + "\n");
        instruction.append("Enter 2: for reading file" + "\n");
        instruction.append("Enter 3: for closing program");
        System.out.println(instruction);
        System.out.println("-------------------------------------------------------------");
    }

    public void initializeTextEditor() {
        System.out.print("Enter your Choice: ");
        Scanner userChoiceScanner = new Scanner(System.in);
        String userChoice = userChoiceScanner.nextLine();
        switch (userChoice) {
            case "1":
                createTextFile();
                break;
            case "2":
                readTextFile();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
                initializeTextEditor();
                break;
        }


    }

}
