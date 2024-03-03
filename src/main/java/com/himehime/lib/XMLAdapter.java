package com.himehime.lib;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.*;
import javax.xml.namespace.QName;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class XMLAdapter{

    // public ArrayList<Item> readWarehouseItems(String filename)
    // {
    //     File file = new File(filename);
    //     JAXBContext jaxbContext = JAXBContext.newInstance(ArrayList<Item>.class);
    // }
    // CustomerManager  readCustomerList(String path);
    // ArrayList<Bill> readBill(String path);
    
    // public static void writeMakanan(ListMakanan makanan)
    // {
    //     try{
    //         JAXBContext jaxbContext = JAXBContext.newInstance(Makanan[].class);
    //         Marshaller marshaller = jaxbContext.createMarshaller();
    //         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    //         File file = new File("makanan.xml");
    //         JAXBElement<Makanan[]> hasil = new JAXBElement<Makanan[]>(new QName("makanan"), Makanan[].class, makanan.getMakanan().toArray(new Makanan[makanan.getMakanan().size()]));
    //         marshaller.marshal(hasil, file);

    //     }
    //     catch (JAXBException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static Makanan readMakanan()
    // {
    //     try{
    //         File file = new File("makanan.xml");
    //         JAXBContext jaxbContext = JAXBContext.newInstance(Makanan.class);
    //         Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    //         Makanan makanan = (Makanan) unmarshaller.unmarshal(file);
    //         return makanan;
    //     }
    //     catch (JAXBException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // public static void main(String[] args) {
    //     Barang barang1 = new Barang("barang1", 1000, 1);
    //     Barang barang2 = new Barang("barang2", 2000, 2);
    //     ArrayList<Barang> listBarang = new ArrayList<Barang>();
    //     listBarang.add(barang1);
    //     listBarang.add(barang2);
    //     Makanan makanan1 = new Makanan("makanan213111", 1000, listBarang);
    //     Makanan makanan2 = new Makanan("makanan244111", 2000, listBarang);
    //     ArrayList<Makanan> list = new ArrayList<Makanan>();
    //     list.add(makanan1);
    //     list.add(makanan2);
    //     ListMakanan listMakanan = new ListMakanan();
    //     listMakanan.setListMakanan(list);
    //     writeMakanan(listMakanan);
    //     // Makanan listMakanan2 = readMakanan();
    //     // System.out.println(listMakanan2.getNama());
    //     // ArrayList<Makanan> listMakanan3 = listMakanan2.getMakanan();
    //     // System.out.println(listMakanan3);

    //     // ListMakanan listMakanan = new ListMakanan();
    //     // listMakanan.setListMakanan(list);
    //     // ListMakanan listMakanan2 = readMakanan();
    //     // ArrayList<Makanan> listMakanan3 = listMakanan2.getMakanan();
    //     // System.out.println(listMakanan3);
    // }
        
    
    
}
