package server;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MultiWriter {

    private static List<ObjectOutputStream> writers = new ArrayList<>();

    public void addWriter(ObjectOutputStream o) {
        writers.add(o);
    }

    public List<ObjectOutputStream> getWriters() {
        return writers;
    }

    public void removeWriter(ObjectOutputStream o) {
        writers.remove(o);
    }

}
