package com.practice.myappvs.controller;

import com.practice.myappvs.dao.MessageRepo;
import com.practice.myappvs.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
public class MessageController {

    @Autowired
    private MessageRepo msgrepo;

    @GetMapping("/check")
    public String Checking(){
        return "Hello This is yogi";
    }
    @PostMapping("/addmsg")
    public ResponseEntity<Message> submitMessage(@RequestBody Message newMsg){
        Message savedMsg = msgrepo.save(newMsg);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMsg);
    }

    @GetMapping("/getmsg")
    public ResponseEntity<Message> getMsg(@PathVariable Long id){
        Optional<Message>op=msgrepo.findById(id);
        if(op.isPresent()){
            Message myMsg=op.get();
            return ResponseEntity.ok(myMsg);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallmsg")
    public ResponseEntity<List<Message>> getAllMsg(){
        List<Message> myList = msgrepo.findAll();

        if (myList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(myList);
    }

    @PutMapping("/updatemsg/{id}")
    public ResponseEntity<Message> updateMsg(@PathVariable Long id, @RequestBody Message newMessage) {
        Optional<Message> existingMessageOptional = msgrepo.findById(id);

        if (existingMessageOptional.isPresent()) {
            Message existingMessage = existingMessageOptional.get();
            existingMessage.setContent(newMessage.getContent()); // Assuming you have a 'content' field
            // Update other fields as necessary
            Message updatedMessage = msgrepo.save(existingMessage);
            return ResponseEntity.ok(updatedMessage);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/updatemsg")
//    public void updateMsg(@RequestBody String newMsg){
//
//    }
    @Transactional
    @DeleteMapping("/deletemsg")
    public ResponseEntity<String> deleteMessage(@RequestParam Long id) {
        Optional<Message> op = msgrepo.findById(id);
        if (op.isPresent()) {
            Message myMsg = op.get();
            msgrepo.delete(myMsg);
            return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
    }

}
