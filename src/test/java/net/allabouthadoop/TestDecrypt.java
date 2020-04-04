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
}
