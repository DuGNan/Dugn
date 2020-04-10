# Java并发编程

## 一、宏观概念
(对于多线程框架的掌握，知识展开之后是不是像一棵树一样。这些问题要回答的宽一点，等着他来细追究。)  


**Q: 多线程有了解吗？什么是线程安全问题**   
A: 有。多线程安全问题指的是在多个线程在操作内存中的同一个线程共享数据时，这个数据由于CPU多个线程之间来回切换导致的数据值错乱问题。
    

**Q: 出现多线程安全问题的原因有哪些？**  
A: 在Java中，根据JMM内存模型，导致线程不安全的原因有三个。  
    1、可见性:一个线程对数据修改之后，其他线程不知道这个值已经被修改。  
    2、原子性:当一个线程对共享数据操作到一半的时候，刚好CPU切换到其他线程也来操作共享变量，干扰了前一个线程的操作。  
    3、有序性:由于Java的编译器在生成字节码文件的时候会对字节码指令进行排序。另外CPU也会对交给它处理的机器指令进行排序优化提升效率，这两种指令的重新排序都会造成数据的安全性问题。  
    

**Q:Java中的锁能大概说一下吗？**  
A:(答全，不答细)。  
解决线程安全问题的不同思路，就有很多种锁。  
用乐观锁的思想，使用volatile配合自旋锁，能形成乐观锁。  
synchronized是典型的在JVM层面的悲观锁。  
而在JDK层面，JUC并发包下面实现了AQS同步器类的各个子类也都有不同的加锁办法。  
可重入锁，公平锁，非公平锁，偏向锁，读写锁等等，都可以通过这个类去自定义实现，总的来说就是思路的不同，就有不同的锁。  
最终的目的就是根据业务在并发量和数据安全上找到一个最符合需求的点来选择不同的锁。  
    

  
## 二、线程、线程池
**(核心点：线程池7参数、线程方法、线程状态)**  
**Q:有几种获得线程的方式？**  
A：常用的是四种。继承Thread类，实现Runnable接口，实现Callable获得一个可以带返回值的线程，还有就是使用线程池。  
一般在实际开发中要使用线程池。好处就是能够控制最大并发数，线程复用，方便管理线程。  
    

**Q:JDK自带的线程池有哪些？能说一下么？**  
A:JDK能够借助Executors创建4种线程池。  
1、Executors.newFixedThreadPool(5) 创建一个固定数目的线程池。  
2、Executors.newSingleThreadPool() 创建一个只有一个线程的线程池。  
3、Executors.newCachesThreadPool() 创建一个能随机扩容的线程池 在没有线程可用的时候会创建 也会清除那些60s没有使用的线程。  
4、Executors.newScheduledThreadPool() 创建一个能延迟执行或者定时执行的线程池。  
    


**Q:平时工作中用使用上面哪一种方式创建线程池？**  
A:一般都不用。这些线程池底层要么是阻塞队列的长度是Integer.maxvalue,要么线程的最大数量时Integer.maxvalue。可能会造成大量的任务堆积，都不合适。  
这些线程池都是使用了ThreadPoolExecutor去创建线程的，在实际工作中也要自己去使用这个类去创建线程池。  
    


**Q:那你说一说怎么使用这个ThreadPoolExecutor。**（考察线程池结构和7参数）  
A:好。首先呢，一般线程池主要有4部分组成。  
1、线程池管理器用来创建和管理线程。  
2、工作线程。  
3、任务接口，用于工作线程调度运行。  
4、任务队列，一般是用阻塞队列来实现，用于存放待处理的任务。  
然后在创建线程池的时候要提供7个参数。  
参数1：corePoolSize：线程池中的常驻核心线程数  
&nbsp;&nbsp;&nbsp;在创建了线程池之后，当有请求任务来了之后，就会安排池中的线程去执行请求任务，近似理解为今日当值线程。  
&nbsp;&nbsp;&nbsp;当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列中。  
参数2：maximumPoolSize : 线程池所能容纳同时执行的最大线程数，必须大于等于1。  
参数3：keepAliveTile：多余的空闲线程的存活时间，当线程池数量超过corePoolSize的时，当空闲时间达到该值得时候，多余空闲线程会被销。  
&nbsp;&nbsp;&nbsp;默认情况下，只有当线程池中的线程数大于corePoolSize的时候，keepAliveTime才会起作用，知道线程池中的线程数不大于corePoolSize。  
&nbsp;&nbsp;&nbsp;毁直到只剩下corePoolSize个线程为止。  
参数4：unit ：keepAliveTime的时间单位  
&nbsp;&nbsp;&nbsp;在规定的时间单位里面，没有新的任务了，线程没有执行，这些线程就会被销毁，直至数量降低为corePoolSize的数量。  
参数5：workQueue ： 任务队列，被提交但是还没有被执行的任务。  
参数6：threadFactory：表示生产线程池中工作线程的线程工厂，用于创建线程一般默认的即可。  
参数7：handler： 拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数时，如何来拒绝。  
    

