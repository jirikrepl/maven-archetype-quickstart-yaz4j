package quickstartZ3950;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcWriter;
import org.marc4j.MarcXmlWriter;
import org.yaz4j.*;
import org.yaz4j.exception.ZoomException;

import java.io.ByteArrayInputStream;

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
                ByteArrayInputStream in = new ByteArrayInputStream(record.getContent());
                /* MARC4J */
                MarcReader reader = new MarcStreamReader(in, "windows-1250");
                MarcWriter writer = new MarcXmlWriter(System.out, true);

                while (reader.hasNext()) {
                    org.marc4j.marc.Record marcRecord = reader.next();
                    writer.write(marcRecord);
                }
                writer.close();
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
