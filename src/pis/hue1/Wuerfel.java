package pis.hue1;

/**
 * Wuerfel ist ein Art von kodierung das prinzip ist der Verschiebung von zeichen
 * @author winnie
 * @version java 15.0.1
 */
public class Wuerfel implements Codec {
    String schluessel;

    /**
     * Das ist unsere Konstruktor
     * @param schluessel
     */
    Wuerfel(String schluessel){
        this.schluessel=schluessel;
    }

    /**
     * ubergibt als parameter der klartext
     * und gibt der geheimTest zurück
     * @param geheimtext ist der Parameter unsere Mehode
     * @return es wird der dekodierte text zurueckgegeben
     */
    @Override
    public String dekodiere(String geheimtext) {
        int size=this.schluessel.length();
        int[]arr=permutation();
        char []dekodiere = new char [geheimtext.length()];
        int count=0;

        for(int i=0; i<size;i++){
            for(int j=arr[i];j<geheimtext.length();j+=size){
                dekodiere[j] = geheimtext.charAt(count);
                count++;
            }
        }

        return charAtoString(dekodiere);
    }

    /**
     * ubergibt als parameter ein arrray von Character
     * und gibt dieser Array in type String zurueck
     * @param a array of zeichen
     * @return es wird ein String zurueck gegeben
     */
    private String charAtoString(char []a){
        String result="";
        for(int i=0; i<a.length; i++){
            result += a[i];
        }
        return  result;
    }

    /**
     * bekommt als parameter der Klartext.
     * der erste for schleife laueft von unsere variable i bis der lenge unserre
     * schluessel. der zweite for schleife laueft von i bis der laenge unsere Klartext
     * dann haben wir ein String answer der am anfang leer ist und bei zweite for schleife durchlaueft
     * inkrementiert sich von der charakter die an der postion von unsere j sich finden
     * @param klartext
     * @return es wird der kodierte text zurueckgegeben
     */

    @Override
    public String kodiere(String klartext) {
       // String answer=" ";
        StringBuffer answer=new StringBuffer("");

        int size=this.schluessel.length();
        int[]arr=permutation();
        for(int i=0; i<size;i++){
            for(int j=arr[i];j<klartext.length();j+=size){
               // answer=answer+klartext.charAt(j);
                answer.append(klartext.charAt(j));
            }
        }
        return answer.toString();
    }

    /**
     * @return es wird der Schluessel oder loesung wort zuruck geben
     */
    @Override
    public String gibLosung() {
        return this.schluessel=schluessel;
    }

    /**
     * diese Methode setzt der Schluessel
     * @param schluessel
     * @throws IllegalArgumentException fehler
     */
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        this.schluessel=schluessel;
        if(schluessel.length()>26){
            throw new IllegalArgumentException("Schlueßel zu lange!!!");
        }
    }

    /**
     * diese methode nimmt ein zeichen als parametter
     * und gibt sein entsprechende groeße im bezug auf die andere zeichen.
     * @param c ist der parameter
     * @return est wird ein int zahl zueruckgegeben
     */
    private int findPositionChar(char c){
        String schluessel1 = schluessel.toUpperCase();
        int count=0;
        for(int i=0;i<schluessel.length();i++){
            if(schluessel1.charAt(i)<c){
                count++;
            }
        }
        return count;
    }

    /**
     * Diese Methode Klassifiziert der Schluessel und an ihrer position attribut eine zahl entsprechend ihrer
     * groeße nach den anderen zeichen
     * @return es wird ein array von int zueruckgegeben
     */
    private  int[] findPositionLoesungwort(){
        String schluessel1 = schluessel.toUpperCase();
        int tab[]=new int[schluessel.length()];
        for(int i=0;i<schluessel.length();i++){
                tab[i]=findPositionChar(schluessel1.charAt(i));
        }
        for(int i=0;i<schluessel.length()-1;i++){
            for(int j=i+1;j<schluessel.length();j++){
                if(tab[i]!=tab[j]){
                    tab[i]=tab[i];
                }
                if(tab[i]==tab[j]){
                    tab[i]=tab[i];
                    tab[j]=tab[j]+1;

                }
            }
        }
        return tab;
    }

    /**
     * @return es wird ein Array von position der Sortierte Character mit ihr zugehörigen wert zurueckgegeben
     */
    public  int[]permutation(){
        int[] p=findPositionLoesungwort();
        int tab[]=new int[p.length];
        for(int i=0;i<p.length;i++){
            tab[p[i]]=i;
        }
        return tab;
    }



}
