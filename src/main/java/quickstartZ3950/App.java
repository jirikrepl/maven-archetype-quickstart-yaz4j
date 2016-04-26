package quickstartZ3950;

import org.marc4j.*;
import org.yaz4j.*;
import org.yaz4j.exception.ZoomException;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("Duplicates")
public class App {
    public static void main(String[] args) throws ZoomException, UnsupportedEncodingException {
        Connection connection = new Connection("aleph.mzk.cz", 9991);
        connection.setDatabaseName("MZK01");
        PrefixQuery query = new PrefixQuery("@attrset bib-1 @attr 1=12 \"000088411\"");
        ResultSet set = null;

        /* DOWNLOAD DC START */
//         setSyntax("xml") downloads DC
        connection.setSyntax("xml");
        try {
            connection.connect();
            set = connection.search(query);
        } catch (ZoomException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < set.getHitCount(); i++) {
            System.out.println("\nRAW DC DATA - record.render()\n");
            Record record = set.getRecord(i);
            System.out.println(new String(record.getContent(), "windows-1250"));
        }
        /* DOWNLOAD DC END */


        /* DOWNLOAD MARC START */
        // jinak je "usmarc" implicitni nastaveni
        connection.setSyntax("usmarc");
        try {
            set = connection.search(query);
        } catch (ZoomException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < set.getHitCount(); i++) {
            Record record = set.getRecord(i);
            System.out.println("\n\nRAW MARC DATA - record.render()\n");
            System.out.println(record.render());

            ByteArrayInputStream in = new ByteArrayInputStream(record.getContent());
            MarcReader reader = new MarcStreamReader(in/*, "windows-1250"*/);

            // takto bez nastaveneho kodovani projde i marc4j (pak je nutne nejak opravit kodovani)
            // MarcReader reader = new MarcStreamReader(in);

            MarcWriter writer = new MarcXmlWriter(System.out, true);

            System.out.println("\n\nMARC4J MARC->XML\n");
            while (reader.hasNext()) {
                org.marc4j.marc.Record marcRecord = reader.next();
                writer.write(marcRecord);
            }
            writer.close();
        }
        connection.close();
        /* DOWNLOAD MARC END */
    }
}
