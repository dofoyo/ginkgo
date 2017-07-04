package com.rhb.ginkgo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final List<Greeting> list = new ArrayList<Greeting>();

    @GetMapping("/greeting")
    public ResponseContent<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting g = new Greeting(counter.incrementAndGet(), String.format(template, name));
    	list.add(g);
        return new ResponseContent<Greeting>("100","ok",g);
    }
    
    @GetMapping("/greetings")
    public ResponseContent<List<Greeting>> greetings() {
        return new ResponseContent<List<Greeting>>("100","ok",this.list);
    }
    
}