**Q:那线程池处理线程的原理也说说吧。**（只要把7参数记清楚了，原理就简单了）  
A:   
1、在创建了线程池之后，等待提交过来的任务请求  
2、当调用execute()方法添加一个请求任务时，线程池会做以下判断：  
&nbsp;&nbsp;&nbsp;如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；  
&nbsp;&nbsp;&nbsp;如果正运行的线程数量大于或者等于corePoolSize，那么会将这个线程放入队列；  
&nbsp;&nbsp;&nbsp;如果这个时候队列满了并且正在运行的线程数量小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务  
&nbsp;&nbsp;&nbsp;如果队列满了并且正在运行的线程数量大于或者等于maximumPoolSize，那么线程池会启动饱和和拒绝策略来执行。  
3、当一个线程完成任务的时候，它会从队列中取下一个任务来执行。  
4、当一个线程无事可做超过一定时间(KeepAliveTime)时，线程池会判断：  
如果当前运行的线程数量大于corePoolSize，那么这个线程就被停掉。  
所以线程池的所有任务完成后它最终会收缩到corePoolSize的大小。  
    

**Q:那JDK哪些拒绝策略？**  
A: 默认有4种，拒绝策略要去RejectedExecutionHandler接口  
1、AbortPolicy(默认)：直接抛出RejectedException异常阻止系统正常运行。  
2、CallerRunsPolicy:“调用者运行”一种调节机制，给策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退带调用者，从而降低新任务的流量。  
3、DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务。  
4、DiscardPolicy:直接丢弃任务，不予处理也不抛出异常。如果运行任务失败，这是最好的一种方案。  
    

**Q:在项目中怎么配置线程的数量，这个由了解吗？**  
A:知道一点。首先要根据业务去判断一下我们的程序是IO密集型还是CPU密集型。  
如果是IO密集型，也就是CPU其实不紧张，只是大部分的并发可能都被IO操作阻塞，这时候建议配置尽可能多的线程数量。  
&nbsp;&nbsp;&nbsp; 一般参考公式有两个，一个是CPU核心数 * 2，另一个是 CPU核数  /  1-阻塞系数 阻塞系数在0.8~0.9之间。  
如果是CPU密集型，也就是系统主要依赖于CPU的计算，这时候应该尽可能的少配置线程的数量，一般就是创建 CPU核心数+1 个线程的线程池。  
    

**Q:线程的几种状态能说一下吗？**  
A:当线程对象创建的时候，线程处于新建状态。  
当调用start()方法之后，线程处于就绪状态。  
start方法会自己去调用run方法,如果获得了CPU，线程就处于运行状态。  
当处于运行状态的线程因为某些原因放弃了cpu的使用权，线程就会进入阻塞状态。  
&nbsp;&nbsp;&nbsp;如果是因为调用wait方法就是等待阻塞状态。
&nbsp;&nbsp;&nbsp;如果是获取同步锁失败则称为同步阻塞状态。
&nbsp;&nbsp;&nbsp;如果是调用sleep,join,JVM也会把线程置为阻塞状态。  
&nbsp;&nbsp;&nbsp;sleep超时，join线程结束等，线程会重新转入运行状态。  
线程执行完成或者爆出异常之后会进入死亡状态。  
    

**Q:线程终止几种方式。**  
A:四种。  
1、用一个volatile修饰的标志变量，使用CAS循环监控。  
2、用interrupt()方法。但是还是要监控它抛出异常或者监控终端标志，也能退出线程。  
3、用stop方法，但是会引起死锁问题，一般不用。  
4、最后就是线程正常退出也会终止线程。  
    


