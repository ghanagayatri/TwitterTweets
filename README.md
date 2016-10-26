# TwitterTweets
This code shows how to pull tweets sequentially as well as concurrently


## Description of the Sequential Version:
1.In the sequential version of the program we take a string array of the twitter usernames and write the program using two for loops such that the first loop iterates through the users and the second loop iterates through the pages one to sixteen.
2.So for each user, a new file is created and each page is retrieved one after one and copied into a file of that particular user.
3.We use Rome and JDom libraries to parse the atom feed into the text format.
4.The total time taken for the sequential program to retrieve the tweets for all the nine users is 346.688989595.


## Description of the Concurrent Version:
1.In the concurrent version of the program we make use of the Executor Service, Callable and Future interfaces to create the tasks and invoke them by creating threads.
2.The blocking coefficient is taken as 0.9 as a lot of time is being spent retrieving data from the twitter site.
3.So a total of forty threads are created as the numbers of cores are four.
4.We create tasks using the Callable interface, one task for each user and page.
5.These tasks are invoked using the ExecutorService with the method invokeAll, the threads start executing the tasks and return the results to the Future object.
6.The results are copied into files of the particular user by comparing the key of the hashmap with the user name.
7.The total time taken to execute the concurrent version is 38.728414079.
