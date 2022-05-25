package edu.ucr.cs.cs167.mvinc006;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Filter log file by response code
 */
public class Filter {

    // Where does the main function run? Q3
    public static void main(String[] args) throws Exception {
        // Program Arguments.
        String inputPath = args[0];
        String outputPath = args[1];
        String desiredResponse = args[2];

        // Job / Config Object creation.
        Configuration conf = new Configuration();
        // TODO pass the desiredResponse code to the MapReduce program
        conf.set("rCode", desiredResponse);

        // https://hadoop.apache.org/docs/stable/api/org/apache/hadoop/mapreduce/Job.html
        Job job = Job.getInstance(conf, "filter");

        // Job setup.
        job.setJarByClass(Filter.class); // What does this do? Q1
        job.setMapperClass(TokenizerMapper.class);
        job.setNumReduceTasks(0); // What effect does this have? Q2
        job.setInputFormatClass(TextInputFormat.class);

        // Input path.
        Path input = new Path(inputPath);
        FileInputFormat.addInputPath(job, input);

        // Output path.
        Path output = new Path(outputPath);
        FileOutputFormat.setOutputPath(job, output);

        // Exit on completion.
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class TokenizerMapper extends
            Mapper<LongWritable, Text, NullWritable, Text> {

        @Override
        protected void setup(Context context)
                throws IOException, InterruptedException {
            super.setup(context);
            // TODO add additional setup to your map task, if needed.
        }

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            if (key.get() == 0) return; // Skip header line
            String[] parts = value.toString().split("\t");
            String responseCode = parts[5];

            // TODO Filter by response code

            // Get the context and passed specific code.
            Configuration configuration = context.getConfiguration();
            String code = configuration.get("rCode");

            // Modified to use the code passed in by the user.
            if (responseCode.equals(code))
                context.write(NullWritable.get(), value);

        }
    }

}
