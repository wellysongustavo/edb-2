package br.com.edb.huffmanCoding;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CompressorTest {

    @Before
    public void setUp() throws Exception {
        transformMapToNodeTree();
        //returnBinaryTableForCheck();
    }

//    @Test
//    public void returnBinaryTableForCheck() {
//        //Arrange
//        Compressor comp = new Compressor();
//        Map<String, String> b_table = new HashMap<String, String>();
//        Map<String, String> t_table = new HashMap<String, String>();
//        BinaryTree minHeap = new BinaryTree();
//        String teste = "teste1.txt";
//        comp.transformCharToNode(minHeap, teste);
//        //{p=0110, a=00, z=010, l=11, EOF=0111, o=10}
//        t_table.put("p", "0110");
//        t_table.put("a", "00");
//        t_table.put("z", "010");
//        t_table.put("l", "11");
//        t_table.put("EOF", "0111");
//        t_table.put("o", "10");
//
//        System.out.println("oi");
//        System.out.println(t_table);
//
//        //Act
//        b_table = comp.createBinaryTable(minHeap, b_table);
//
//        //Assert
//
//        assertTrue(t_table.equals(b_table));
//    }

    @Test
    public void transformMapToNodeTree() {
        //Arrange
        Compressor comp = new Compressor();
        String teste = "teste1.txt";
        BinaryTree minHeap = new BinaryTree();
        BinaryTree check = new BinaryTree();
        //{p=1, a=3, z=1, l=4, o=3}
        check.insert(new Node("p", 1));
        check.insert(new Node("a", 3));
        check.insert(new Node("z", 1));
        check.insert(new Node("l", 4));
        check.insert(new Node("o", 3));
        check.insert(new Node("EOF", 1));

        //Act
        comp.transformCharToNode(minHeap, teste);

        //Asset
        for (int i = 0; i < 6; i++) {
            assertEquals((check.getNodes())[i].getLetter(), (minHeap.getNodes())[i].getLetter());
        }
    }
}