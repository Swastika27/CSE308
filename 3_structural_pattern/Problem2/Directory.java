package Problem2;

import java.util.ArrayList;
import java.util.Date;

public class Directory extends FileComponent{
    private ArrayList<FileComponent> children;

    public Directory(String name, int size, Type type, Date creationTime, String directory) {
        super(name, size, type, creationTime, directory);
        children = new ArrayList<>();
    }

    @Override
    public int getComponentCount() {
        return children.size();
    }

    @Override
    public int getSizeInKB() {
        int total = 0;
        for (FileComponent f : children) {
            total += f.getSizeInKB();
        }
        return total;
    }
    @Override
    public FileComponent changeDirectory(String name) {
        for(FileComponent f : children) {
            try {
                if(f.getName().equals(name) && f.componentCount >= 0) {
                    return f;
                }
            } catch (UnsupportedOperationException e) {
//                System.out.println("Error! Change directory called with a file name ");
                throw e;
            }
        }
        return null;
    }
    @Override
    public String getList() {
        StringBuilder list = new StringBuilder();

        for(FileComponent component: children) {
            list.append(component.getName()).append("\t")
                    .append(component.getSizeInKB()).append("kB\t")
                    .append(component.getCreationTime())
                    .append("\n");
        }

        return list.toString();
    }

    @Override
    public boolean delete(String name) {
        for(FileComponent component : children) {
            try{
                if(component.getName().equals(name) && component.getComponentCount() == 0) {
                    children.remove(component);
                    return true;
                }
            } catch(UnsupportedOperationException e) {
                children.remove(component);
            }
        }
        return false; // no such file or directory
    }
    @Override
    public void deleteRecursive() {
        for(FileComponent component : children) {
            try {
                component.deleteRecursive();
            } catch (UnsupportedOperationException ignored) {

            } finally {
                children.remove(component);
            }
        }
    }
    @Override
    public void recursiveDelete(String name) {
        for(FileComponent f : children) {
            if(f.getName().equals(name)) {
                try {
                    f.deleteRecursive();
                } catch (UnsupportedOperationException e) {
                    System.out.println("Warning: Calling recursive delete on a file");
                }
                children.remove(f);
                break;
            }
        }
    }

    @Override
    public void makeDirectory(String name) {
        FileComponent directory = new Directory(name, 0, Type.Folder, new Date(), this.directory + "\\" + name);
        children.add(directory);
    }

    @Override
    public FileComponent touch(String name, int size) {
        FileComponent file = new File(name, size, Type.File, new Date(), this.directory);
        children.add(file);
        return file;
    }

    @Override
    public FileComponent find(String name) {
        if (this.name.equals(name)) return this;
        else {
            FileComponent foundFile;
            for (FileComponent f : children) {
                foundFile = f.find(name);
                if(foundFile != null) {
                    return foundFile;
                }
            }
        }
        return null;
    }
}
