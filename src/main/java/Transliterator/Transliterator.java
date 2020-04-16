package Transliterator;

import org.apache.tomcat.util.bcel.classfile.ConstantUtf8;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Transliterator {

    private static final ArrayList<String[]> LETTERS_TRANSLITE_VARS;


    public static ArrayList<StringBuilder> transliterate(String name) {

        ArrayList<StringBuilder> transliterateNames = new ArrayList<>();
        transliterateNames.add(new StringBuilder());

        for (int i = 0; i < name.length(); i++) {

            String[] letters = letters_vars.get(Character.toString(name.charAt(i)));
            System.out.println(letters[0]);

            if (letters.length == 1) {
                for (StringBuilder transName : transliterateNames) {
                    transName.append(letters[0]);
                }
            }
            if (letters.length > 1) {
                int size = transliterateNames.size();
                for (int j = 1; j < letters.length; j++) {
                    for (int k = 0; k < size; k++) {
                        transliterateNames.add(new StringBuilder(transliterateNames.get(k)).append(letters[j]));
                    }
                }
                for (int k = 0; k < size; k++) {
                    transliterateNames.get(k).append(letters[0]);
                }
            }
        }
        return transliterateNames;
    }


    static {
        LETTERS_TRANSLITE_VARS = new ArrayList<>();
        LETTERS_TRANSLITE_VARS.add(new String[]{"A"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"B"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"V"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"G"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"D"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"E", "Je", "Ye"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Jo", "Yo", "E", "Ye"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Zh", "J"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Z"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"I", "Yi"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Jj", "J", "Y", "I"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"K", "C"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"L"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"M"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"N"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"O"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"P"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"R"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"S"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"T"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"U"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"F", "Ph"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Kh", "X", "Ch", "H"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"C", "Cz", "Ts", "Tc"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Cc", "Ch"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Sh"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Shh", "Shch", "Sc", "Sch"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"'", "Ie", ""});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Y", "I"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"", "'"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Eh", "E"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Yu", "Ju", "Iu"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"Ya", "Ja", "Ia"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"a"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"b"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"v"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"g"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"d"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"e", "je", "ye"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"jo", "yo", "e", "ye"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"zh", "j"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"z"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"i", "yi"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"jj", "j", "y", "i"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"k", "c"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"l"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"m"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"n"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"o"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"p"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"r"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"s"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"t"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"u"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"f", "ph"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"kh", "x", "ch", "h"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"c", "cz", "ts", "tc"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"cc", "ch"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"sh"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"shh", "shch", "sc", "sch"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"'", "ie", ""});
        LETTERS_TRANSLITE_VARS.add(new String[]{"y", "i"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"", "'"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"eh", "e"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"yu", "ju", "iu"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"ya", "ja", "ia"});
        LETTERS_TRANSLITE_VARS.add(new String[]{"."});
        LETTERS_TRANSLITE_VARS.add(new String[]{" "});
        LETTERS_TRANSLITE_VARS.add(new String[]{"i", "yi", "y"});
    }

    private static Map<String, String[]> letters_vars = new HashMap<>();

    static {
        letters_vars.put("А", LETTERS_TRANSLITE_VARS.get(0));
        letters_vars.put("Б", LETTERS_TRANSLITE_VARS.get(1));
        letters_vars.put("В", LETTERS_TRANSLITE_VARS.get(2));
        letters_vars.put("Г", LETTERS_TRANSLITE_VARS.get(3));
        letters_vars.put("Д", LETTERS_TRANSLITE_VARS.get(4));
        letters_vars.put("Е", LETTERS_TRANSLITE_VARS.get(5));
        letters_vars.put("Ё", LETTERS_TRANSLITE_VARS.get(6));
        letters_vars.put("Ж", LETTERS_TRANSLITE_VARS.get(7));
        letters_vars.put("З", LETTERS_TRANSLITE_VARS.get(8));
        letters_vars.put("И", LETTERS_TRANSLITE_VARS.get(9));
        letters_vars.put("Й", LETTERS_TRANSLITE_VARS.get(10));
        letters_vars.put("К", LETTERS_TRANSLITE_VARS.get(11));
        letters_vars.put("Л", LETTERS_TRANSLITE_VARS.get(12));
        letters_vars.put("М", LETTERS_TRANSLITE_VARS.get(13));
        letters_vars.put("Н", LETTERS_TRANSLITE_VARS.get(14));
        letters_vars.put("О", LETTERS_TRANSLITE_VARS.get(15));
        letters_vars.put("П", LETTERS_TRANSLITE_VARS.get(16));
        letters_vars.put("Р", LETTERS_TRANSLITE_VARS.get(17));
        letters_vars.put("С", LETTERS_TRANSLITE_VARS.get(18));
        letters_vars.put("Т", LETTERS_TRANSLITE_VARS.get(19));
        letters_vars.put("У", LETTERS_TRANSLITE_VARS.get(20));
        letters_vars.put("Ф", LETTERS_TRANSLITE_VARS.get(21));
        letters_vars.put("X", LETTERS_TRANSLITE_VARS.get(22));
        letters_vars.put("Ц", LETTERS_TRANSLITE_VARS.get(23));
        letters_vars.put("Ч", LETTERS_TRANSLITE_VARS.get(24));
        letters_vars.put("Ш", LETTERS_TRANSLITE_VARS.get(25));
        letters_vars.put("Щ", LETTERS_TRANSLITE_VARS.get(26));
        letters_vars.put("Ъ", LETTERS_TRANSLITE_VARS.get(27));
        letters_vars.put("Ы", LETTERS_TRANSLITE_VARS.get(28));
        letters_vars.put("Ь", LETTERS_TRANSLITE_VARS.get(29));
        letters_vars.put("Э", LETTERS_TRANSLITE_VARS.get(30));
        letters_vars.put("Ю", LETTERS_TRANSLITE_VARS.get(31));
        letters_vars.put("Я", LETTERS_TRANSLITE_VARS.get(32));
        letters_vars.put("а", LETTERS_TRANSLITE_VARS.get(33));
        letters_vars.put("б", LETTERS_TRANSLITE_VARS.get(34));
        letters_vars.put("в", LETTERS_TRANSLITE_VARS.get(35));
        letters_vars.put("г", LETTERS_TRANSLITE_VARS.get(36));
        letters_vars.put("д", LETTERS_TRANSLITE_VARS.get(37));
        letters_vars.put("е", LETTERS_TRANSLITE_VARS.get(38));
        letters_vars.put("ё", LETTERS_TRANSLITE_VARS.get(39));
        letters_vars.put("ж", LETTERS_TRANSLITE_VARS.get(40));
        letters_vars.put("з", LETTERS_TRANSLITE_VARS.get(41) );
        letters_vars.put("и", LETTERS_TRANSLITE_VARS.get(42));
        letters_vars.put("й", LETTERS_TRANSLITE_VARS.get(43));
        letters_vars.put("к", LETTERS_TRANSLITE_VARS.get(44));
        letters_vars.put("л", LETTERS_TRANSLITE_VARS.get(45));
        letters_vars.put("м", LETTERS_TRANSLITE_VARS.get(46));
        letters_vars.put("н", LETTERS_TRANSLITE_VARS.get(47));
        letters_vars.put("о", LETTERS_TRANSLITE_VARS.get(48));
        letters_vars.put("п", LETTERS_TRANSLITE_VARS.get(49));
        letters_vars.put("р", LETTERS_TRANSLITE_VARS.get(50));
        letters_vars.put("с", LETTERS_TRANSLITE_VARS.get(51));
        letters_vars.put("т", LETTERS_TRANSLITE_VARS.get(52));
        letters_vars.put("у", LETTERS_TRANSLITE_VARS.get(53));
        letters_vars.put("ф", LETTERS_TRANSLITE_VARS.get(54));
        letters_vars.put("x", LETTERS_TRANSLITE_VARS.get(55));
        letters_vars.put("ц", LETTERS_TRANSLITE_VARS.get(56));
        letters_vars.put("ч", LETTERS_TRANSLITE_VARS.get(57));
        letters_vars.put("ш", LETTERS_TRANSLITE_VARS.get(58));
        letters_vars.put("щ", LETTERS_TRANSLITE_VARS.get(59));
        letters_vars.put("ъ", LETTERS_TRANSLITE_VARS.get(60));
        letters_vars.put("ы", LETTERS_TRANSLITE_VARS.get(61));
        letters_vars.put("ь", LETTERS_TRANSLITE_VARS.get(62));
        letters_vars.put("э", LETTERS_TRANSLITE_VARS.get(63));
        letters_vars.put("ю", LETTERS_TRANSLITE_VARS.get(64));
        letters_vars.put("я", LETTERS_TRANSLITE_VARS.get(65));
        letters_vars.put(".", LETTERS_TRANSLITE_VARS.get(66));
        letters_vars.put(" ", LETTERS_TRANSLITE_VARS.get(67));
        letters_vars.put("ий", LETTERS_TRANSLITE_VARS.get(68));
    }


}
