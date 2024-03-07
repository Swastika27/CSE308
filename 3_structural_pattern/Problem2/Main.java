package Problem2;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(new File("input2.txt"));

        fileSystem.printDirectory();

        while(scanner.hasNext()) {
            String[] command = scanner.nextLine().split(" ");
            if(command[0].equals("cd") ) {
                if(command[1].equals("~")) {
                    fileSystem.jumpToRoot();
                }
                else {
                    fileSystem.changeDirectory(command[1]);
                }
            }
            else if(command[0].equals("ls")) {
                fileSystem.getDetails(command[1]);
            }
            else if(command[0].equals("list")) {
                fileSystem.list();
            }
            else if(command[0].equals("delete")) {
                if(command[1].equals("-r")) {
                    fileSystem.recursiveDelete(command[2]);
                }
                else fileSystem.delete(command[1]);
            }
            else if(command[0].equals("mkdir")) {
                fileSystem.makeDirectory(command[1]);
            }
            else if(command[0].equals("touch")) {
                fileSystem.touch(command[1], Integer.parseInt(command[2]));
            }
            else if(command[0].equals("mkdrive")) {
                fileSystem.makeDrive(command[1]);
            }
            else {
                System.out.println("Unknown command");
            }
            fileSystem.printDirectory();

        }
    }
}
