package edu.emp.pfe.utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Utilities {
    static public void writeToFile(String filePath, String content) {

        String directoryPath = filePath.substring(0, filePath.lastIndexOf("/"));
        Path path = Paths.get(directoryPath);

        try {
            Files.createDirectories(path);
            Files.write(Paths.get(filePath),
                    content.getBytes());
        } catch (IOException e) {
            System.out.println("can't create the file " + filePath + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String  readFile(String filePath) {
        String data="";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. can't read the file" + filePath);
            e.printStackTrace();
        }

        return data;
    }

    public static boolean deleteDirectory(String path) {
        File directoryToBeDeleted = new File(path);
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file.getPath());
            }
        }
        return directoryToBeDeleted.delete();
    }

//    public static void copyFunction(String src, String dest) {
//
//        String directoryPath = dest.substring(0, dest.lastIndexOf("/"));
//        Path path = Paths.get(directoryPath);
//
//
//        File source = new File(src);
//        File destination = new File(dest);
//
//        InputStream is = null;
//        OutputStream os = null;
//        try {
//            Files.createDirectories(path);
//            is = new FileInputStream(source);
//            os = new FileOutputStream(destination);
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                os.write(buffer, 0, length);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void createDirectory(String vagrantEnvPath) {
        Path path = Paths.get(vagrantEnvPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
