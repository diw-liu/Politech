package com.example.demo.services;

import com.example.demo.enums.Status;
import com.example.demo.algo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    Status status;
    public JobService(){
        status = Status.IDLE;
    }

    public Status startJob(){
        if(status != Status.IDLE) {
            return Status.FAILED;
        }
        setStatus(Status.PROCESSING);

        return getStatus();
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

}