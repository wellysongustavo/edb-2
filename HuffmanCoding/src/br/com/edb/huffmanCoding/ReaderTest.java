package br.com.edb.huffmanCoding;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReaderTest {

    @Before
    public void setUp() throws Exception {
        returnMapWithFrequencyCharacter();
    }

    @Test
    public void returnMapWithFrequencyCharacter() {
        //Arrange
        Reader reader = new Reader();
        String teste = "teste1.txt";
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Character, Integer> retorno = new HashMap<Character, Integer>();
        // {p=1, a=3, z=1, l=4, o=3}
        map.put('p', 1);
        map.put('a', 3);
        map.put('z', 1);
        map.put('l', 4);
        map.put('o', 3);

        //Act
        retorno = reader.leituraArquivo(teste);

        //Assert
        assertTrue(map.equals(retorno));
    }
}