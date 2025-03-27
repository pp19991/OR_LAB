package ui;

import java.math.BigDecimal;
import java.util.Objects;

public class State {
    private String state;
    private State parent;
    private BigDecimal cost;

    public State(String state,State parent,BigDecimal cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return Objects.equals(state, state1.state) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

}
