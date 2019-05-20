package ru.bmstu.lab8.model;

import ru.bmstu.lab8.enums.OperationType;

import java.time.LocalTime;

public class Client {

    private Long id;

    private LocalTime startTime;

    private LocalTime stopTime;

    private LocalTime serviceTime;

    private OperationType operation;

    private Boolean requestState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public Boolean getRequestState() {
        return requestState;
    }

    public void setRequestState(Boolean requestState) {
        this.requestState = requestState;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