**Q:sleep和wait的区别**  
A：s两个方法都可以放弃CPU一段时间。不同的是sleep不会放弃锁，wait会放弃锁。  
    

**Q:说说start和run的区别。**  
A:（上面已经说了）线程启动之后会自己去掉用run方法。如果直接调用run方法，线程对象只是创建了，根本没有启动。
    

**Q:知道守护线程吗？**  
A:知道一点。一般守护线程的优先级比较低，比如说JVM的GC线程就是守护线程，这些线程为用户线程服务。当Java程序只有守护线程的时候，JVM就会关闭退出了。  
      

**Q:说几个线程的基本方法。**  
A：  
sleep方法，不会释放锁，线程进入TIMED-waiting状态。  
yield方法，让出CPU的时间片，大家一起来重新竞争。  
interrupt方法，只会改变一个中断的标志位，这个线程本身不会因为此而改变状态。但是可以根据这个状态去控制这个线程终止。  
join方法，等待另一个线程结束，一般用于获取另一个线程的计算结果。  
Object类的notify方法，唤醒在此对象监视器上等待的单个线程。如果有多个在等待，会随机唤醒一个。  
Object类的notifyAll方法，唤醒所有在这个监视器上等待的所有线程。  
Object类的wait方法，使线程进入Waiting状态，只有等待其他线程的通知或者被中断才会返回，会释放当前锁。一般用在同步代码块中。  
    

**Q:什么是线程上下文切换？**  
A:从使用CPU到不使用CPU就叫一次山下文切换。就是CPU给每一个任务一定的时间，把当前的任务状态保存下来。当再切换回来的时候，再加载回来任务状态。  
其实也就是程序计数器中的内容，也就当前线程是下一条需要指令的地址。  
    


## 三、volatile
**(核心点：CAS、ABA、Unsafe类、乐观锁、不保证原子性、原子包装类、原子引用)**

**Q:volatile关键字用过吗？谈谈你的认识。**  
A:用过。它是一种乐观锁的思想。  
volatile关键字能保证可见性跟有序性，不能保证原子性，也就是在读写数据的时候会有线程安全问题。它必须配合CAS算法才能完成乐观锁加锁的这么一个过程。  
volatile能禁止指令重排是因为使用的CPU内存屏障指令能一下子保证禁止重排和内存可见。
    

**Q：那你说一说CAS。**  
A：CAS是一种更新数据时候的原子操作，比较期望值和内存值是否一样，一样才会更新内存值，不一样就会取到内存最新值重新计算。  
用CAS操作完成自选锁，目的是为了让线程多等待一会，等待持有锁的线程释放锁，那么自己就不需要去线程状态的切换，尽量避免自己进入阻塞或者挂起状态。  
使用自旋锁的时候要注意场景是那种占用锁时间比较短，竞争不太激烈的情况下，这样才不会浪费很多的CPU资源。  
但是如果是竞争特别激烈或者是占用锁时间很长的情况，在jdk1.6中就把这种自旋锁进行了改进，叫适应性自旋锁，JVM会自己计算应该循环多少时间。  
假设上一次循环3秒钟就拿到了资源，就认为是比较容易能拿到这把锁的，自旋的时间就会长一些。  
假设上一次循环比较长时间都没拿到锁，就认为是不容易拿到锁资源，自旋的时间就会比较短，因为这种时候循环消耗的CPU资源比起上下文切换更加不划算。  
另外，JUC把一些常见数据包装成了原子操作的包装类(AtomicInteger)，还提供了原子引用能把对象数据包装成为原子操作类(AtomicReference)。开发者用起来也比较方便。  
AtomicInteger的CAS是用到了Unsafe类结合内存地址偏移量的写法，是这个native方法是C++写的用指针保证比较并交换的成功。  
    

