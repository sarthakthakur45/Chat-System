/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Sunil
 */
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
class Server{
	public static void main(String args[]) throws IOException{
		ServerSocket ss = new ServerSocket(9999);
		for(int i=0;i<10;i++) {
			Socket s = ss.accept();
			Chat c = new Chat(s,i);
			System.out.println("New Chat thread Started which is "+i);
			c.start();
		}
	}
}
class Chat extends Thread{
	Socket soc;
	int id;
	String name;
	Chat(Socket s,int id) {
		this.soc = s;
		this.id = id;
	}
	public void run(){
		// for(int i=0;i<10;i++) {
		// 	System.out.println("Hi"+i);
		// }
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		PrintWriter pr = new PrintWriter(soc.getOutputStream(),true);
		pr.println("Who Are You:");
		this.name = br.readLine();
		Information info = new Information(this.id,this.name,this.soc,br,pr);
		Option options = new Option();
		Hashuse.clients.putIfAbsent(this.name, info);
		for(int i=0;i<100;i++) {
		String option = br.readLine();
		System.out.println(option);
		options.check(option,info);

		if(i==99) {
			i=0;
		}
		}
	}
	catch(Exception e) {
		System.out.println(e);
                Hashuse.clients.remove(this.name);
                System.out.println(Hashuse.clients);
	}
	}
}
class Hashuse {
	public static HashMap<String, Information> clients = new HashMap<>();
}
class Option {
	public void check(String opt,Information info) throws IOException{
		System.out.println(opt);
		switch(opt){
			case "Send Message": System.out.println("4");
                            String tosendclient = info.br.readLine();
			System.out.println(tosendclient);
			Information tosendinfo = Hashuse.clients.get(tosendclient);
			System.out.println(Hashuse.clients.get(tosendclient));
			String msg = info.br.readLine();
                        System.out.println("4");
			System.out.println("Done3");
                        
                        tosendinfo.pr.println("Incoming Message");
                {
                    try {
                        Thread.sleep(100);
                        System.out.println("5");
			tosendinfo.pr.println(info.name);
                        Thread.sleep(100);
                        System.out.println("6");
                        tosendinfo.pr.println(msg);
                        System.out.println("7");
                        System.out.println("Done all the way");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
			break;

			case "Send Who Is Online":info.pr.println("Sending Who Is Online");
                        System.out.println("Sending who is online");
                        Set<String> keys = Hashuse.clients.keySet();
                        String keystr = String.join(",", keys);
			info.pr.println(keystr);
                        System.out.println(keystr);
			break;
                        
                        case "Typing":String whotosend = info.br.readLine();
                        Information tosend = Hashuse.clients.get(whotosend);
			System.out.println(tosend.name);
                        
                        tosend.pr.println("Typing");
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        tosend.pr.println(info.name);
                        System.out.println(info.name+" is typing to "+whotosend);
                        System.out.println("Typing done");
                            break;
                        case "Broadcast":System.out.println("Inside Broadcast");
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                            String tobroadcast = info.br.readLine();
                            HashMap temp = Hashuse.clients;
                        Iterator iterator = temp.entrySet().iterator();
                        while (iterator.hasNext()) {
                             Map.Entry me2 = (Map.Entry) iterator.next();
//                          System.out.println("Key: "+me2.getKey() + " & Value: " + me2.getValue());
                            // sending each client that is active a message as sending message
                            Information tempo = (Information) me2.getValue();
                            tempo.pr.println("Incoming Message");
                             try {
                                Thread.sleep(100);
                                System.out.println("5");
                                tempo.pr.println(info.name);
                                Thread.sleep(100);
                                System.out.println("6");
                                tempo.pr.println(tobroadcast);
                                System.out.println("7");
                                System.out.println("Done all the way");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        } 
		}
	}
}
class Information {
	int id;
	String name;
	Socket soc;
	BufferedReader br;
	PrintWriter pr;
	Information(int id, String name, Socket soc, BufferedReader br, PrintWriter pr) {
		this.id = id;
		this.name = name;
		this.soc = soc;
		this.br = br;
		this.pr = pr;
	}
}