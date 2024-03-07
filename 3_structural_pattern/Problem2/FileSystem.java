package Problem2;

import java.util.ArrayList;
import java.util.Date;

public class FileSystem {
    private ArrayList<FileComponent> drives;
    private FileComponent currentDirectory;

    public FileSystem() {
        drives = new ArrayList<>();
        currentDirectory = null;
    }
    public void makeDrive(String name) throws Exception {
        for(FileComponent d : drives) {
            if(d.getName().equals(name + ":\\")) {
                throw new Exception("A drive with this name already exists");
            }
        }
        FileComponent newDrive = new Directory(name + ":\\", 0, Type.Drive, new Date(), name + ":");
        drives.add(newDrive);
    }
    public void changeDirectory (String name) {
        if(currentDirectory == null) {
            for (FileComponent d : drives) {
                if(d.getName().equals(name)) {
                    currentDirectory = d;
                }
            }
            if(currentDirectory == null) {
                System.out.println("No such folder or drive in current directory");
            }
        }
        else {
            FileComponent prevDir = currentDirectory;
            try {
                currentDirectory = currentDirectory.changeDirectory(name);
                if (currentDirectory == null) {
                    System.out.println("No such folder or drive in current directory");
                    currentDirectory = prevDir;
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("Error! Change directory called with file name");
                currentDirectory = prevDir;
            }

        }
    }

    private FileComponent find(String name) {
        for(FileComponent d : drives) {
            if(d.getName().equals(name)) return d;
            else return d.find(name);
        }
        return null;
    }
    public void getDetails(String name) {
        FileComponent component = find(name);

        if(component != null) {
            System.out.println(component.getDetails());
        }
        else {
            System.out.println("Error! No file, folder, or drive with this name");
        }
    }
    public void list() {
        if(currentDirectory != null)
            System.out.println(currentDirectory.getList());
        else {
            StringBuilder list = new StringBuilder();
            for(FileComponent component : drives) {
                list.append(component.getName()).append("\t")
                        .append(component.getSizeInKB()).append("kB\t")
                        .append(component.getCreationTime())
                        .append("\n");
            }
        }
    }
    public void delete(String name) {
        currentDirectory.delete(name);
    }
    public void recursiveDelete(String name) {
        try {
            currentDirectory.recursiveDelete(name);
        } catch (UnsupportedOperationException e) {
            System.out.println("Warning: calling recursive delete on a file");
        }
    }
    public void jumpToRoot() {
        currentDirectory = null;
    }
    public void makeDirectory(String name)  {
        if(currentDirectory != null) {
            currentDirectory.makeDirectory(name);
        }
        else {
            System.out.println("Error! Can not make directory in root");
        }
    }
    public void touch(String name, int size) {
        if(currentDirectory != null) {
            currentDirectory.touch(name, size);
        }
        else {
            System.out.println("Error! Can not create file in root");
        }
    }
    public void printDirectory() {
        if(currentDirectory != null) {
            System.out.print(currentDirectory.getDirectory() + "> ");
        }
        else {
            System.out.print("root> ");
        }
    }
}