**Q:ABA问题知道吗?说一说。**  
A:知道。ABA问题是CAS引起的。  
线程A和其他多线程同时获取数据之后，其他把数据改成了另一个，然后又改回来了。  
线程A不知道数据的历史版本的时候，就会把数据更新。  
这就要看业务需求了，假设对历史数据没有硬性要求，那就可以不用考虑ABA问题。  
如果历史数据对最终的结果有影响，那就可以使用一个版本号来解决，线程A不光比较数值，还要比较版本号是否正确。提交数据的时候附带也要提交版本号。就能解决ABA问题。  
    

**Q:CAS的缺点能总结一下吗？**  
A:缺点有三。1是会消耗CPU,2是只能保证一个共享变量的原子性,3是会引发ABA问题。  
    


## 四、synchronized
**(核心点较多，算是比较难的知识点了：使用、可重入、非公平、不可中断、monitor关联对象(计数器,owner)、monitorenter、monitorexit、ContentionList\EntryList\WaitSet\OnDeck\Owner、锁升级过程(head 标志位)、偏向锁、锁消除、锁粗化)**  
**Q:谈谈对synchronized关键字的理解。**  
A:  
一、首先是synchronized的使用，它可以作用在方法、静态方法和代码块上。  
当一个普通方法使用synchronized修饰的时候，锁住的是当前对象this。  
当一个作用于一个静态方法的时候，锁住的是Class实例，相当于类的一个全局锁，会锁住所有调用该方法的线程。  
当一个作用于一个对象实例的时候，锁住的是所有用这个对象为锁的代码块。  
二、其次是synchronized的三个特点。非公平、可重入，和不可中断。  
非公平锁:指的是在多线程竞争锁资源的时候，未必是按照先到先得的顺序，能保证更大的吞吐量。synchronized是一种悲观锁思想。  
可重入：指的是一个线程获得这个对象的锁资源的时候，再运行到还用这个对象锁住的代码的时候，可以不用继续申请锁资源，直接运行，这样的好处是避免死锁。  
不可中断：指的是一个线程获得锁资源之后，另一个线程想要获得这个对象的锁，就必须处于阻塞或者是等待状态，并且后一个线程不能被中断。  
synchronized就是属于不可被中断的锁，后面线程的状态是BLOCK阻塞。而Lock对象，如果使用的是lock()方法，也是不可中断的，并且线程的状态是Waiting。
使用trylock()方法的时候，是可以被中断的。  
三、还有就是synchronized加锁的原理。  
锁对象不能完成synchronized的加锁操作，他的关联的对象monitor才是真正的锁，这个对象也叫监视器。这个监视器有两个重要的变量，
一个是锁的拥有者是哪一个线程owner,另一个是记录获取锁对象次数的计数器recursions。  
每个同步代码块在编译之后都会被编译在monitorenter和monitorexit两个字节码指令之间。分别代表了进入同步代码块和退出。  
monitorenter进入的逻辑：当JVM执行某个线程的某个同步方法的monitorenter的时候，回去尝试获取当前对象对应的monitor的所有权。  
如果没有线程占用这个monitor，计数器的值是0，代表目前锁对象没有线程拥有，线程就进入monitor,修改计数器和owner的值。  
如果线程已经拥有了这个锁对象的monitor,意味着是重入monitor,就会把计数器的值加1。  
如果其他线程已经占用了这个monitor,那么当前尝试获取monitor的操作就会被阻塞，直到monitor的计数器变成0，才会重新尝试获取这个monitor的所有权。  
monitorexit退出的逻辑：每次退出将计数器的值减一，减到0的时候就退出monitor,其他被这个monitor阻塞的线程就可以去尝试获得这个monitor的所有权。  
四、最后是两个细节。一个是同步方法在编译的时候不会产生monitorenter等指令，但是这个方法会被标记成ACC_SYNCHRONIZED,会隐式的调用monitorenter和moniterexit。  
另一个细节是，JVM在方法的结束地方和异常表里面都有monitorexit，保证了方法一定能够释放锁，这也是synchronized避免死锁的原因。  
    


