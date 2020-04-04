package net.allabouthadoop;


import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name = "Decrypt", value = "Decrypt the Given Column", extended = "SELECT Decrypt('Hello World!');")
public class Encrypt extends UDF {

    private Text result = new Text();

    public Text evaluate(Text str) {
        if (str == null) {
            return null;
        }
        String encryptedText = AES.encrypt(str.toString(), "shah");
        result.set(encryptedText);
        return result;
    }

}

