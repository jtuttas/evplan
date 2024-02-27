public class getBeitrag {
    public static double getBeitrag(int alter, int mitgliedsjahre) {
        double beitrag = 0;
        if (alter < 18) {
            beitrag = 30.0;
        } else if (alter <= 12) {
            beitrag = 15.0;
        } else {
            beitrag = 50.0;
        }
        if (mitgliedsjahre > 5 && mitgliedsjahre <= 15) {
            beitrag = beitrag * 0.9;
        } else if (mitgliedsjahre > 15) {
            beitrag = beitrag * 0.8;
        }
        return beitrag;
    }
}