**Q: synchronized的底层处理细节知道吗？**  
A: 大致知道一点。(别答的太自信，让说锁竞争、锁释放、锁等待的过程就GG)  
所有请求锁资源的线程都会被放在一个先进后出的竞争队列中(ContentionList),由于这个队列会被频繁的CAS访问，为了降低这个竞争队列队尾元素的竞争，JVM会将一部分线程移动到EntryList这个队列中。  
当Owner线程在释放锁资源之后，竞争队列的一部分线程就会进入到EntryList中，并且指定了一个线程作为OnDeck线程。  
OnDeck线程不直接把锁交给OnDeck，而是把竞争锁的权利给OnDeck，OnDeck需要重新竞争锁。  
当某一个线程获取到了锁资源的时候，他就成为Owner，没有得到锁资源的线程就继续放在EntryList中。  
如果Owner线程被wait()方法阻塞，它就会被转移到WaitSet的阻塞队列中，直到它被唤醒之后，会重新把它放到EntryList中。  
    

**Q:知道synchronized升级的过程吗？简单说一下。**  
A:知道一点。  
synchronized会有一个： 不加锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁  的过程。  
要先说一个对象头的概念。  
Java对象由三部分构成，对象头，实例数据和补全数据。其中对象头里面就存储了一些对象自身在运行时的数据。  
偏向锁就是偏心的意思，其实就是发现在大多数情况下，总是由同一个线程多次获得锁对象，为了让线程获得锁的代价更低，就引出了偏向锁。  
从无锁升级为偏向锁：  
当有线程第一次访问同步代码块的时候，就会先把synchronized变成偏向锁。  
会先把锁住对象的对象头的标志位设置成01，也就是偏向锁模式。  
同时使用CAS操作吧这个获取到锁的线程ID记录在对象头的mark word里面，  
操作成功了以后，只要是这个线程进入这个锁的代码块的时候，就可以不就行任何操作，提高效率。  
偏向锁释放的时候需要让全部的线程停下来，才能撤销偏向锁，把mark word的标志位修改成00轻量级锁或者01无锁的状态。  
偏向锁适用于一个线程反复得到同一把锁的情况，有同步无竞争的程序使用的时候性能最好。  
  
从偏向锁升级为轻量级锁：  
关闭偏向锁或者是多个线程争抢偏向锁的时候都会升级成轻量级锁。  
在无锁状态下，也就是偏向锁刚刚推出，没有线程获取到当前锁的时候，  
JVM会在当前方法的栈帧中创建一个锁记录的空间Lock record，并且把mark work拷贝到锁记录空间中，  
拷贝完成后，线程就会进行CAS操作尝试把锁对象的mork word更新成指向Lock record的指针，成功的话就代表当前线程持有了这个锁，修改标志位，执行代码。  
如果失败就表示锁已经被别的线程抢占了，这时候锁就需要升级成重量级的锁，锁对象的标志位就会10，后面等待的线程就会进入阻塞状态。  
轻量级锁适用于多个线程交替执行的情况，不争抢，只是依次轮流获取锁的情况。  
  
最后还有一个锁消除和锁粗化的问题。  
锁消除，指的是锁住的对象不会存在多线程竞争问题，synchronized没有必要存在。JIT即时编译器就会把锁消除。  
锁粗化：JVM探测到一连串重复的操作中，都有用到monitorenter和monitorexit，就会把同步的范围扩大到这些重复操作的外面去。  
    

**Q:synchronized和Lock的区别，平时使用synchronized都应该注意什么？**  
A:区别主要有：  
Synchronized是关键字，JVM直接支持。Lock是一个接口。一个是JVM层面的，一个是JDK层面的。  
synchronized会自动释放锁，即时抛出异常也会释放，而Lock必须手动释放锁。  
synchronized是不可中断的，而Lock使用lock方法是不可中断的，使用trylock是可以中断的。  
Lock可以知道当前线程有木有拿点锁，synchronized不可以。  
synchronized可以锁住方法和代码块，而Lock只能锁住代码块。  
Lock可以使用读锁提高多线程读取数据的效率。  
synchronized是非公平锁，使用ReentrantLock可以控制是否公平。  
平时使用synchronized的时候要注意：  
1、减少synchronized的范围。代码减少，减少执行时间，减少锁的竞争。可能用轻量级锁或者自旋锁就搞定了，避免升级到重量级锁。   
2、降低synchronized锁的粒度。几个无关功能使用不同的锁对象。比如：linkedBlockQueue在入队和出队的时候使用的就是两把锁。  
3、读写分离。读的时候不加锁，写的时候加锁。  
    


