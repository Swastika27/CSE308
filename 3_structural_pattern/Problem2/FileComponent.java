package Problem2;

import java.util.Date;

public class FileComponent {
    protected String name;
    protected int sizeInKB;
    protected Type type;
    protected String directory;
    protected int componentCount;
    protected Date creationTime;

    public String getName() {
        return name;
    }

    public int getSizeInKB() {
        return sizeInKB;
    }

    public Type getType() {
        return type;
    }

    public String getDirectory() {
        return directory;
    }

    public int getComponentCount() {
        throw new UnsupportedOperationException();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public FileComponent(String name, int size, Type type, Date creationTime, String directory) {
        this.name = name;
        this.sizeInKB = size;
        this.type = type;
        this.componentCount = 0;
        this.creationTime = creationTime;
        this.directory = directory;
    }

    public FileComponent changeDirectory(String name) {
        throw new UnsupportedOperationException();
    }
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(name).append("\n")
                .append("Type: ").append(type).append("\n")
                .append("Size: ").append(getSizeInKB()).append("kB\n");
        try {
            if(getComponentCount() >= 0) {
                details.append("Component Count: ").append(getComponentCount()).append("\n");
            }
        } catch (UnsupportedOperationException e) {

        }
        details.append("Creation time: ").append(creationTime.toString()).append("\n");

        return details.toString();
    }
    public String getList() {
        throw new UnsupportedOperationException();
    }
    public boolean delete(String name) {
        throw new UnsupportedOperationException();
    }
    public void deleteRecursive() {
        throw new UnsupportedOperationException();
    }
    public void recursiveDelete(String name) {
        throw new UnsupportedOperationException();
    }
    public void makeDirectory(String name) {
        throw new UnsupportedOperationException();
    }
    public FileComponent touch(String name, int size) {
        throw new UnsupportedOperationException();
    }
    public FileComponent find(String name) {
        throw new UnsupportedOperationException();
    }

}
