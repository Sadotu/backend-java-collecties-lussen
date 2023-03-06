import java.util.HashMap;

public class Translator {

    private HashMap<Integer,String> numericAlpha;

    public Translator(String[] alphabetic, Integer[] numeric) {
        numericAlpha = new HashMap<Integer,String>();
        for (int i = 0; i < alphabetic.length; i++) {
            numericAlpha.put(numeric[i], alphabetic[i]);
        }
    }

    public String translate(Integer integer) {
        if(this.numericAlpha.containsKey(integer))
            return this.numericAlpha.get(integer);
        else {
            return null;
        }
    }
}


