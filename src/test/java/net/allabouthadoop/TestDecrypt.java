package net.allabouthadoop;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestDecrypt  {

//    @Test
//    public void testDecrypt(){
//        Decrypt decrypt = new Decrypt();
//        Assert.assertEquals("Gaurang", decrypt.evaluate(new Text("jgrmocBXb4o8Bz4yH8hLfg==")).toString());
//    }
//    @Test
//    public void testEncrypt(){
//        Encrypt encrypt = new Encrypt();
//        Assert.assertEquals("jgrmocBXb4o8Bz4yH8hLfg==", encrypt.evaluate(new Text("Gaurang")).toString());
//    }

    @Test
    public void testEncrypt() throws HiveException
    {
        Decrypt decrypt = new Decrypt();
        ObjectInspector stringOI = PrimitiveObjectInspectorFactory.javaStringObjectInspector;

        JavaStringObjectInspector resultInspector = (JavaStringObjectInspector) decrypt.initialize(new ObjectInspector[]{stringOI, stringOI, stringOI});

        Object result = decrypt.evaluate(new GenericUDF.DeferredJavaObject[]{
                    new GenericUDF.DeferredJavaObject("Gaurang"),
                    new GenericUDF.DeferredJavaObject("CBC"),
                    new GenericUDF.DeferredJavaObject("shah")
        });
        Assert.assertEquals("jgrmocBXb4o8Bz4yH8hLfg==", resultInspector.getPrimitiveJavaObject(result));

    }

    @Test
    public void testDecrypt() throws HiveException
    {
        Decrypt decrypt = new Decrypt();
        ObjectInspector stringOI = PrimitiveObjectInspectorFactory.javaStringObjectInspector;

        JavaStringObjectInspector resultInspector = (JavaStringObjectInspector) decrypt.initialize(new ObjectInspector[]{stringOI, stringOI, stringOI});

        Object result = decrypt.evaluate(new GenericUDF.DeferredJavaObject[]{
                new GenericUDF.DeferredJavaObject("jgrmocBXb4o8Bz4yH8hLfg=="),
                new GenericUDF.DeferredJavaObject("CBC"),
                new GenericUDF.DeferredJavaObject("shah")
        });
        Assert.assertEquals("Gaurang", resultInspector.getPrimitiveJavaObject(result));

    }
}


