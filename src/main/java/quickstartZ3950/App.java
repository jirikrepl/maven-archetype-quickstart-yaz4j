package quickstartZ3950;

import org.yaz4j.*;
import org.yaz4j.exception.ZoomException;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Connection connection = new Connection("aleph.mzk.cz", 9991);
        connection.setDatabaseName("MZK01");

        try {
            connection.connect();
            /*
            PrefixQuery query = new PrefixQuery("@attr 1=4 karel");
            ScanSet set = connection.scan(query);
            for (int i = 0; i < set.getSize(); i++) {
                ScanTerm rec = set.get(i);
                System.out.println(rec.getTerm());
            }
            */

            PrefixQuery query = new PrefixQuery("@attrset bib-1 @attr 1=12 \"000088411\"");
            ResultSet set = connection.search(query);
            for (int i = 0; i < set.getHitCount(); i++) {
                Record record = set.getRecord(i);
//                System.out.println(record.render());
                String x = new String(record.getContent());
                System.out.println(x);

            }

        } catch (ZoomException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
