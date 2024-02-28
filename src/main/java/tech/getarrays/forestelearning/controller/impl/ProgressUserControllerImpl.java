package tech.getarrays.forestelearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.forestelearning.constants.UserConstants;
import tech.getarrays.forestelearning.controller.ProgressUserController;
import tech.getarrays.forestelearning.model.ProgressUser;
import tech.getarrays.forestelearning.service.ProgressService;
import tech.getarrays.forestelearning.utils.UserUtils;
import tech.getarrays.forestelearning.wrapper.ProgressWrapper;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProgressUserControllerImpl implements ProgressUserController {

    @Autowired
    ProgressService progressService;
    public ResponseEntity<List<ProgressUser>> getProgressByUser(@PathVariable Long id){
        try {
            return progressService.getProgressByUser(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<ProgressWrapper>> getProgress(){
        try {
            return progressService.getProgress();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addProgress(Map<String, String> requestMap) {
        try {
            return progressService.addProgress(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_ADDING_PROGRESS,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
