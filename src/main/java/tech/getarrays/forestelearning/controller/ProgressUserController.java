package tech.getarrays.forestelearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.forestelearning.model.ProgressUser;
import tech.getarrays.forestelearning.wrapper.ProgressWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/progress")
public interface ProgressUserController {

    @GetMapping("/getProgressByUser/{id}")
    ResponseEntity<List<ProgressUser>> getProgressByUser(@PathVariable Long id);

    @GetMapping("/getProgress")
    ResponseEntity<List<ProgressWrapper>> getProgress();

    @PostMapping(path = "/add")
    ResponseEntity<String> addProgress(@RequestBody(required = true) Map<String, String> requestMap);
}
