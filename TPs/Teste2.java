import java.util.ArrayList;
import java.util.List;

public class Teste2 {
    private static Double euclidianDistance(Integer[] value1, Double[] value2){
        return Math.sqrt(Math.pow(value1[0] - value2[0], 2) + Math.pow(value1[1] + value2[1], 2));
//        return Math.abs((value1[0] - value2[0]) + (value1[1] - value2[1]));
    }

    public static void main(String[] args){
        List<Integer[]> values = new ArrayList<>();
        values.add(new Integer[]{-5, 2});
        values.add(new Integer[]{5, -2});
        values.add(new Integer[]{0, 2});
        values.add(new Integer[]{0, -2});
        values.add(new Integer[]{-5, 1});
        values.add(new Integer[]{-5, -2});
        values.add(new Integer[]{5, 2});
        values.add(new Integer[]{5, -1});

        int iterations = 2;
        List<Double[]> centroids = new ArrayList<>();
        centroids.add(new Double[2]);
        centroids.get(0)[0] = Double.valueOf(values.get(2)[0]);
        centroids.get(0)[1] = Double.valueOf(values.get(2)[1]);

        centroids.add(new Double[2]);
        centroids.get(1)[0] = Double.valueOf(values.get(3)[0]);
        centroids.get(1)[1] = Double.valueOf(values.get(3)[1]);

        List<List<Integer[]>> groups = new ArrayList<>();
        for(int j=0; j<iterations; j++) {
            groups.add(new ArrayList<>());
            groups.add(new ArrayList<>());

            values.forEach(i -> {
                System.out.println(i[0] + " " + i[1] + " | (" + centroids.get(0)[0] + " " + centroids.get(0)[1] + " || " + centroids.get(1)[0] + " " + centroids.get(1)[1] + ")");
                System.out.println(euclidianDistance(i, centroids.get(0)) + " | " + euclidianDistance(i, centroids.get(1)));
                if (euclidianDistance(i, centroids.get(0)) < euclidianDistance(i, centroids.get(1))) {
                    groups.get(0).add(i);
                } else {
                    groups.get(1).add(i);
                }
            });
            System.out.println("\n\n\n");
            centroids.get(0)[0] = Double.valueOf(groups.get(0).stream().map(i -> i[0]).reduce(0, Integer::sum))/(groups.get(0).size() == 0 ? 1 : groups.get(0).size());
            centroids.get(0)[1] = Double.valueOf(groups.get(0).stream().map(i -> i[1]).reduce(0, Integer::sum))/(groups.get(0).size() == 0 ? 1 : groups.get(0).size());

            centroids.get(1)[0] = Double.valueOf(groups.get(1).stream().map(i -> i[0]).reduce(0, Integer::sum))/(groups.get(1).size() == 0 ? 1 : groups.get(1).size());
            centroids.get(1)[1] = Double.valueOf(groups.get(1).stream().map(i -> i[1]).reduce(0, Integer::sum))/(groups.get(1).size() == 0 ? 1 : groups.get(1).size());

            if(j < iterations-1) {
                groups.remove(1);
                groups.remove(0);
            }
        }

        groups.forEach(i -> {
            i.forEach(j -> System.out.println(j[0] + " " + j[1]));
            System.out.println("\n--x---x--\n");
        });


    }
}
