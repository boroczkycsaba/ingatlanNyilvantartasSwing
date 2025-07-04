package hu.ingatlan.kereso.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Böröczky Csaba
 */
public class IngatlanMeglevoAdatok implements Serializable {

    private IngatlanMeglevoAdatok() {

    }

    public static List<IngatlanAdat> ingatlanAdatokLetrehozasa() {
        List<IngatlanAdat> ingatlanAdatok = new ArrayList<>();
        ingatlanAdatok.add(createIngatlanAdat("Magyar Endre", "125/2", 170, 75000000));
        ingatlanAdatok.add(createIngatlanAdat("Kiss Lilla", "3181/1", 110, 45000000));
        ingatlanAdatok.add(createIngatlanAdat("Tóth Eszter", "11055", 80, 32000000));
        ingatlanAdatok.add(createIngatlanAdat("Fekete Benedek", "1101/1", 100, 40000000));
        ingatlanAdatok.add(createIngatlanAdat("Nagy Andrea", "2345", 135, 59000000));

        return ingatlanAdatok;
    }

    private static IngatlanAdat createIngatlanAdat(String tulajdonos, String helyrajziSzam, long alapterulet, long becsultErtek) {
        return new IngatlanAdat(tulajdonos, helyrajziSzam, alapterulet, becsultErtek);
    }

}
