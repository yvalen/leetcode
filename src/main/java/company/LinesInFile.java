package company;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/*
 * LinkedIn
 * Implement a (Java) Iterable object that iterates lines one by one from a text file.
 * Iterable: a simple representation of a series of elements that can be iterated over
 * Iterator: the object with iteration state, manages iteration over iterable
 * 
 */
public class LinesInFile implements Iterable<String> {
    private Stream<String> lines;
    private Path path;
 
    public LinesInFile(String filename) throws IOException {
        path = Paths.get(filename);
        System.out.println(path.getFileName());
        //lines = Files.lines(path);
    }

    @Override
    public Iterator<String> iterator() {
        //return lines.iterator();
        try {
            return new LineIterator(Files.newBufferedReader(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static class LineIterator implements Iterator<String> {
        private BufferedReader reader;
        private String line;
        
        public LineIterator(BufferedReader bufferedReader) {
            this.reader = bufferedReader;
            advance();
        }
        
        
        @Override
        public boolean hasNext() {
            return line != null;
            /*
            try {
                reader.mark(1);
                if (reader.read() < 0) {
                    return false;
                }
                reader.reset();
                return true;
            } catch (IOException e) {
                return false;
            }*/
        }

        @Override
        public String next() {
            String result = line;
            advance();
            return result;
            /*
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
            */
        }
        
        private void advance() {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                if (line == null && reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        try {
            LinesInFile lif = new LinesInFile("/Users/yuanyuanxu/Downloads/input.txt");
            /*
            try (BufferedReader bufferedReader = Files.newBufferedReader(lif.path))  {
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }
            };*/
            
            for (String s : lif) {
                System.out.println(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
