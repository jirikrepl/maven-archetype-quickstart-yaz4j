package quickstartZ3950;

import org.yaz4j.*;
import org.yaz4j.exception.ZoomException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Connection con = new Connection("aleph.mzk.cz", 9991);
        con.setDatabaseName("MZK01");

        try {
            con.connect();
            PrefixQuery query = new PrefixQuery("@attr 1=4 karel");

            ScanSet set = con.scan(query);

            for (int i = 0; i < set.getSize(); i++) {
                ScanTerm rec = set.get(i);
                System.out.println(rec.getTerm());
            }

        } catch (ZoomException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }


    }
}
