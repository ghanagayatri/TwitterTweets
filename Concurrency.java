
import java.util.Iterator;
import java.io.*;
import java.util.Date;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Concurrency {
  public void gatheringTwitters() 
      throws InterruptedException, ExecutionException,IOException {      
    final int numberOfCores = Runtime.getRuntime().availableProcessors();
    final double blockingCoefficient = 0.9;
    final int poolSize = (int)(numberOfCores / (1 - blockingCoefficient));
	final String [] users=new String[]{"notch","justinbieber","YouTube","aplusk","katyperry","rihanna","SnoopDogg","cnnbrk","ParisHilton"};
    int pages[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    
	
    System.out.println("Number of Cores available is " + numberOfCores);
    System.out.println("Pool size is " + poolSize);
   // final List<Callable<List<String>>> partitions = 
    //  new ArrayList<Callable<List<String>>>();
    final List<Callable<HashMap<String,List<String>>>> partitions = 
    	      new ArrayList<Callable<HashMap<String,List<String>>>>();
    for(final String user : users) {
    	for(final int page: pages){
          partitions.add(new Callable<HashMap<String,List<String>>>() {
          public HashMap<String,List<String>> call() throws Exception {
            final HashMap<String, List<String>> usertweetmap = new HashMap<String, List<String>>();
            final List<String> tweets = new ArrayList<String>();
            URL url  = new URL("http://twitter.com/statuses/user_timeline/"+user+".atom?count=200&page="+page);
            XmlReader reader = null;
            
              try {
   	
                 reader = new XmlReader(url);
                 SyndFeed feed = new SyndFeedInput().build(reader);
             
      
               
                 for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
                	 
   	                Date date=new Date();
                   SyndEntry entry = (SyndEntry) i.next();
                   tweets.add(entry.getTitle()+","+date.getTime());
                 }
                
                 usertweetmap.put(user, tweets);
                 System.out.println("Retrieving tweets page "+page+" of user "+user);
                        
              }
            finally {
            if (reader != null)
               reader.close();
              }               
         
         
          return usertweetmap;
         
        }        
      });
    }
    }   
   
    final ExecutorService executorPool = 
      Executors.newFixedThreadPool(poolSize);    
    final List<Future<HashMap<String, List<String>>>> valueOfStocks = 
      executorPool.invokeAll(partitions, 500000, TimeUnit.SECONDS);
   
  	  File f1= new File("notch.txt");
  	  FileWriter writer1= new FileWriter(f1);
  	  f1.createNewFile();
  	  File f2 =new File("justinbieber.txt");
  	  FileWriter writer2= new FileWriter(f2);
	  f2.createNewFile();
	  File f3 =new File("YouTube.txt");
  	  FileWriter writer3= new FileWriter(f3);
	  f3.createNewFile();
	  File f4 =new File("aplusk.txt");
  	  FileWriter writer4= new FileWriter(f4);
	  f4.createNewFile();
	  File f5 =new File("katyperry.txt");
  	  FileWriter writer5= new FileWriter(f5);
	  f5.createNewFile();
	  File f6 =new File("rihanna.txt");
  	  FileWriter writer6= new FileWriter(f6);
	  f6.createNewFile();
	  File f7 =new File("SnoopDogg.txt");
  	  FileWriter writer7= new FileWriter(f7);
	  f7.createNewFile();
	  File f8 =new File("cnnbrk.txt");
  	  FileWriter writer8= new FileWriter(f8);
	  f8.createNewFile();
	  File f9 =new File("ParisHilton.txt");
  	  FileWriter writer9= new FileWriter(f9);
	  f9.createNewFile();
	  List<String> val;
    for(final Future<HashMap<String,List<String>>> valueOfAStock : valueOfStocks){  
    	 HashMap<String, List<String>> usertweets=new HashMap<String, List<String>>();
        usertweets=valueOfAStock.get();
        if(usertweets.containsKey("notch")){
        	val=usertweets.get("notch");
        	for(String s1:val)
    	 writer1.append("\r\n"+" "+s1); 
        	val.clear();
        }
        else if(usertweets.containsKey("justinbieber")){
        	val=usertweets.get("justinbieber");
        	  for(String s1:val)
                writer2.append("\r\n"+" "+s1);
        	  val.clear();
    }    
        else if(usertweets.containsKey("YouTube")){
        	val=usertweets.get("YouTube");
        	  for(String s1:val)
                writer3.append("\r\n"+" "+s1);
        	  val.clear(); 
        }
        else if(usertweets.containsKey("aplusk")){
        	val=usertweets.get("aplusk");
        	  for(String s1:val)
                writer4.append("\r\n"+" "+s1);
        	  val.clear(); 
        }
        else if(usertweets.containsKey("katyperry")){
        	val=usertweets.get("katyperry");
        	  for(String s1:val)
                writer5.append("\r\n"+" "+s1);
        	  val.clear(); 
        }
        else if(usertweets.containsKey("rihanna")){
        	val=usertweets.get("rihanna");
        	  for(String s1:val)
                writer6.append("\r\n"+" "+s1);
        	  val.clear(); 
        } 
        else if(usertweets.containsKey("SnoopDogg")){
        	val=usertweets.get("SnoopDogg");
        	  for(String s1:val)
                writer7.append("\r\n"+" "+s1);
        	  val.clear(); 
        } 
        else if(usertweets.containsKey("cnnbrk")){
        	val=usertweets.get("cnnbrk");
        	  for(String s1:val)
                writer8.append("\r\n"+" "+s1);
        	  val.clear(); 
        }
        else if(usertweets.containsKey("ParisHilton")){
        	val=usertweets.get("ParisHilton");
        	  for(String s1:val)
                writer9.append("\r\n"+" "+s1);
        	  val.clear(); 
        }
        }
    executorPool.shutdown();
     
  }
  
  public static void main(final String[] args)
    throws ExecutionException, InterruptedException, IOException { 
	  final long start = System.nanoTime();
    new Concurrency().gatheringTwitters();
      final long end = System.nanoTime();
      System.out.println("Time (seconds) taken " + (end - start)/1.0e9);
  }
}


