import com.intellij.cat.orm.TbUserParser;
import org.junit.Test;

public class TestTableParse {


    @Test
    public void testParse() {

        String table = TbUserParser.createTable();

        System.out.println("table==\n" + table);
    }
}
