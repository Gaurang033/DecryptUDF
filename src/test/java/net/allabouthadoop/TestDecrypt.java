package net.allabouthadoop;

import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class TestDecrypt  {

    @Test
    public void testDecrypt(){
        Decrypt decrypt = new Decrypt();
        Assert.assertEquals("Gaurang", decrypt.evaluate(new Text("jgrmocBXb4o8Bz4yH8hLfg==")).toString());
    }
    @Test
    public void testEncrypt(){
        Encrypt encrypt = new Encrypt();
        Assert.assertEquals("jgrmocBXb4o8Bz4yH8hLfg==", encrypt.evaluate(new Text("Gaurang")).toString());
    }



}
