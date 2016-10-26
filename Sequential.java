import java.net.URL;
import java.util.Iterator;
import java.io.*;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Sequential {

  public static void main(String[] args) throws Exception {
  String [] user=new String[]{"notch","justinbieber","YouTube","aplusk","katyperry","rihanna","SnoopDogg","cnnbrk","ParisHilton"};
  final long start = System.nanoTime();
  for(int a=0;a<user.length;a++){
	  File f= new File(user[a]+".txt");
	  FileWriter writer= new FileWriter(f);
	  f.createNewFile();
	  int j=0;
	  for(int b=1;b<=16;b++){
          URL url  = new URL("http://twitter.com/statuses/user_timeline/"+user[a]+".atom?count=200&page="+b);
          XmlReader reader = null;
             try {
    	
               reader = new XmlReader(url);
               SyndFeed feed = new SyndFeedInput().build(reader);
               writer.write("Feed Title: "+ feed.getTitle());
       
                
                  for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
    	          j++;
                  SyndEntry entry = (SyndEntry) i.next();
                  writer.append("\r\n"+j+" "+(entry.getTitle()));
        
                  }  //third loop        
                  System.out.println("Retrieving tweets page "+b+" of user "+user[a]);   
             } finally {
             if (reader != null)
                reader.close();
               }
      }//second for loop
	  writer.flush();
	  writer.close();
}//first loop
  final long end = System.nanoTime();
  System.out.println("Time (seconds) taken " + (end - start)/1.0e9);
  }
}