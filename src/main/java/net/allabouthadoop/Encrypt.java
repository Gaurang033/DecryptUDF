package net.allabouthadoop;


import net.allabouthadoop.libs.AES;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

@Description(name = "Decrypt", value = "Decrypt the Given Column", extended = "SELECT Decrypt('Hello World!');")
//public class Encrypt extends UDF {
//
//    private Text result = new Text();
//
//    public Text evaluate(Text str) {
//        if (str == null) {
//            return null;
//        }
//        String encryptedText = AES.encrypt(str.toString(), "shah");
//        result.set(encryptedText);
//        return result;
//    }
//
//}
public class Encrypt extends GenericUDF {
    StringObjectInspector mode;
    StringObjectInspector key;
    StringObjectInspector col;

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        if (arguments.length != 3) {
            throw new UDFArgumentLengthException("arrayContainsExample only takes 2 arguments: List<T>, T");
        }

        // 1. Check we received the right object types.
        ObjectInspector modeObject = arguments[1];
        ObjectInspector keyObject = arguments[2];
        ObjectInspector colObject = arguments[0];


        if (!(modeObject instanceof StringObjectInspector) || !(keyObject instanceof StringObjectInspector) ||
                !(colObject instanceof StringObjectInspector)) {
            throw new UDFArgumentException("first argument must be a list / array, second argument must be a string");
        }

        this.mode = (StringObjectInspector) modeObject;
        this.key = (StringObjectInspector) keyObject;
        this.col = (StringObjectInspector) colObject;

        // the return type of our function is a boolean, so we provide the correct object inspector
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        String modeString = mode.getPrimitiveJavaObject(deferredObjects[1].get());
        String keyString = key.getPrimitiveJavaObject(deferredObjects[2].get());
        String colString = col.getPrimitiveJavaObject(deferredObjects[0].get());
        String decryptedString = null;
        if (modeString.contains("CBC")) {
            decryptedString = AES.encrypt(colString, keyString);
        }

        return decryptedString;
    }

    @Override
    public String getDisplayString(String[] strings) {
        return null;
    }

}


