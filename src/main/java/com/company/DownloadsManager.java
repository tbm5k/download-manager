package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class DownloadsManager {

    private static File[] content;
    private static File fileName;

    //Folder paths
    private static final String downloadsPath = "C:\\Users\\tbm5k\\Downloads";
    private static final String imageDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Images\\";
    private static final String videosDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Videos\\";
    private static final String zippedDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Zipped\\";
    private static final String docsDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Docs\\";
    private static final String undefDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Undefined\\";
    private static final String musicDes = "C:\\Users\\tbm5k\\Desktop\\Download-Manger-Sorted\\Music\\";

    public static void main(String[] args) throws IOException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.print(
                        "============================================== \n" +
                                "LISTING ALL THE FILES \n" +
                                "============================================== \n");

                while (true) {
                    //Opening the downloads folder and listing all the downloads available
                    File directory = null;
                    try {
                        directory = new File(properties());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    content = directory.listFiles();

                    assert content != null;
                    for (File obj : content) {
                        if (obj.isFile()) {
                            System.out.format("File name : %s %n", obj.getName());
                        } else if (obj.isDirectory()) {
                            System.out.format("Directory name : %s %n", obj.getName());
                        }
                    }

                    //calling this method to sort out the downloads folder
                    try {
                        transferDownload();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException interruptedException){
                        System.out.println("Interrupted exception : " + interruptedException);
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static String properties() throws IOException {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propsConfigPath = rootPath + "app.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propsConfigPath));

        String downloadsPath = appProps.getProperty("downloadsPath");
        //String appName = appProps.getProperty("name");

        return downloadsPath;
    }

    //This method checks the file type and returns that value
    private static String identifyFileType() {

        String fileType = "Undetermined";

        if(fileName != null) {
            System.out.print(
                    "============================================== \n" +
                            "LISTING THE FILE TYPES \n" +
                            "============================================== \n");
        }

        for (File file : content) {

            fileName = file;

            try {
                fileType = Files.probeContentType(Paths.get(String.valueOf(fileName)));

                if (fileType != null) {
                    System.out.println("Type :" + fileType);
                    return fileType;
                } else {
                    System.out.println("Sorry, the file type is unknown");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //this method changes the directory of a file in the downloads folder to the target folder
    private static void transferDownload() throws IOException {

        File aFile;

        //identifyFileType method gets called and compared
        if(identifyFileType() == null){
            aFile = new File(String.valueOf(fileName));

            if (aFile.renameTo(new File(undefDes + aFile.getName()))) {
                System.out.println("File is moved successful!");
            }
        }else {
            switch (Objects.requireNonNull(identifyFileType())) {

                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                case "application/x-zip-compressed":
                case "application/x-tar":
                case "application/x-msdownload":
                    aFile = new File(String.valueOf(fileName));

                    if (aFile.renameTo(new File(zippedDes + aFile.getName()))) {
                        System.out.println("File is moved successful!");
                    }

                    break;

                case "video/3gpp":
                    aFile = new File(String.valueOf(fileName));

                    if (aFile.renameTo(new File(videosDes + aFile.getName()))) {
                        System.out.println("File is moved successful!");
                    }

                    break;

                case "text/plain":
                case "application/pdf":
                    aFile = new File(String.valueOf(fileName));

                    if (aFile.renameTo(new File(docsDes + aFile.getName()))) {
                        System.out.println("File is moved successful!");
                    }

                    break;

                case "image/jpeg":
                case "image/png":
                    aFile = new File(String.valueOf(fileName));

                    if (aFile.renameTo(new File(imageDes + aFile.getName()))) {
                        System.out.println("File is moved successful!");
                    }

                    break;

                case "audio/mpeg":
                    aFile = new File(String.valueOf(fileName));

                    if (aFile.renameTo(new File(musicDes + aFile.getName()))) {
                        System.out.println("File is moved successful!");
                    }

                    break;

                default:
                    System.out.println("Failed to move file!");
                    break;

            }
        }
    }
}

