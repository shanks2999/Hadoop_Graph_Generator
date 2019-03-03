import java.io.*;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class XMLBuddy {
    public static Set<String> mySet = new HashSet<String>();
    public static java.util.Map<String, String> myMap = new HashMap<String, String>();

    public static class Map extends Mapper<LongWritable, Text,
            Text, LongWritable> {
        private static final Log LOG = LogFactory.getLog(Map.class);

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Professors prof = new Professors();
            prof.getProfessors();
            mySet = prof.getMySet();
            myMap = prof.getMyMap();
        }

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String document = value.toString();
            document= StringEscapeUtils.unescapeHtml4(document);
            try{
                String authors = "";
                String[] lines = document.split("\n");
                for(String line: lines) {
                    if(line.startsWith("<author")) {
                        String name = line.substring(line.indexOf(">")+1, line.lastIndexOf("</author>"));
                        if(mySet.contains(name)) {
                            if(myMap.containsKey(name))
                                authors += myMap.get(name) + "\n";
                            else
                                authors += name + "\n";
                        }

                    }
                }

                String arr[] = authors.split(Pattern.quote("\n"));
                if(authors.trim().equalsIgnoreCase(""))
                    return;
                if( arr.length == 1)
                    context.write(new Text(arr[0]), new LongWritable(1));

                else {
                    Arrays.sort(arr);
                    for(int i=0;i<arr.length;i++) {
                        context.write(new Text(arr[i]), new LongWritable(1));
                        for(int j=i+1;j<arr.length;j++) {
                            context.write(new Text(arr[i] + "," + arr[j]), new LongWritable(1));
                        }
                    }
                }
            }
            catch(Exception e){
//                throw new IOException(e);
                System.out.println("ERROR");
            }
        }
    }

    public static class Reduce
            extends Reducer<Text, LongWritable, Text, LongWritable> {
        private LongWritable result = new LongWritable();
        public void reduce(Text key, Iterable<LongWritable> values,
                           Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (LongWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }



    public static void main(String[] args) throws Exception
    {
        Instant start = Instant.now();
//        new XMLBuddy().getProfessors();

        System.setProperty("hadoop.home.dir", "C:/hadoop-2.8.0");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "xml count");
//        System.out.println(new Path("/professors.txt").toUri());
//        job.addCacheFile(new Path("/professors.txt").toUri());


        job.setJarByClass(XMLBuddy.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapperClass(Map.class);
        job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(XmlInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);

        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time in seconds: " +
                interval.getSeconds());
    }
}