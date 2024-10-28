//package com.handson.chatbot.controller;
//
//
//import com.handson.chatbot.service.ImdbService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/bot")
//public class BotController {
//
//    @Autowired
//    ImdbService amazonService;
//
//    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
//    public ResponseEntity<?> getProduct(@RequestParam String keyword)
//    {
//        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
//    }
//}

package com.handson.chatbot.controller;

import com.handson.chatbot.service.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    ImdbService imdbService; // Updated from amazonService to imdbService

    // Updated endpoint path from "/amazon" to "/imdb"
    @RequestMapping(value = "/imdb", method = RequestMethod.GET)
    public ResponseEntity<?> getImdbData(@RequestParam String keyword) {
        try {
            // Fetch HTML content from IMDb
            String html = imdbService.getProductHtml(keyword);
            // Parse HTML content to find relevant information
            String parsedData = imdbService.parseProductHtml(html);
            return new ResponseEntity<>(parsedData, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error fetching data from IMDb", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
