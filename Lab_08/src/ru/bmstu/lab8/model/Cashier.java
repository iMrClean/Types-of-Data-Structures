package ru.bmstu.lab8.model;

import ru.bmstu.lab8.enums.CashierState;
import ru.bmstu.lab8.enums.CashierType;

public class Cashier {

    private Long id;

    private Client client;

    private CashierType type;

    private CashierState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CashierType getType() {
        return type;
    }

    public void setType(CashierType type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CashierState getState() {
        return state;
    }

    public void setState(CashierState state) {
        this.state = state;
    }
}
