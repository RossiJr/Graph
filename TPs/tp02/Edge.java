package tp02;

public class Edge {
    private int origin;
    private int destiny;
    
    public int getDestiny() {
        return destiny;
    }
    public void setDestiny(int destiny) {
        this.destiny = destiny;
    }
    public int getOrigin() {
        return origin;
    }
    public void setOrigin(int origin) {
        this.origin = origin;
    }

    Edge(int orig, int dest){
        origin = orig;
        destiny = dest;
    }

    public void printClass(){
        System.out.print(origin+" "+destiny);
    }
}