## 五、AQS框架
**(核心点：独占锁、共享锁、state标志、双向链表构成的一个等待队列 FIFO 、AQS实现类、阻塞队列)**  
(这大概就是一道送命题吧，能回答多少看运气了，主要不是问这个AQS，想问的是整个Lock架构。生死由命，富贵在天！)  
  
**Q:AQS知道吗？简单说一下。**  
A:知道一点。  
AQS叫抽象队列同步器，Doug Lea 大神写的一套多线程访问共享资源的同步器框架。对比synchronized它不是jvm层面的锁，而是JDK层面的锁规范。  
许多类都是实现了这个抽象类的，ReentrantLock Semaphore CountDownLatch。  
这个抽象类主要由两部分组成。一个用volatile修饰的int变量state标识，还有一个先进先出的线程等待队列（FIFO），这个队列的底层是一个双向链表。  
  
AQS指定了两种锁的方式，独占锁，比如ReentrantLock 和共享锁 比如Semaphore和CountDownLatch。  
它只是规范了锁的结构，具体的的锁细节交给他的子类去实现，获取资源的方式，释放锁的方式等等。  
举几个例子来说：  
ReentrantLock：他是一种独占锁。 初始状态state是0，代表锁定状态。  
lock()方法会调用tryAcquire独占锁并且state+1,其他线程tryAcquire就会失败，直到线程使用unlock方法让state被减为0,其他线程才能竞争锁资源。state在lock时候加一跟在unlock时候减一就是可重入的实现。  
  
CountDownLatch:它是一种共享锁。  
整个任务被划分成N个子线程去执行，这几个线程是并行的，调用countDown()一次，state就会减一，这个减一过程还是用CAS操作的，等到所有线程都执行完的时候，state的值才会变成0，然后才回到主调用线程。  
AQS也允许独占锁和共享锁并存，也就是tryAcquire - tryRelease这一组独占锁需要实现的方法，跟tryAcquireShared-tayReleaseShared这组共享锁需要实现的方法，
在这种锁中也实现了。比如说读写锁就是这样的。ReentrantReadWriteLock。  
    


**Q:刚刚你说到了ReentrantLock和CountDownLatch这两个并发工具类，你能说说别的常用的并发工具类吗？**（就是要听知识掌握的宽度）  
A:刚刚说的是实现思路，在用的时候一般是这几个CountDownLatch，CyclicBarrier，Semaphore。  
CountDownLatch是一种倒计时锁，让某个线程阻塞，直到另外的一些线程完成之后才被唤醒。主要是await方法等待被唤醒，countDown在线程执行完任务之后把state减一。  
CyclicBarrier翻译过来叫循环屏障，就是所有线程都等待，直到所有线程都准备好进入await方法之后，所有线程才开始执行。  
Semaphore 可以控制同时访问线程的个数acquired一个，release释放一个。一般用在多线程争抢资源的时候。  
它设计的两个目的是：一、用于多个共享资源的互斥作用，还有就是并发线程的控制数。  
    


**Q:阻塞队列能说说吗？**  
A:BlockQueue,先进先出。  
对列是空的时候，想消费元素，这个线程会被阻塞。  
队列是满的时候，想添加元素，这个线程也会被阻塞。  
一般使用offer和poll这组操作，使用add和remove会抛出异常。  
Java实现的阻塞队列比较多。  
1、ArrayBlockingQueue : 又数组结构组成的有界阻塞队列。new ArrayList就是new了一个初始值是10的数组。  
2、LinkedBlockingQueue ： 由链表构成的有界阻塞队列（但大小默认值为Integer.MAX_VALUE 这个数字特别大21亿，基本无界） 慎用。  
3、PriorityBlockingQueue : 支持优先级排序的无界阻塞队列。  
4、DelayQueue ： 使用优先级队列实现的延迟无界阻塞队列。  
5、SynchronousQueue : 不存储元素的阻塞队列，也即单个元素的队列。有且仅有一个。  
6、LinkedTransferQueue : 由链表结构组成的无界阻塞队列。  
7、LinkedBlockingDeque : 由链表结构组成的双向阻塞队列。  

(后面再问就说不知道，加锁过程释放锁过程能问到怀疑人生。synchronized的也一样。)   
Q:                
A:                