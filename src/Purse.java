import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Purse {
    private Map<Denomination, Integer> cash;
    private List<Observer> observers; // Observer pattern

    public Purse() {
        cash = new HashMap<>();
        observers = new ArrayList<>();
    }

    // Observer methods
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void add(Denomination type, int num) {
        cash.merge(type, num, Integer::sum);
        notifyObservers(); // Notify on change
    }

    public double remove(Denomination type, int num) {
        cash.put(type, cash.get(type) - num);
        notifyObservers(); // Notify on change
        return type.amt() * num;
    }

    public double getValue() {
        return cash.entrySet().stream()
                .mapToDouble(e -> e.getKey().amt() * e.getValue())
                .sum();
    }

    public Map<Denomination, Integer> getCash() {
        return new HashMap<>(cash);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Purse contents:\n");
        cash.forEach((den, count) ->
                sb.append(String.format("%d x %s ($%.2f each)\n",
                        count, den.name(), den.amt())));
        return sb.toString();
    }
}