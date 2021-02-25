package pis.hue1;

/**
 *  Caesar ist ein Verschluesselungsverfahren, das auf der
 *  monoalphabetischen Substitution basiert ist.
 * @author winnie
 * @version java 15.0.1
 */
public class Caesar implements Codec{
    private static String  schluessel;
    char []alphabet={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char []Alphabet={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    /**
     * diese methode gibt der kodierte text zueruck
     * @param Klartext ist der String(Wort) der Kodiere wird
     * @return es wird der kodierte text zueruck gegeben
     */
    @Override
    public String kodiere(String Klartext) {
        int size=schluessel.length();
        String ende="";
        for(int i=0;i<Klartext.length();i++){
            ende+=suchCharKodiere(Klartext.charAt(i),size)+"";
        }
        return ende;
    }

    /**
     * diese methode gibt der dekodierte text zueruck
     * @param geheimtext ist der String(Wort) der dekodierte wird
     * @return es wird der dekodierte text zueruck gegeben
     */
    @Override
    public String dekodiere(String geheimtext) {
        int size=schluessel.length();
        String ende="";
        for(int i=0;i<geheimtext.length();i++){
            ende+=suchChardekodiere(geheimtext.charAt(i),size)+"";
        }
        return ende;
    }

    /**
     * diese Methode gib der Schluessel zurueck
     * @return es wird der Schluessel Wort zurueck gegeben
     */
    @Override
    public String gibLosung() {

        return this.schluessel;
    }

    /**
     * diese Methode setzt der Schluessel
     * @param schluessel das Loesungsword
     * @throws IllegalArgumentException den Fehler
     */
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {

        this.schluessel=schluessel;
        if(schluessel.length()>26){
            throw new IllegalArgumentException("Schlueßel zu lange!!!");
        }

    }

    /**
     * diese methode nimmt zwei parameter ein  char un der size der schluessel(loesungwort)
     * und gibt die neue wert von unserem char  c nachdem er verschieb wurde.
     * wenn der character c ein leer zeichen oder ein zahlen wird der character nicht verandert.
     * @param c ist ein char
     * @param size ist die laenge der Schluessel
     * @return es wird ein char zueruckgegeben
     */
    private char suchCharKodiere(char c,int size){
        for(int i=0;i<26;i++){
            if(c==alphabet[i]||c==Alphabet[i]){
                int b=((i+size)%26);
                return c==alphabet[i]?alphabet[b]:Alphabet[b];
            }
        }
        return c;
    }

    /**
     * diese methode nimmt zwei parameter ein  char un der size der schluessel(loesungwort)
     * und gibt die neue wert von unserem char  c nachdem er verschieb wurde.
     * wenn der character c ein leer zeichen oder ein zahlen wird der character nicht verandert.
     * wir machen noch ein %26 um immer der bereich der Alphabet sich zu finden
     * @param c ist ein char
     * @param size ist die laenge der Schluessel
     * @return es wird ein char zueruckgegeben
     */
    private char suchChardekodiere(char c,int size){
        for(int i=0;i<26;i++){
            int b=0;
            if(c==alphabet[i]||c==Alphabet[i]){
                b=(i-size)%26>0?(i-size)%26:((i-size)+26)%26;
                return c==alphabet[i]?alphabet[b]:Alphabet[b];
            }
        }
        return c;
    }


}
