package edu.ucr.cs.cs167.mvinc006;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;

import java.io.FileNotFoundException;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        // Handles incorrect number of args.
        if (args.length != 2) {
            System.err.println("Incorrect number of arguments! Expected two arguments.");
            System.exit(-1);
        }

        // stores input and output path in appropriate local variable.
        org.apache.hadoop.fs.Path inputPath = new Path(args[0]);
        org.apache.hadoop.fs.Path outputPath = new Path(args[1]);

        // Retrieve the file system & store.
        // Also need to create a configuration
        Configuration conf = new Configuration();
        org.apache.hadoop.fs.FileSystem fsIn = null;
        org.apache.hadoop.fs.FileSystem fsOut = null;
        try {
            fsIn = inputPath.getFileSystem(conf);
            fsOut = outputPath.getFileSystem(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check whether the input file exists or not.
        long sizeOfInputBytes = 0;
        try {
            assert fsIn != null;
            assert fsOut != null;
            fsIn.getFileStatus(inputPath);
            sizeOfInputBytes = fsIn.getFileStatus(inputPath).getLen();
        } catch (FileNotFoundException e) {
            // `input` is a Path variable in step 3
            System.err.printf("Input file '%s' does not exist!\n", inputPath);
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // Check whether the output file exists or not.
        try {
            fsOut.getFileStatus(outputPath);
            // our file must exist which we do not want.
            // `output` is a Path variable in step 3
            System.err.printf("Output file '%s' already exists!\n", outputPath);
            System.exit(-1);
        } catch (FileNotFoundException e) {
            // Our file does not exists, which is what we want.
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // Begin timing.
        float startTime = System.nanoTime();

        // Open the input file and copy all of its contents to the output. Will also measure the time this takes.
        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;
        try {
            inputStream = fsIn.open(inputPath);
            outputStream = fsOut.create(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        assert outputStream != null;

        // begin copying over bytes.
        for (long i = 0; i < sizeOfInputBytes; i++) {
            // read and write
            try {
                outputStream.write(inputStream.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Always close the streams!
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        float endTime = System.nanoTime();

        // print out statics
        System.out.printf("Copied %d bytes from '%s' to '%s' in %f seconds\n",
                sizeOfInputBytes, inputPath, outputPath, (endTime - startTime) * 1E-9);
    }

}
