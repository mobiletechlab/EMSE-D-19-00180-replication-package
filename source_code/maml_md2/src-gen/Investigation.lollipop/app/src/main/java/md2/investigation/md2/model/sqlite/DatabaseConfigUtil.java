package md2.investigation.md2.model.sqlite;
import java.io.IOException;
import java.sql.SQLException;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
 
 
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
 
	public static void main(String[] args) throws SQLException, IOException {
		
		// Provide the name of .txt file which you have already created and kept in res/raw directory
		writeConfigFile("ormlite_config.txt");
	}
}	
