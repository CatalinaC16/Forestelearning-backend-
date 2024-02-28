package tech.getarrays.forestelearning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.getarrays.forestelearning.model.ProgressUser;
import tech.getarrays.forestelearning.wrapper.ProgressWrapper;

import java.util.List;
import java.util.Map;

public interface ProgressService {

    ResponseEntity<List<ProgressUser>> getProgressByUser(@PathVariable Long id);

    ResponseEntity<List<ProgressWrapper>> getProgress();

    ResponseEntity<String> addProgress(@RequestBody(required = true) Map<String, String> requestMap);
}
