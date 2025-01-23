import java.util.HashMap;
import java.util.Map;

public class Purse {
    private Map<Denomination, Integer> cash;

    public Purse() {
        cash = new HashMap<>();
    }

    public void add(Denomination type, int num) {
        cash.merge(type, num, Integer::sum);
    }

    public double remove(Denomination type, int num) {
        cash.put(type, cash.get(type) - num);
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
