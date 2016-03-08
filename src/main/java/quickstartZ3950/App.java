package quickstartZ3950;

import org.yaz4j.Connection;
import org.yaz4j.Record;
import org.yaz4j.ResultSet;
import org.yaz4j.exception.ZoomException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Connection con = new Connection(null, 0);
        /*
        con.setSyntax(syntax);
        try {
            con.connect();
            ResultSet set = con.search(query, Connection.QueryType.PrefixQuery);
            response.getWriter().println("Showing "+maxrecs+" of "+set.getSize());
            response.getWriter().println();
            for(int i=0; i<set.getSize() && i<maxrecs; i++) {
                Record rec = set.getRecord(i);
                response.getWriter().print(rec.render());
            }
        } catch (ZoomException ze) {
            throw new ServletException(ze);
        } finally {
            con.close();
        }
        */

    }
}
