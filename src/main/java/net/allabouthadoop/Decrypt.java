package net.allabouthadoop;


import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

@Description(name = "Decrypt", value = "Decrypt the Given Column", extended = "SELECT Decrypt('Hello World!');")
public class Decrypt extends UDF {

    private Text result = new Text();

    public Text evaluate(Text str) {
        if (str == null) {
            return null;
        }
        String decryptedString = AES.decrypt(str.toString(), "shah");
//        result.set(str.toString().toUpperCase());
        result.set(decryptedString);
        return result;
    }

//    @Override
//    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
//        if (arguments.length != 2) {
//            throw new UDFArgumentLengthException("arrayContainsExample only takes 2 arguments: List<T>, T");
//        }
//
//        // 1. Check we received the right object types.
//        ObjectInspector a = arguments[0];
//        ObjectInspector b = arguments[1];
//        if (!(a instanceof ListObjectInspector) || !(b instanceof StringObjectInspector)) {
//            throw new UDFArgumentException("first argument must be a list / array, second argument must be a string");
//        }
//        this.listOI = (ListObjectInspector) a;
//        this.elementOI = (StringObjectInspector) b;
//        // 2. Check that the list contains strings
//        if(!(listOI.getListElementObjectInspector() instanceof StringObjectInspector)){
//            throw new UDFArgumentException("first argument must be a list of strings");
//        }
//        // the return type of our function is a boolean, so we provide the correct object inspector
//        return PrimitiveObjectInspectorFactory.javaBooleanObjectInspector;
//    }
//    }
}

