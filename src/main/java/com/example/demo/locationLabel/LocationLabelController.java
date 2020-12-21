package com.example.demo.locationLabel;

import com.example.demo.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationLabelController {

    private LocationLabelRepository repository;

    @Autowired
    public LocationLabelController(LocationLabelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/locationLabel")
    public List<LocationInfo> getAll() {
        WebSocketServer.sendMessage("台州-乐清 SDH");
        return repository.findAll();
    }
}